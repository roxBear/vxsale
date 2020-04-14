package com.rox.vxsale.controller;

import com.rox.vxsale.dto.CategoryForm;
import com.rox.vxsale.entity.ProductCategory;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.ProductCategoryService;
import com.rox.vxsale.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Optional;

/**
 * @author roxBear
 * @creat 2020/4/8
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 所有类目
     * @param map
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(Map<String , Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList" , productCategoryList);
        return new ModelAndView("category/list" , map);
    }


    /**
     * 修改 / 新增页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId" , required = false) Integer categoryId ,
                              Map<String , Object> map){
        if(categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory" , productCategory);
        }
        return new ModelAndView("category/index" , map);
    }



    /**
     * 新增 / 修改后 , 保存类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm categoryForm ,
                             BindingResult bindingResult ,
                             Map<String  , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sale/seller/category/index");
            return new ModelAndView("common/error" , map);
        }

        try {
            if(categoryForm.getCategoryId() != null){
                /*已有类目,修改类目信息以及该类目的所有商品的categoryNum*/
                ProductCategory productCategory = categoryService.findOne(categoryForm.getCategoryId());
                List<ProductInfo> productInfoList = productInfoService.findByCategoryNum(productCategory.getCategoryNum());
                /*无需设置id在数据库自增*/
                BeanUtils.copyProperties(categoryForm , productCategory);
                productCategory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                categoryService.saveOne(productCategory);

                /*更新所有该类目下的商品的类目*/
                for (ProductInfo productInfo : productInfoList) {
                    productInfo.setCategoryNum(categoryForm.getCategoryNum());
                }
            }else{
                /*新增*/
                ProductCategory productCategory = new ProductCategory();
                BeanUtils.copyProperties(categoryForm , productCategory);
                productCategory.setCreateTime(new Timestamp(System.currentTimeMillis()));
                productCategory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                categoryService.saveOne(productCategory);
            }
        }catch (SaleException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sale/seller/category/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类目操作成功！");
        map.put("url" , "/sale/seller/category/list");
        return new ModelAndView("common/success" , map);
    }

    //删除类目
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam("categoryId") Integer categoryId ,
                               Map<String , Object> map){
        try {
            categoryService.delete(categoryId);
        }catch (SaleException e){
            map.put("msg" , "还有商品属于该类目 , 不能删除该类目！");
            map.put("url" , "/sale/seller/category/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类目删除成功！");
        map.put("url" , "/sale/seller/category/list");
        return new ModelAndView("common/success" , map);
    }

}
