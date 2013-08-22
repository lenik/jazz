package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;

public class PersonMdaFeatures
        extends AbstractCommonMdaFeatures<Person> {

    public PersonMdaFeatures() {
        super(Person.class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        return null;
    }

}
