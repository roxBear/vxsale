package com.rox.vxsale.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回最外层对象
 * @author roxBear
 * @creat 2020/4/3
 */
@Data
public class ResultVo<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    /**
     * 正确返回带值
     * @param t
     * @return
     */
    public static<T> ResultVo successOf(T t){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(t);
        return resultVo;
    }

    /**
     * 正确返回无值
     * @return
     */
    public static ResultVo successOf(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        return resultVo;
    }
}
