package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchemaList
        extends ArrayList<ISchemaMetadata> {

    private static final long serialVersionUID = 1L;

    public SchemaList() {
        super();
    }

    public SchemaList(Collection<? extends ISchemaMetadata> c) {
        super(c);
    }

    public List<QualifiedSchemaName> getQNames() {
        List<QualifiedSchemaName> qNames = new ArrayList<>(size());
        for (ISchemaMetadata schema : this)
            qNames.add(schema.getQName());
        return qNames;
    }

    public static final SchemaList empty() {
        return new SchemaList();
    }

}
