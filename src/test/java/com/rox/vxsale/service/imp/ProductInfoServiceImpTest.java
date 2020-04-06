package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.mapper.ProductInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImpTest {

    @Autowired
    private ProductInfoServiceImp service;
    @Autowired
    private ProductInfoMapper mapper;

    @Test
    public void saveOne() {
        System.out.println(service);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("040304");
        productInfo.setProductName("黄元帅苹果");
        productInfo.setProductPrice(new BigDecimal(0.8));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("apple");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryNum(103);
        productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int count = service.saveOne(productInfo);
        System.out.println(count);
    }

    @Test
    public void findByOne() {
        ProductInfo productInfo = service.findOne("040303");
        System.out.println(productInfo);
    }

    @Test
    public void updateStock(){
        ProductInfo productInfo = service.findOne("040305");
        productInfo.setProductStock(productInfo.getProductStock()-20);
        System.out.println(productInfo);
        mapper.updateStock(productInfo);
    }

}