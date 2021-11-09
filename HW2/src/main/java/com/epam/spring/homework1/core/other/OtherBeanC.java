package com.epam.spring.homework1.core.other;

import com.epam.spring.homework1.core.beans.BeanC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanC {
    @Autowired
    private BeanC beanC;

    public OtherBeanC(){
        System.out.println(beanC);
    }
}
