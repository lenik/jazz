package net.bodz.bas.esm;

import java.util.Objects;

import net.bodz.bas.err.IllegalUsageException;

public class EsmSource
        extends EsmScope {

    public final EsmModule module;
    public final String path;

    public final EsmName __bare = new EsmName(this, null, null, false);

    public EsmSource(EsmModule module, String path) {
        this(module, path, 0);
    }

    public EsmSource(EsmModule module, String path, int priority) {
        super(priority);

        if (module == null)
            throw new NullPointerException("module");

        this.module = module;
        this.path = path;
    }

    public EsmSource(EsmModule module, int priority) {
        super(priority);

        if (module == null)
            throw new NullPointerException("module");

        SourcePath aPath = getClass().getAnnotation(SourcePath.class);
        if (aPath == null)
            throw new IllegalUsageException();

        this.module = module;
        this.path = aPath.value();
    }

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.SOURCE;
    }

    public EsmName defaultExport(String alias) {
        if (alias == null)
            throw new NullPointerException("alias");
        return new EsmName(this, null, alias, false);
    }

    public EsmName name(String name) {
        if (name == null)
            throw new NullPointerException("name");
        return new EsmName(this, name);
    }

    public EsmName name(String name, String alias) {
        if (name == null)
            throw new NullPointerException("name");
        if (alias == null)
            throw new NullPointerException("alias");
        return new EsmName(this, name, alias);
    }

    public EsmName typeName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        return new EsmName(this, name, null, true);
    }

    public EsmName typeName(String name, String alias) {
        if (name == null)
            throw new NullPointerException("name");
        if (alias == null)
            throw new NullPointerException("alias");
        return new EsmName(this, name, alias, true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, path);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EsmSource other = (EsmSource) obj;
        return Objects.equals(module, other.module) && Objects.equals(path, other.path);
    }

    public String getFullPath() {
        String fullPath = module.name;
        if (this.path != null)
            fullPath += "/" + this.path;
        return fullPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (module != null)
            sb.append(module);

        if (path != null)
            sb.append(" / ");
        sb.append(path);

        return sb.toString();
    }

}
