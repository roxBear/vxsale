package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.ProductCategory;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/3/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test//测试查询一个
    public void findTest(){
        ProductCategory category = productCategoryMapper.findById(2);
        System.out.println(category);
    }

    @Test//测试查询多个
    public void findByCategoryNumListTest(){
        List<Integer> categoryNumList = Arrays.asList(1,3,4);
        List<ProductCategory> categories = productCategoryMapper.findByCategoryNumIn(categoryNumList);
        for (ProductCategory category : categories) {
            System.out.println(category);
        }
    }

    @Test//测试增加一个
    public void saveTest(){
        Integer count = productCategoryMapper.saveOne(new ProductCategory("休闲零食", 105, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        System.out.println(count);
    }

    @Test//测试修改
    public void updateTest(){
        //先查询出对象改值再保存
        ProductCategory category = productCategoryMapper.findById(1);
        category.setCategoryName("小鱼小肉");
        category.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int count = productCategoryMapper.updateOne(category);
        System.out.println(category);
    }

    @Test//测试删除
    public void deleteTest(){
        List<Integer> categoryNumList = Arrays.asList(1,3,4);
        StringUtils.strip(categoryNumList.toString(),"[");
        StringUtils.strip(categoryNumList.toString(),"]");
        System.out.println(categoryNumList);
    }
}