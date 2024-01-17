package net.bodz.bas.t.pojo.eg;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.meta.lang.typer;

@typer.family(PersonTypers.class)
public class Person {

    String name;
    MBTIType type;

    // asl
    int age;
    boolean isGirl;
    Address location;

    // body
    float height;
    float weight;
    BigDecimal salary;

    final Set<String> tags = new HashSet<>();

    public Person() {
    }

    public Person(String name, int age, boolean girl, Address location) {
        this.name = name;
        this.age = age;
        this.isGirl = girl;
        this.location = location;
    }

    public Person(String name, int age, boolean girl) {
        this(name, age, girl, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MBTIType getType() {
        return type;
    }

    public void setType(MBTIType type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 1000)
            throw new IllegalArgumentException("Not a valid age: " + age);
        this.age = age;
    }

    public boolean isBoy() {
        return !isGirl;
    }

    public void setBoy(boolean boy) {
        this.isGirl = !boy;
    }

    public boolean isGirl() {
        return isGirl;
    }

    public void setGirl(boolean girl) {
        this.isGirl = girl;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, height, isGirl, location, name, salary, tags, type, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return age == other.age && Float.floatToIntBits(height) == Float.floatToIntBits(other.height)
                && isGirl == other.isGirl && Objects.equals(location, other.location)
                && Objects.equals(name, other.name) && Objects.equals(salary, other.salary)
                && Objects.equals(tags, other.tags) && type == other.type
                && Float.floatToIntBits(weight) == Float.floatToIntBits(other.weight);
    }

    @Override
    public String toString() {
        return String.format("<Person name=%s age=%d %s location=\"%s\">", //
                name, age, isGirl ? "girl" : "boy", location);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        Integer age;
        Boolean boy;
        Boolean girl;
        Float height;
        Address location;
        String name;
        BigDecimal salary;
        MBTIType type;
        Float weight;

        boolean _location_specified;
        boolean _name_specified;
        boolean _salary_specified;
        boolean _type_specified;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder boy(boolean boy) {
            this.boy = boy;
            return this;
        }

        public Builder girl(boolean girl) {
            this.girl = girl;
            return this;
        }

        public Builder height(float height) {
            this.height = height;
            return this;
        }

        public Builder location(Address location) {
            this.location = location;
            this._location_specified = true;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            this._name_specified = true;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            this._salary_specified = true;
            return this;
        }

        public Builder type(MBTIType type) {
            this.type = type;
            this._type_specified = true;
            return this;
        }

        public Builder weight(float weight) {
            this.weight = weight;
            return this;
        }

        public Person build() {
            Person o = new Person();
            if (this.age != null)
                o.setAge(this.age);
            if (this.boy != null)
                o.setBoy(this.boy);
            if (this.girl != null)
                o.setGirl(this.girl);
            if (this.height != null)
                o.setHeight(this.height);
            if (_location_specified)
                o.setLocation(this.location);
            if (_name_specified)
                o.setName(this.name);
            if (_salary_specified)
                o.setSalary(this.salary);
            if (_type_specified)
                o.setType(this.type);
            if (this.weight != null)
                o.setWeight(this.weight);
            if (_tags_specified)
                o.tags.addAll(tags);
            return o;
        }

        public Builder asl(int age, boolean girl, Address loc) {
            this.age = age;
            this.girl = girl;
            return location(loc);
        }

        Set<String> tags = new LinkedHashSet<>();
        boolean _tags_specified;

        public Builder tag(String... v) {
            for (String a : v)
                tags.add(a);
            _tags_specified = true;
            return this;
        }

    }

    public static final Person Tom;
    public static final Person Lucy;
    public static final Person Shecti;
    public static final Person Lenik;
    static {
        Tom = Person.builder().name("Tom").asl(18, false, Address.Marks100).build();
        Lucy = Person.builder().name("Lucy").asl(16, true, Address.Golf200).build();
        Shecti = Person.builder().name("Shecti").asl(20, true, Address.YHLib).tag("mine").build();
        Lenik = Person.builder().name("Lenik").asl(28, true, Address.HNHome).tag("mime").build();
    }

}
