package net.bodz.uni.shelj.codegen.java.ioforms;

import java.util.Collection;

import net.bodz.uni.shelj.codegen.java.JavaCodeBuilder;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public abstract class SourceBuilderForMembers
        extends JavaCodeBuilder {

    protected Collection<IMember> members;

    public Collection<IMember> getMembers() {
        return members;
    }

    public void setMembers(Collection<IMember> members) {
        this.members = members;
    }

}
