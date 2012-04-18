package net.bodz.mda.xjdoc.conv;

import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.mda.xjdoc.meta.ITagType;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.ElementDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodSignature;

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
    ImportMap sourceFileImports;

    boolean createClassImports;
    DomainString missingDoc = DEFAULT_MISSING_DOC;

    static final DomainString DEFAULT_MISSING_DOC;
    static {
        DEFAULT_MISSING_DOC = DomainString.parseMultiLangString(//
                "\"(No document)\"" + //
                        "zh-cn \"(无文档)\"");
    }

    public ClassDocBuilder(IXjLanguage lang, ImportMap sourceFileImports) {
        if (lang == null)
            throw new NullPointerException("lang");
        if (sourceFileImports == null)
            throw new NullPointerException("sourceFileImports");
        this.lang = lang;
        this.sourceFileImports = sourceFileImports;
    }

    public boolean isCreateClassImports() {
        return createClassImports;
    }

    public void setCreateClassImports(boolean createClassImports) {
        this.createClassImports = createClassImports;
    }

    public DomainString getMissingDoc() {
        return missingDoc;
    }

    public void setMissingDoc(DomainString missingDoc) {
        this.missingDoc = missingDoc;
    }

    public ClassDoc buildClass(JavaClass javaClass) {
        String fqcn = javaClass.getFullyQualifiedName();
        ClassDoc classDoc = new ClassDoc(fqcn);
        ImportMap classImports = createClassImports ? classDoc.getOrCreateImports() : null;

        populate(classDoc, javaClass);

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
                Type paramType = types[i];
                String paramFqcn = paramType.getFullyQualifiedName();
                int paramDims = paramType.getDimensions();
                signature.setParameterType(i, paramFqcn, paramDims);
                if (createClassImports)
                    classImports.add(paramFqcn);
            }

            MethodDoc methodDoc = new MethodDoc(classDoc, signature);
            populate(methodDoc, javaMethod);

            for (JavaParameter jparam : javaMethod.getParameters()) {
                // javadoc may not include all the parameters.
                String paramName = jparam.getName();
                DomainString paramDoc = methodDoc.getParamDoc(paramName);
                if (paramDoc == null && missingDoc != null)
                    methodDoc.setParamDoc(paramName, missingDoc);
            }

            // This throws clause is defined in method prototype, but not in javadoc.
            for (Type exceptionType : javaMethod.getExceptions()) {
                // javadoc may not include all the exceptions.
                String exceptionFqcn = exceptionType.getFullyQualifiedName();
                // String simple = typeNameContext.importTypeName(exceptionFqcn);
                DomainString exceptionDoc = methodDoc.getExceptionDoc(exceptionFqcn);
                if (exceptionDoc == null && missingDoc != null)
                    methodDoc.setExceptionDoc(exceptionFqcn, missingDoc);
                if (createClassImports)
                    classImports.add(exceptionFqcn);
            }

            classDoc.setMethodDoc(signature, methodDoc);
        }
        return classDoc;
    }

    void populate(ElementDoc elementDoc, AbstractJavaEntity javaEntity) {
        String comment = javaEntity.getComment(); // maybe null if no javadoc.
        if (comment != null) {
            DomainString text = DomainString.parseParaLang(comment);
            elementDoc.setText(text);
        }

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
