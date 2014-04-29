package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public class MethodDoc
        extends MutableElementDoc {

    final ClassDoc classDoc;
    final MethodId methodId;

    public MethodDoc(ClassDoc classDoc, MethodId methodId) {
        // super(signature.getMethodId(null));
        super(methodId.getMethodName());
        this.classDoc = classDoc;
        this.methodId = methodId;
    }

    public static MethodDoc n_a(ClassDoc classDoc, MethodId methodId) {
        MethodDoc methodDoc = new MethodDoc(classDoc, methodId);
        methodDoc.setLabel(iString.fn.val("no label"));
        methodDoc.setText(iString.fn.val(methodId));
        return methodDoc;
    }

    public ClassDoc getClassDoc() {
        return classDoc;
    }

    public MethodId getMethodId() {
        return methodId;
    }

    /**
     * Get the return doc.
     *
     * @return <code>null</code> if return doc isn't existed.
     */
    public iString getReturnDoc() {
        return getTag("return", iString.class);
    }

    /**
     * Set the return doc.
     */
    public void setReturnDoc(iString returnDoc) {
        setTag("return", returnDoc);
    }

    /**
     * Get the docs of parameters.
     *
     * @return Non-<code>null</code> map (e.g. {@link LinkedHashMap}) of parameters.
     *
     *         The map is order-preserved, so that the first parameter is in the first iteration.
     */
    public Map<String, iString> getParamDocs() {
        Map<String, iString> map = getTag("param", Map.class);
        if (map == null) {
            map = new LinkedHashMap<String, iString>();
            setTag("param", map);
        }
        return map;
    }

    public void setParamDocs(Map<String, iString> paramDocs) {
        if (paramDocs == null)
            throw new NullPointerException("paramDocs");
        setTag("param", paramDocs);
    }

    public iString getParamDoc(String paramName) {
        return getParamDocs().get(paramName);
    }

    public void setParamDoc(String paramName, iString paramDoc) {
        if (paramName == null)
            throw new NullPointerException("paramName");
        if (paramDoc == null)
            throw new NullPointerException("paramDoc");
        getParamDocs().put(paramName, paramDoc);
    }

    public final void addParamDoc(String paramName, iString paramDoc) {
        setParamDoc(paramName, paramDoc);
    }

    public Map<String, iString> getExceptionDocs() {
        Map<String, iString> map = getTag("exception", Map.class);
        if (map == null) {
            map = new LinkedHashMap<String, iString>();
            setTag("exception", map);
        }
        return map;
    }

    public void setExceptionDocs(Map<String, iString> exceptionDocs) {
        if (exceptionDocs == null)
            throw new NullPointerException("exceptionDocs");
        setTag("exception", Map.class);
    }

    public iString getExceptionDoc(String exceptionType) {
        return getExceptionDocs().get(exceptionType);
    }

    public void setExceptionDoc(String exceptionType, iString exceptionDoc) {
        if (exceptionType == null)
            throw new NullPointerException("exceptionType");
        if (exceptionDoc == null)
            throw new NullPointerException("exceptionDoc");
        getExceptionDocs().put(exceptionType, exceptionDoc);
    }

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        ImportMap imports = getClassDoc().getOrCreateImports();
        String importedForm = methodId.getImportedForm(imports);
        out.beginSection("method:" + importedForm);
        super.writeObject(out, options);
        out.endSection();
    }

}
