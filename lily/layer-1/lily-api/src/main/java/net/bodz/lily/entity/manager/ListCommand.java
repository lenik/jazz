package net.bodz.lily.entity.manager;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.format.ITableSheetBuilder;
import net.bodz.lily.model.base.StructRowMask;

@ForEntityType(IJsonForm.class)
@ForEntityCriteriaType(IVarMapForm.class)
public class ListCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "__data";
    public static final String[] NAMES = { NAME, "list" };

    public ListCommand() {
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
    public IEntityCommandProcess createProcess(IEntityCommandContext context) {
        return new ListProcess(this, context);
    }

}

class ListProcess
        extends AbstractEntityCommandProcess<ListCommand> {

    TableOfPathProps tableData;
    final StructRowMask criteria;
    final SelectOptions selectOptions;

    int format = JSON;
    static final int JSON = 0;
    static final int HSSF = 1;
    static final int XSSF = 2;

    ITableSheetBuilder tableSheetBuilder;

    public ListProcess(ListCommand type, IEntityCommandContext context) {
        super(type, context);

        tableData = new TableOfPathProps(typeInfo.getEntityClass());

        Class<?> criteriaClass = typeInfo.getCrtieriaClass();
        try {
            criteria = (StructRowMask) criteriaClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(String.format(//
                    "Can't instantiate criteria: %s", e.getMessage()), e);
        }

        selectOptions = new SelectOptions();
    }

    @Override
    public void setQueryContext(IQueryable context) {
        ITableSheetBuilder tableSheetBuilder = context.query(ITableSheetBuilder.class);
        if (tableSheetBuilder != null)
            this.tableSheetBuilder = tableSheetBuilder;
    }

    public IVarMapForm getCriteria() {
        return criteria;
    }

    public SelectOptions getSelectOptions() {
        return selectOptions;
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
        List<Object> dataList = buildDataList();
        tableData.setList(dataList);

        if (tableData.isWantTotalCount()) {
            long totalCount = getEntityMapper().count(criteria);
            tableData.setTotalCount(totalCount);
        }

        if (format == JSON)
            return tableData;

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
    }

    @Override
    protected Boolean isReturningJsonResult() {
        return false;
    }

    protected List<Object> buildDataList() {
        return getEntityMapper().filter(criteria, selectOptions);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        tableData.readObject(map);
        criteria.readObject(map);
        selectOptions.readObject(map);
    }

}
