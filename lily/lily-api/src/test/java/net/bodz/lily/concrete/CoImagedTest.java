package net.bodz.lily.concrete;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class CoImagedTest {

    public static void main(String[] args)
            throws Exception {
        BeanInfo info = Introspector.getBeanInfo(CoImaged.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(
                    pd.getReadMethod().getDeclaringClass() + "         =>          " + pd.getReadMethod().getName());
            if (pd.getName().equals("ETag")) {
                Method getter = pd.getReadMethod();
                System.out.println(getter);
            }
        }
    }

}
