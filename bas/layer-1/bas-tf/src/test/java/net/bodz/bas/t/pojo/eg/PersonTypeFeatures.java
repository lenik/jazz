package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;

public class PersonTypeFeatures
        extends AbstractCommonTypeFeatures<Person> {

    public PersonTypeFeatures() {
        super(Person.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        return null;
    }

}
