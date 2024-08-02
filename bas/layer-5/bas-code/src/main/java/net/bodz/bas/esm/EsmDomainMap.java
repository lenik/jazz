package net.bodz.bas.esm;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.tuple.QualifiedName;

public class EsmDomainMap
        extends DomainMap<EsmModule> {

    EsmModule contextModule;
    int localPriority;

    EsmDomainMap() {
    }

    public static class Builder {

        EsmModule contextModule;
        Integer localPriority;

        public Builder contextModule(EsmModule contextModule) {
            this.contextModule = contextModule;
            return this;
        }

        public Builder localPriority(int localPriority) {
            this.localPriority = localPriority;
            return this;
        }

        public EsmDomainMap build() {
            EsmDomainMap o = new EsmDomainMap();
            if (this.contextModule != null)
                o.contextModule = this.contextModule;
            if (this.localPriority != null)
                o.localPriority = this.localPriority;
            return o;
        }

    }

    public EsmSource findSource(QualifiedName qName, String extension, QualifiedName context) {
        // don't import bare name.
        if (qName.packageName == null)
            return null;

        String path = qName.getLocalPath(extension);
        if (path == null)
            throw new IllegalUsageException("null path of qName");

        EsmModule module = find(qName);
        if (module != null && ! Nullables.equals(contextModule, module)) {
            return module.source(path);
        }

        String contextPath = context.getLocalPath();
        String href = FilePath.getRelativePath(path, contextPath);
        if (! (href.startsWith("../") || href.startsWith("./")))
            href = "./" + href;

        EsmSource source = EsmModule.local(localPriority).source(href);
        return source;
    }

}
