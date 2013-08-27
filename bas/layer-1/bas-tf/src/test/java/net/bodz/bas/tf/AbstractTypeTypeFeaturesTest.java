package net.bodz.bas.tf;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.TypeFeatureClass;
import net.bodz.bas.tf.std.ICommonTypeFeatures;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.bas.t.pojo.eg.PersonTypeFeatures;

@TypeFeatureClass(PersonTypeFeatures.class)
public class AbstractTypeTypeFeaturesTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        ICommonTypeFeatures<Person> typeFeatures = new PersonTypeFeatures();

        typeFeatures.getValidator().validate(Person.Lenik);
        String lucyFormat = typeFeatures.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
