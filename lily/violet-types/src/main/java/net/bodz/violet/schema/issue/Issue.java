package net.bodz.violet.schema.issue;

import javax.persistence.Table;

@Table(schema = Issue.SCHEMA_NAME, name = Issue.TABLE_NAME)
public class Issue
        extends _Issue_stuff {

    private static final long serialVersionUID = 1L;

}
