package net.bodz.lily.entity.manager.cmd;

import java.io.ByteArrayOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.format.ITableSheetBuilder;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;

public class ListProcess
        extends AbstractEntityCommandProcess {

    IVariantMap<String> parameterMap;
    IDataFetcher dataFetcher;

    TableOfPathProps tableData;

    int seq;

    int format = JSON;
    static final int JSON = 0;
    static final int HSSF = 1;
    static final int XSSF = 2;

    ITableSheetBuilder tableSheetBuilder;

    public ListProcess(ListCommand type, IEntityCommandContext context) {
        super(type, context);
        tableData = new TableOfPathProps(typeInfo.getEntityClass());
        if (context instanceof IDataFetcher)
            dataFetcher = (IDataFetcher) context;
        else
            throw new NotImplementedException();
    }

    @Override
    public void setQueryContext(IQueryable context) {
        ITableSheetBuilder tableSheetBuilder = context.query(ITableSheetBuilder.class);
        if (tableSheetBuilder != null)
            this.tableSheetBuilder = tableSheetBuilder;
    }

    @Override
    public void setCommandPath(IPathFields path) {
        super.setCommandPath(path);
        switch (path.getExtension("js")) {
        case "js":
        case "json":
            format = JSON;
            break;
        case "xls":
            format = HSSF;
            break;
        case "xlsx":
            format = XSSF;
            break;
        }
    }

    public void setTableSheetBuilder(ITableSheetBuilder tableSheetBuilder) {
        this.tableSheetBuilder = tableSheetBuilder;
    }

    @Override
    public Object execute()
            throws Exception {
        DataFetchResult result = dataFetcher.fetchDataList(parameterMap, tableData.isWantTotalCount());
        tableData.setList(result.range);
        if (tableData.isWantTotalCount())
            tableData.setTotalCount(result.count);

        switch (format) {
        case JSON:
            return tableData;

        case HSSF:
        case XSSF:
            if (tableSheetBuilder == null)
                throw new IllegalUsageException("sheet builder isn't set.");
            Workbook workbook = WorkbookFactory.create(format == XSSF);
            tableSheetBuilder.buildTableSheet(tableData, workbook, parameters);
            ByteArrayOutputStream buf = new ByteArrayOutputStream(30000);
            workbook.write(buf);

            int activeSheetIndex = workbook.getActiveSheetIndex();
            String sheetName = workbook.getSheetName(activeSheetIndex);
            String fileName = sheetName + (format == XSSF ? ".xlsx" : ".xls");

            return new FileContent(fileName, ContentTypes.application_vnd_ms_excel, buf.toByteArray());

        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected Boolean isReturningJsonResult() {
        return false;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        tableData.readObject(map);
        this.parameterMap = map;
    }

}
