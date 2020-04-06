package com.rox.vxsale.service.imp;

import com.rox.vxsale.dto.CarDTO;
import com.rox.vxsale.entity.ProductCategory;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.enums.ProductStatus;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.mapper.ProductCategoryMapper;
import com.rox.vxsale.mapper.ProductInfoMapper;
import com.rox.vxsale.service.ProductInfoService;
import com.rox.vxsale.vo.ProductInfoVo;
import com.rox.vxsale.vo.ProductVo;
import com.rox.vxsale.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@Service
public class ProductInfoServiceImp implements ProductInfoService {

    @Autowired
    private ProductInfoMapper infoMapper;
    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Override
    public int saveOne(ProductInfo productInfo) {
        int count = infoMapper.saveOne(productInfo);
        return count;
    }

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo product = infoMapper.findById(productId);
        return product;
    }

    @Override
    public List<ProductInfo> findByStatus(Integer productStatus) {
        List<ProductInfo> products = infoMapper.findByStatus(ProductStatus.UP.getCode());
        for (ProductInfo product : products) {
            System.out.println(product);
        }
        return products;
    }

    /**
     * 分页查询
     * controller中传入分页对象
     * service传入对象或值都行
     * dao中拆分值 size page
     * @return
     */
    @Override
    public List<ProductVo> findAll() {

        //1.按需求查询出所有的商品
        List<ProductInfo> products = infoMapper.findAll();

        //2.查询出所有商品的各个Category并去重,返回商品类目的num集合
        List<Integer> categoryList = products.stream()
                .map(productInfo -> productInfo.getCategoryNum())
                .collect(Collectors.toList());


        //根据categoryNum集合查出所有类目
        List<ProductCategory> productCategoryList = categoryMapper.findByCategoryNumIn(categoryList);

        //3.数据封装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            //显示层中类目信息
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryNum(productCategory.getCategoryNum());

            //每一个商品类目下的所有商品
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : products) {
                //商品类目对应则存放到相对应的类目vo中
                if(productInfo.getCategoryNum().equals(productCategory.getCategoryNum())){
                    //商品在该类目下,则存到显示层商品信息
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            //显示层每一个类目信息存放
            productVoList.add(productVo);
        }
        //设置返回对象
        return productVoList;
    }


    @Override
    public void increaseStock(List<CarDTO> carDTOList) {
        for(CarDTO carDTO : carDTOList){
            ProductInfo productInfo = infoMapper.findById(carDTO.getProductId());
            if(productInfo == null){
                throw new SaleException(SaleErrorCode.PRODUCT_NOT_EXIST);
            }
            int count = productInfo.getProductStock() + carDTO.getProductQuantity();
            if(count < 0){
                 throw new SaleException(SaleErrorCode.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(count);
            infoMapper.updateStock(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CarDTO> carDTOList) {
        for(CarDTO carDTO : carDTOList){
            ProductInfo productInfo = infoMapper.findById(carDTO.getProductId());
            if(productInfo == null){
                throw new SaleException(SaleErrorCode.PRODUCT_NOT_EXIST);
            }
            int count = productInfo.getProductStock() - carDTO.getProductQuantity();
            if(count < 0){
                throw new SaleException(SaleErrorCode.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(count);
            infoMapper.updateStock(productInfo);
        }
    }
}
