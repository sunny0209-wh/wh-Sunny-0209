package cn.qing.tian.test.utils.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//重写角色授权筛选器                          //肉司 奥手 Z星 飞儿 特儿
public class MyAuthorizationFilter extends RolesAuthorizationFilter {
    private static final Logger logger = LoggerFactory.getLogger(MyAuthorizationFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        boolean allowed =super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;                   //奥不醒司
            }
        }
        return allowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        logger.info("----------角色控制-------------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 前端Ajax请求时requestHeader里面带一些参数，用于判断是否是前端的请求
        String header = req.getHeader("X-Requested-With");
        boolean isajax = header.equals("XMLHttpRequest");
        if (isajax) {
            // 前端Ajax请求，则不会重定向
            logger.info("-------AJAX请求拒绝---------");
            resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json; charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String result = "{\"MESSAGE\":\"角色，权限不足\"}";
            out.println(result);
            out.flush();
            out.close();
            return false;
        }
        return super.onAccessDenied(request, response);
    }
}
