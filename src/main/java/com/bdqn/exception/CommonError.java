package com.bdqn.exception;
/**
 * ClassName: {@link CommonError}
 * Description: 全局错误的接口
 * Author: xyf
 * Date 2019/9/9 8:42
 */
public interface CommonError {


    /**
     * Description: 获取错误代码
     * param: []
     * return: int
     * Date: 2019/9/9 8:44
     */
    int getErrorCode();

    /**
     * Description: 获取错误的信息
     * param: []
     * return: java.lang.String
     * Date: 2019/9/9 8:44
     */
    String getErrMsg();


    /**
     * Description: 手动设置错误的业务信息（定制化）
     * param: [errMsg]
     * return: com.bdqn.exception.CommonError
     * Date: 2019/9/9 8:47
     */
    CommonError setErrMsg(String errMsg);
}
