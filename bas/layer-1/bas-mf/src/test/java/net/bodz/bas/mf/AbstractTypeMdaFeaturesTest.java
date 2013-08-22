package net.bodz.bas.mf;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.MdaFeatureClass;
import net.bodz.bas.mf.std.ICommonMdaFeatures;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.bas.t.pojo.eg.PersonMdaFeatures;

@MdaFeatureClass(PersonMdaFeatures.class)
public class AbstractTypeMdaFeaturesTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        ICommonMdaFeatures<Person> mdaFeatures = new PersonMdaFeatures();

        mdaFeatures.getValidator().validate(Person.Lenik);
        String lucyFormat = mdaFeatures.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
