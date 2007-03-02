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
        int fields = table.sizeFields();
        for (int i = 0; i < fields; i++) {
            Object cell = row.get(i);
            if (cell == Cell.MISSING)
                continue;
            Field field = table.getField(i);
            XMLTag cellTag = new XMLTag(field.getName(), cell);
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

        Row row = new RowFull(table);
        for (Node<?> child : tag.children()) {
            if (!(child instanceof XMLTag))
                continue;
            XMLTag cell = (XMLTag) child;
            String name = cell.getName();
            int index = table.getFieldIndex(name);
            String value = cell.text();
            row.set(index, value);
        }
        return row;
    }

}
