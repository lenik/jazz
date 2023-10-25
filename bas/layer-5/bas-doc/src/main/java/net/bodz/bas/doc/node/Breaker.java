package net.bodz.bas.doc.node;

public class Breaker
        extends AbstractDocRun {

    BreakerType breakType;

    public Breaker(IHaveRuns parent, BreakerType breakType) {
        super(parent);
        setBreakType(breakType);
    }

    @Override
    public NodeType getType() {
        return NodeType.BREAKER;
    }

    public BreakerType getBreakType() {
        return breakType;
    }

    public void setBreakType(BreakerType breakType) {
        if (breakType == null)
            throw new NullPointerException("breakType");
        this.breakType = breakType;
    }

    @Override
    public void buildText(StringBuilder a) {
    }

    @Override
    public void setText(String s) {
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.breaker(this);
    }

}
