package net.bodz.swt.gui.testtypes;

import net.bodz.bas.gui.a.Color;
import net.bodz.bas.gui.a.Label;
import net.bodz.swt.gui.a.MaxLength;

public class ComplexPerson {

    public String name;

    @Color(back = "#ffffcc")
    public ASL    asl;

    public void setName(String name) {
        this.name = name;
    }

    public void setASL(//
            int age, //
            @Label("Is Male? ") boolean sex, //
            @MaxLength(30) String location) {
        this.asl = new ASL(age, sex, location);
    }

    @Override
    public String toString() {
        return "<" + name + ": " + asl + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

}
