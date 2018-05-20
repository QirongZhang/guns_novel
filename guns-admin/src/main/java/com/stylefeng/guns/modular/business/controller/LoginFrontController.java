package com.stylefeng.guns.modular.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.business.utils.CookieUtil;
import com.stylefeng.guns.modular.business.utils.DataUtil;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 章节管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-26 13:49:26
 */
@Controller
@RequestMapping("/loginFront")
public class LoginFrontController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 点击登录执行的动作
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map data = new HashMap();

        String string = DataUtil.getBody(request);
        JSONObject jsonObject = JSON.parseObject(string);
        String account = jsonObject.getString("account");
        String password = jsonObject.getString("password");

        if (account == null || StringUtils.isBlank(account)) {
            data.put("flag",false);
            data.put("massage","用户名为空！");
            return data;
        }

        if (password == null || StringUtils.isBlank(password)) {
            data.put("flag",false);
            data.put("massage","密码为空！");
            return data;
        }

        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("account", account);
        userEntityWrapper.eq("password", password);
        User user = userService.selectOne(userEntityWrapper);

        if (user == null || StringUtils.isBlank(user.getAccount())) {
            data.put("flag",false);
            data.put("massage","用户名或者密码错误！");
            return data;
        }

        //cookie保存1周
        CookieUtil.addCookie(response,"account",account,604800);
        data.put("flag",true);
        data.put("massage","登录成功！");
        return data;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logOut(HttpServletResponse response, @PathVariable String account) {
        Map data = new HashMap();
        CookieUtil.removeCookie(response,account);
        data.put("flag",true);
        data.put("massage","注销成功！");
        return data;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(HttpServletRequest request , HttpServletResponse response) throws Exception{
        Map data = new HashMap();

        String string = DataUtil.getBody(request);
        JSONObject jsonObject = JSON.parseObject(string);
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("email");
        String account = jsonObject.getString("account");
        String password = jsonObject.getString("password");
        User user = new User();
        user.setName(name);
        user.setAccount(account);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(ManagerStatus.OK.getCode());
        userService.insert(user);

        data.put("flag",true);
        data.put("massage","注册成功！");
        return data;
    }
}
