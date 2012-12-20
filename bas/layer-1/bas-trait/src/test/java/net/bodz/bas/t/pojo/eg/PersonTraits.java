package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.traits.AbstractCommonTraits;

public class PersonTraits
        extends AbstractCommonTraits<Person> {

    public PersonTraits() {
        super(Person.class);
    }

    @Override
    protected Object query(int traitIndex) {
        return null;
    }

}
