package com.stylefeng.guns.modular.business.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.business.service.INovelService;
import com.stylefeng.guns.modular.system.model.Novel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Chapter;
import com.stylefeng.guns.modular.business.service.IChapterService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 章节管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-26 13:49:26
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseController {

    private String PREFIX = "/business/chapter/";

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private INovelService novelService;

    /**
     * 跳转到章节管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "chapter.html";
    }

    /**
     * 跳转到添加章节管理
     */
    @RequestMapping("/chapter_add")
    public String chapterAdd() {
        return PREFIX + "chapter_add.html";
    }

    /**
     * 跳转到修改章节管理
     */
    @RequestMapping("/chapter_update/{chapterId}")
    public String chapterUpdate(@PathVariable String chapterId, Model model) {
        Chapter chapter = chapterService.selectById(chapterId);
        model.addAttribute("item", chapter);
        LogObjectHolder.me().set(chapter);
        return PREFIX + "chapter_edit.html";
    }

    /**
     * 获取章节管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String novelId) {
//        ShiroUser shiroUser = ShiroKit.getUser();
//        EntityWrapper<Novel>novelEntityWrapper = new EntityWrapper<>();
//        novelEntityWrapper.eq("author",shiroUser.getName());
//        Novel novel = novelService.selectOne(novelEntityWrapper);
        EntityWrapper<Chapter> wrapper = new EntityWrapper<>();
        if (novelId != null && StringUtils.isNotBlank(novelId)){
            wrapper.eq("novel_id",novelId);
            wrapper.orderBy("ord_by",false);
            return chapterService.selectList(wrapper);
        }
        return null;
    }

    /**
     * 根据小说id获取章节管理列表
     */
    @RequestMapping(value = "/listByNovelId/{novelId}")
    @ResponseBody
    public Object listByNovelId(@PathVariable("novelId") String novelId) {
//        new EntityWrapper<Chapter>().eq("novel_id",novelId).setParamAlias();
        List<Map<String, Object>> maps = chapterService.selectChapterNames(novelId);

        return chapterService.selectChapterNames(novelId);
    }

    /**
     * 新增章节管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Chapter chapter) {
        ShiroUser shiroUser = ShiroKit.getUser();
        chapter.setCreateTime(new Date());
        chapter.setUpdateTime(new Date());
        chapter.setCreateBy(shiroUser.getName());
        chapter.setUpdateBy(shiroUser.getName());
//        EntityWrapper<Chapter> wrapper = new EntityWrapper<>();
//        wrapper.eq("novel_id",chapter.getNovelId());
//        int count = chapterService.selectCount(wrapper);
//        chapter.setOrdBy(count +1);
        chapterService.insert(chapter);
        return SUCCESS_TIP;
    }

    /**
     * 删除章节管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer chapterId) {
        chapterService.deleteById(chapterId);
        return SUCCESS_TIP;
    }

    /**
     * 修改章节管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Chapter chapter) {
        chapterService.updateById(chapter);
        return SUCCESS_TIP;
    }

    /**
     * 章节管理详情
     */
    @RequestMapping(value = "/detail/{chapterId}")
    @ResponseBody
    public Object detail(@PathVariable("chapterId") String chapterId) {
        return chapterService.selectById(chapterId);
    }

    /**
     * 章节管理详情
     */
    @RequestMapping(value = "/detailByOrder")
    @ResponseBody
    public Object detailByOrdBy(String novelId, Integer ordBy) {
        EntityWrapper<Chapter> wrapper = new EntityWrapper<>();
        wrapper.eq("novel_id",novelId);
        wrapper.eq("ord_by",ordBy);
        Chapter chapter = chapterService.selectOne(wrapper);
        return chapter;
    }
}
