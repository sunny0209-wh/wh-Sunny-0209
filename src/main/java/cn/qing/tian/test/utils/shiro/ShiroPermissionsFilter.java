package cn.qing.tian.test.utils.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroPermissionsFilter extends PermissionsAuthorizationFilter {
    private static final Logger logger = LoggerFactory.getLogger(ShiroPermissionsFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        boolean allowed = super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            // 判断请求是否是options请求
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }
        return allowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        logger.info("----------权限控制-------------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String header = req.getHeader("X-Requested-With");
        boolean isajax = header.equals("XMLHttpRequest");
        if(isajax)
        {
            logger.info("-------AJAX请求拒绝---------");
            resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            req.setCharacterEncoding("UTF_8");
            resp.setContentType("application/json");
            //返回禁止访问的json字符串
            PrintWriter out = resp.getWriter();
            String result = "{\"MESSAGE\":\"角色，权限不足\"}";
            out.println(result);
            out.flush();
            out.close();
        }
        else
        {
            logger.info("-----普通请求拒绝-----");
            resp.sendRedirect("/403");
        }
        return false;
    }
}
