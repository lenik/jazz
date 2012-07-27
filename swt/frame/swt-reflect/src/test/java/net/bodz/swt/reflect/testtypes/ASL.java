package net.bodz.swt.reflect.testtypes;

import net.bodz.bas.meta.info.Doc;

@Doc("Age/Sex/Location")
@ParseBy(ASL.ASLParser.class)
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

    @Override
    public String toString() {
        char sexChar = sex ? 'm' : 'f';
        return age + "/" + sexChar + "/" + location;
    }

    public static class ASLParser
            implements TypeParser {

        @Override
        public ASL parse(String text)
                throws ParseException {
            int slash = text.indexOf('/');
            int age = Integer.parseInt(text.substring(0, slash));
            text = text.substring(slash + 1);
            slash = text.indexOf('/');
            String sex = text.substring(0, slash);
            String location = text.substring(slash + 1);
            return new ASL(age, "m".equalsIgnoreCase(sex), location);
        }

    }

}
