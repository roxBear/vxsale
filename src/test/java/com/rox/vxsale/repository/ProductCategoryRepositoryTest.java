package com.rox.vxsale.repository;

import com.rox.vxsale.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author roxBear
 * @creat 2020/3/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveOneTest(){
        System.out.println(productCategoryRepository);
    }

    @Test
    public void findOneTest(){
        ProductCategory category = productCategoryRepository.findById(2).orElse(null);
        System.out.println(category);
    }

}