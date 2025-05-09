package net.bodz.bas.codegen.java.ioforms;

import java.util.ArrayList;

import net.bodz.bas.codegen.java.member.IMember;

public class MemberSelection
        extends ArrayList<IMember> {

    Class<?> clazz;

    public MemberSelection(Class<?> clazz) {
        this.clazz = clazz;
    }

}
