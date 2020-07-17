package com.wujiabo.opensource.feather.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        logger.error("请求访问：{}，认证失败，无法访问系统资源",request.getRequestURI());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "认证失败，无法访问系统资源");
    }
}
