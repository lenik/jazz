package net.bodz.inplas.kid;

import java.util.ArrayList;
import java.util.List;

public class Table {

    protected Project     project;

    protected String      name;
    protected String      comment;
    protected List<Field> fields = new ArrayList<Field>();

    public Table() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addField(Field field) {
        field.table = this;
        field.project = this.project;
        this.fields.add(field);
    }

}
