package net.bodz.bas.esm;

import net.bodz.bas.codegen.IImportNaming;
import net.bodz.bas.codegen.QualifiedName;
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

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig, EsmPackageMap packageMap) {
        this(qName, _orig, null, null);
    }

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig, EsmImports imports, EsmPackageMap packageMap) {
        super(_orig);

        if (imports == null)
            imports = EsmImports.forLocal(qName);
        this.im = imports;

        if (packageMap == null)
            packageMap = new EsmPackageMap();
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
        EsmName esmName = packageMap.findSource(qName, null, contextQName)._class(qName.name);
        return name(esmName);
    }

    public String importVue(QualifiedName qName) {
        EsmName alias = packageMap.findSource(qName, "vue", contextQName).defaultExport(qName.name);
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
