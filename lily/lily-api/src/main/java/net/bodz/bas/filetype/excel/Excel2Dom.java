package net.bodz.bas.filetype.excel;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.catalog.poi.SheetBook;

@ProgramName("excel2dom")
public class Excel2Dom
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(Excel2Dom.class);

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        for (String name : args) {
            File file = new File(name);
            if (! file.canRead()) {
                logger.error("Can't read from " + file);
                continue;
            }

            Workbook src = WorkbookFactory.create(file);
            SheetBook book = new SheetBook();
            ExcelParseOptions options = new ExcelParseOptions();
            book.readObject(src, options);

            String xml = XmlFn.toString(book);
            System.out.println(xml);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new Excel2Dom().execute(args);
    }

}
