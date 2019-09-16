package com.bdqn.exception;
/**
 * ClassName: {@link BusinessException}
 * Description: 业务异常类
 * Author: xyf
 * Date 2019/9/9 8:49
 */
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    /**
     * Description: 构造业务异常类
     * param: [commonError]
     * return:
     * Date:
     */
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    /**
     * Description: 构造业务异常类（需要构造自定义的错误代码和信息）
     * param: [commonError, errMsg]
     * return:
     * Date:
     */
    public BusinessException(CommonError commonError, String errMsg){
        this(commonError);
        this.commonError.setErrMsg(errMsg);
    }

    /**
    /**
     * Description: 获取错误代码
     * param: []
     * return: int
     * Date: 2019/9/9 8:44
     */
    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    /**
     * Description: 获取错误的信息
     * param: []
     * return: java.lang.String
     * Date: 2019/9/9 8:44
     */
    @Override
    public String getErrMsg() {
        return commonError.getErrMsg();
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
        this.commonError.setErrMsg(errMsg);
        return commonError;
    }
}
