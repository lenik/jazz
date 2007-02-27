package net.bodz.xml.xmod.modpdb;

import net.bodz.xml.util.XMLTagMapper;
import net.sf.freejava.fp.xml.XMLTag;
import net.sf.freejava.util.tree.Node;

import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class RowMapper extends XMLTagMapper {

    public RowMapper() {
        super();
    }

    public RowMapper(String uri, int index, String name) {
        super(uri, index, name);
    }

    @Override
    public void marshal(Object obj, IMarshallingContext ictx)
            throws JiBXException {
        assert obj != null;
        Row row = (Row) obj;
        Table table = row.getTable();
        assert table != null;

        XMLTag rowTag = new XMLTag(name);
        for (Cell cell : row.getCells()) {
            Field field = table.getField(cell.getIndex());
            XMLTag cellTag = new XMLTag(field.getName(), cell.getValue());
            rowTag.add(cellTag);
        }
        super.marshal(rowTag, ictx);
    }

    @Override
    public Object unmarshal(Object obj, IUnmarshallingContext ictx)
            throws JiBXException {
        XMLTag tag = (XMLTag) super.unmarshal(obj, ictx);
        assert tag != null;

        Table table = (Table) ictx.getStackTop();
        if (table == null)
            throw new IllegalStateException(
                    "<row> isn't appeared inside of a valid <table>");

        Row row = new Row(table);
        for (Node<?> child : tag.children()) {
            if (!(child instanceof XMLTag))
                continue;
            XMLTag cell = (XMLTag) child;
            String name = cell.getName();
            String value = cell.text();
            row.addCell(name, value);
        }
        return row;
    }

}
