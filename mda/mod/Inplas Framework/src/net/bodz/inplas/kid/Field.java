package net.bodz.inplas.kid;

import net.bodz.bas.lang.err.NotImplementedException;
import net.sf.freejava.sql.model.TypeStruct;

public class Field {

    protected Project    project;
    protected Table      table;

    protected String     name;
    protected String     display;

    protected TypeStruct type;

    /** default value */
    protected Object     initial;

    protected String     comment;

    public Field() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TypeStruct getType() {
        return type;
    }

    public void setType(TypeStruct type) {
        this.type = type;
    }

    public Object getInitial() {
        return initial;
    }

    public void setInitial(Object initial) {
        this.initial = initial;
    }

    public String getName_X() {
        return getName();
    }

    public void setName_X(String name_X) {
        setName(name_X);
    }

    public String getDisplay_X() {
        return getDisplay();
    }

    public void setDisplay_X(String display_X) {
        setDisplay(display_X);
    }

    public String getComment_X() {
        return getComment();
    }

    public void setComment_X(String comment_X) {
        setComment(comment_X);
    }

    public String getType_X() {
        throw new NotImplementedException();
    }

    public void setType_X(String type_X) {

    }

    public String getProps_X() {
        throw new NotImplementedException();
    }

    public void setProps_X(String props_X) {
        // Object initial = type.decode(props_X);
        setInitial(initial);
    }

}
