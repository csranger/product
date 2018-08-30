package com.csranger.product.exception;

import com.csranger.product.result.CodeMsg;
import com.csranger.product.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {

        // 商品异常
        if (e instanceof ProductException) {
            ProductException pe = (ProductException) e;
            return Result.error(pe.getCodeMsg());
        }

        // 未知异常，打印异常信息
        return Result.error(new CodeMsg(500000, e.getMessage()));
    }
}
