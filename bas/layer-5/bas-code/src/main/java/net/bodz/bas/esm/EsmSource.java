package net.bodz.bas.esm;

import java.util.Objects;

import net.bodz.bas.err.IllegalUsageException;

public class EsmSource
        extends EsmScope {

    public static final String PATH_BARE = null;

    public final EsmModule module;
    public final String localPath;

    public final EsmName __bare = new EsmName(this, PATH_BARE, null, false);

    public EsmSource(EsmModule module, String path) {
        this(module, path, 0);
    }

    public EsmSource(EsmModule module, String path, int priority) {
        super(priority);

        if (module == null)
            throw new NullPointerException("module");

        this.module = module;
        this.localPath = path;
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

    public String getLocalPath() {
        return localPath;
    }

    public EsmSource child(String childName) {
        if (childName == null)
            return null;
        String childPath = this.localPath == null ? childName : (this.localPath + "/" + childName);
        return new EsmSource(module, childPath, priority);
    }

    public boolean isBare() {
        return localPath == PATH_BARE;
    }

    public boolean isRelativePath() {
        if (isBare())
            return false;
        return localPath.startsWith("./") || localPath.startsWith("../");
    }

    public String getBaseName() {
        if (localPath == null)
            return null;
        int lastSlash = localPath.lastIndexOf('/');
        if (lastSlash == -1)
            return localPath;
        else
            return localPath.substring(lastSlash + 1);
    }

    public String getExtension() {
        String baseName = getBaseName();
        if (baseName == null)
            return null;
        int lastDot = baseName.lastIndexOf('.');
        if (lastDot == -1)
            return null;
        else
            return baseName.substring(lastDot + 1);
    }

    public boolean isVue() {
        return "vue".equals(getExtension());
    }

    public EsmName defaultExport(String alias) {
        if (alias == null)
            throw new NullPointerException("alias");
        return new EsmName(this, null, alias, false);
    }

    public EsmName defaultTypeExport(String alias) {
        if (alias == null)
            throw new NullPointerException("alias");
        return new EsmName(this, null, alias, true);
    }

    public EsmName wildcardAs(String alias) {
        return name(EsmName.ALL_NAMES, alias);
    }

    public EsmName name(String name) {
        return name(name, null);
    }

    public EsmName name(String name, String alias) {
        return name(name, alias, false);
    }

    public EsmName name(String name, String alias, boolean type) {
        if (name == null)
            throw new NullPointerException("name");
        EsmSource source = this;
        int lastSlash = name.lastIndexOf('/');
        if (lastSlash != -1) {
            String childSource = name.substring(0, lastSlash);
            name = name.substring(lastSlash + 1);
            source = source.child(childSource);
        }
        return new EsmName(source, name, alias, type);
    }

    public EsmName type(String name) {
        return name(name, null, true);
    }

    public EsmName value(String name) {
        return name(name, null, false);
    }

    public EsmName _interface(String name) {
        return value(name);
    }

    public EsmName _class(String name) {
        return value(name);
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
        EsmSource other = (EsmSource) obj;
        return Objects.equals(module, other.module) && Objects.equals(localPath, other.localPath);
    }

    public boolean relativeEquals(EsmSource o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        EsmSource other = o;
        if (! Objects.equals(module, other.module))
            return false;

        if (localPath == null)
            return other.localPath == null;

        if (other.isRelativePath())
            return PathUtils.matchRelative(localPath, other.localPath);
        else
            return Objects.equals(localPath, other.localPath);
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
