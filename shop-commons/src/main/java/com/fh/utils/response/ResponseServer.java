package com.fh.utils.response;


public class ResponseServer {

    private Integer code;
    private String message;
    private Object data;

    private ResponseServer(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    private ResponseServer(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseServer error() {
        return new ResponseServer(ServerEnum.ERROR.getCode(), ServerEnum.ERROR.getMessage());
    }

    public static ResponseServer error(ServerEnum serverEnum) {
        return new ResponseServer(serverEnum.getCode(), serverEnum.getMessage());
    }

    public static ResponseServer success() {
        return new ResponseServer(ServerEnum.SUCCESS.getCode(), ServerEnum.SUCCESS.getMessage());
    }

    public static ResponseServer success(Object data) {
        return new ResponseServer(ServerEnum.SUCCESS.getCode(), ServerEnum.SUCCESS.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
