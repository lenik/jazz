package net.bodz.bas.potato.provider.bean;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Red {
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Black {
}

class Base {
    @Red
    public int getColor() {
        return 0;
    }
}

class Derived
        extends Base {
    @Override
    @Black
    public int getColor() {
        return 0;
    }
}

public class BeanPropertyTest {

    public static void main(String[] args) {
        IType type = BeanTypeProvider.getInstance().getType(Derived.class);
        IProperty prop = type.getProperty("color");
        System.out.println("prop: " + prop);
        for (IProperty p : prop.ancestorsToRoot(true))
            System.out.println("ancestor: " + p.getQName());
    }

}
