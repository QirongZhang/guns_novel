package com.stylefeng.guns.modular.business.service.impl;

import com.stylefeng.guns.modular.system.model.Chapter;
import com.stylefeng.guns.modular.system.dao.ChapterMapper;
import com.stylefeng.guns.modular.business.service.IChapterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张起荣123
 * @since 2018-03-26
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public List<Map<String, Object>> selectChapterNames(String novelId) {
        return chapterMapper.selectChapterNames(novelId);
    }
}
