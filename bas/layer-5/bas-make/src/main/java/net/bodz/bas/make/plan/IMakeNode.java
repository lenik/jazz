package net.bodz.bas.make.plan;

import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.DataTypedKeys;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.MakeNodeType;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeNode
        extends IElement {

    @NotNull
    MakeNodeType getType();

    default boolean isSelect() {
        return getType() == MakeNodeType.SELECT;
    }

    default boolean isSequence() {
        return getType() == MakeNodeType.SEQUENCE;
    }

    default boolean isAction() {
        return getType() == MakeNodeType.RUN;
    }

    @NotNull
    List<IMakeNode> getChildren();

    default int getChildCount() {
        return getChildren().size();
    }

    void addChild(@NotNull IMakeNode child);

    void removeChild(@NotNull IMakeNode child);

    BoundRule<?> getBoundRule();

    default double getMinEstimatedCost() {
        switch (getType()) {
            case SELECT:
                if (getChildCount() == 0)
                    return 0;
                double min = Double.MAX_VALUE;
                for (IMakeNode child : getChildren()) {
                    double childMin = child.getMinEstimatedCost();
                    if (childMin < min)
                        min = childMin;
                }
                return min;

            case SEQUENCE:
                double sum = 0;
                for (IMakeNode child : getChildren())
                    sum += child.getMinEstimatedCost();
                return sum;

            case RUN:
                BoundRule<?> boundRule = getBoundRule();
                double cost = boundRule.getCost();
                for (IMakeNode child : getChildren())
                    cost += child.getMinEstimatedCost();
                return cost;
        }
        throw new UnexpectedException();
    }

    default IMakeNode reduceToMinCost() {
        switch (getType()) {
            case SELECT:
                switch (getChildCount()) {
                    case 0:
                        return null;
                    case 1:
                        return getChildren().get(0).reduceToMinCost();
                    default:
                        IMakeNode minNode = null;
                        double minCost = Double.MAX_VALUE;
                        for (IMakeNode child : getChildren()) {
                            double childCost = child.getMinEstimatedCost();
                            if (childCost < minCost) {
                                minCost = childCost;
                                minNode = child;
                            }
                        }
                        assert minNode != null;
                        return minNode.reduceToMinCost();
                }

            case SEQUENCE:
                MutableMakeNode newSeq = MutableMakeNode.seq();
                for (IMakeNode child : getChildren()) {
                    IMakeNode reduced = child.reduceToMinCost();
                    if (reduced == null)
                        continue;
                    newSeq.addChild(reduced);
                }
                switch (newSeq.getChildCount()) {
                    case 0:
                        return null;
                    case 1:
                        return newSeq.getChildren().get(0);
                    default:
                        return newSeq;
                }

            case RUN:
                MutableMakeNode newRun = MutableMakeNode.run(getBoundRule());
                for (IMakeNode child : getChildren()) {
                    IMakeNode reduced = child.reduceToMinCost();
                    if (reduced == null)
                        continue;
                    newRun.addChild(reduced);
                }
                return newRun;

            default:
                throw new UnexpectedException();
        }
    }

    default void run(Predicate<IMakeNode> selector)
            throws MakeException {
        switch (getType()) {
            case SELECT:
                for (IMakeNode child : getChildren())
                    if (selector.test(child)) {
                        child.run(selector);
                        break;
                    }
                break;

            case SEQUENCE:
                for (IMakeNode child : getChildren())
                    child.run(selector);
                break;

            case RUN:
                for (IMakeNode child : getChildren()) // inputs
                    child.run(selector);
                BoundRule<?> boundRule = getBoundRule();
                boundRule.run();
                break;

            default:
                throw new UnexpectedException();
        }
    }

    default void dump(ITreeOut out) {
        switch (getType()) {
            case SELECT:
            case SEQUENCE:
                out.println(getType().name() + ": ");
                out.enter();
                for (IMakeNode child : getChildren())
                    child.dump(out);
                out.leave();
                break;

            case RUN:
                BoundRule<?> boundRule = getBoundRule();
                IMakeRule<?> rule = boundRule.getRule();
                IKeyData<?, ?> target = boundRule.getTarget();
                String targetSpec = DataTypedKeys.format(target);

                IKeyData<?, ?>[] inputKeys = boundRule.getInputs();
                String inputSpecs = DataTypedKeys.format(inputKeys);

                out.printf("RUN rule %s ::= %s(%s)\n", targetSpec, rule.getClass().getSimpleName(), inputSpecs);
                out.enter();
                for (IMakeNode child : getChildren()) {
                    out.print("INPUT ");
                    child.dump(out);
                }
                out.leave();
        }
    }

}