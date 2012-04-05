package net.bodz.mda.xjdoc.cdf;

import java.util.Map.Entry;

import net.bodz.mda.xjdoc.base.ClassDoc;
import net.bodz.mda.xjdoc.base.ElementDoc;
import net.bodz.mda.xjdoc.base.FieldDoc;
import net.bodz.mda.xjdoc.base.IDocVisitor;
import net.bodz.mda.xjdoc.base.MethodDoc;
import net.bodz.mda.xjdoc.dstr.DomainString;


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
