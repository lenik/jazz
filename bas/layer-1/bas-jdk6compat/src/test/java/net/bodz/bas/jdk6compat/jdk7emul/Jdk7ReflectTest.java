package net.bodz.bas.jdk6compat.jdk7emul;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

public class Jdk7ReflectTest {

    public static class Dog {

        String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String hello(String friend) {
            return "Hello, " + friend + ", I'm " + name;
        }

        public void error()
                throws Exception {
            throw new Exception("Error");
        }

    }

    Class<Dog> dogClass = Dog.class;
    Dog blackDog = new Dog("Black");

    @Test
    public void testGetMethodOk()
            throws ReflectiveOperationException {
        Jdk7Reflect.getMethod(dogClass, "hello", String.class);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testGetMethodFail()
            throws ReflectiveOperationException {
        Jdk7Reflect.getMethod(dogClass, "notexist");
    }

    @Test
    public void testMethodInvokeSuccess()
            throws ReflectiveOperationException {
        Method helloMethod = Jdk7Reflect.getMethod(dogClass, "hello", String.class);

        Object mesg = Jdk7Reflect.invoke(helloMethod, blackDog, "Tom");
        assertEquals("Hello, Tom, I'm Black", mesg);
    }

    @Test(expected = InvocationTargetException.class)
    public void testMethodInvokeFail()
            throws ReflectiveOperationException {
        Method errorMethod = Jdk7Reflect.getMethod(dogClass, "error");
        Jdk7Reflect.invoke(errorMethod, blackDog);
    }

}
