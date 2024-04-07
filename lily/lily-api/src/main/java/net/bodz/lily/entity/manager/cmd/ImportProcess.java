package net.bodz.lily.entity.manager.cmd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.records.CsvRecords;
import net.bodz.bas.fmt.records.CsvRow;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.repr.form.PropertyChain;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.site.file.UploadResult;
import net.bodz.bas.site.file.UploadedFileInfo;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.catalog.*;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.CoObject;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.StrVar;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.storage.IVolume;

public class ImportProcess
        extends AbstractEntityCommandProcess {

    String encoding;
    String delim;

    boolean renameDuplicatedColumns = true;

    // String id;

    PropertyChain[] fieldProps;
    protected boolean parsed;

    public ImportProcess(ImportCommand type, IEntityCommandContext context) {
        super(type, context);
    }

    @Override
    public Object execute()
            throws Exception {
        IVolume volume = dataApp.getIncomingVolume(typeInfo.getEntityClass());
        File localDir = volume.getLocalDir();
        IAnchor anchor = volume.getVolumeAnchor();
        UploadHandler uploadHandler = new UploadHandler(localDir, anchor);
        UploadResult uploadResult;
        try {
            uploadResult = uploadHandler.handlePostRequest(request);
        } catch (Exception e) {
            throw new ServiceTargetException("upload handler: " + e.getMessage(), e);
        }

        JsonResult result = new JsonResult();

        for (UploadedFileInfo fileInfo : uploadResult) {
            File file = fileInfo.getFile();
            String extension = FilePath.getExtension(file);
            if (extension == null)
                return result.fail("file without extension.");

            switch (extension.toLowerCase()) {
            case "csv":
                return importCsv(file);

            case "xls":
            case "xlsx":
                // return importExcel(file);
                return result.fail("invalid state.");

            default:
                return result.fail("unknown file type.");
            }
        }
        return uploadResult;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        encoding = map.getString("encoding", encoding);
        delim = map.getString("delim", delim);
    }

    JsonResult importCsv(File file)
            throws IOException, ParseException {
        String encoding = this.encoding;
        if (encoding == null)
            encoding = "utf-8";
        char delim = ',';
        if (Nullables.isNotEmpty(this.delim))
            delim = this.delim.charAt(0);

        CsvRecords csv = new CsvRecords(ResFn.file(file), delim);
        MutableTable table = csv2Table(csv);
        return parseAndImportTable(table);
    }

    JsonResult importExcel(File file) {
        JsonResult result = new JsonResult();
        return result;
    }

    protected MutableTable csv2Table(CsvRecords csv)
            throws IOException, ParseException {
        CsvRow head = csv.first();
        int nColumn = head.size();

        DefaultTableMetadata tableType = new DefaultTableMetadata();
        for (int i = 0; i < nColumn; i++) {
            String name = head.get(i);

            if (renameDuplicatedColumns)
                if (tableType.getColumn(name) != null) {
                    String rename = null;
                    for (int nTry = 1; nTry < 10; nTry++) {
                        String tryName = name + "_" + nTry;
                        IColumnMetadata existing = tableType.getColumn(tryName);
                        if (existing == null) {
                            rename = tryName;
                            break;
                        }
                    }
                    if (rename == null)
                        throw new DuplicatedKeyException("column duplicated: " + name);
                    name = rename;
                }

            tableType.addNewColumn(name);
        }

        MutableTable table = new MutableTable(tableType);

        int rowIndex = 0;
        for (CsvRow csvRow : csv) {
            if (csvRow.getRawLine().trim().isEmpty())
                continue;

            if (rowIndex++ == 0)
                continue;

            CoObject obj = (CoObject) typeInfo.newInstance();
            IMutableRow row = table.newRow();
            for (int iCol = 0; iCol < nColumn; iCol++) {
                String cell = csvRow.get(iCol);
                row.addNewCell().setData(cell);
            }
        }

        return table;
    }

    JsonResult parseAndImportTable(MutableTable table)
            throws ParseException {
        prepareTableType((DefaultTableMetadata) table.getMetadata());
        if (! parsed)
            parseTableCells(table);
        List<StructRow> objects = buildObjects(table);
        return importObjects(objects);
    }

    void prepareTableType(DefaultTableMetadata table) {
        int nColumn = table.getColumnCount();
        fieldProps = new PropertyChain[nColumn];

        for (int i = 0; i < nColumn; i++) {
            DefaultColumnMetadata column = (DefaultColumnMetadata) table.getColumn(i);
            String columnName = column.getName();
            PropertyChain fieldProp = context.resolveFieldProp(columnName);
            fieldProps[i] = fieldProp;
            Class<?> type = fieldProp.getPropertyClass();
            column.setJavaClass(type);
        }
    }

    protected void parseTableCells(MutableTable table)
            throws ParseException {
        ITableMetadata tableType = table.getMetadata();
        List<? extends IColumnMetadata> columns = tableType.getColumns();
        int nColumn = tableType.getColumnCount();

        int rowIndex = 0;
        for (IRow _row : table.getRows()) {
            MutableRow row = (MutableRow) _row;
            rowIndex++;

            for (int iCol = 0; iCol < nColumn; iCol++) {
                IColumnMetadata column = columns.get(iCol);
                IMutableCell cell = row.getCell(iCol);
                String cellText = (String) cell.getData();

                Class<?> cellType = column.getJavaClass();

                Object cellVal;
                try {
                    cellVal = StrVar.parse(cellType, cellText);
                } catch (ParseException e) {
                    throw new ParseException(String.format(//
                            "Error parse csv cell at row %d, column %d: %s", //
                            rowIndex, iCol, e.getMessage()), e);
                }

                cell.setData(cellVal);
            }
        }
    }

    protected List<StructRow> buildObjects(MutableTable table)
            throws ParseException {
        ITableMetadata tableType = table.getMetadata();
        int nColumn = tableType.getColumnCount();

        List<StructRow> list = new ArrayList<>();
        int rowIndex = 0;
        for (IRow _row : table.getRows()) {
            rowIndex++;
            StructRow obj = (StructRow) typeInfo.newInstance();

            for (int iCol = 0; iCol < nColumn; iCol++) {
                IColumnMetadata column = tableType.getColumn(iCol);

                ICell cell = _row.getCell(iCol);
                Object cellVal = cell.getData();
                if (cellVal == null)
                    continue;

                PropertyChain fieldProp = fieldProps[iCol];
                Class<?> propType = fieldProp.getPropertyClass();
                Class<?> boxed = Primitives.box(propType);
                if (cellVal != null && ! boxed.isAssignableFrom(cellVal.getClass()))
                    throw new IllegalUsageException(String.format(//
                            "Invalid cell %s[%d] %s, expected %s", //
                            column.getName(), iCol, //
                            cellVal.getClass(), propType));

                try {
                    fieldProp.setValue(obj, cellVal);
                } catch (Exception e) {
                    throw new ParseException(String.format(//
                            "Error load csv cell at row %d, column %s[%d]: %s", //
                            rowIndex, column.getName(), iCol, e.getMessage()), e);
                }
            }

            list.add(obj);
        }
        return list;
    }

    protected JsonResult importObjects(List<StructRow> list) {
        JsonResult result = new JsonResult();
        IEntityMapper<CoObject> mapper = getEntityMapper();
        ImportResult resultData = new ImportResult();

        int objIndex = 0;
        for (StructRow item : list) {
            CoObject obj = (CoObject) item;
            try {
                if (obj.id() == null) {
                    resultData.updatedRows += mapper.insert(obj);
                    resultData.inserts.add(obj);
                } else {
                    resultData.updatedRows += mapper.update(obj);
                    resultData.updates.add(obj);
                }
            } catch (Throwable e) {
                if (e instanceof PersistenceException) {
                    e = e.getCause();
                }
                String error = String.format("Error import data[%d]: %s", objIndex, e.getMessage());
                resultData.errors.add(error);
            }
            objIndex++;
        }
        result.setData(resultData);
        return result;
    }

}
