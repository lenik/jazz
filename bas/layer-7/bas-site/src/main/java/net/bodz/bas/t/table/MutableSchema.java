package net.bodz.bas.t.table;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;

public class MutableSchema
        extends TableMap
        implements
            ISchema {

    public MutableSchema() {
        super();
    }

    public MutableSchema(DefaultSchemaMetadata metadata) {
        super(metadata);
    }

    public static MutableSchema fromSchemaElement(IElement x_schema)
            throws ParseException, LoaderException {
        MutableSchema o = new MutableSchema();
        o.readObject(x_schema);
        return o;
    }

    @Override
    protected DefaultSchemaMetadata createMetadata() {
        return new DefaultSchemaMetadata();
    }

    @Override
    public ISchemaMetadata getMetadata() {
        return (ISchemaMetadata) super.getMetadata();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getTableCount(), getMetadata().toString());
    }

}
