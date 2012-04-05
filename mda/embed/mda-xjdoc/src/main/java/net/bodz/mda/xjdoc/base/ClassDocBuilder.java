package net.bodz.mda.xjdoc.base;

import net.bodz.mda.xjdoc.dstr.DomainString;

import com.thoughtworks.qdox.model.AbstractJavaEntity;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

/**
 * Build class documents from the qdox parsed java sources.
 */
public class ClassDocBuilder {

    static void populate(ElementDoc elementDoc, AbstractJavaEntity javaEntity) {
        String comment = javaEntity.getComment();
        DomainString text = DomainString.parseParaLang(comment);
        elementDoc.text = text;

        for (DocletTag tag : javaEntity.getTags()) {
            String tagName = tag.getName();
            String plText = tag.getValue();
            DomainString value = DomainString.parseParaLang(plText);
            elementDoc.tagMap.put(tagName, value);
        }
    }

    public static ClassDoc buildClass(JavaClass javaClass) {
        ClassDoc classDoc = new ClassDoc();
        populate(classDoc, javaClass);

        for (JavaField javaField : javaClass.getFields()) {
            String fieldName = javaField.getName();
            FieldDoc fieldDoc = buildField(javaField);
            classDoc.fields.put(fieldName, fieldDoc);
        }

        for (JavaMethod javaMethod : javaClass.getMethods()) {
            String methodName = javaMethod.getName(); // XXX signature
            MethodDoc methodDoc = buildMethod(javaMethod);
            classDoc.methods.put(methodName, methodDoc);
        }

        return classDoc;
    }

    public static FieldDoc buildField(JavaField javaField) {
        FieldDoc fieldDoc = new FieldDoc();
        populate(fieldDoc, javaField);
        return fieldDoc;
    }

    public static MethodDoc buildMethod(JavaMethod javaMethod) {
        MethodDoc methodDoc = new MethodDoc();
        populate(methodDoc, javaMethod);
        return methodDoc;
    }

}
