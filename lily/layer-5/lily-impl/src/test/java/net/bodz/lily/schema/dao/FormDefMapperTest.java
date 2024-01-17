package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.FormDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class FormDefMapperTest
        extends AbstractTableTest<FormDef, FormDefCriteriaBuilder, FormDefMapper> {

    @Override
    public FormDef buildSample()
            throws Exception {
        FormDefSamples a = new FormDefSamples();
        a.schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return a.build();
    }

}
