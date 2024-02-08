package net.bodz.bas.esm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EsmModule
        extends EsmScope {

    public final String name;

    public final EsmSource main = new EsmSource(this, null);

    public EsmModule(String name) {
        this(name, 0);
    }

    public EsmModule(String name, int priority) {
        super(priority);
        this.name = name;
    }

    public static EsmModule local(int priority) {
        return new EsmModule(null, priority);
    }

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.MODULE;
    }

    Map<String, EsmSource> map = new HashMap<>();

    public <S extends EsmSource> S add(S source) {
        map.put(source.path, source);
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
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EsmModule other = (EsmModule) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
