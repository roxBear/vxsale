package com.rox.vxsale.service;

import com.rox.vxsale.entity.SellerInfo;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
public interface SellerService {


    SellerInfo findOne(String phone);

    SellerInfo findById(Integer sellerId);

    List<SellerInfo> findAll();

    int create(SellerInfo sellerInfo);

    int update(SellerInfo sellerInfo);

}
