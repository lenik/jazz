package net.bodz.lily.entity.format;

import org.apache.poi.ss.usermodel.Workbook;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.t.variant.IVariantMap;

public interface ITableSheetBuilder {

    void buildTableSheet(TableOfPathProps tableData, Workbook book, IVariantMap<String> q)
            throws FormatException;

}
