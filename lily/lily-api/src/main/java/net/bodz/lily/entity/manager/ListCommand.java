package net.bodz.lily.entity.manager;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.db.ibatis.sql.Order;
import net.bodz.bas.db.ibatis.sql.Orders;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.format.ITableSheetBuilder;

@ForEntityType(IJsonForm.class)
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
    public IEntityCommandProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new ListProcess(this, context, resolvedEntity);
    }

}

class ListProcess
        extends AbstractEntityCommandProcess<ListCommand> {

    TableOfPathProps tableData;
    CoObjectCriteriaBuilder<?> mask;
    // Junction criteria;

    final SelectOptions selectOptions;
    int seq;

    int format = JSON;
    static final int JSON = 0;
    static final int HSSF = 1;
    static final int XSSF = 2;

    ITableSheetBuilder tableSheetBuilder;

    public ListProcess(ListCommand type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context, resolvedEntity);
        tableData = new TableOfPathProps(typeInfo.getEntityClass());
        mask = (CoObjectCriteriaBuilder<?>) context.getEntityTypeInfo().newCriteriaBuilder();
        selectOptions = context.newSelectOptions();
    }

    @Override
    public void setQueryContext(IQueryable context) {
        ITableSheetBuilder tableSheetBuilder = context.query(ITableSheetBuilder.class);
        if (tableSheetBuilder != null)
            this.tableSheetBuilder = tableSheetBuilder;
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
            long totalCount = getEntityMapper().count(mask.get());
            tableData.setTotalCount(totalCount);
        }

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

    protected List<Object> buildDataList() {
        return getEntityMapper().filter(mask.get(), selectOptions);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        tableData.readObject(map);
        selectOptions.readObject(map);
        mask.readObject(map);

        Orders orders = selectOptions.getOrders();
        if (orders != null) {
            Orders newOrders = new Orders();
            for (Order order : orders) {
                String property = order.getColumn();
                String[] columns = findColumnForProperty(property);
                if (columns == null)
                    newOrders.add(order);
                else
                    for (String column : columns) {
                        Order newOrder = new Order();
                        newOrder.setAscending(order.isAscending());
                        newOrder.setColumn(column);
                        newOrders.add(newOrder);
                    }
            }
            selectOptions.setOrders(newOrders);
        }
    }

    String[] findColumnForProperty(String propertyName) {
        IType type = typeInfo.getPotatoType();
        IProperty property = type.getProperty(propertyName);
        if (property != null) {
            Column aColumn = property.getAnnotation(Column.class);
            if (aColumn != null) {
                String[] columnNames = { aColumn.name() };
                return columnNames;
            }

            if (property.isAnnotationPresent(ManyToOne.class) //
                    || property.isAnnotationPresent(OneToOne.class)) {
                JoinColumn aJoinColumn = property.getAnnotation(JoinColumn.class);
                if (aJoinColumn == null) {
                    JoinColumns aJoinColumns = property.getAnnotation(JoinColumns.class);
                    JoinColumn[] aJoinColumnv = aJoinColumns.value();
                    String[] foreignColumnNames = new String[aJoinColumnv.length];
                    for (int i = 0; i < foreignColumnNames.length; i++)
                        foreignColumnNames[i] = aJoinColumnv[i].name();
                    return foreignColumnNames;
                } else {
                    String[] foreignColumnNames = { aJoinColumn.name() };
                    return foreignColumnNames;
                }
            }
        }
        return null;
    }

}
