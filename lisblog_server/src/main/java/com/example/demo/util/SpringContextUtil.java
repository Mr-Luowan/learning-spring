package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static Object getBean(String name, Class<?> requireType) throws BeansException {
        return applicationContext.getBean(name, requireType);
    }

    public static <T> T getBean(Class<T> clazz)
            throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
