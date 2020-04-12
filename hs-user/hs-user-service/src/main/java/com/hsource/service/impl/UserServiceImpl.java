package com.hsource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.common.utils.NumberUtils;
import com.hsource.common.utils.UuidUtil;
import com.hsource.dto.UserDTO;
import com.hsource.dto.UserPageDTO;
import com.hsource.entry.User;
import com.hsource.mapper.UserMapper;
import com.hsource.service.UserService;
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
 *  用户服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 缓存 中 验证码 key
     */
    static final String KEY_PREFIX = "sms:phone:verify:code";

    @Override
    public Boolean checkData(String data, Integer type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        switch (type){
            case 1:
                wrapper.eq("username", data);
                break;
            case 2:
                wrapper.eq("phone", data);
                break;
        }
        return 0 == this.count(wrapper);
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
        // String cacheCode = redisTemplate.opsForValue().get(KEY_PREFIX + userDTO.getPhone());
//        if(! StringUtils.equals(code, cacheCode)){
//            throw new HsException(ExceptionEnum.BRAND_SAVE_FAILED);
//        }
        //
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        // 密码加密
        String passworld = CodecUtils.md5Hex(user.getPassword() , user.getPhone());
        user.setPassword(passworld);
        user.setId(UuidUtil.get32UUID());
        // 写入数据库
        this.save(user);
    }

    @Override
    public UserDTO queryUsernameAndPassword(String username, String password) {
        User user = this.getOne(new QueryWrapper<User>().eq("user_name", username));
        if(null == user){
            throw new HsException(ExceptionEnum.USER_NULL);
        }
        if(!StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(password , user.getPhone()))){
            throw new HsException(ExceptionEnum.USER_PASS_FALSE);
        }

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    /**
     * 根据id 查找 用户是否存在
     *
     * @param id
     * @return true 不存在
     */
    @Override
    public Boolean selectUserById(String id) {
        return 0 == this.count(new QueryWrapper<User>().eq("id", id));
    }

    @Override
    public Page<User> searchList(UserPageDTO dto) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(null != dto && StringUtils.isNotBlank(dto.getSearch())){
            wrapper.like("user_name", dto.getSearch())
                    .or().like("nick_name", dto.getSearch());
        }

        if(0 == dto.getType()){
            wrapper.eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode());
        }else {
            wrapper.eq("del_falg", DelFlagEnum.DEL_FLAG_TRUE.getCode());
        }


        Page<User> userPage = this.page(new Page<>(dto.getPage(), dto.getPageSize()), wrapper);
        userPage.getRecords().forEach(v->{
            if(DelFlagEnum.DEL_FLAG_FALSE.getCode().equals(v.getDelFalg())){
                v.setDelFalg(DelFlagEnum.DEL_FLAG_FALSE.getMsg());
            }else {
                v.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getMsg());
            }
        });
        return userPage;
    }

    @Override
    public Void delUserById(UserPageDTO dto) {
        User user = this.getOne(new QueryWrapper<User>().eq("id", dto.getId())
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode()));
        if(null == user){
            throw new HsException(ExceptionEnum.DEL_USER_NULL);
        }
        user.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getCode());
        this.updateById(user);
        return null;
    }

    @Override
    public void keepById(UserPageDTO dto) {
        User user = this.getOne(new QueryWrapper<User>().eq("id", dto.getId())
                .eq("del_falg", DelFlagEnum.DEL_FLAG_TRUE.getCode()));
        if(null == user){
            throw new HsException(ExceptionEnum.DEL_USER_NULL);
        }
        user.setDelFalg(DelFlagEnum.DEL_FLAG_FALSE.getCode());
        this.updateById(user);
    }
}
