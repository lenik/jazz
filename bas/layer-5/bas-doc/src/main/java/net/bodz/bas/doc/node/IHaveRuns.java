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

    @Override
    default void buildText(StringBuilder a) {
        for (IRun run : getRuns())
            run.buildText(a);
    }

    @Override
    default void setText(String s) {
        getRuns().clear();
        addTextRun(s);
    }

    default TextRun setTextRun(String s) {
        getRuns().clear();
        return addTextRun(s);
    }

    default TextRun addTextRun(String s) {
        return getRuns().append(new TextRun(this, s));
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

    default Image addImage() {
        return getRuns().append(new Image(this));
    }

}
