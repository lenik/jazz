package net.bodz.lily.schema.contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.db.ibatis.IResultSetForm;
import net.bodz.bas.typer.std.IAttributes;
import net.bodz.lily.concrete.util.ExtraAttributes;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@Table(schema = Person.SCHEMA_NAME, name = Person.TABLE_NAME)
public class Person
        extends _Person_stuff
        implements
            IResultSetForm {

    private static final long serialVersionUID = 1L;

    static final IEntityTypeInfo TYPE = EntityTypes.getTypeInfo(Person.class);

    ExtraAttributes attrs = new ExtraAttributes(TYPE);

    public String getHello() {
        return "world";
    }

    public List<String> getPeers() {
        return null;
    }

    // @Internal
    public IAttributes getAttributes() {
        return attrs;
    }

    @Override
    public void readObject(ResultSet rs)
            throws SQLException {
        attrs.readObject(rs);
    }

}
