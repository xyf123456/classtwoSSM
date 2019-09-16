package com.bdqn.exception;

import com.bdqn.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: {@link GlobalExceptionHandler}
 * Description: 全局异常处理
 * Author: xyf
 * Date 2019/9/9 9:49
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(value = Exception.class)
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        public Object handleException(HttpServletRequest request,Exception e) throws Exception{
            Map<String,Object> responseData = new HashMap<>();
            if (e instanceof BusinessException){//业务异常，（包含业务异常的代码和信息）
                BusinessException businessException= (BusinessException) e;
                responseData.put("errCode",businessException.getErrorCode());
                responseData.put("errMsg",businessException.getErrMsg());
            }else {//未知异常（包含文字和信息）
                 responseData.put("errCode",EnumBusinessError.UNKNOWNERROR.getErrorCode());
                 responseData.put("errMsg",EnumBusinessError.UNKNOWNERROR.getErrMsg());
            }
//            同一类型返回给前端
            return CommonReturnType.create(responseData,"fail");
        }
}
