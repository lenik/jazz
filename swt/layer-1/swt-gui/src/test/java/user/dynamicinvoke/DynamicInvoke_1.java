package user.dynamicinvoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class DynamicInvoke_1 {

    public void greet(Object a) {
        System.out.println("Hello, " + a);
    }

    public void greet(String a, String b) {
        System.out.println("Hi, " + a + ", I am " + b + ".");
    }

    public static void main(String... av)
            throws Throwable {
        Lookup lookup = MethodHandles.lookup();

// MethodType f1 = MethodType.methodType(void.class, String.class);
        MethodType f1 = MethodType.genericMethodType(1);
        MethodType f2 = MethodType.genericMethodType(2);

        MethodHandle greet1 = lookup.findVirtual(DynamicInvoke_1.class, "greet", f1);
        // MethodHandle greet2 = lookup.findVirtual(Dyn1.class, "greet", f2);

        DynamicInvoke_1 a = new DynamicInvoke_1();
        greet1.invoke(a, "Tom");
        // greet2.invoke(a, "Lucy", "Lily");
    }

}
