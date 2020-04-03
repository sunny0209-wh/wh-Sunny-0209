package cn.qing.tian.test.config;

import cn.qing.tian.test.utils.shiro.*;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器(关联SecuritManager)
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        //将自定义 的权限验证失败的过滤器ShiroFilterFactoryBean注入map
        filters.put("authc",new ShiroAuthenticationFilter());
        filters.put("perms",new ShiroPermissionsFilter());
        filters.put("roles",new MyAuthorizationFilter());
        //将map注入shiroFilter
        shiroFilterFactoryBean.setFilters(filters);
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/logout", "logout");
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon：无需认证（登录）可以访问
         *          authc：必须认证才可以访问
         *          user：如果使用rememberMe的功能可以直接访问
         *          perms：该资源必须得到资源权限才可以访问
         *          role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //拦截器
        filterChainDefinitionMap.put("/shiro/denglu","anon");
        filterChainDefinitionMap.put("/emp/update","anon");
        filterChainDefinitionMap.put("/emp/one","anon");
        filterChainDefinitionMap.put("/emp/types","anon");

        filterChainDefinitionMap.put("/websocket/*","anon");

        filterChainDefinitionMap.put("/emp/www","anon");
        filterChainDefinitionMap.put("/file/uploads","anon");

        filterChainDefinitionMap.put("/emp/page","roles[svip]");
        filterChainDefinitionMap.put("/emp/page","perms[select]");

        filterChainDefinitionMap.put("/emp/page1","anon");
        filterChainDefinitionMap.put("/emp/all","anon");
        filterChainDefinitionMap.put("/sms/send","anon");
        filterChainDefinitionMap.put("/sms/testing","anon");
        filterChainDefinitionMap.put("/shiro/rememberi","anon");

        filterChainDefinitionMap.put("/shiro/add","roles[svip]");
        filterChainDefinitionMap.put("/shiro/add","perms[add]");

        filterChainDefinitionMap.put("/**","authc");

        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面，这个就是类似于登录界面
        //shiroFilterFactoryBean.setLoginUrl("/html/denglu");
        //将map放入shiro管理器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        logger.info("-------------shiro拦截器工厂类注入成功----------------");
        return shiroFilterFactoryBean;
    }

    /*
     * 配置核心安全事务管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm")UserRealm userRealm,@Qualifier("sessionManager")SessionManager sessionManager) {
        logger.info("--------------shiro安全事务管理器已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSessionManager(sessionManager);
        //关联userRealm
        manager.setRealm(userRealm);
        return manager;
    }

    /*
     * 配置自定义的权限登录器
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public SessionManager sessionManager(){
        return new MySessionManager();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持.
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
