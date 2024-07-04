package net.bodz.lily.entity.manager.cmd;

import java.nio.charset.Charset;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.IllegalUsageException;
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
import net.bodz.lily.entity.format.IMarkdownBuilder;
import net.bodz.lily.entity.format.IWordDocBuilder;
import net.bodz.lily.entity.format.WordUtils;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IId.class)
public class FetchCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_FETCH;
    public static final String[] NAMES = { "fetch" };
    public static final String[] METHODS = { "GET" };

    public FetchCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public String[] getAcceptedMethods() {
        return METHODS;
    }

    @Override
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public IEntityCommandProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new ResolveProcess(this, context, resolvedEntity);
    }

}

class ResolveProcess
        extends AbstractEntityCommandProcess {

    ResolvedEntity resolvedEntity;

    JsonFormOptions jsonFormOptions;

    int format = JSON;
    static final int JSON = 0;
    static final int MARKDOWN = 1;
    static final int DOC = 2;
    static final int DOCX = 3;
    static final int PDF = 4;
    static final int OTHER = -1;

    IMarkdownBuilder markdownBuilder;
    IWordDocBuilder docxBuilder;

    public ResolveProcess(FetchCommand type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context);
        this.resolvedEntity = resolvedEntity;
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

        IWordDocBuilder docBuilder = context.query(IWordDocBuilder.class);
        if (docBuilder != null)
            this.docxBuilder = docBuilder;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    @Override
    protected Boolean isReturningJsonResult() {
        return false;
    }

    @Override
    public Object execute()
            throws Exception {
        if (resolvedEntity.entity instanceof IVarMapForm) {
            IVarMapForm varMapForm = resolvedEntity.entity;
            varMapForm.readObject(parameters);
        }

        JsonWrapper data = JsonWrapper.wrap(resolvedEntity);
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

        // case DOC:
        case DOCX:
            if (docxBuilder == null)
                throw new IllegalUsageException("doc builder isn't set.");
            XWPFDocument wordDoc = docxBuilder.buildWordDoc(resolvedEntity, parameters);
            FileContent file = WordUtils.toFile(wordDoc, getContentPath().getFileName());
            return file;

        case PDF:
            return null;

        case OTHER:
        default:
            throw new IllegalArgumentException("unknown file type: " + getCommandPath());
        }
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}