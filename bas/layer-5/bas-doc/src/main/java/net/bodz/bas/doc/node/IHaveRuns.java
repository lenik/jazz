package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeList;

public interface IHaveRuns
        extends
            INode {

    @Override
    default boolean haveRuns() {
        return true;
    }

    DocNodeList<IRun> getRuns();

    default TextRun addTextRun() {
        TextRun run = new TextRun(this);
        return getRuns().append(run);
    }

    default TextRun getTextRunToAppend() {
        DocNodeList<IRun> runs = getRuns();
        if (runs.isEmpty())
            return addTextRun();
        IRun lastRun = runs.get(runs.size() - 1);
        if (lastRun.getType() == NodeType.TEXT_RUN)
            return (TextRun) lastRun;
        else
            return addTextRun();
    }

    @Override
    default void buildText(StringBuilder a) {
        for (IRun run : getRuns())
            run.buildText(a);
    }

    @Override
    default void setText(String s) {
        getRuns().clear();
        addText(s);
    }

    default TextRun setTextRun(String s) {
        getRuns().clear();
        return addText(s);
    }

    default TextRun addText(String s) {
        return getTextRunToAppend().addText(s);
    }

    default RunGroup addEnv() {
        return getRuns().append(new RunGroup(this));
    }

    default FontEnv addFontEnv() {
        return getRuns().append(new FontEnv(this));
    }

    default FontStyleEnv addFontStyleEnv() {
        return getRuns().append(new FontStyleEnv(this));
    }

    default Breaker addBreak(BreakerType breakType) {
        return getRuns().append(new Breaker(this, breakType));
    }

    default Image addImage() {
        return getRuns().append(new Image(this));
    }

}
