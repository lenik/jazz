package net.bodz.bas.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectBean {

    public static void f(String x) {
    }

    public static void g(int a, Float b) {
    }

    public static void g(int a, Double b) {
    }

    public static void main(String[] args)
            throws Exception {
        // Method method = ReflectBean.class.getMethod("g", int.class, null);
        // System.out.println(method);
        List<Integer> list = new ArrayList<Integer>();
        // list.remove()
        // Method m = list.getClass().getMethod("remove", int.class);
        Method m = list.getClass().getMethod("indexOf", Object.class);
        Type type = m.getGenericReturnType();
        System.out.println(type.getClass());

        Dog dog = new Dog();
        dog.setColor("red");
        BeanDump.dumpProperties(dog);
        
        
    }

}
