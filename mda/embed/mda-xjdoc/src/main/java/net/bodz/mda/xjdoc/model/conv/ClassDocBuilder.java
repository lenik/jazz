package net.bodz.mda.xjdoc.model.conv;

import java.util.Map;

import javax.free.IllegalUsageException;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.mda.xjdoc.meta.ITagType;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.ElementDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.IMethodIdStrategy;
import net.bodz.mda.xjdoc.util.SimpleMethodIdStrategy;
import net.bodz.mda.xjdoc.util.TypeNameContext;

import com.thoughtworks.qdox.model.AbstractJavaEntity;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

/**
 * Build class documents from the qdox parsed java sources.
 */
public class ClassDocBuilder {

    TypeNameContext typeNameContext;
    IMethodIdStrategy methodIdStrategy;
    IXjLanguage lang;

    public ClassDocBuilder(IXjLanguage lang) {
        if (lang == null)
            throw new NullPointerException("lang");
        this.lang = lang;
    }

    public ClassDoc buildClass(JavaClass javaClass) {
        ClassDoc classDoc = new ClassDoc(javaClass.getFullyQualifiedName());
        populate(classDoc, javaClass);

        for (JavaField javaField : javaClass.getFields()) {
            String fieldName = javaField.getName();
            FieldDoc fieldDoc = new FieldDoc(fieldName);
            populate(fieldDoc, javaField);
            classDoc.setFieldDoc(fieldName, fieldDoc);
        }

        IMethodIdStrategy methodIdStrategy = this.methodIdStrategy;
        if (methodIdStrategy == null)
            methodIdStrategy = new SimpleMethodIdStrategy(classDoc.getTypeNameContext());

        for (JavaMethod javaMethod : javaClass.getMethods()) {
            String methodId = methodIdStrategy.getMethodId(javaMethod);
            MethodDoc methodDoc = new MethodDoc(methodId);
            populate(methodDoc, javaMethod);
            classDoc.setMethodDoc(methodId, methodDoc);
        }

        return classDoc;
    }

    void populate(ElementDoc elementDoc, AbstractJavaEntity javaEntity) {
        String comment = javaEntity.getComment();
        DomainString text = DomainString.parseParaLang(comment);
        elementDoc.setText(text);

        Map<String, Object> tagMap = elementDoc.getTagMap();
        for (DocletTag docletTag : javaEntity.getTags()) {
            String tagName = docletTag.getName();
            String tagValueString = docletTag.getValue();

            // DomainString value = DomainString.parseParaLang(tagValueString);
            ITagType tagType = lang.getTagType(tagName);
            if (tagType == null)
                throw new IllegalUsageException("Undefined Tag: " + tagName);
            Object cont = tagMap.get(tagName);
            Object tagValue = tagType.parseJavadoc(cont, tagValueString);
            if (cont == null)
                tagMap.put(tagName, tagValue);
        }
    }

}
