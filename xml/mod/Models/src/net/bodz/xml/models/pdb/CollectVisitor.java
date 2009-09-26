package net.bodz.xml.models.pdb;

import java.util.List;

import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.xml.models.pdb.PDB.Import;

import org.w3c.dom.Element;

public class CollectVisitor implements PDBVisitor {

    protected PDB   pdb;
    protected Table table;
    protected Field field;
    protected Index index;
    protected View  view;

    @Override
    public void visit(PDB pdb) {
        this.pdb = pdb;
        pdb.tableOrViewMap = new TreeTextMap<Object>();
        pdb.tableMap = new HashTextMap<Table>();
        pdb.viewMap = new TreeTextMap<View>();
    }

    @Override
    public void visit(Import _import) {
    }

    @Override
    public void visit(Table table) {
        this.table = table;
        String name = table.getName();
        if (name == null)
            throw new NullPointerException("table name");
        pdb.tableOrViewMap.put(name, table);
        pdb.tableMap.put(name, table);

        table.fieldMap = new TreeTextMap<Field>();
        table.indexMap = new TreeTextMap<Index>();
    }

    @Override
    public void visit(Field field) {
        this.field = field;
        String name = field.getName();
        if (name == null)
            throw new NullPointerException("field name");
        table.fieldMap.put(name, field);
        field.table = table;
    }

    @Override
    public void visit(Index index) {
        this.index = index;
        String name = index.getName();
        if (name != null)
            table.indexMap.put(name, index);
        index.table = table;
    }

    @Override
    public void visit(Check check) {
    }

    @Override
    public void visit(Trigger trigger) {
    }

    @Override
    public void visit(View view) {
        this.view = view;
        String name = view.getName();
        if (name == null)
            throw new NullPointerException("view name");
        pdb.tableOrViewMap.put(name, view);
        pdb.viewMap.put(name, view);

        view.fieldMap = new TreeTextMap<View.Field>();
    }

    @Override
    public void visit(View.Field field) {
        String name = field.getName();
        if (name != null)
            view.fieldMap.put(name, field);
    }

    @Override
    public void visit(Instance instance) {
        instance.table = table;
        List<Object> any = instance.getAny();
        RowData data = new RowData(table);
        for (Object _cell : any) {
            Element cellElement = (Element) _cell;
            String fieldName = cellElement.getLocalName();
            String fieldContet = cellElement.getTextContent();
            data.set(fieldName, fieldContet);
        }
        instance.setRowData(data);
    }

}
