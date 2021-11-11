package com.epam.spring.homework2.core.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("In postProcessBeanFactory");
        BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("beanB");
        bd.setInitMethodName("otherInitMethod");

        ((DefaultListableBeanFactory) configurableListableBeanFactory)
                .registerBeanDefinition("beanB", bd);
    }


}
