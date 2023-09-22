package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.TextList;

public class DocTextRun
        extends AbstractDocRun {

    public final TextList textList = new TextList();

    public DocTextRun(IDocNode parent) {
        super(parent);
    }

    public DocTextRun(IDocNode parent, String text) {
        super(parent);
        this.textList.add(text);
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

    public void addText(String s) {
        textList.add(s);
    }

    public void clearText() {
        textList.clear();
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.textRun(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (String s : textList)
            visitor.chars(s);
    }

}
