package net.bodz.bas.esm;

import java.util.Objects;

import net.bodz.bas.err.IllegalUsageException;

public class EsmSource
        extends EsmScope {

    public final EsmModule module;
    public final String localPath;

    public final EsmName __bare = new EsmName(this, null, null, false);

    public EsmSource(EsmModule module, String path) {
        this(module, path, 0);
    }

    public EsmSource(EsmModule module, String path, int priority) {
        super(priority);

        if (module == null)
            throw new NullPointerException("module");

        this.module = module;
        this.localPath = path;
//        this.fullPath = localPath == null ? null : module.join(localPath);
    }

    public EsmSource(EsmModule module, int priority) {
        super(priority);

        if (module == null)
            throw new NullPointerException("module");

        SourcePath aPath = getClass().getAnnotation(SourcePath.class);
        if (aPath == null)
            throw new IllegalUsageException();

        this.module = module;
        this.localPath = aPath.value();
//        this.fullPath = module.join(localPath);
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

    public EsmName type(String name) {
        if (name == null)
            throw new NullPointerException("name");
        return new EsmName(this, name, null, true);
    }

    public EsmName _interface(String name) {
        if (name == null)
            throw new NullPointerException("name");
        return new EsmName(this, name, null, true);
    }

    public EsmName _class(String name) {
        if (name == null)
            throw new NullPointerException("name");
        return new EsmName(this, name, null, true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, localPath);
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
        return Objects.equals(module, other.module) && Objects.equals(localPath, other.localPath);
    }

    public String getFullPath() {
        if (module.name == null) {
            if (localPath == null)
                throw new IllegalUsageException("either module or path must be specified.");
            return localPath;
        }

        if (localPath == null)
            // import the main file specified in the package.json.
            return module.basePath;

        return module.basePath + "/" + localPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (module != null)
            sb.append(module);

        if (localPath != null)
            sb.append(" :: ");
        sb.append(localPath);

        return sb.toString();
    }

}
