package net.bodz.lily.entity.manager;

import java.nio.charset.Charset;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
import net.bodz.lily.entity.format.IMarkdownBuilder;
import net.bodz.lily.entity.format.IWordDocBuilder;
import net.bodz.lily.entity.format.WordUtils;

@ForEntityType(IId.class)
public class FetchCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "fetch";
    public static final String[] NAMES = { NAME };

    public FetchCommand() {
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
        extends AbstractEntityCommandProcess<FetchCommand> {

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

    public ResolveProcess(FetchCommand type, IEntityCommandContext context) {
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

        IWordDocBuilder docBuilder = context.query(IWordDocBuilder.class);
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
            throws LoaderException, ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}