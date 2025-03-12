package net.bodz.bas.esm;

import java.lang.reflect.Type;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.esm.util.TsTypeInfoResolver;
import net.bodz.bas.esm.util.TsTypeResolver;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.tuple.QualifiedName;

public class TypeScriptWriter
        extends DecoratedTreeOut
        implements ITsImporter {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(TypeScriptWriter.class);

    public final EsmImports im;
    public final EsmDomainMap domainMap;
    final QualifiedName contextQName;

    public final TsTypeResolver typeResolver;
    public final TsTypeInfoResolver typeInfoResolver;

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig) {
        this(qName, _orig, EsmImports.forLocal(qName), new EsmDomainMap());
    }

    public TypeScriptWriter(QualifiedName qName, ITreeOut _orig, EsmImports imports, EsmDomainMap domainMap) {
        super(_orig);

        if (qName == null)
            throw new NullPointerException("qName");
        if (imports == null)
            throw new NullPointerException("imports");
        if (domainMap == null)
            throw new NullPointerException("domainMap");

        this.im = imports;
        this.domainMap = domainMap;

        this.contextQName = qName;

        this.typeResolver = new TsTypeResolver(this);
        this.typeInfoResolver = new TsTypeInfoResolver(this);
    }

    public String name(EsmName name) {
        return im.name(name);
    }

    @Override
    public EsmSource findSource(QualifiedName qName) {
        return findSource(qName, null);
    }

    @Override
    public EsmSource findSource(QualifiedName qName, String extension) {
        return domainMap.findSource(qName, extension, contextQName);
    }

    @Override
    public EsmName resolveName(QualifiedName qName, String alias, boolean type) {
        EsmSource source = findSource(qName, null);
        if (source == null) // reserved name, don't import.
            return null;

        if (alias == null)
            alias = qName.name;

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
        TypeScriptWriter bufOut = new TypeScriptWriter(contextQName, buf.indented(), im, domainMap);
        return bufOut;
    }

}
