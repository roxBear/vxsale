package com.rox.vxsale.service;

import com.rox.vxsale.entity.SellerInfo;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
public interface SellerService {

    SellerInfo findSellerInfoByPhone(String phone);

    int updateSeller(SellerInfo sellerInfo);
}
