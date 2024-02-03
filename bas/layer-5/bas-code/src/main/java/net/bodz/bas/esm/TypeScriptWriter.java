package net.bodz.bas.esm;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;

public class TypeScriptWriter
        extends DecoratedTreeOut {

    private static final long serialVersionUID = 1L;

    public final EsmImports im;

    QualifiedName contextQName;

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig) {
        this(qName, _orig, null);
    }

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig, EsmImports imports) {
        super(_orig);

        if (imports == null) {
            String extension = null; // "ts";
            String localPath = qName.getLocalPath(extension);
            EsmSource source = EsmModules.local.source(localPath);
            imports = new EsmImports(source);
        }
        this.im = imports;

        this.contextQName = qName;
    }

    public String name(EsmName name) {
        return im.name(name);
    }

    EsmSource localSource(QualifiedName qName, String extension, QualifiedName context) {
        String path = qName.getLocalPath(extension);
        String contextPath = context.getLocalPath();
        String href = FilePath.getRelativePath(path, contextPath);
        if (! (href.startsWith("../") || href.startsWith("./")))
            href = "./" + href;

        EsmSource source;
        if ("vue".equals(extension))
            source = EsmModules.localVue.source(href);
        else
            source = EsmModules.local.source(href);
        return source;
    }

    public String localName(QualifiedName qName) {
        return localName(qName, contextQName);
    }

    public String localName(QualifiedName qName, QualifiedName context) {
        EsmName esmName = localSource(qName, null, context).name(qName.name);
        return name(esmName);
    }

    public String localVue(QualifiedName qName) {
        return localVue(qName, contextQName);
    }

    public String localVue(QualifiedName qName, QualifiedName context) {
        EsmName alias = localSource(qName, "vue", context).defaultExport(qName.name);
        return name(alias);
    }

}
