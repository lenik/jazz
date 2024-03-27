package net.bodz.violet.schema.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("id")
@PrimaryKeyProperties("id")
@Table(schema = TestApplyItem.SCHEMA_NAME, name = TestApplyItem.TABLE_NAME)
public class TestApplyItem
        extends _TestApplyItem_stuff {

    private static final long serialVersionUID = 1L;

}
