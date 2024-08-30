package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.tagtype.HeadedTextTag;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.MethodId;

public class MethodDoc
        extends MutableElementDoc {

    final IClassDoc classDoc;
    final MethodId methodId;

    public MethodDoc(IClassDoc classDoc, MethodId methodId) {
        super(classDoc.getTagLibrary());
        this.classDoc = classDoc;
        this.methodId = methodId;
    }

    static iString NO_LABEL = null; // iString.fn.val("no label");

    public static MethodDoc n_a(IClassDoc classDoc, MethodId methodId) {
        MethodDoc methodDoc = new MethodDoc(classDoc, methodId);
        methodDoc.setTag(LABEL, StrFn.conv(methodId));
        return methodDoc;
    }

    public IClassDoc getClassDoc() {
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
        return getTag("return").getText();
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
    public IContainer<iString> getParamDocs() {
        HeadedTextTag paramTag = makeTag("param");
        return paramTag.getContainer();
    }

    public void setParamDocs(IContainer<iString> paramDocs) {
        if (paramDocs == null)
            throw new NullPointerException("paramDocs");
        HeadedTextTag tag = makeTag("param");
        tag.setData(paramDocs);
    }

    public iString getParamDoc(String paramName) {
        return getParamDocs().get(paramName);
    }

    public void setParamDoc(String paramName, iString paramDoc) {
        if (paramName == null)
            throw new NullPointerException("paramName");
        if (paramDoc == null)
            throw new NullPointerException("paramDoc");
        getParamDocs().set(paramName, paramDoc);
    }

    public final void addParamDoc(String paramName, iString paramDoc) {
        setParamDoc(paramName, paramDoc);
    }

    public IContainer<iString> getExceptionDocs() {
        HeadedTextTag exceptionTag = makeTag("exception");
        return exceptionTag.getContainer();
    }

    public void setExceptionDocs(IContainer<iString> exceptionDocs) {
        if (exceptionDocs == null)
            throw new NullPointerException("exceptionDocs");
        HeadedTextTag tag = makeTag("exception");
        tag.setData(exceptionDocs);
    }

    public iString getExceptionDoc(String exceptionType) {
        return getExceptionDocs().get(exceptionType);
    }

    public void setExceptionDoc(String exceptionType, iString exceptionDoc) {
        if (exceptionType == null)
            throw new NullPointerException("exceptionType");
        if (exceptionDoc == null)
            throw new NullPointerException("exceptionDoc");
        getExceptionDocs().addNamed(exceptionDoc, exceptionType);
    }

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException, FormatException {
        ImportMap imports = getClassDoc().getOrCreateImports();
        String importedForm = methodId.getImportedForm(imports);
        out.beginSection("method:" + importedForm);
        super.writeObject(out, options);
        out.endSection();
    }

}
