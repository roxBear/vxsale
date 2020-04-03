package com.rox.vxsale.controller;

import com.rox.vxsale.service.ProductCategoryService;
import com.rox.vxsale.service.ProductInfoService;
import com.rox.vxsale.vo.ProductVo;
import com.rox.vxsale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@RestController
public class ProductController {

    @Autowired
    private ProductInfoService productService;
    @Autowired
    private ProductCategoryService categoryService;


    /**
     * 查询所有商品,管理端
     * @return
     */
    @GetMapping("product/list")
    public ResultVo list(){

        //返回显示层VO对象
        List<ProductVo> productVoList = productService.findAll();

        return ResultVo.successOf(productVoList);
    }

}
