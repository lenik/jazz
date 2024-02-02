package net.bodz.bas.esm;

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

    @Override
    public EsmScopeType getType() {
        return EsmScopeType.MODULE;
    }

    public EsmSource source(String path) {
        return new EsmSource(this, path);
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
