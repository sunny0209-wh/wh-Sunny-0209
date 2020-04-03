package cn.qing.tian.test.utils.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 *
 * @Description 自定义SessionManager
 * @Author 166433
 */
public class MySessionManager extends DefaultWebSessionManager {
    private static final Logger logger = LoggerFactory.getLogger(MySessionManager.class);

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //前端ajax的headers中必须传入Authorization的值
        String token = httpServletRequest.getHeader("Authorization");
        //如果请求头中有 Authorization 则其值为token
        if (!StringUtils.isEmpty(token)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            logger.info("调用MySessionManager获取token:"+token);
            return token;
        }
        else
        {
            //否则按默认规则从cookie取sessionId
            logger.info("调用MySessionManager使用默认模式从cookie获取token为"+token);
            return super.getSessionId(request, response);
        }
    }
}
