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

    protected PDB   context;
    protected Table contextTable;
    protected Field contextField;
    protected Index contextIndex;
    protected View  contextView;

    @Override
    public boolean visit(PDB pdb) {
        this.context = pdb;
        pdb.tableOrViewMap = new TreeTextMap<Object>();
        pdb.tableMap = new HashTextMap<Table>();
        pdb.viewMap = new TreeTextMap<View>();
        return true;
    }

    @Override
    public boolean visit(Import _import) {
        Object location = context.getLocation();
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
        List<Object> tableOrViews = this.context.getTableOrViews();
        tableOrViews.addAll(pdb.getTableOrViews());
        return true;
    }

    @Override
    public boolean visit(Table table) {
        this.contextTable = table;
        table.pdb = context;
        String name = table.getName();
        if (name == null)
            throw new NullPointerException("table name");
        context.tableOrViewMap.put(name, table);
        context.tableMap.put(name, table);
        table.fieldMap = new TreeTextMap<Field>();
        table.indexMap = new TreeTextMap<Index>();
        return true;
    }

    @Override
    public boolean visit(Field field) {
        this.contextField = field;
        field.table = contextTable;
        String name = field.getName();
        if (name == null)
            throw new NullPointerException("field name");
        contextTable.fieldMap.put(name, field);
        return true;
    }

    @Override
    public boolean visit(Index index) {
        this.contextIndex = index;
        index.table = contextTable;
        String name = index.getName();
        if (name != null)
            contextTable.indexMap.put(name, index);
        return true;
    }

    @Override
    public boolean visit(Instance instance) {
        instance.table = contextTable;
        List<Object> any = instance.getAny();
        RowData data = new RowData(contextTable);
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
        this.contextView = view;
        view.pdb = context;
        String name = view.getName();
        if (name == null)
            throw new NullPointerException("view name");
        context.tableOrViewMap.put(name, view);
        context.viewMap.put(name, view);
        view.fieldMap = new TreeTextMap<View.Field>();
        return true;
    }

    @Override
    public boolean visit(View.Field field) {
        String name = field.getName();
        if (name != null)
            contextView.fieldMap.put(name, field);
        return true;
    }

}
