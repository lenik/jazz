package net.bodz.mda.xjdoc.conv;

import java.util.List;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.ParaLangStrings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.ListOptions;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.IDocTag;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.model.AbstractElementDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaAnnotatedElement;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;

/**
 * Build class documents from the qdox parsed java sources.
 */
public class ClassDocBuilder {

    private final ITagLibrary tagLibrary;
    private iString missingDoc;

    public ClassDocBuilder(ITagLibrary tagLibrary) {
        if (tagLibrary == null)
            throw new NullPointerException("tagLibrary");
        // if (sourceFileImports == null)
        // throw new NullPointerException("sourceFileImports");
        this.tagLibrary = tagLibrary;
        // this.sourceFileImports = sourceFileImports;
    }

    public iString getMissingDoc() {
        return missingDoc;
    }

    public void setMissingDoc(iString missingDoc) {
        this.missingDoc = missingDoc;
    }

    public ClassDoc buildClass(JavaClass javaClass)
            throws ParseException {
        String fqcn = javaClass.getFullyQualifiedName();
        ClassDoc classDoc = new ClassDoc(tagLibrary, fqcn);

        ImportMap classImports = classDoc.getOrCreateImports();
        IOptions options = new ListOptions() //
                .addOption(ImportMap.class, classImports);

        populate(classDoc, javaClass, javaClass.getName(), options);

        for (JavaField javaField : javaClass.getFields()) {
            String fieldName = javaField.getName();
            FieldDoc fieldDoc = new FieldDoc(classDoc, fieldName);
            populate(fieldDoc, javaField, fieldName, options);
            classDoc.setFieldDoc(fieldName, fieldDoc);
        }

        /*
         * <pre>
         * 1. parameter types => import-map
         * 2. javaMethod => method-doc
         * 3. missing-doc => missing params in javadoc
         * 4. missing-doc => missing throws in javadoc
         * </pre>
         */
        for (JavaMethod javaMethod : javaClass.getMethods()) {
            List<JavaType> types = javaMethod.getParameterTypes(true);
            MethodId methodId = new MethodId(javaMethod.getName(), types.size());
            for (int i = 0; i < types.size(); i++) {
                JavaType paramType = types.get(i);
                String paramFqcn;
                try {
                    paramFqcn = paramType.getFullyQualifiedName();
                } catch (Exception e) {
                    System.err.println("Failed to get fully qualified name from " + paramType);
                    throw e;
                }
                int paramDims = 0;
                if (paramType instanceof JavaClass) {
                    JavaClass jclass = (JavaClass) paramType;
                    paramDims = jclass.getDimensions();
                }
                methodId.setParameterType(i, paramFqcn, paramDims);
                classImports.add(paramFqcn);
            }

            MethodDoc methodDoc = new MethodDoc(classDoc, methodId);
            populate(methodDoc, javaMethod, javaMethod.getName(), options);

            for (JavaParameter jparam : javaMethod.getParameters()) {
                // javadoc may not include all the parameters.
                String paramName = jparam.getName();
                iString paramDoc = methodDoc.getParamDoc(paramName);
                if (paramDoc == null && missingDoc != null)
                    methodDoc.setParamDoc(paramName, missingDoc);
            }

            // This throws clause is defined in method prototype, but not in javadoc.
            for (JavaClass exceptionType : javaMethod.getExceptions()) {
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

    <J extends JavaAnnotatedElement> //
    void populate(AbstractElementDoc elementDoc, //
            J javaEntity, String name, IOptions options)
            throws ParseException {
        String comment = javaEntity.getComment(); // maybe null if no javadoc.
        if (comment != null) {
            iString text = ParaLangStrings.parse(comment);
            elementDoc.setText(text);
        }

        for (DocletTag docletTag : javaEntity.getTags()) {
            String tagName = docletTag.getName();
            String tagValueString = docletTag.getValue();

            String rootTagName = tagLibrary.getRootTagName(tagName);
            String extension = null;

            if (rootTagName == null) {
                throw new IllegalUsageException(String.format(//
                        "Undefined tag @%s occurred in %s %s, taglib = %s", //
                        tagName, javaEntity.getClass().getSimpleName(), name, tagLibrary.getName()));
            } else if (tagName.startsWith(rootTagName)) {
                extension = tagName.substring(rootTagName.length());
                if (extension.startsWith("."))
                    extension = extension.substring(1);
                else
                    extension = null;
            }

            IDocTag<?> tag = elementDoc.makeTag(rootTagName);
//            if (javaEntity instanceof JavaClass) {
//                System.err.printf("type %s, name %s, tag %s :: %s  ==> %s\n", //
//                        javaEntity.getClass().getSimpleName(), //
//                        name, rootTagName, extension, tag.getClass().getSimpleName());
//            }

            tag.parseJavadoc(rootTagName, extension, tagValueString, options);
        }

//        if (javaEntity instanceof JavaClass)
//            System.err.printf("type %s, tags %s\n", name, elementDoc.getTagMap());
    }

}
