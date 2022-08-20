package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.codegen.IFileInfo;
import net.bodz.bas.codegen.JavaOrXmlSourceBuilder;
import net.bodz.bas.codegen.MutableFileInfo;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public abstract class JavaGenFileType
        extends JavaOrXmlSourceBuilder<ITableViewMetadata> {

    static final Logger logger = LoggerFactory.getLogger(JavaGenFileType.class);

    protected static final String JAVA = "java";
    protected static final String XML = "xml";

    public final JavaGenProject project;
    protected final MiscTemplates templates;
    private final ClassPathInfo pathInfo;

    public JavaGenFileType(JavaGenProject project, ClassPathInfo pathInfo) {
        this.project = project;
        this.templates = new MiscTemplates(project);
        this.pathInfo = pathInfo;
    }

    protected abstract String getExtension();

    @Override
    protected IFileInfo getFileInfo(ITableViewMetadata model) {
        String localPath = pathInfo.getPath(getExtension());
        return new MutableFileInfo(pathInfo.getBaseDir(), localPath);
    }

    @Override
    protected QualifiedName getClassName(ITableViewMetadata model) {
        return pathInfo;
    }

}
