package com.hsource.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.common.utils.NumberUtils;
import com.hsource.common.utils.UuidUtil;
import com.hsource.dto.UserDTO;
import com.hsource.entry.TbUser;
import com.hsource.mapper.TbUserMapper;
import com.hsource.service.TbUserService;
import com.hsource.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 缓存 中 验证码 key
     */
    static final String KEY_PREFIX = "sms:phone:verify:code";

    @Override
    public Boolean checkData(String data, Integer type) {

        EntityWrapper<TbUser> wrapper = new EntityWrapper<>();
        switch (type){
            case 1:
                wrapper.eq("username", data);
                break;
            case 2:
                wrapper.eq("phone", data);
                break;
        }
        return 0 == this.selectCount(wrapper);
    }

    private void sengCode(String phone){
        // 生成 key
        String key =  KEY_PREFIX + phone;

        // 生成 code
        String code = NumberUtils.generateCode(6);

        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        // TODO 通过amp调用短信微服务发送验证码
        // TODO 保存验证码
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void register(UserDTO userDTO, String code) {
        /**
         * BeanUtils.copyProperties(a, b);
         * BeanUtils是org.springframework.beans.BeanUtils， a拷贝到b
         * BeanUtils是org.apache.commons.beanutils.BeanUtils，b拷贝到a
         */
        // 检验验证码
        String cacheCode = redisTemplate.opsForValue().get(KEY_PREFIX + userDTO.getPhone());
        if(! StringUtils.equals(code, cacheCode)){
            // TODO 解决异常，异常不对
            throw new HsException(ExceptionEnum.BRAND_SAVE_FAILED);
        }
        //
        TbUser user = new TbUser();
        BeanUtils.copyProperties(userDTO, user);
        // 密码加密
        String passworld = CodecUtils.md5Hex(user.getPassword() , user.getPhone());
        user.setPassword(passworld);
        user.setId(UuidUtil.get32UUID());
        // 写入数据库
        this.insert(user);
    }

    @Override
    public UserDTO queryUsernameAndPassword(String username, String password) {
         this.selectCount(new EntityWrapper<TbUser>().eq("username", username).eq("password", password));
        return null;
    }
}
