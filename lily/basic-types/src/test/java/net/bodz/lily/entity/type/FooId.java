package net.bodz.lily.entity.type;

import javax.persistence.Column;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.lily.entity.AbstractIdentity;
import net.bodz.lily.entity.BeanIdentityTypeInfo;
import net.bodz.lily.entity.IIdentityTypeInfo;

public class FooId
        extends AbstractIdentity {

    //    public static ExternalFooIdTypeInfo TYPE = ExternalFooIdTypeInfo.INSTANCE;
    public static IIdentityTypeInfo TYPE = new BeanIdentityTypeInfo(FooId.class);

    String name;
    int age;

    public FooId() {
    }

    public FooId(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public IIdentityTypeInfo getType() {
        return TYPE;
    }

    @Ordinal(0)
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Ordinal(1)
    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "FooId [name=" + name + ", age=" + age + "]";
    }

    public static void main(String[] args)
            throws ParseException {
        FooId id = new FooId();
        IIdentityTypeInfo type = id.getType();
        System.out.println("column names: " + Arrays.asList(type.getColumnNames()));
        System.out.println("property names: " + Arrays.asList(type.getPropertyNames()));
        id.parse("tom", "18");
        System.out.println(id);

    }

}
