package net.bodz.bas.esm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.tuple.QualifiedName;

public class EsmImports {

    final Set<EsmName> names;

    Set<EsmModule> excludeModules = new HashSet<>();
    Set<EsmSource> excludeSources = new HashSet<>();

    public EsmImports(EsmSource source) {
        this(source, EsmNameComparator.INSTANCE);
    }

    public EsmImports(EsmSource source, Comparator<EsmName> order) {
        names = new TreeSet<>(order);
        if (source != null)
            excludeSources.add(source);
    }

    public void exclude(EsmModule module) {
        if (module == null)
            throw new NullPointerException("module");
        excludeModules.add(module);
    }

    public void exclude(EsmSource source) {
        if (source == null)
            throw new NullPointerException("source");
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

        boolean excluded = false;
        excluded |= excludeSources.contains(name.source);

        if (name.source.isRelativePath()) {
            for (EsmSource exclude : excludeSources)
                if (exclude.relativeEquals(name.source)) {
                    excluded = true;
                    break;
                }
        }

        if (name.source.module != null)
            excluded |= excludeModules.contains(name.source.module);

        if (! excluded)
            names.add(name);

        if (name.alias != null)
            return name.alias;
        if (name.name != null)
            return name.name;
        return "";
    }

    public int dump(IPrintOut out, Boolean vue) {
        int lines = 0;
        ImportStatement buf = new ImportStatement();
        for (EsmName name : names) {
            if (vue != null)
                if (name.source.isVue() != vue)
                    continue;

            lines += buf.printOutTypeScriptForNewFrom(out, name.source);
            buf.addName(name);
        }
        lines += buf.printOutTypeScriptForNewFrom(out, null);
        return lines;
    }

    @Override
    public String toString() {
        return names.toString();
    }

}
