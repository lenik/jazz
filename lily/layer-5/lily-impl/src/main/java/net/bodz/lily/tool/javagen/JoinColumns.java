package net.bodz.lily.tool.javagen;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.catalog.CrossReference;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;

public class JoinColumns
        implements
            IColumnOrder {

    Map<String, CrossReference> aliasMap = new LinkedHashMap<>();
    Map<CrossReference, String> refAliasMap = new HashMap<>();
    Map<String, AliasedColumn> aliasColumns = new LinkedHashMap<>();

    Map<String, IColumnMetadata> foreignColumns = new LinkedHashMap<>();

    boolean ignoreCase;
    String[] defaultParentColumns = { "id", "label", "description", "image" };

    public JoinColumns() {
    }

    public JoinColumns(ITableMetadata table) {
        addAll(table.getForeignKeys().values());
    }

    public void addAll(Collection<CrossReference> refs) {
        addAll(refs, ignoreCase);
    }

    public void addAll(Collection<CrossReference> refs, boolean ignoreCase) {
        for (CrossReference ref : refs)
            add(ref, ignoreCase, defaultParentColumns);
    }

    public void add(CrossReference ref, String... parentColumns) {
        add(ref, ignoreCase, parentColumns);
    }

    public void add(CrossReference ref, boolean ignoreCase, String... parentColumns) {
        add("", ref, ignoreCase, parentColumns);
    }

    public void add(String prefix, CrossReference ref, String... parentColumns) {
        add(prefix, ref, ignoreCase, parentColumns);
    }

    public void add(String prefix, CrossReference ref, boolean ignoreCase, String... parentColumns) {
        if (refAliasMap.containsKey(ref))
            throw new IllegalArgumentException("already added: " + ref);

        String preferredParentAlias = ref.getPreferredAlias(prefix);
        Integer seq = null;
        String parentAlias;
        while (aliasMap.containsKey(parentAlias = concat(preferredParentAlias, seq)))
            if (seq == null)
                seq = 1;
            else
                seq++;
        aliasMap.put(parentAlias, ref);
        refAliasMap.put(ref, parentAlias);

        for (IColumnMetadata c : ref.getForeignColumns()) {
            IColumnMetadata existing = foreignColumns.get(c.getName());
            if (existing != null)
                throw new IllegalUsageException("foreign column is already used: " + existing);
            foreignColumns.put(c.getName(), c);
        }

        ITableMetadata parent = ref.getParentTable();
        for (String name : parentColumns) {
            IColumnMetadata column = parent.getColumn(name, ignoreCase);
            if (column == null)
                continue; // just ignore non-existing parent columns
            String parentColumnAlias = parentAlias + "_" + column.getName();
            AliasedColumn ac = new AliasedColumn(parentAlias, parentColumnAlias, column);
            aliasColumns.put(parentColumnAlias, ac);
        }
    }

    static String concat(String a, Object b) {
        if (b == null)
            return a;
        else
            return a + b;
    }

    @Override
    public SeqByPass getOrder(IColumnMetadata column) {
        int pass;
        if (column.isPrimaryKey())
            pass = 0;
        else if (!foreignColumns.containsKey(column.getName()))
            pass = 1;
        else
            pass = 2;
        return new SeqByPass(pass, column.getOrdinal());
    }

}
