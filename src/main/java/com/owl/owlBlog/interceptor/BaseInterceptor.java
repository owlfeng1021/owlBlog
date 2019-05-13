package com.owl.owlBlog.interceptor;


import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Option;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.IOptionService;
import com.owl.owlBlog.service.IUserService;
import com.owl.owlBlog.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";
    @Resource
    private IUserService userService;

    @Resource
    private IOptionService optionService;
    private MapCache cache = MapCache.single();
    @Resource
    private Commons commons;
    @Resource
    private AdminCommons adminCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        String contextPath = request.getContextPath();
        // System.out.println(contextPath);
        String uri = request.getRequestURI();
        if (!uri.startsWith(contextPath + "/admin" + "/images")) {
            LOGGE.info("UserAgent: {}", request.getHeader(USER_AGENT));
            LOGGE.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));

            //请求拦截处理
            User user = TaleUtils.getLoginUser(request);
            if (null == user) {
                Integer uid = TaleUtils.getCookieUid(request);
                if (null != uid) {
                    //这里还是有安全隐患,cookie是可以伪造的
                    user = userService.findByID(String.valueOf(uid));
                    request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
                }
            }

            if (uri.startsWith(contextPath + "/admin") && !uri.startsWith(contextPath + "/admin/login") && null == user) {
//                换成转发就可以了
                response.sendRedirect(request.getContextPath() + "/admin/login");
//                request.getRequestDispatcher(request.getContextPath() + "/admin/login").forward(request, response);

                return false;

            }


            //设置get请求的token
            if (request.getMethod().equals("GET")) {
                String csrf_token = UUID.UU64();
                // 默认存储30分钟
                cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
                request.setAttribute("_csrf_token", csrf_token);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Option ov = optionService.getOptionByName("site_record");

        httpServletRequest.setAttribute("commons", commons);//一些工具类和公共方法
        httpServletRequest.setAttribute("option", ov);
        httpServletRequest.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
