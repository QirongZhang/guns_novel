package com.stylefeng.guns.modular.business.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.business.utils.CookieUtil;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Bookshelf;
import com.stylefeng.guns.modular.business.service.IBookshelfService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 书架管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-28 09:43:39
 */
@Controller
@RequestMapping("/bookshelf")
public class BookshelfController extends BaseController {

    private String PREFIX = "/business/bookshelf/";

    @Autowired
    private IBookshelfService bookshelfService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到书架管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bookshelf.html";
    }

    /**
     * 跳转到添加书架管理
     */
    @RequestMapping("/bookshelf_add")
    public String bookshelfAdd() {
        return PREFIX + "bookshelf_add.html";
    }

    /**
     * 跳转到修改书架管理
     */
    @RequestMapping("/bookshelf_update/{bookshelfId}")
    public String bookshelfUpdate(@PathVariable Integer bookshelfId, Model model) {
        Bookshelf bookshelf = bookshelfService.selectById(bookshelfId);
        model.addAttribute("item",bookshelf);
        LogObjectHolder.me().set(bookshelf);
        return PREFIX + "bookshelf_edit.html";
    }

    /**
     * 获取书架管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bookshelfService.selectList(null);
    }


    /**
     * 获取书架管理列表
     */
    @RequestMapping(value = "/listByUserId")
    @ResponseBody
    public Object listByUserId(HttpServletRequest request,String condition) {
//        Map data = new HashMap();
        String account = CookieUtil.getCookieByName(request,"account").getValue();
        User user = userService.getByAccount(account);
        List<Map>bookshelfs = bookshelfService.selectByUserId(user.getId());

        return bookshelfs;
    }


    /**
     * 新增书架管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HttpServletRequest request, Integer novelId) {
        Map data = new HashMap();
        String account = CookieUtil.getCookieByName(request,"account").getValue();
        User user = userService.getByAccount(account);

        //验证书是否在书架中
        EntityWrapper<Bookshelf> bookshelfEntityWrapper = new EntityWrapper<>();
        bookshelfEntityWrapper.eq("novel_id",novelId);
        bookshelfEntityWrapper.eq("user_id",user.getId());
        int i = bookshelfService.selectCount(bookshelfEntityWrapper);
        if (i > 0) {
            data.put("flag",true);
            data.put("massage","该书已经在书架中！");
            return data;
        }


        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setNovelId(novelId);
        bookshelf.setUserId(user.getId());
        bookshelfService.insert(bookshelf);
        data.put("flag",true);
        data.put("massage","添加到书架成功！");
        return data;
    }

    /**
     * 删除书架管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer novelId,@RequestParam Integer userId) {
        Map data =new HashMap();
        EntityWrapper<Bookshelf> wrapper = new EntityWrapper<>();
        wrapper.eq("novel_id",novelId);
        wrapper.eq("user_id",userId);
        bookshelfService.delete(wrapper);
        data.put("flag",true);
        data.put("massage","删除成功！");
        return data;
    }

    /**
     * 修改书架管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Bookshelf bookshelf) {
        bookshelfService.updateById(bookshelf);
        return SUCCESS_TIP;
    }

    /**
     * 书架管理详情
     */
    @RequestMapping(value = "/detail/{bookshelfId}")
    @ResponseBody
    public Object detail(@PathVariable("bookshelfId") Integer bookshelfId) {
        return bookshelfService.selectById(bookshelfId);
    }
}
