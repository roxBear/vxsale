package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.Picture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Mapper
public interface PictureMapper {

    @Select("select * from picture")
    List<Picture> findAll();

    @Select("select * from picture where pic_id = #{picId}")
    Picture findById(Integer picId);

    @Insert("insert into picture (pic_url,pic_message) values(#{picUrl},#{picMessage})")
    int save(Picture picture);

    @Delete("delete from picture where pic_id = #{picId}")
    int delete(Integer picId);
}
