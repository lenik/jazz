package net.bodz.bas.meta.build;

import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.DecoratedClassDoc;
import net.bodz.mda.xjdoc.model.IClassDoc;

public class ClassInfo
        extends DecoratedClassDoc {

    private static final long serialVersionUID = 1L;

    public ClassInfo(IClassDoc _orig) {
        super(_orig);
        ClassDoc cd;
    }

    public String getAuthor() {
        Object author = getTag("author");
        return String.valueOf(author);
    }

}
