package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {

    @Autowired
    private ProductInfoMapper infoMapper;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("040303");
        productInfo.setProductName("酸辣土豆丝");
        productInfo.setProductPrice(new BigDecimal(0.8));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("西伯利亚挖土豆");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryNum(102);
        productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int count = infoMapper.saveOne(productInfo);
        System.out.println(count);
    }

    @Test
    public void findByIdTest(){
        ProductInfo product = infoMapper.findById("040302");
        System.out.println(product);
    }

    @Test
    public void findByStatusTest(){
        List<ProductInfo> products = infoMapper.findByStatus(0);
        for (ProductInfo product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void findByCategoryNumTest(){
        List<ProductInfo> products = infoMapper.findByCategoryNum(101);
        for (ProductInfo product : products) {
            System.out.println(product);
        }
    }
}