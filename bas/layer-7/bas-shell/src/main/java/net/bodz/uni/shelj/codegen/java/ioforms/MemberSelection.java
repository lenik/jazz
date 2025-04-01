package net.bodz.uni.shelj.codegen.java.ioforms;

import java.util.ArrayList;

import net.bodz.uni.shelj.codegen.java.member.IMember;

public class MemberSelection
        extends ArrayList<IMember> {

    Class<?> clazz;

    public MemberSelection(Class<?> clazz) {
        this.clazz = clazz;
    }

}
