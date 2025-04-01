package net.bodz.uni.shelj.codegen.java.ioforms;

import net.bodz.uni.shelj.codegen.java.JavaCodeBuilder;

public abstract class SourceBuilderForMembers
        extends JavaCodeBuilder {

    protected MemberSelection members;

    public MemberSelection getMembers() {
        return members;
    }

    public void setMembers(MemberSelection members) {
        this.members = members;
    }

}
