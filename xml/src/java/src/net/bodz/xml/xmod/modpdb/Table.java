package net.bodz.xml.xmod.modpdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.xml.util.Term;
import net.sf.freejava.fp.dump.XMLDump;

class Column extends ArrayList<Object> {

    private static final long serialVersionUID = -8024718839909013511L;

    public Column() {
        super();
    }

    public Column(int initialCapacity) {
        super(initialCapacity);
    }

}

public class Table extends Docobj {

    /** D, Dr (Forbid: T) */
    public static final int      CACHED         = 1;

    /** Dr */
    public static final int      NOT_REFERENCED = 2;

    /** T (Forbid: D, Dr) */
    public static final int      TRANSIENT      = 4;

    /** Ro */
    public static final int      READ_ONLY      = 8;

    protected int                flags;

    @XMLDump
    protected List<Field>        fields;

    @XMLDump
    protected List<Index>        indexes;

    @XMLDump
    protected List<Check>        checks;

    @XMLDump
    protected List<Trigger>      triggers;

    @XMLDump
    protected List<Row>          rows;
    private Map<String, Column>  columns;

    private Map<String, Integer> fieldIndexMap;

    public Table() {
        rows = new ArrayList<Row>();
        columns = new HashMap<String, Column>();
    }

    /**
     * <pre>
     *     D=cache/dict
     *     Dr=redundant (info-dict)
     *     T=transient table
     *     Ro=read-only table
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) {
        opt.getName();
        return true;
    }

    protected int getFieldIndex(String name) {
        if (fieldIndexMap == null) {
            fieldIndexMap = new HashMap<String, Integer>();
            for (int index = 0; index < fields.size(); index++)
                fieldIndexMap.put(fields.get(index).name, index);
        }
        Integer index = fieldIndexMap.get(name);
        if (index == null)
            throw new NoSuchElementException(name + " (table " + name + ")");
        return index;
    }

    public Row getRow(int index) {
        return rows.get(index);
    }

    public void setRow(int index, Row row) {
        rows.set(index, row);
    }

    public void addRow(Row row) {
        rows.add(row);

        // optimize
        // 1, add new columns if not exists
        for (Cell cell : row.getCells()) {
            Field field = fields.get(cell.getIndex());
            String fname = field.name;
            if (!columns.containsKey(fname)) {
                int initcap = rows.size();
                Column newColumn = new Column(initcap + 1);
                for (int i = 0; i < initcap; i++)
                    newColumn.add(null);
                columns.put(fname, newColumn);
            }
        }

        // 2, dispatch row into columns
        for (Map.Entry<String, Column> entry : columns.entrySet()) {
            String fname = entry.getKey();
            int findex = getFieldIndex(fname);
            Column column = entry.getValue();

            if (row.hasField(findex))
                column.add(row.getField(findex));
            else
                column.add(null);
        }
    }

    public Field getField(int index) {
        return fields.get(index);
    }

    public Index getIndex(int index) {
        return indexes.get(index);
    }

    public Check getCheck(int index) {
        return checks.get(index);
    }

    public Trigger getTrigger(int index) {
        return triggers.get(index);
    }

    public int sizeFields() {
        return fields.size();
    }

    public Iterator<Row> iteratorRows() {
        return rows.iterator();
    }

    public int sizeRows() {
        return rows.size();
    }

    public Column getColumn(String fieldName) {
        return columns.get(fieldName);
    }

    public Column getColumn(int fieldIndex) {
        String fieldName = fields.get(fieldIndex).getName();
        return columns.get(fieldName);
    }

}
