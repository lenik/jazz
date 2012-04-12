package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.MethodSignature;

public class MethodDoc
        extends ElementDoc {

    final ClassDoc classDoc;
    final MethodSignature signature;

    public MethodDoc(ClassDoc classDoc, MethodSignature signature) {
        // super(signature.getMethodId(null));
        super(signature.getMethodName());
        this.classDoc = classDoc;
        this.signature = signature;
    }

    public ClassDoc getClassDoc() {
        return classDoc;
    }

    public MethodSignature getSignature() {
        return signature;
    }

    /**
     * Get the return doc.
     * 
     * @return <code>null</code> if return doc isn't existed.
     */
    public DomainString getReturnDoc() {
        return getTag("return", DomainString.class);
    }

    /**
     * Set the return doc.
     */
    public void setReturnDoc(DomainString returnDoc) {
        setTag("return", returnDoc);
    }

    /**
     * Get the docs of parameters.
     * 
     * @return Non-<code>null</code> map (e.g. {@link LinkedHashMap}) of parameters.
     * 
     *         The map is order-preserved, so that the first parameter is in the first iteration.
     */
    public Map<String, DomainString> getParamDocs() {
        Map<String, DomainString> map = getTag("param", Map.class);
        if (map == null) {
            map = new LinkedHashMap<String, DomainString>();
            setTag("param", map);
        }
        return map;
    }

    public void setParamDocs(Map<String, DomainString> paramDocs) {
        if (paramDocs == null)
            throw new NullPointerException("paramDocs");
        setTag("param", paramDocs);
    }

    public DomainString getParamDoc(String paramName) {
        return getParamDocs().get(paramName);
    }

    public void setParamDoc(String paramName, DomainString paramDoc) {
        if (paramName == null)
            throw new NullPointerException("paramName");
        if (paramDoc == null)
            throw new NullPointerException("paramDoc");
        getParamDocs().put(paramName, paramDoc);
    }

    public final void addParamDoc(String paramName, DomainString paramDoc) {
        setParamDoc(paramName, paramDoc);
    }

    public Map<String, DomainString> getExceptionDocs() {
        Map<String, DomainString> map = getTag("exception", Map.class);
        if (map == null) {
            map = new LinkedHashMap<String, DomainString>();
            setTag("exception", map);
        }
        return map;
    }

    public void setExceptionDocs(Map<String, DomainString> exceptionDocs) {
        if (exceptionDocs == null)
            throw new NullPointerException("exceptionDocs");
        setTag("exception", Map.class);
    }

    public DomainString getExceptionDoc(String exceptionType) {
        return getExceptionDocs().get(exceptionType);
    }

    public void setExceptionDoc(String exceptionType, DomainString exceptionDoc) {
        if (exceptionType == null)
            throw new NullPointerException("exceptionType");
        if (exceptionDoc == null)
            throw new NullPointerException("exceptionDoc");
        getExceptionDocs().put(exceptionType, exceptionDoc);
    }

    @Override
    public void writeObject(IFlatfOutput out)
            throws IOException {

        // TypeNameContext typeNameContext = classDoc.getTypeNameContext();

        String methodId = getMethodId();
        out.sectionBegin("method:" + methodId);
        super.writeObject(out);
        out.sectionEnd();
    }

}
