package com.studyonline.base.exception;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lili
 * @description 错误响应类
 * @date 2023/2/6 18:41
 */
public class RestErrorResponse implements Serializable {
    private String errMessage;
    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }
    public String getErrMessage() {
        return errMessage;
    }
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

}
