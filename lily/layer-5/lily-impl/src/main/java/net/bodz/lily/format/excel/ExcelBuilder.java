package net.bodz.lily.format.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;

public abstract class ExcelBuilder
        implements
            IVarMapForm {

    // protected Workbook workbook;

    public ExcelBuilder() {
    }

    protected abstract String getTitle();

    protected boolean useTitleAsSheetName() {
        return false;
    }

    public final Workbook build(IVariantMap<String> q)
            throws FormatException {
        return build(false, q);
    }

    public final synchronized Workbook build(boolean xssf, IVariantMap<String> q)
            throws FormatException {
        try {
            readObject(q);
        } catch (LoaderException | ParseException e) {
            throw new IllegalArgumentException("Failed to parse parameters: " + e.getMessage(), e);
        }
        return build();
    }

    public final Workbook build()
            throws FormatException {
        return build(false);
    }

    public final Workbook build(boolean xssf)
            throws FormatException {
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(xssf);
        } catch (IOException e) {
            throw new FormatException(e.getMessage(), e);
        }
        buildWorkbook(workbook);
        return workbook;
    }

    public synchronized void buildWorkbook(Workbook workbook)
            throws FormatException {
        Sheet sheet = workbook.createSheet();

        buildMainSheet(sheet);

        if (useTitleAsSheetName()) {
            String title = getTitle();
            if (title != null) {
                int sheetIndex = workbook.getSheetIndex(sheet);
                workbook.setSheetName(sheetIndex, title);
            }
        }
    }

    protected abstract void buildMainSheet(Sheet sheet)
            throws FormatException;

    public byte[] toBytes()
            throws FormatException {
        return toBytes(false);
    }

    public byte[] toBytes(boolean xssf)
            throws FormatException {
        Workbook workbook = build(xssf);

        ByteArrayOutputStream buf = new ByteArrayOutputStream(30000);
        try {
            workbook.write(buf);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }

        return buf.toByteArray();
    }

    public FileContent toContent(boolean xssf)
            throws FormatException {
        Workbook workbook = build(xssf);

        ByteArrayOutputStream buf = new ByteArrayOutputStream(30000);
        try {
            workbook.write(buf);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }

        String fileName = getTitle();
        fileName += (xssf ? ".xlsx" : ".xls");
        return new FileContent(fileName, ContentTypes.application_vnd_ms_excel, buf.toByteArray());
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

}
