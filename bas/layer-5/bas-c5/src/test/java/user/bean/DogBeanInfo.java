package user.bean;

import java.lang.reflect.Method;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.PropertyDescriptor;
import com.googlecode.openbeans.SimpleBeanInfo;

public class DogBeanInfo
        extends SimpleBeanInfo {

    static class Add
            extends SimpleBeanInfo {
        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            try {
                Method color_r = Dog.class.getMethod("getColor");
                Method color_w = Dog.class.getMethod("setColor", String.class);
                PropertyDescriptor colour = new PropertyDescriptor("colour", color_r, color_w);
                colour.setValue("mytag", "value");
                return new PropertyDescriptor[] { colour };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        return new BeanInfo[] { new Add() };
    }

}
