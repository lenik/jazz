package net.bodz.bas.codegen.java.ioforms;

import net.bodz.bas.codegen.java.JavaCodeBuilder;

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
