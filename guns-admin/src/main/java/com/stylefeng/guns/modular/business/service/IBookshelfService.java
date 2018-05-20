package com.stylefeng.guns.modular.business.service;

import com.stylefeng.guns.modular.system.model.Bookshelf;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-28
 */
public interface IBookshelfService extends IService<Bookshelf> {

    /** 
    * @Description: 根据用户id获取书架书籍
    * @Param: [userId]
    * @return: java.util.List<java.util.Map> 
    * @Author: Zhang Qirong 
    * @Date: 2018/5/16 
    */
    List<Map> selectByUserId(Integer userId);
}
