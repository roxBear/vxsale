package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.SellerInfo;
import com.rox.vxsale.mapper.SellerInfoMapper;
import com.rox.vxsale.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;


    @Override
    public SellerInfo findSellerInfoByPhone(String phone) {
        SellerInfo seller = sellerInfoMapper.findOne(phone);
        return seller;
    }

    @Override
    public int updateSeller(SellerInfo sellerInfo) {
        //sellerInfoMapper.updateOne(sellerInfo);
        return 0;
    }
}
