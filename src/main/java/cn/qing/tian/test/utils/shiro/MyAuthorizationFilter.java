package cn.qing.tian.test.utils.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        boolean allowed =super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }
        return allowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 前端Ajax请求时requestHeader里面带一些参数，用于判断是否是前端的请求
        String header = req.getHeader("X-Requested-With");
        boolean isajax = header.equals("XMLHttpRequest");
        if (isajax) {
            // 前端Ajax请求，则不会重定向
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
