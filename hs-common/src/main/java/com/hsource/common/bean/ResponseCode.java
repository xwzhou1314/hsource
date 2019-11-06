package com.hsource.common.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/18/10:54
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    OK(1,"操作成功");


    public Integer code;

    public String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static List<ResponseMessage> getArrayMessage(){
        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        for (ResponseCode statusEnum : ResponseCode.values()) {
            responseMessages.add(new ResponseMessageBuilder()
                    .code(statusEnum.code)
                    .message(statusEnum.msg)
                    .build());
        }
        return responseMessages;
    }

    public static String getMsg(int code){
        for (ResponseCode statusEnum : ResponseCode.values()) {
            if(code == statusEnum.getCode()){
                return statusEnum.getMsg();
            }
        }
        return null;
    }


}
