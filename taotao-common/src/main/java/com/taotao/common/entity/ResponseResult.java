package com.taotao.common.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 操作响应结果
 */
public class ResponseResult {

    private Integer STATE = 0;
    /**
     * 操作结果(成功 true/失败 false)
     */
    private boolean SUCCEED = false;
    /**
     * 提示信息
     */
    private String MESSAGE = "";
    /**
     * 结果数据
     */
    private Object Data = null;

    public static ResponseResult Ok(String message){
        return new ResponseResult(true,message,null);
    }
    public static ResponseResult Ok(String message,Object data){
        return new ResponseResult(true,message,data);
    }
    public static ResponseResult build(Boolean succeed,String message,Object data){
        return new ResponseResult(succeed,message,data);
    }

    private ResponseResult(boolean SUCCEED, String MESSAGE, Object data) {
        this.SUCCEED = SUCCEED;
        this.MESSAGE = MESSAGE;
        Data = data;
    }
}
