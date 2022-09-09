package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.XmlSourceBuffer;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class FooMapper__xml
        extends VFooMapper__xml {

    public FooMapper__xml(JavaGenProject project) {
        super(project);
    }

    @Override
    protected void buildXmlBody(XmlSourceBuffer out, ITableViewMetadata tableView) {
        ITableMetadata table = (ITableMetadata) tableView;

        out.println("<!DOCTYPE mapper");
        out.println("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
        out.println("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        out.println("<mapper namespace=\"" + project.FooMapper + "\">");
        out.enter();
        {
            out.println();
            resultMap_objlist_map(out, table);
            out.println();
            sql_objlist_sql(out, table);
            out.println();
            sql_objedit_sql(out, table);
            out.println();
            sql_filtconds(out, table);
            out.println();
            select_all(out, table);
            out.println();
            select_filter(out, table);
            out.println();
            select(out, table);
            out.println();
            insert(out, table);
            out.println();
            update(out, table);
            out.println();
            delete(out, table);
            out.println();
            select_count(out, table);
            out.println();
            out.leave();
        }
        out.println("</mapper>");
    }

    void insert(XmlSourceBuffer out, ITableMetadata table) {
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
                    Phrase name = Phrase.foo_bar(column.getName());

                    if (!first)
                        out.print(",\n");

                    // insertVal
                    out.print("#{" + name.fooBar + "}");

                    first = false;
                }
                out.leave();
                out.println();
            }

            out.println(");");
            out.leave();
        }
        out.println("]]></insert>");
    }

    void update(XmlSourceBuffer out, ITableMetadata table) {
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
                    Phrase name = Phrase.foo_bar(column.getName());
                    out.printf("%s = #{%s}", name.foo_bar, name.fooBar);
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

    void delete(XmlSourceBuffer out, ITableMetadata table) {
        out.println("<delete id=\"delete\">");
        out.enter();
        {
            out.println("delete from " + table.getCompactName() + " where id = #{id}");
            out.leave();
        }
        out.println("</delete>");
    }

}