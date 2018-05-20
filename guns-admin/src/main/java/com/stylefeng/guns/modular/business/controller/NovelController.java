package com.stylefeng.guns.modular.business.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.business.service.INovelService;
import com.stylefeng.guns.modular.system.model.Novel;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 小说管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-21 21:40:24
 */
@Controller
@RequestMapping("/novel")
public class NovelController extends BaseController {

    private String PREFIX = "/business/novel/";

    @Autowired
    private INovelService novelService;

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到小说管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "novel.html";
    }

    /**
     * 跳转到添加小说管理
     */
    @RequestMapping("/novel_add")
    public String novelAdd() {
        return PREFIX + "novel_add.html";
    }

    /**
     * 跳转到修改小说管理
     */
    @RequestMapping("/novel_update/{novelId}")
    public String novelUpdate(@PathVariable Integer novelId, Model model) {
        Novel novel = novelService.selectById(novelId);
        model.addAttribute("item", novel);
        LogObjectHolder.me().set(novel);
        return PREFIX + "novel_edit.html";
    }

    /**
     * 获取小说管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        ShiroUser shiroUser = ShiroKit.getUser();

        EntityWrapper<Novel> wrapper = new EntityWrapper<>();
        wrapper.eq("author",shiroUser.getName());

        return novelService.selectList(wrapper);
    }

    /**
     * 根据分类Id获取小说管理列表
     */
    @RequestMapping(value = "/listByCategoryId/{categoryId}")
    @ResponseBody
    public Object listByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        Page<Novel> page = new Page<>();
        page.setLimit(50);
        EntityWrapper<Novel> novelEntityWrapper = new EntityWrapper<>();
        novelEntityWrapper.eq("category_id", categoryId);
//        List<Novel> novels = novelService.selectList(novelEntityWrapper);
        return novelService.selectPage(page, novelEntityWrapper);
    }

    /**
     * 根据分类Id获取小说管理列表
     */
    @RequestMapping(value = "/listByNovelName/{novelName}")
    @ResponseBody
    public Object listByNovelName(@PathVariable("novelName") String novelName) {
        EntityWrapper<Novel> novelEntityWrapper = new EntityWrapper<>();
        novelEntityWrapper.like("novel_name", novelName);
        return  novelService.selectList(novelEntityWrapper);
    }

    @RequestMapping(value = "/recommendNovels")
    @ResponseBody
    public Object recommendNovels() {
        Page<Novel> page = new Page<>();
        page.setLimit(50);
        EntityWrapper<Novel> novelEntityWrapper = new EntityWrapper<>();
        novelEntityWrapper.orderBy("click_rate");
//        novelEntityWrapper.eq("category_id", categoryId);
//        List<Novel> novels = novelService.selectList(novelEntityWrapper);
        page = novelService.selectPage(page, novelEntityWrapper);
        return novelService.selectPage(page, novelEntityWrapper);
    }

    /**
     * 新增小说管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Novel novel) {
        ShiroUser shiroUser = ShiroKit.getUser();
        novel.setAuthor(shiroUser.getName());
        novel.setCreateBy(shiroUser.getName());
        novel.setUpdateBy(shiroUser.getName());
        novel.setCreateTime(new Date());
        novel.setUpdateTime(new Date());
        novelService.insert(novel);
        return SUCCESS_TIP;
    }

    /**
     * 删除小说管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String novelId) {
        novelService.deleteById(novelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改小说管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Novel novel) {
        novelService.updateById(novel);
        return SUCCESS_TIP;
    }

    /**
     * 小说管理详情
     */
    @RequestMapping(value = "/detail/{novelId}")
    @ResponseBody
    public Object detail(@PathVariable("novelId") String novelId) {
        return novelService.selectById(novelId);
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/selectList4Front")
    @ResponseBody
    public Object selectList4Front() {
        return noticeService.selectList(null);
    }
}
