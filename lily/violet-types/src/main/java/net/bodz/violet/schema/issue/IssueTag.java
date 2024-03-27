package net.bodz.violet.schema.issue;

import javax.persistence.Table;

@Table(schema = IssueTag.SCHEMA_NAME, name = IssueTag.TABLE_NAME)
public class IssueTag
        extends _IssueTag_stuff<IssueTag> {

    private static final long serialVersionUID = 1L;

}
