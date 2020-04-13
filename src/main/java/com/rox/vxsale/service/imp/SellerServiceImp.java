package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.SellerInfo;
import com.rox.vxsale.mapper.SellerInfoMapper;
import com.rox.vxsale.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;


    @Override
    public SellerInfo findOne(String phone) {
        SellerInfo sellerInfo = sellerInfoMapper.findOne(phone);
        return sellerInfo;
    }

    @Override
    public SellerInfo findById(Integer sellerId) {
        SellerInfo sellerInfo = sellerInfoMapper.findById(sellerId);
        return sellerInfo;
    }

    @Override
    public List<SellerInfo> findAll() {
        List<SellerInfo> sellers = sellerInfoMapper.findAll();
        return sellers;
    }

    @Override
    public int create(SellerInfo sellerInfo) {
        int count = sellerInfoMapper.create(sellerInfo);
        return count;
    }

    @Override
    public int update(SellerInfo sellerInfo) {
        int count = sellerInfoMapper.update(sellerInfo);
        return count;
    }
}
