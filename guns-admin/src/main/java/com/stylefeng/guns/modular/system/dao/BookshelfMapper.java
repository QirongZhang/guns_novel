package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Bookshelf;
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
public interface BookshelfMapper extends BaseMapper<Bookshelf> {

    /**
     * @Description: 根据用户id获取书架
     * @Param: [userId]
     * @return: java.util.List<java.util.Map>
     * @Author: Zhang Qirong
     * @Date: 2018/5/16
     */
    List<Map> selectByUserId(@Param("userId") Integer userId);
}
