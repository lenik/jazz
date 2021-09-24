package net.bodz.bas.t.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bodz.bas.db.sql.dialect.SqlFormats;

public class SqlRowBuilderTest {

    @Test
    public void testInsertInto() {
        SqlRowBuilder sb = new SqlRowBuilder(SqlFormats.POSTGRESQL);
        sb.putEntry("name", "foo");
        sb.putEntry("age", 13);
        String sql = sb.insertInto("s.t");
        assertEquals("insert into \"s\".\"t\"(\"name\", \"age\") values('foo', 13);", sql);
    }

    @Test
    public void testInsertIntoPrepared() {
        SqlRowBuilder sb = new SqlRowBuilder(SqlFormats.POSTGRESQL);
        sb.prepared();
        sb.putEntry("name", "foo");
        sb.putEntry("age", 13);
        String sql = sb.insertInto("s.t");
        assertEquals("insert into \"s\".\"t\"(\"name\", \"age\") values(?, ?);", sql);
    }

    @Test
    public void testInsertIntoNamed() {
        SqlRowBuilder sb = new SqlRowBuilder(SqlFormats.POSTGRESQL);
        sb.named();
        sb.putEntry("name", "foo");
        sb.putEntry("age", 13);
        String sql = sb.insertInto("s.t");
        assertEquals("insert into \"s\".\"t\"(\"name\", \"age\") values(:name, :age);", sql);
    }

}
