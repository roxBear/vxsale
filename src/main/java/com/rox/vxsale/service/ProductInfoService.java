package com.rox.vxsale.service;

import com.rox.vxsale.dto.CarDTO;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.vo.ProductVo;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
public interface ProductInfoService {

    int saveOne(ProductInfo productInfo);

    ProductInfo findOne(String productId);

    //查询上下架
    List<ProductInfo> findByStatus(Integer productStatus);

    /**
     * 分页查询
     * 此处传入分页数据对象PO
     * @return
     */
    List<ProductVo> findAll();

    //修改库存
    void increaseStock(List<CarDTO> carDTOList);

    //减库存
    void decreaseStock(List<CarDTO> carDTOList);

    //上架

    //下架
}
