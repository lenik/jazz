package net.bodz.lily.schema.contact;

import javax.persistence.Table;

/**
 * 职位关联
 */
@Table(schema = PersonRole.SCHEMA_NAME, name = PersonRole.TABLE_NAME)
public class PersonRole
        extends _PersonRole_stuff {

    private static final long serialVersionUID = 1L;

}
