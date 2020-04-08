package com.rox.vxsale.controller;

import com.rox.vxsale.dto.ProductInfoDTO;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.service.ProductCategoryService;
import com.rox.vxsale.service.ProductInfoService;
import com.rox.vxsale.vo.ProductVo;
import com.rox.vxsale.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productService;
    @Autowired
    private ProductCategoryService categoryService;


    /**
     * 查询所有商品
     * @return
     */
    @GetMapping("list")
    public ResultVo list(){

        //返回显示层VO对象
        List<ProductVo> productVoList = productService.findAll();

        return ResultVo.successOf(productVoList);
    }

    /**
     * 查询单个商品详情
     * @return
     */
    @GetMapping("detail")
    public ResultVo detail(String productId){

        ProductInfo productInfo = productService.findOne(productId);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        productInfoDTO.setProductId(productInfo.getProductId());
        BeanUtils.copyProperties(productInfo,productInfoDTO);
        return ResultVo.successOf(productInfoDTO);
    }

}
