package net.bodz.xml.models.pdb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.stream.Location;

import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.xml.models.pdb.PDB.Import;

import org.w3c.dom.Element;
import org.xml.sax.Locator;

public class CollectVisitor extends _PDBVisitor {

    protected PDB   pdb;
    protected Table table;
    protected Field field;
    protected Index index;
    protected View  view;

    @Override
    public boolean visit(PDB pdb) {
        this.pdb = pdb;
        pdb.tableOrViewMap = new TreeTextMap<Object>();
        pdb.tableMap = new HashTextMap<Table>();
        pdb.viewMap = new TreeTextMap<View>();
        return true;
    }

    @Override
    public boolean visit(Import _import) {
        Object location = pdb.getLocation();
        String systemId;
        if (location instanceof Location)
            systemId = ((Location) location).getSystemId();
        else if (location instanceof Locator)
            systemId = ((Locator) location).getSystemId();
        else
            throw new Error("Unsupported location type: " + location);
        URL importUrl;
        try {
            URL url = new URL(systemId);
            importUrl = new URL(url, _import.href);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        PDB pdb = JAXB.unmarshal(importUrl, PDB.class);
        // ignore the pdb database properties.
        List<Object> tableOrViews = this.pdb.getTableOrViews();
        tableOrViews.addAll(pdb.getTableOrViews());
        return true;
    }

    @Override
    public boolean visit(Table table) {
        this.table = table;
        String name = table.getName();
        if (name == null)
            throw new NullPointerException("table name");
        pdb.tableOrViewMap.put(name, table);
        pdb.tableMap.put(name, table);

        table.fieldMap = new TreeTextMap<Field>();
        table.indexMap = new TreeTextMap<Index>();
        return true;
    }

    @Override
    public boolean visit(Field field) {
        this.field = field;
        String name = field.getName();
        if (name == null)
            throw new NullPointerException("field name");
        table.fieldMap.put(name, field);
        field.table = table;
        return true;
    }

    @Override
    public boolean visit(Index index) {
        this.index = index;
        String name = index.getName();
        if (name != null)
            table.indexMap.put(name, index);
        index.table = table;
        return true;
    }

    @Override
    public boolean visit(Instance instance) {
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
        return true;
    }

    @Override
    public boolean visit(View view) {
        this.view = view;
        String name = view.getName();
        if (name == null)
            throw new NullPointerException("view name");
        pdb.tableOrViewMap.put(name, view);
        pdb.viewMap.put(name, view);

        view.fieldMap = new TreeTextMap<View.Field>();
        return true;
    }

    @Override
    public boolean visit(View.Field field) {
        String name = field.getName();
        if (name != null)
            view.fieldMap.put(name, field);
        return true;
    }

}
