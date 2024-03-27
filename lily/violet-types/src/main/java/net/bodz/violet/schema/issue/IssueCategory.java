package net.bodz.violet.schema.issue;

import javax.persistence.Table;

@Table(schema = IssueCategory.SCHEMA_NAME, name = IssueCategory.TABLE_NAME)
public class IssueCategory
        extends _IssueCategory_stuff<IssueCategory> {

    private static final long serialVersionUID = 1L;

}
