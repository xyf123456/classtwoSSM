package com.bdqn.exception;

import com.bdqn.pojo.User;

/**
 * ClassName: {@link EnumBusinessError}
 * Description:  业务异常的通用的枚举
 * Author: xyf
 * Date 2019/9/9 9:05
 */
public enum EnumBusinessError implements CommonError{

    UNKNOWNERROR(10001,"未知错误"),
    ILLEGAL_ARGS(10002,"不合法参数"),

    USER_NOT_FOUND(20001,"用户未找到"),
    ROLES_NOT_FOUND(30001,"角色数据未找到"),
    ;
    private int errCode;//错误代码
    private String errMsg;//错误描述


    EnumBusinessError() {
    }

    EnumBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * Description: 获取错误代码
     * param: []
     * return: int
     * Date: 2019/9/9 8:44
     */
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    /**
     * Description: 获取错误的信息
     * param: []
     * return: java.lang.String
     * Date: 2019/9/9 8:44
     */
    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * Description: 手动设置错误的业务信息（定制化）
     * param: [errMsg]
     * return: com.bdqn.exception.CommonError
     * Date: 2019/9/9 8:47
     *
     * @param errMsg
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public static void main(String[] args) {
        /*Object o = new User();
        o.toString();*/
//        CommonError commonError = new EnumBusinessError(30000,"供应商信息错误");
//        BusinessException businessException = new BusinessException(EnumBusinessError.UNKNOWNERROR);
        BusinessException businessException = new BusinessException(EnumBusinessError.USER_NOT_FOUND);
        System.out.println(businessException.getErrMsg());
        System.out.println(businessException.getErrorCode());
    }
}
