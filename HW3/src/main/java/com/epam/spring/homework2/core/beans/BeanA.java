package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements Validator, InitializingBean, DisposableBean {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Inside DisposableBean.destroy() method beanA");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Inside InitializingBean.afterPropertiesSet() method beanA");
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanA did not pass the validation :(");
        }
    }
}
