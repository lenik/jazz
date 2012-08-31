package user;

import net.bodz.bas.gui.a.Border;
import net.bodz.bas.gui.a.Color;
import net.bodz.bas.gui.a.Label;

public class School {

    /**
     * School Identifier
     */
    @Color(value = "blue", back = "#ccccff")
    public final String id;

    public School(String id) {
        this.id = id;
    }

    @Label("Lucy Girl")
    @Border
    public SimplePerson lucy;

    @Label("Lily Girl")
    public ComplexPerson lily;

    @Override
    public String toString() {
        return "Lucy is " + lucy + "\nLily is " + lily;
    }

}
