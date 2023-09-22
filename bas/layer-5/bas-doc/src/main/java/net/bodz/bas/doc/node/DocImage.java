package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.MeasureLength;
import net.bodz.bas.io.res.builtin.InputStreamSource;

public class DocImage
        extends AbstractDocPar
        implements
            IDocRun {

    String href;
    InputStreamSource data;
    MeasureLength width;
    MeasureLength height;
    String description;

    public DocImage(IDocNode parent) {
        super(parent);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public String getText() {
        return description;
    }

    @Override
    public void buildText(StringBuilder a) {
        a.append(description);
    }

    @Override
    public void setText(String s) {
        description = s;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.image(this);
    }

}
