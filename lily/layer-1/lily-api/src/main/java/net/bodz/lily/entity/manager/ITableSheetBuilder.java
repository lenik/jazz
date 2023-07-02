package net.bodz.lily.entity.manager;

import org.apache.poi.ss.usermodel.Workbook;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.site.json.TableOfPathProps;

public interface ITableSheetBuilder {

    void buildTableSheet(TableOfPathProps tableData, Workbook book)
            throws FormatException;

}
