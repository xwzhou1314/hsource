package com.hsource.sms.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hsource.sms.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    @Autowired
    private SmsProperties prop;
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String product = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * 缓存 中 短信发送 key
     */
    static final String KEY_PREFIX = "sms:phone";

    /**
     * 单位发送时间 默认 1 分钟
     */
    static final long SMS_MIN_INTERVAL_IN_MIllIS = 60000;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public SendSmsResponse sendSms(String phone, String code, String signName, String template) {
        String key = KEY_PREFIX + phone;
        // 读取时间
        String lastTime = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(lastTime)){
            Long last = Long.valueOf(lastTime);
            if(System.currentTimeMillis() - last < SMS_MIN_INTERVAL_IN_MIllIS){
                log.info("【短信服务】 发送短信频率过高被拦截：{}", phone);
                return null;
            }
        }
        try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                    prop.getAccessKeyId(), prop.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(template);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + code + "\"}");

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("123456");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = null;

            sendSmsResponse = acsClient.getAcsResponse(request);

            if("OK".equals(sendSmsResponse.getCode())){
                log.info("【短信服务】 发送短信失败");
            }

            log.info("发送短信状态：{}", sendSmsResponse.getCode());
            log.info("发送短信消息：{}", sendSmsResponse.getMessage());

            // 发送短信成功后,写入redis,指定时间一分钟
            redisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()), 1 , TimeUnit.MINUTES);

            return sendSmsResponse;
        } catch (Exception e) {
            log.error("【短信服务】 发送异常, 手机号", phone, e);
            return null;
        }

    }
}
