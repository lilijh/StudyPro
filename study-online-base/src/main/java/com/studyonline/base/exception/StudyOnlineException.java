package com.studyonline.base.exception;

/**
 * @author lili
 * @description 自定义异常类
 * @date 2023/2/6 18:43
 */
public class StudyOnlineException extends RuntimeException{
    private static final long serialVersionUID = 5565760508056698922L;
    private String errMessage;
    public StudyOnlineException() {
        super();
    }
    public StudyOnlineException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }
    public String getErrMessage() {
        return errMessage;
    }
    public static void cast(CommonError commonError){
        throw new StudyOnlineException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new StudyOnlineException(errMessage);
    }
}
