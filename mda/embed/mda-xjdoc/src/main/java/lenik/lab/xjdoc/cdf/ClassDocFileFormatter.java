package lenik.lab.xjdoc.cdf;

import java.util.Map.Entry;

import lenik.lab.xjdoc.base.ClassDoc;
import lenik.lab.xjdoc.base.ElementDoc;
import lenik.lab.xjdoc.base.FieldDoc;
import lenik.lab.xjdoc.base.IDocVisitor;
import lenik.lab.xjdoc.base.MethodDoc;
import lenik.lab.xjdoc.dstr.DomainString;

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
