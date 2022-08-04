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

    public List<SchemaId> id() {
        List<SchemaId> ids = new ArrayList<>(size());
        for (ISchemaMetadata schema : this)
            ids.add(schema.getId());
        return ids;
    }

    public static final SchemaList empty() {
        return new SchemaList();
    }

}
