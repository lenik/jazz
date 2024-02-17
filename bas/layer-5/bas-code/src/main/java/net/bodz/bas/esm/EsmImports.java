package net.bodz.bas.esm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.io.IPrintOut;

public class EsmImports
        extends TreeSet<EsmName> {

    private static final long serialVersionUID = 1L;

    Set<EsmModule> excludeModules = new HashSet<>();
    Set<EsmSource> excludeSources = new HashSet<>();

    public EsmImports(EsmSource source) {
        this(source, EsmNameComparator.INSTANCE);
    }

    public EsmImports(EsmSource source, Comparator<EsmName> order) {
        super(order);
        excludeSources.add(source);
    }

    public static EsmImports forLocal(QualifiedName qName) {
        return forLocal(qName, null);
    }

    public static EsmImports forLocal(QualifiedName qName, String extension) {
        String localPath = qName.getLocalPath(extension);
        EsmSource source = EsmModule.local(0).source(localPath);
        return new EsmImports(source);
    }

    public void excludeModule(EsmModule module) {
        excludeModules.add(module);
    }

    public void excludeSource(EsmSource source) {
        excludeSources.add(source);
    }

    public String name(EsmName name) {
        if (name == null)
            throw new NullPointerException("name");
        add(name);
        if (name.alias != null)
            return name.alias;
        if (name.name != null)
            return name.name;
        return "";
    }

    public int dump(IPrintOut out) {
        int lines = 0;
        ImportStatement buf = new ImportStatement();
        for (EsmName name : this) {
            lines += buf.printOutTypeScriptForNewFrom(out, name.source);
            if (name.isDefaultExport())
                buf.defaultAlias = name.alias;
            else if (name.type)
                buf.typeNames.add(name);
            else
                buf.names.add(name);
        }
        lines += buf.printOutTypeScriptForNewFrom(out, null);
        return lines;
    }

}
