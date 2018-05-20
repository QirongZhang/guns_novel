package com.stylefeng.guns.modular.business.service.impl;

import com.stylefeng.guns.modular.system.model.Bookshelf;
import com.stylefeng.guns.modular.system.dao.BookshelfMapper;
import com.stylefeng.guns.modular.business.service.IBookshelfService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-28
 */
@Service
public class BookshelfServiceImpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements IBookshelfService {

    /**
     * @Description: 根据用户id获取书架书籍
     * @Param: [userId]
     * @return: java.util.List<java.util.Map>
     * @Author: Zhang Qirong
     * @Date: 2018/5/16
     */
    @Override
    public List<Map> selectByUserId(Integer userId) {

        return this.baseMapper.selectByUserId(userId);
    }
}
