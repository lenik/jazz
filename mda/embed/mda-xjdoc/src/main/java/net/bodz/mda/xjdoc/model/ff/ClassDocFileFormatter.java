package net.bodz.mda.xjdoc.model.ff;

import java.util.Map.Entry;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.ElementDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.IDocVisitor;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class ClassDocFileFormatter
        implements IDocVisitor {

    static final String TEXT_KEY = ".";

    // boolean importTypes;
    String indent = "    ";

    @Override
    public void visit(ElementDoc elementDoc) {
        formatEntry(TEXT_KEY, elementDoc.getText());
        for (Entry<String, Object> entry : elementDoc.getTagMap().entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();
            formatEntry(tagName, tagValue);
        }
    }

    /**
     * @.doc
     */
    @Override
    public void visit(ClassDoc classDoc) {
    }

    @Override
    public void visit(FieldDoc fieldDoc) {
    }

    @Override
    public void visit(MethodDoc methodDoc) {
    }

    void formatEntry(String key, DomainString dstr) {

    }

}
