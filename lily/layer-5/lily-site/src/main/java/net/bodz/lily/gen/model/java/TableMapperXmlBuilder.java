package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class TableMapperXmlBuilder
        extends ViewMapperXmlBuilder {

    public TableMapperXmlBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildBody(ITableViewMetadata tableView) {
        ITableMetadata table = (ITableMetadata) tableView;

        out.println("<!DOCTYPE mapper");
        out.println("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
        out.println("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        out.println("<mapper namespace=\"" + fragmentQName + "\">");
        out.enter();
        {
            out.println();
            resultMap_objlist_map(table);
            out.println();
            sql_objlist_sql(table);
            out.println();
            sql_objedit_sql(table);
            out.println();
            sql_filtconds(table);
            out.println();
            select_all(table);
            out.println();
            select_filter(table);
            out.println();
            select(table);
            out.println();
            insert(table);
            out.println();
            update(table);
            out.println();
            delete(table);
            out.println();
            select_count(table);
            out.println();
            out.leave();
        }
        out.println("</mapper>");
    }

    void insert(ITableMetadata table) {
        out.printf("<insert id=\"insert\" useGeneratedKeys=\"true\" keyProperty=\"id\"><![CDATA[\n");
        out.enter();
        {
            out.printf("insert into %s(\n", table.getCompactName());
            {
                out.enter();
                boolean first = true;

                for (IColumnMetadata column : table.getColumns()) {
                    if (!first)
                        out.print(",\n");

                    // insertKey
                    out.print(column.getName());

                    first = false;
                }
                out.leave();
                out.println();
            }

            out.println(") values(");
            {
                out.enter();
                boolean first = true;
                for (IColumnMetadata column : table.getColumns()) {
                    String col_name = column.getName();
                    String colName = StringId.UL.toCamel(col_name);

                    if (!first)
                        out.print(",\n");

                    // insertVal
                    out.print("#{" + colName + "}");

                    first = false;
                }
                out.leave();
                out.println();
            }

            out.println(") returning id; --");
            out.leave();
        }
        out.println("]]></insert>");
    }

    void update(ITableMetadata table) {
        out.println("<update id=\"update\">");
        {
            out.enter();
            out.printf("update %s\n", table.getCompactName());
            out.println("<set>");
            out.enter();
            {
                boolean co = false;
                if (co)
                    out.println("<include refid=\"co.setUS\" />");
                for (IColumnMetadata column : table.getColumns()) {
                    String col_name = column.getName();
                    String colName = StringId.UL.toCamel(col_name);

                    out.printf("%s = #{%s}", col_name, colName);
                    out.println(",");
                }
                out.leave();
            }
            out.println("</set>");

            out.println("<where>");
            out.enter();
            {
                out.println("    id = #{id}");
                out.leave();
            }
            out.println("</where>");
        }
        out.leave();
        out.println("</update>");
    }

    void delete(ITableMetadata table) {
        out.println("<delete id=\"delete\">");
        out.enter();
        {
            out.println("delete from " + table.getCompactName() + " where id = #{id}");
            out.leave();
        }
        out.println("</delete>");
    }

}