package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.TextList;

public class TextRun
        extends AbstractDocRun {

    public final TextList textList = new TextList();

    public TextRun(INode parent) {
        super(parent);
    }

    public TextRun(INode parent, String text) {
        super(parent);
        this.textList.add(text);
    }

    @Override
    public NodeType getType() {
        return NodeType.TEXT_RUN;
    }

    @Override
    public boolean isTextRun() {
        return true;
    }

    @Override
    public String getText() {
        return textList.compactText();
    }

    @Override
    public void buildText(StringBuilder a) {
        synchronized (textList) {
            for (String block : textList)
                a.append(block);
        }
    }

    @Override
    public void setText(String s) {
        clearText();
        addText(s);
    }

    public TextRun addText(String s) {
        textList.add(s);
        return this;
    }

    public void clearText() {
        textList.clear();
    }

    @Override
    public boolean canReduce() {
        return textList.isEmpty();
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.textRun(this);
    }

}
