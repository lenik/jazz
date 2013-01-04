package net.bodz.bas.c.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

public class TypeParamTest
        extends Assert {

    String str;
    ArrayList<String> strList;

    TreeMap<Integer, Long> getMap(TreeMap<Short, Boolean> input) {
        return null;
    }

    @Test
    public void testClassTypeParam() {
        class StringList
                extends ArrayList<String> {
            private static final long serialVersionUID = 1L;
        }
        Class<Object> itemType = TypeParam.infer1(StringList.class, List.class, 0);
        assertEquals(String.class, itemType);
    }

    @Test
    public void testFieldParam()
            throws Exception {
        Field field = TypeParamTest.class.getDeclaredField("strList");
        Type[] typeArgs = TypeParam.getTypeArgs(field.getGenericType(), List.class);
        assertArrayEquals(new Object[] { String.class }, typeArgs);
    }

    @Test
    public void testMethodParam()
            throws Exception {
        Method method = TypeParamTest.class.getDeclaredMethod("getMap", TreeMap.class);

        Type returnType = method.getGenericReturnType();
        Type[] returnTypeArgs = TypeParam.getTypeArgs(returnType, Map.class);
        assertArrayEquals(new Object[] { Integer.class, Long.class }, returnTypeArgs);

        Type param1Type = method.getGenericParameterTypes()[0];
        Type[] param1TypeArgs = TypeParam.getTypeArgs(param1Type, Map.class);
        assertArrayEquals(new Object[] { Short.class, Boolean.class }, param1TypeArgs);
    }

    @Test
    public void testRawFieldParam()
            throws Exception {
        Field field = TypeParamTest.class.getDeclaredField("str");
        Type genericType = field.getGenericType();
        System.out.println(genericType);
    }

}
