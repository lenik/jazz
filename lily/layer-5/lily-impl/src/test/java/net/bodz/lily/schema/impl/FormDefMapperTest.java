package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.FormDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class FormDefMapperTest
        extends AbstractMapperTest<FormDef, FormDefMask, FormDefMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FormDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return FormDefSamples.build(schema);
    }

}
