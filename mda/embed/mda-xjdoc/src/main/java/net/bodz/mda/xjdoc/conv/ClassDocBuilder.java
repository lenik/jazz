package net.bodz.mda.xjdoc.conv;

import static net.bodz.bas.rtx.Negotiation.list;
import static net.bodz.bas.rtx.Negotiation.option;

import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.JavaElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.tagtype.ITagType;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

import com.thoughtworks.qdox.model.*;

/**
 * Build class documents from the qdox parsed java sources.
 */
public class ClassDocBuilder {

    ITagLibrary taglib;
    iString missingDoc;

    public ClassDocBuilder(ITagLibrary taglib) {
        if (taglib == null)
            throw new NullPointerException("taglib");
        // if (sourceFileImports == null)
        // throw new NullPointerException("sourceFileImports");
        this.taglib = taglib;
        // this.sourceFileImports = sourceFileImports;
    }

    public iString getMissingDoc() {
        return missingDoc;
    }

    public void setMissingDoc(iString missingDoc) {
        this.missingDoc = missingDoc;
    }

    public ClassDoc buildClass(JavaClass javaClass) {
        String fqcn = javaClass.getFullyQualifiedName();
        ClassDoc classDoc = new ClassDoc(fqcn);

        ImportMap classImports = classDoc.getOrCreateImports();
        INegotiation negotiation = list(//
        option(ImportMap.class, classImports));

        populate(classDoc, javaClass, negotiation);

        for (JavaField javaField : javaClass.getFields()) {
            String fieldName = javaField.getName();
            FieldDoc fieldDoc = new FieldDoc(classDoc, fieldName);
            populate(fieldDoc, javaField, negotiation);
            classDoc.setFieldDoc(fieldName, fieldDoc);
        }

        /**
         * <pre>
         * 1. parameter types => import-map 
         * 2. javaMethod => method-doc 
         * 3. missing-doc => missing params in javadoc
         * 4. missing-doc => missing throws in javadoc
         * </pre>
         */
        for (JavaMethod javaMethod : javaClass.getMethods()) {
            Type[] types = javaMethod.getParameterTypes(true);
            MethodId methodId = new MethodId(javaMethod.getName(), types.length);
            for (int i = 0; i < types.length; i++) {
                Type paramType = types[i];
                String paramFqcn = paramType.getFullyQualifiedName();
                int paramDims = paramType.getDimensions();
                methodId.setParameterType(i, paramFqcn, paramDims);
                classImports.add(paramFqcn);
            }

            MethodDoc methodDoc = new MethodDoc(classDoc, methodId);
            populate(methodDoc, javaMethod, negotiation);

            for (JavaParameter jparam : javaMethod.getParameters()) {
                // javadoc may not include all the parameters.
                String paramName = jparam.getName();
                iString paramDoc = methodDoc.getParamDoc(paramName);
                if (paramDoc == null && missingDoc != null)
                    methodDoc.setParamDoc(paramName, missingDoc);
            }

            // This throws clause is defined in method prototype, but not in javadoc.
            for (Type exceptionType : javaMethod.getExceptions()) {
                // javadoc may not include all the exceptions.
                String exceptionFqcn = exceptionType.getFullyQualifiedName();
                // String simple = typeNameContext.importTypeName(exceptionFqcn);
                iString exceptionDoc = methodDoc.getExceptionDoc(exceptionFqcn);
                if (exceptionDoc == null && missingDoc != null)
                    methodDoc.setExceptionDoc(exceptionFqcn, missingDoc);
                classImports.add(exceptionFqcn);
            }

            classDoc.setMethodDoc(methodId, methodDoc);
        }
        return classDoc;
    }

    void populate(JavaElementDoc elementDoc, AbstractJavaEntity javaEntity, INegotiation negotiation) {
        String comment = javaEntity.getComment(); // maybe null if no javadoc.
        if (comment != null) {
            iString text = XiString.parseParaLangString(comment);
            elementDoc.setText(text);
        }

        Map<String, Object> tagMap = elementDoc.getTagMap();
        for (DocletTag docletTag : javaEntity.getTags()) {
            String tagName = docletTag.getName();
            String tagValueString = docletTag.getValue();

            // DomainString value = DomainString.parseParaLang(tagValueString);
            ITagType tagType = taglib.getTagType(tagName);
            if (tagType == null)
                throw new IllegalUsageException("Undefined Tag: " + tagName);
            Object cont = tagMap.get(tagName);
            Object tagValue;
            try {
                tagValue = tagType.parseJavadoc(cont, tagValueString, negotiation);
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            if (cont == null)
                tagMap.put(tagName, tagValue);
        }
    }

}
