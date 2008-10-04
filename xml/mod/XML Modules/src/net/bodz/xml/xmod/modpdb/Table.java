package net.bodz.xml.xmod.modpdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.xml.util.Term;
import net.bodz.xml.xmod.util.Docobj;

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
    public static final int            CACHED         = 1;

    /** Dr */
    public static final int            NOT_REFERENCED = 2;

    /** T (Forbid: D, Dr) */
    public static final int            TRANSIENT      = 4;

    /** Ro */
    public static final int            READ_ONLY      = 8;

    protected Modpdb                   db;

    @XMLDump
    protected final List<Field>        fields;

    // @XMLDump
    // protected final List<TableIndex> indexes;

    @XMLDump
    protected final List<TableCheck>   checks;

    @XMLDump
    protected final List<TableTrigger> triggers;

    @XMLDump
    protected final List<Row>          rows;

    private Map<String, Integer>       nameIndexes;

    protected PrimaryKey               pkey;
    protected Map<String, ForeignKey>  fkeys;
    protected Map<String, Index>       idxes;
    protected Field                    f_TimeStamp;
    protected Field                    f_Version;

    public Table() {
        fields = new ArrayList<Field>();
        // indexes = new ArrayList<TableIndex>();
        checks = new ArrayList<TableCheck>();
        triggers = new ArrayList<TableTrigger>();
        rows = new ArrayList<Row>();

        fkeys = new HashMap<String, ForeignKey>();
        idxes = new HashMap<String, Index>();
    }

    protected void pre_set(Object outer) {
        assert outer instanceof Modpdb;
        db = (Modpdb) outer;
    }

    protected int getFieldIndex(String name) {
        if (nameIndexes == null) {
            nameIndexes = new HashMap<String, Integer>();
            for (int index = 0; index < fields.size(); index++)
                nameIndexes.put(fields.get(index).getName(), index);
        }
        Integer index = nameIndexes.get(name);
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
    }

    public Field getField(int index) {
        return fields.get(index);
    }

    public Index getIndex(String name) {
        return idxes.get(name);
    }

    public TableCheck getCheck(int index) {
        return checks.get(index);
    }

    public TableTrigger getTrigger(int index) {
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

    public Column getColumn(int fieldIndex) {
        if (rows == null)
            return null;
        Column column = new Column(rows.size());
        for (Row row : rows) {
            column.add(row.get(fieldIndex));
        }
        return column;
    }

    public Column getColumn(String fieldName) {
        int index = getFieldIndex(fieldName);
        return getColumn(index);
    }

    /** (default) */
    public static final int MAIN_TABLE     = 0;
    /** S, Ss */
    public static final int SUB_TABLE      = 1;
    /** Sc */
    public static final int SUB_TABLE_CONT = 2;
    /** Sx */
    public static final int SUB_TABLE_EXT  = 3;

    protected int           subs           = MAIN_TABLE;

    /**
     * <pre>
     *  D=cache/dict
     *  Dr=redundant (info-dict)
     *  Ro=read-only table
     *  S, Ss, Sc, Sx=sub-table
     *  T=transient table
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) throws ParseException {
        String abb = opt.getName();
        int len = abb.length();
        switch (abb.charAt(0)) {
        case 'D':
            if (len == 1)
                flags |= CACHED;
            else if ("Dr".equals(abb))
                flags |= CACHED | NOT_REFERENCED;
            else
                break;
            return true;
        case 'R':
            if ("Ro".equals(abb))
                flags |= READ_ONLY;
            else
                break;
            return true;
        case 'S':
            if (len == 1)
                subs = SUB_TABLE;
            else if ("Ss".equals(abb))
                subs = SUB_TABLE;
            else if ("Sc".equals(abb))
                subs = SUB_TABLE_CONT;
            else if ("Sx".equals(abb))
                subs = SUB_TABLE_EXT;
            else
                break;
            return true;
        case 'T':
            if (len == 1)
                flags |= TRANSIENT;
            else
                break;
            return true;
        }
        return super.parseOpts(opt);
    }

    public boolean isAbstract() {
        return isCommentOut();
    }

    public int getSubmode() {
        return subs;
    }
    
}
