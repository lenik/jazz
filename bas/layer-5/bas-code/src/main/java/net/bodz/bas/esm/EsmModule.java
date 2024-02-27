package net.bodz.bas.esm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EsmModule
        extends EsmScope {

    public static final String LOCAL_NAME = null;

    public final String name;
    public final String baseDir;
    public final String basePath;

    public final EsmSource main = new EsmSource(this, null);

    Map<String, EsmSource> map = new HashMap<>();

    public EsmModule(String name) {
        this(name, null, 0);
    }

    public EsmModule(String name, int priority) {
        this(name, null, priority);
    }

    public EsmModule(String name, String baseDir) {
        this(name, baseDir, 0);
    }

    public EsmModule(String name, String baseDir, int priority) {
        super(priority);
        this.name = name;
        this.baseDir = baseDir;

        if (name == null)
            this.basePath = baseDir;
        else if (baseDir == null)
            this.basePath = name;
        else
            this.basePath = name + "/" + baseDir;
    }

    public static EsmModule local(int priority) {
        return new EsmModule(LOCAL_NAME, null, priority);
    }

    public static EsmModule local(String baseDir, int priority) {
        return new EsmModule(LOCAL_NAME, baseDir, priority);
    }

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.MODULE;
    }

    public String join(String path) {
        if (baseDir == null)
            return path;
        else
            return baseDir + "/" + path;
    }

    public <S extends EsmSource> S add(S source) {
        map.put(source.localPath, source);
        return source;
    }

    public EsmSource source(String path) {
        EsmSource source = map.get(path);
        if (source == null) {
            source = new EsmSource(this, path);
            // map.put(path, source);
        }
        return source;
    }

    public EsmName defaultClass(String path) {
        EsmSource source = source(path);

        int lastSlash = path.lastIndexOf("/");
        String base = lastSlash == -1 ? path : path.substring(lastSlash + 1);

        String name = base;
        EsmName esmName = source.defaultExport(name);
        return esmName;
    }

    public EsmName _class(String path) {
        EsmSource source = source(path);

        int lastSlash = path.lastIndexOf("/");
        String base = lastSlash == -1 ? path : path.substring(lastSlash + 1);

        String name = base.substring(0, base.length() - 4);
        EsmName esmName = source._class(name);
        return esmName;
    }

    public EsmName vue(String path) {
        EsmSource source = source(path);

        int lastSlash = path.lastIndexOf("/");
        String base = lastSlash == -1 ? path : path.substring(lastSlash + 1);
        if (! base.endsWith(".vue"))
            throw new IllegalArgumentException("path should have extension .vue");

        String name = base.substring(0, base.length() - 4);
        EsmName esmName = source.defaultExport(name);
        return esmName;
    }

    protected abstract class Source
            extends EsmSource {

        public Source(int priority) {
            super(EsmModule.this, priority);
        }

        public Source() {
            this(0);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseDir);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        EsmModule other = (EsmModule) obj;
        return Objects.equals(name, other.name) //
                && Objects.equals(baseDir, other.baseDir) //
        ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name == null)
            sb.append("<local>");
        else
            sb.append(name);
        if (baseDir != null) {
            sb.append("[");
            sb.append(baseDir);
            sb.append("]");
        }
        return sb.toString();
    }

}
