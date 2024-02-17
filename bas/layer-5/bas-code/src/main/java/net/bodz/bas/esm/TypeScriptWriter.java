package net.bodz.bas.esm;

import net.bodz.bas.codegen.IImportNaming;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;

public class TypeScriptWriter
        extends DecoratedTreeOut
        implements
            IImportNaming {

    private static final long serialVersionUID = 1L;

    public final EsmImports im;
    public final EsmPackageMap packageMap;
    final QualifiedName contextQName;

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig) {
        this(qName, _orig, EsmImports.forLocal(qName), new EsmPackageMap());
    }

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig, EsmImports imports, EsmPackageMap packageMap) {
        super(_orig);

        if (qName == null)
            throw new NullPointerException("qName");
        if (imports == null)
            throw new NullPointerException("imports");
        if (packageMap == null)
            throw new NullPointerException("packageMap");

        this.im = imports;
        this.packageMap = packageMap;

        this.contextQName = qName;
    }

    public String name(EsmName name) {
        return im.name(name);
    }

    @Override
    public String importName(String qName) {
        if (qName == null)
            throw new NullPointerException("qName");
        return importName(QualifiedName.parse(qName));
    }

    @Override
    public String importName(QualifiedName qName) {
        EsmSource source = packageMap.findSource(qName, null, contextQName);
        if (source == null) // reserved name, don't import.
            return qName.getFullName();
        EsmName esmName = source._class(qName.name);
        return name(esmName);
    }

    public String importVue(QualifiedName qName) {
        EsmSource source = packageMap.findSource(qName, "vue", contextQName);
        if (source == null)
            throw new IllegalUsageException("vue name can't be reserved word: " + qName);
        EsmName alias = source.defaultExport(qName.name);
        return name(alias);
    }

    public TypeScriptWriter buffer() {
        BCharOut buf = new BCharOut();
        TypeScriptWriter bufOut = new TypeScriptWriter(contextQName, buf.indented(), im, packageMap);
        return bufOut;
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

}
