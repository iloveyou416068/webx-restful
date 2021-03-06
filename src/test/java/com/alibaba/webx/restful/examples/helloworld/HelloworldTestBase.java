package com.alibaba.webx.restful.examples.helloworld;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockServletContext;

import com.alibaba.webx.restful.Constants;
import com.alibaba.webx.restful.RestfulServletFilter;
import com.alibaba.webx.restful.process.RestfulComponent;
import com.alibaba.webx.restful.util.ApplicationContextUtils;

public class HelloworldTestBase extends TestCase {

    protected ClassPathXmlApplicationContext applicationContext;
    protected MockServletContext             servletContext;
    protected RestfulServletFilter       filter;
    protected RestfulComponent           component;

    protected void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("hello-spring.xml");
        servletContext = new MockServletContext();

        ApplicationContextUtils.setApplicationContext(servletContext, applicationContext);

        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);

        filterConfig.addInitParameter(Constants.PROVIDER_PACKAGES,
                                      "com.alibaba.webx.restful.examples.helloworld");

        filter = new RestfulServletFilter();
        filter.init(filterConfig);

        component = filter.getComponent();
    }

    protected void tearDown() throws Exception {
        filter.destroy();
        applicationContext.destroy();
    }

}
