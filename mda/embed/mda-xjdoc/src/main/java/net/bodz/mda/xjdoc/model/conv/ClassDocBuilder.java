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
import net.bodz.mda.xjdoc.util.MethodSignature;
import net.bodz.mda.xjdoc.util.TypeNameContext;

import com.thoughtworks.qdox.model.AbstractJavaEntity;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;

/**
 * Build class documents from the qdox parsed java sources.
 */
public class ClassDocBuilder {

    IXjLanguage lang;
    TypeNameContext sharedContext;

    public ClassDocBuilder(IXjLanguage lang, TypeNameContext sharedContext) {
        if (lang == null)
            throw new NullPointerException("lang");
        this.lang = lang;
        this.sharedContext = sharedContext;
    }

    public ClassDoc buildClass(JavaClass javaClass) {
        String fqcn = javaClass.getFullyQualifiedName();

        String packageName;
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot == -1)
            packageName = "";
        else
            packageName = fqcn.substring(0, lastDot);
        TypeNameContext typeNameContext = new TypeNameContext(packageName, sharedContext);

        ClassDoc classDoc = new ClassDoc(fqcn);
        populate(classDoc, javaClass);

        classDoc.setTypeNameContext(typeNameContext);

        for (JavaField javaField : javaClass.getFields()) {
            String fieldName = javaField.getName();
            FieldDoc fieldDoc = new FieldDoc(classDoc, fieldName);
            populate(fieldDoc, javaField);
            classDoc.setFieldDoc(fieldName, fieldDoc);
        }

        for (JavaMethod javaMethod : javaClass.getMethods()) {
            Type[] types = javaMethod.getParameterTypes(true);
            MethodSignature signature = new MethodSignature(javaMethod.getName(), types.length);
            for (int i = 0; i < types.length; i++) {
                Type type = types[i];
                signature.setParameterType(i, type.getFullyQualifiedName(), type.getDimensions());
                typeNameContext.importType(type);
            }

            MethodDoc methodDoc = new MethodDoc(classDoc, signature);
            classDoc.setMethodDoc(signature, methodDoc);

            populate(methodDoc, javaMethod);

            for (JavaParameter jparam : javaMethod.getParameters()) {
                // javadoc may not include all the parameters.
                String paramName = jparam.getName();
                DomainString paramDoc = methodDoc.getParamDoc(paramName);
                if (paramDoc == null)
                    methodDoc.setParamDoc(paramName, null/* "" */);
            }

            // This throws clause is defined in method prototype, but not in javadoc.
            for (Type exceptionType : javaMethod.getExceptions()) {
                // javadoc may not include all the exceptions.
                String exceptionFqcn = exceptionType.getFullyQualifiedName();
                // String simple = typeNameContext.importTypeName(exceptionFqcn);
                DomainString exceptionDoc = methodDoc.getExceptionDoc(exceptionFqcn);
                if (exceptionDoc == null)
                    methodDoc.setExceptionDoc(exceptionFqcn, null/* "" */);
            }
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
