package com.xiaxin.controller;

import com.xiaxin.annotation.ParamModel;
import com.xiaxin.entity.Users;
import com.xiaxin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Slf4j
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 设置cookie
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/set")
    public String setCookie(HttpServletRequest request, HttpServletResponse response) {
        // 新建Cookie
        Cookie cookie = new Cookie("user", "cxq");
        // 设置域名
//        cookie.setDomain(".helloweenvsfei.com");
        // 设置路径
//        cookie.setPath("/");
        // 设置有效期
//        cookie.setMaxAge(Integer.MAX_VALUE);
        // 输出到客户端
        response.addCookie(cookie);
        return "success";
    }

    /**
     * @param request
     * @param users
     * @return
     */
    @PostMapping("/getUser")
    public Users getUser(HttpServletRequest request, @RequestBody Users users) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(cookie.getName() + ":" + cookie.getValue());
            }
        }
        Users users1 = userMapper.getUser(users.getUserId()).get(0);
        System.out.println(users1);
        return users1;
    }

    /**
     * 测试非json格式数据 请求参数：下划线->驼峰
     *
     * @param users
     * @return
     */
    @GetMapping("/getUserById")
    public Users getUserById(HttpServletRequest request, @ParamModel Users users) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("cookie:{}={}", cookie.getName(), cookie.getValue());
            }
        }
        Users user = userMapper.getUser(users.getUserId()).get(0);
        log.info("user={}", user);
        return user;
    }

    @GetMapping("/index")
    public String indexPage(ModelMap modelMap) {
        return "success";
    }

}
