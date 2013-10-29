package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.typer.std.ValidationException;

public class PersonTypers
        extends AbstractCommonTypers<Person> {

    public PersonTypers() {
        super(Person.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IValidator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public void validate(Person person, IOptions options)
            throws ValidationException {
        if (Nullables.isEmpty(person.getName()))
            throw new ValidationException("Name is empty.");
    }

}
