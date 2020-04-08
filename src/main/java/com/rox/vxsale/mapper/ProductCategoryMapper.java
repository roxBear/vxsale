package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/3/31
 */
@Mapper
public interface ProductCategoryMapper {
    @Select("select * from product_category where category_id = #{categoryId}")
    ProductCategory findById(Integer categoryId);

    @Insert("insert into product_category (category_name,category_num,create_time,update_time) values(#{categoryName},#{categoryNum},#{createTime},#{updateTime})")
    int saveOne(ProductCategory productCategory);

    @Update("update product_category set category_name=#{categoryName},category_num=#{categoryNum},update_time=#{updateTime} where category_id=#{categoryId}")
    int updateOne(ProductCategory productCategory);

    @Delete("delete from product_category where category_id = #{categoryId}")
    int deleteOne(Integer categoryId);

    @Select("select * from product_category")
    List<ProductCategory> findAll();

    @Select("select * from product_category where category_num = #{categoryNum}")
    List<ProductCategory> findByCategoryNum(Integer categoryNum);

    @Select("<script>"+
            "select * from product_category where category_num in"+
            "<foreach collection='categoryNumList' open='(' item='categoryNum' separator=',' close=')'> #{categoryNum}</foreach>"+
            "</script>")
    List<ProductCategory> findByCategoryNumIn(@Param("categoryNumList") List<Integer> categoryNumList);
}
