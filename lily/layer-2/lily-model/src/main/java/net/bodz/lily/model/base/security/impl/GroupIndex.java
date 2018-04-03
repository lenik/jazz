package net.bodz.lily.model.base.security.impl;

import net.bodz.lily.model.base.security.Group;

public class GroupIndex
        extends CoPrincipalIndex<Group, GroupMask> {

    public static final String SCHEMA = "group";

    public GroupIndex() {
        super(SCHEMA);
    }

}
