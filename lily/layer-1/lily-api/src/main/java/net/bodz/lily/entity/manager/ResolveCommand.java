package net.bodz.lily.entity.manager;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.repr.content.StringContent;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.format.IDocxBuilder;
import net.bodz.lily.entity.format.IMarkdownBuilder;

@ForEntityType(IId.class)
public class ResolveCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "resolve";
    public static final String[] NAMES = { NAME, "load", "get" };

    public ResolveCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public IEntityCommandProcess createProcess(IEntityCommandContext context) {
        return new ResolveProcess(this, context);
    }

}

class ResolveProcess
        extends AbstractEntityCommandProcess<ResolveCommand> {

    JsonFormOptions jsonFormOptions;

    int format = JSON;
    static final int JSON = 0;
    static final int MARKDOWN = 1;
    static final int DOC = 2;
    static final int DOCX = 3;
    static final int PDF = 4;
    static final int OTHER = -1;

    IMarkdownBuilder markdownBuilder;
    IDocxBuilder docxBuilder;

    public ResolveProcess(ResolveCommand type, IEntityCommandContext context) {
        super(type, context);
    }

    @Override
    public void setContentPath(IPathFields path) {
        super.setContentPath(path);
        switch (path.getExtension("js")) {
        case "js":
        case "json":
            format = JSON;
            break;
        case "md":
        case "markdown":
        case "md-doc":
            format = MARKDOWN;
            break;
        case "doc":
            format = DOC;
            break;
        case "docx":
            format = DOCX;
            break;
        case "pdf":
            format = PDF;
            break;
        default:
            format = OTHER;
        }
    }

    @Override
    public void setQueryContext(IQueryable context) {
        IMarkdownBuilder markdownBuilder = context.query(IMarkdownBuilder.class);
        if (markdownBuilder != null)
            this.markdownBuilder = markdownBuilder;

        IDocxBuilder docBuilder = context.query(IDocxBuilder.class);
        if (docBuilder != null)
            this.docxBuilder = docBuilder;
    }

    @Override
    protected Boolean isReturningJsonResult() {
        return false;
    }

    @Override
    public Object execute()
            throws Exception {
        if (resolvedEntity.entity instanceof IVarMapForm) {
            IVarMapForm varMapForm = (IVarMapForm) resolvedEntity.entity;
            varMapForm.readObject(parameters);
        }

        JsonWrapper data = JsonWrapper.wrap(resolvedEntity, "data");
        data.setOptions(jsonFormOptions);

        switch (format) {
        case JSON:
            return data;

        case MARKDOWN:
            if (markdownBuilder == null)
                throw new NullPointerException("markdownBuilder");
            String markdown = markdownBuilder.buildMarkdown(resolvedEntity, parameters);
            boolean md = "md".equals(getContentPath().getExtension());
            if (md) {
                String fileName = getContentPath().getFileName();
                Charset charset = Charsets.UTF_8;
                byte[] bin = markdown.getBytes(charset);
                FileContent fileContent = new FileContent(fileName, ContentTypes.text_plain, bin);
                fileContent.setEncoding(charset.name());
                return fileContent;
            }
            return new StringContent(ContentTypes.text_plain, markdown);

        case DOC:
        case DOCX:
            if (docxBuilder == null)
                throw new IllegalUsageException("doc builder isn't set.");
            WordprocessingMLPackage pack = docxBuilder.buildDocx(resolvedEntity, parameters);
            ByteArrayOutputStream docxBuf = new ByteArrayOutputStream(30000);
            pack.save(docxBuf);

            String fileName = getContentPath().getFileName();
            String title = pack.getTitle();
            if (title != null && !title.trim().isEmpty()) {
                String extension = getContentPath().getExtension();
                fileName = String.format("%s.%s", title.trim(), extension);
            }
            return new FileContent(fileName, ContentTypes.application_vnd_ms_excel, docxBuf.toByteArray());

        case PDF:
            return null;

        case OTHER:
        default:
            throw new IllegalArgumentException("unknown file type: " + getCommandPath());
        }
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}