package com.rox.vxsale.repository;

import com.rox.vxsale.entity.ProductInfo;
import org.junit.Assert;
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
 * @creat 2020/4/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("040302");
        productInfo.setProductName("青椒肉丝");
        productInfo.setProductPrice(new BigDecimal(1.6));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("肉炒青椒");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryNum(101);
        productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        ProductInfo result = infoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {

        List<ProductInfo> productInfoList = infoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }

}