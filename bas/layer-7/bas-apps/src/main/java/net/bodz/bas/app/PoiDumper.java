package net.bodz.bas.app;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.sheet.excel.ExcelParseOptions;
import net.bodz.bas.sheet.excel.XWorkbook;

public class PoiDumper
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(PoiDumper.class);

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        for (String name : args) {
            File file = new File(name);
            if (!file.canRead()) {
                logger.error("Can't read from " + file);
                continue;
            }

            Workbook src = WorkbookFactory.create(file);
            XWorkbook workbook = new XWorkbook();
            ExcelParseOptions options = new ExcelParseOptions();
            workbook.readObject(src, options);

            String xml = XmlFn.toString(workbook);
            System.out.println(xml);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new PoiDumper().execute(args);
    }

}
