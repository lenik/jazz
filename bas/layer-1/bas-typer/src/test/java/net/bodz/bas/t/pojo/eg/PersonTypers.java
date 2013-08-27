package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.typer.std.AbstractCommonTypers;

public class PersonTypers
        extends AbstractCommonTypers<Person> {

    public PersonTypers() {
        super(Person.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        return null;
    }

}
