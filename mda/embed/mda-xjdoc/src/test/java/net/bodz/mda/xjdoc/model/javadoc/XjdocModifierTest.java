package net.bodz.mda.xjdoc.model.javadoc;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * This is the label.
 * 
 * This is the description.
 * 
 * And the remaining are ...
 * 
 * going to be the helpDoc.
 * 
 * @.docpar label description helpDoc
 */
public class XjdocModifierTest
        extends Assert {

    @Test
    public void testProcess() {
        ClassDoc doc = Xjdocs.getDefaultProvider().getClassDoc(XjdocModifierTest.class);
        XjdocModifier.process(doc);

        iString label = (iString) doc.getTag("label");
        iString description = (iString) doc.getTag("description");
        iString helpDoc = (iString) doc.getTag("helpDoc");
        System.out.println("label: " + label);
        System.out.println("description: " + description);
        System.out.println("helpDoc: " + helpDoc);
    }

}
