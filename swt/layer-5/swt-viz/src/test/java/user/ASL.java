package user;

import net.bodz.bas.repr.validate.MaxLength;

/**
 * Age/Sex/Location
 */
public class ASL {

    public int age;
    public boolean sex;
    public String location;

    public ASL(int age, boolean sex, String location) {
        super();
        this.age = age;
        this.sex = sex;
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @label name Is male?
     */
    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @MaxLength(30)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        char sexChar = sex ? 'm' : 'f';
        return age + "/" + sexChar + "/" + location;
    }

}
