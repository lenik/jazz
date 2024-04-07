package net.bodz.bas.t.catalog.poi;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ss.SsGroup;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.catalog.DefaultColumnMetadata;

public class SheetColumn
        extends DefaultColumnMetadata
        implements
            ISheetColumn,
            IXmlForm {

    String columnName;

    SsGroup ss = new SsGroup();

    public SheetColumn(SheetTableMetadata parent) {
        super(parent);
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.element("Column", "");
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

}
