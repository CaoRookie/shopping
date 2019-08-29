package com.shopping.filter;

import com.shopping.constant.SysConstant;
import com.shopping.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Session过滤器
 * Created by pq on 2017/8/25.
 */
public class SessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        //放行
        if (uri.equals(request.getContextPath())
                || uri.equals(request.getContextPath() + "/servlet/login")
                || uri.equals(request.getContextPath() + "/servlet/login/sso")
                || uri.equals(request.getContextPath() + "/servlet/login/platFormLogin")
                || uri.equals(request.getContextPath() + "/servlet/login/signin")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            //过滤
            User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
            if (user == null) {
                logger.error("用户未登录或session已过期！");
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath());
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
    }

    public void destroy() {

    }
}
