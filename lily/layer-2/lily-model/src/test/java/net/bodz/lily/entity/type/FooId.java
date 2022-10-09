package net.bodz.lily.entity.type;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.lily.entity.Identifier;

@Identifier
public class FooId {

    String name;
    int age;

    public FooId(String name, int age) {
        this.name = name;
        this.age = age;
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

}
