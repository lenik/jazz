package net.bodz.lily.schema.impl;

import org.junit.Ignore;

import net.bodz.bas.meta.source.ToDo;
import net.bodz.lily.schema.PriorityDef;
import net.bodz.lily.schema.PriorityDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractTableTest;

@ToDo
@Ignore
public class PriorityDefMapperTest
        extends AbstractTableTest<PriorityDef, PriorityDefMask, PriorityDefMapper> {

    @Override
    public PriorityDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return PriorityDefSamples.build(schema);
    }

}
