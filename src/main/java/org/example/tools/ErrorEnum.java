package org.example.tools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorEnum {
    BAD_PARAM("1002","参数有错"),
    NOT_FOUNT("1003","资源不存在"),
    NO_PERMISSION("1004","权限不足"),
    BAD_INPUT_PARAM("1005","入参有问题"),
    PASSWORD_OR_USERNAME_WRONG("1006", "用户名或密码错误");
    private String errorMsg;
    private String errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
