package com.rox.vxsale.controller;

import com.rox.vxsale.dto.PictureForm;
import com.rox.vxsale.entity.Picture;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.mapper.PictureMapper;
import com.rox.vxsale.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Controller
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Autowired
    private PictureMapper pictureMapper;

    @GetMapping("/getAll")
    public ResultVo getUserInfo(){
        List<Picture> pictures = pictureMapper.findAll();
        return ResultVo.successOf(pictures);
    }

    //页面相关
    //TODO
    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<Picture> pictures = pictureMapper.findAll();
        map.put("pictures" , pictures);
        return new ModelAndView("picture/list" , map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "picId" , required = false) Integer picId ,
                              Map<String , Object> map){
        if(picId != null){
            Picture picture = pictureMapper.findById(picId);
            map.put("picture" , picture);
        }
        return new ModelAndView("picture/index" , map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PictureForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sale/picture/index");
            return new ModelAndView("common/error" , map);
        }
        Picture picture = new Picture();
        try{
            if(form.getPicId() != null){
                picture = pictureMapper.findById(form.getPicId());
            }
            picture.setPicUrl(form.getPicUrl());
            picture.setPicMessage(form.getPicMessage());
            pictureMapper.save(picture);
        }catch (SaleException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sale/picture/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "操作成功！");
        map.put("url", "/sale/picture/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("picId") Integer picId , Map<String , Object> map){
        pictureMapper.delete(picId);
        map.put("msg" , "图片删除成功！");
        map.put("url" , "/sale/picture/list");
        return new ModelAndView("common/success" , map);
    }
}
