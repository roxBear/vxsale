package com.rox.vxsale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.dto.ProductForm;
import com.rox.vxsale.entity.ProductCategory;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.ProductCategoryService;
import com.rox.vxsale.service.ProductInfoService;
import com.rox.vxsale.utils.KeyUtil;
import com.rox.vxsale.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 卖家的商品列表
     * @param page  第几页，从第一页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                             @RequestParam(value = "size" , defaultValue = "5") Integer size ,
                             Map<String , Object> map){
        /*Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage" , productInfoPage);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("product/list" , map);*/

        String orderBy = "create_time desc";
        PageHelper.startPage(page,size,orderBy);
        List<ProductVo> productList = productInfoService.findAll();
        PageInfo<ProductVo> pageInfo = new PageInfo<ProductVo>(productList);
        map.put("productInfoPage" , pageInfo);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("product/list" , map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productInfoService.onSale(productId);
        }catch (SaleException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sale/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品上架成功");
        map.put("url" , "/sale/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productInfoService.offSale(productId);
        } catch (SaleException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sale/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sale/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 新增商品的页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "productId" , required = false) String productId ,
                              Map<String , Object> map){
        if( !StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo" , productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList" , categoryList);
        return new ModelAndView("product/index" ,map);
    }

    /**
     * 保存/更新 商品
     * @return
     */
    //@CacheEvict(cacheNames = "product",key = "123")
    @PostMapping("save")
    //@CachePut(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sale/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        try{
            //TODO
            //如果 productId 为空 , 说明是新增商品
            if(StringUtils.isEmpty(form.getProductId())){
                ProductInfo productInfo = new ProductInfo();
                form.setProductId(KeyUtil.getProductId());
                BeanUtils.copyProperties(form , productInfo);
                productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                productInfoService.saveOne(productInfo);
            }else{
                ProductInfo productInfo = productInfoService.findOne(form.getProductId());
                productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                BeanUtils.copyProperties(form , productInfo);
                productInfoService.saveOne(productInfo);
            }
        }catch (SaleException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sale/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品操作成功");
        map.put("url" , "/sale/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 删除商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productInfoService.delete(productId);
        }catch (SaleException e){
            map.put("msg" , "商品还在售卖中，不能删除该商品！");
            map.put("url" , "/sale/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品删除成功！");
        map.put("url" , "/sale/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

}
