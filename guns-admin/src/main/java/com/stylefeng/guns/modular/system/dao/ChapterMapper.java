package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Chapter;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-28
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

    List<Map<String, Object>> selectChapterNames(@Param("novelId") String novelId);

}
