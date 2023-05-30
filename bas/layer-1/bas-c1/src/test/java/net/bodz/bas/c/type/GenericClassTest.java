package net.bodz.bas.c.type;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigInteger;

import javax.swing.Box;
import javax.swing.text.Caret;

import org.junit.Test;
import org.omg.CORBA.Any;

public class GenericClassTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args)
            throws Exception {

        Method m1 = Foo.class.getMethod("getA");
        Type ret = m1.getGenericReturnType();
        TypeVariable<?> tv = (TypeVariable<?>) ret;
        Type[] bounds = tv.getBounds();
        System.out.println(ret);

        Type bigIntSuper = BigInteger.class.getGenericSuperclass();
        boolean isParamType = bigIntSuper instanceof ParameterizedType;

        Type barSuper = Bar.class.getGenericSuperclass();
        ParameterizedType barSuperParams = (ParameterizedType) barSuper;
        Type[] barArgs = barSuperParams.getActualTypeArguments();

        Type realSuper = Real.class.getGenericSuperclass();
        ParameterizedType realSuperParams = (ParameterizedType) realSuper;
        Type[] realArgs = realSuperParams.getActualTypeArguments();

//        Class<?>[] infer = TypeParam.infer(Bar.class, Foo.class);
        System.out.println();
    }

}

class Foo<A extends Box, B> {

    public A getA() {
        return null;
    }

}

//class Bar<Ccc extends Caret, Bbb extends Box, Aaa extends Any>
//        extends Foo<Aaa, Bbb> {
//}
class Bar<Aaa extends Any, Bbb extends Box, Ccc extends Caret>
        extends Foo<Bbb, Aaa> {
}

class Real
        extends Bar<Any, Box, Caret> {
}
