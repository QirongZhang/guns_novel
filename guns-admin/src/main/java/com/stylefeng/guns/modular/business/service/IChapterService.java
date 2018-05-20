package com.stylefeng.guns.modular.business.service;

import com.stylefeng.guns.modular.system.model.Chapter;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张起荣123
 * @since 2018-03-26
 */
public interface IChapterService extends IService<Chapter> {

    List<Map<String, Object>> selectChapterNames(@Param("novelId") String novelId);

}
