package com.rox.vxsale.handler;

import com.rox.vxsale.config.ProjectUrlConfig;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.exception.SellerAuthorizeException;
import com.rox.vxsale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author roxBear
 * @creat 2020/4/12
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
//        return new ModelAndView("redirect:"
//                .concat(projectUrlConfig.getWechatOpenAuthorize())
//                .concat("/sell/wechat/qrAuthorize")
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getSell())
//                .concat("/sell/seller/login"));
        return new ModelAndView("common/loginView");
    }

    @ExceptionHandler(value = SaleException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SaleException e){
        return ResultVo.errorOf(e.getCode(),e.getMessage());
    }

}
