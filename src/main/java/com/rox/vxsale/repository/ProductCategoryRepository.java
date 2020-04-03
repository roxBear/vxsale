package com.rox.vxsale.repository;

import com.rox.vxsale.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author roxBear
 * @creat 2020/3/31
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}
