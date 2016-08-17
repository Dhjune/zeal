package org.zeal;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

/**
 * author: denghuajun
 * date: 2016/3/4 17:21
 * version: v1.0.0
 */
public class ServiceAnnatation  implements  Autowired{

    public boolean required() {
        return false;
    }

    public Class<? extends Annotation> annotationType() {
        return Autowired.class;
    }
}
