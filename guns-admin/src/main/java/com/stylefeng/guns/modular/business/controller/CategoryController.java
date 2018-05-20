package com.stylefeng.guns.modular.business.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Category;
import com.stylefeng.guns.modular.business.service.ICategoryService;

/**
 * 小说类别管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-21 22:06:50
 */
@Controller
//@CrossOrigin(origins = "http://localhost:8089", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private String PREFIX = "/business/category/";

    @Autowired
    private ICategoryService categoryService;

    /**
     * 跳转到小说类别管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "category.html";
    }

    /**
     * 跳转到添加小说类别管理
     */
    @RequestMapping("/category_add")
    public String categoryAdd() {
        return PREFIX + "category_add.html";
    }

    /**
     * 跳转到修改小说类别管理
     */
    @RequestMapping("/category_update/{categoryId}")
    public String categoryUpdate(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.selectById(categoryId);
        model.addAttribute("item", category);
        LogObjectHolder.me().set(category);
        return PREFIX + "category_edit.html";
    }

    /**
     * 获取小说类别管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return categoryService.selectList(null);
    }

    /**
     * 新增小说类别管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Category category) {
        categoryService.insert(category);
        return SUCCESS_TIP;
    }

    /**
     * 删除小说类别管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer categoryId) {
        categoryService.deleteById(categoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改小说类别管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Category category) {
        categoryService.updateById(category);
        return SUCCESS_TIP;
    }

    /**
     * 小说类别管理详情
     */
    @RequestMapping(value = "/detail/{categoryId}")
    @ResponseBody
    public Object detail(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.selectById(categoryId);
    }
}
