package com.rox.vxsale.repository;

import com.rox.vxsale.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/2
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);

    List<ProductInfo> findByCategoryNum(Integer categoryNum);
}
