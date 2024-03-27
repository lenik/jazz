package net.bodz.violet.schema.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("id")
@PrimaryKeyProperties("id")
@Table(schema = TestQuestionTalk.SCHEMA_NAME, name = TestQuestionTalk.TABLE_NAME)
public class TestQuestionTalk
        extends _TestQuestionTalk_stuff {

    private static final long serialVersionUID = 1L;

}
