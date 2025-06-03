package net.bodz.bas.make.plan;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeNodeType;
import net.bodz.bas.meta.decl.NotNull;

public class MutableMakeNode
        extends MutableElement
        implements IMakeNode {

    private static final long serialVersionUID = -4072712716902647412L;

    MakeNodeType type;
    List<IMakeNode> children = new ArrayList<>();
    BoundRule<?, ?, ?> boundRule;

    public MutableMakeNode(@NotNull MakeNodeType type) {
        this.type = type;
    }

    public static MutableMakeNode select(IMakeNode... inputs) {
        MutableMakeNode node = new MutableMakeNode(MakeNodeType.SELECT);
        node.children.addAll(Arrays.asList(inputs));
        return node;
    }

    public static MutableMakeNode seq(IMakeNode... inputs) {
        MutableMakeNode node = new MutableMakeNode(MakeNodeType.SEQUENCE);
        node.children.addAll(Arrays.asList(inputs));
        return node;
    }

    public static MutableMakeNode run(BoundRule<?, ?, ?> boundRule) {
        MutableMakeNode node = new MutableMakeNode(MakeNodeType.RUN);
        node.boundRule = boundRule;
        return node;
    }

    @NotNull
    @Override
    public MakeNodeType getType() {
        return type;
    }

    public void setType(@NotNull MakeNodeType type) {
        this.type = type;
    }

    @NotNull
    @Override
    public List<IMakeNode> getChildren() {
        return children;
    }

    @Override
    public void addChild(@NotNull IMakeNode child) {
        this.children.add(child);
    }

    @Override
    public void removeChild(@NotNull IMakeNode child) {
        this.children.remove(child);
    }

    @Override
    public BoundRule<?, ?, ?> getBoundRule() {
        return boundRule;
    }

    public void setBoundRule(BoundRule<?, ?, ?> boundRule) {
        this.boundRule = boundRule;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        switch (type) {
            case SELECT:
            case SEQUENCE:
                buf.append(type);
                buf.append("(");
                for (int i = 0; i < children.size(); i++) {
                    if (i != 0)
                        buf.append(", ");
                    IMakeNode child = children.get(i);
                    buf.append(child.toString());
                }
                buf.append(")");
                break;

            case RUN:
                IMakeRule<?, ?, ?> rule = boundRule.getRule();
                //                IKeyData<?, ?> target = boundRule.getTarget();
                //                IKeyData<?, ?>[] inputs = boundRule.getInputs();

                buf.append("RUN ").append(rule.getClass().getSimpleName());
                buf.append("(");
                for (int i = 0; i < children.size(); i++) {
                    if (i != 0)
                        buf.append(", ");
                    IMakeNode child = children.get(i);
                    buf.append(child.toString());
                }
                buf.append(")");
        }
        return buf.toString();
    }

}
