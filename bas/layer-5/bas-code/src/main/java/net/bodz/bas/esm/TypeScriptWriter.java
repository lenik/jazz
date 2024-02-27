package net.bodz.bas.esm;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.tuple.QualifiedName;

public class TypeScriptWriter
        extends DecoratedTreeOut
        implements
            IImportTsNaming {

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

    public EsmSource findSource(QualifiedName qName, String extension) {
        return packageMap.findSource(qName, extension, contextQName);
    }

    EsmName resolveName(QualifiedName qName, String alias, boolean type) {
        EsmSource source = findSource(qName, null);
        if (source == null) // reserved name, don't import.
            return null;
        EsmName esmName;
        if (type)
            esmName = source.defaultTypeExport(alias);
        else
            esmName = source.defaultExport(alias);
        return esmName;
    }

    @Override
    public String importDefaultAs(QualifiedName qName, String alias) {
        EsmName esmName = resolveName(qName, alias, false);
        if (esmName == null)
            return qName.getFullName();
        return name(esmName);
    }

    @Override
    public String importDefaultTypeAs(QualifiedName qName, String alias) {
        EsmName esmTypeName = resolveName(qName, alias, true);
        if (esmTypeName == null)
            return qName.getFullName();
        return name(esmTypeName);
    }

    @Override
    public String importName(EsmName name) {
        return name(name);
    }

    @Override
    public String importName(QualifiedName qName) {
        EsmSource source = findSource(qName, null);
        if (source == null) // reserved name, don't import.
            return qName.getFullName();
        EsmName esmName = source._class(qName.name);
        return name(esmName);
    }

    public String importVue(QualifiedName qName) {
        EsmSource source = findSource(qName, "vue");
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
