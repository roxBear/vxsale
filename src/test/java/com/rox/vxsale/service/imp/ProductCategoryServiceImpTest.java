package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author roxBear
 * @creat 2020/4/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImpTest {

    @Autowired
    private ProductCategoryServiceImp categoryService;

    @Test
    public void findOne() {
        ProductCategory category = categoryService.findOne(3);
        System.out.println(category);
    }

    @Test
    public void findAll() {
        List<ProductCategory> categories = categoryService.findAll();
        for (ProductCategory category : categories) {
            System.out.println(category);
        }
    }

    @Test
    public void findByCategoryNumIn() {
        List<Integer> categoryNumList = Arrays.asList(1,2,4);
        List<ProductCategory> categoryList = categoryService.findByCategoryNumIn(categoryNumList);
        for (ProductCategory category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    public void saveOne() {
        ProductCategory productCategory = new ProductCategory("夜间休闲",106,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        int count = categoryService.saveOne(productCategory);
        System.out.println(count);
    }
}