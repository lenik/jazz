package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;

public class GroupIndex
        extends CoPrincipalIndex<Group, GroupMask> {

    public static final String SCHEMA = "group";

    public GroupIndex() {
        super(SCHEMA);
    }

}
