package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.lily.gen.XmlFragmentBuilder;

public class MapperXmlBuilder
        extends XmlFragmentBuilder<ITableMetadata> {

    public MapperXmlBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildBody(ITableMetadata model) {
        out.println("<!DOCTYPE mapper");
        out.println("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
        out.println("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        out.println("<mapper namespace=\"" + fragmentQName + "\">");
        out.enter();
        {
            out.println();
            resultMap_objlist_map(model);
            out.println();
            sql_objlist_sql(model);
            out.println();
            sql_objedit_sql(model);
            out.println();
            sql_filtconds(model);
            out.println();
            select_all(model);
            out.println();
            select_filter(model);
            out.println();
            select(model);
            out.println();
            insert(model);
            out.println();
            update(model);
            out.println();
            delete(model);
            out.println();
            select_count(model);
            out.println();
            out.leave();
        }
        out.println("</mapper>");
    }

    void resultMap_objlist_map(ITableMetadata table) {
        out.printf("<resultMap id=\"objlist_map\" type=\"%s\">\n", mainQName);
        out.enter();
        {
            for (IColumnMetadata column : table.getColumns()) {
                String col_name = column.getName();
                String colName = StringId.UL.toCamel(col_name);
                // Class<?> type = column.getType();
                out.printf("<result property=\"%s\" column=\"%s\" />\n", //
                        colName, col_name);
            }
            out.leave();
        }
        out.println("</resultMap>");
    }

    void sql_objlist_sql(ITableMetadata table) {
        out.println("<sql id=\"objlist_sql\"><![CDATA[");
        out.enter();
        {
            out.println("select");
            out.enter();
            {
                out.println("a.*");
                out.leave();
            }
            out.println("from " + table.getCompactName() + " a");
            out.println("]]>");
            out.leave();
        }
        out.println("</sql>");
    }

    void sql_objedit_sql(ITableMetadata table) {
        out.println("<sql id=\"objedit_sql\"><![CDATA[");
        out.enter();
        {
            out.println("select");
            out.enter();
            {
                out.println("a.*");
                out.leave();
            }
            out.println("from " + table.getCompactName() + " a");
            // out.enter();
            // out.println("left join zj_qfx_dm_mx m on a.mx_dm=m.mx_dm");
            // out.leave();
            out.println("]]>");
            out.leave();
        }
        out.println("</sql>");
    }

    void sql_filtconds(ITableMetadata table) {
        out.println("<sql id=\"filtconds\">");
        out.enter();
        {
            boolean includeCo = false;
            if (includeCo) {
                out.println("<!-- co -->");
                out.println("<include refid=\"co.modefilt\" />");
                out.println("<include refid=\"co.filter-id\" />");
                out.println("<include refid=\"co.filter-ui\" />");
                out.println("<include refid=\"co.filter-version\" />");
                out.println("<include refid=\"message.filter-all\" />");
            }

            for (IColumnMetadata column : table.getColumns()) {
                filter(column);
            }
            out.leave();
        }
        out.println("</sql>");
    }

    void select_all(ITableMetadata table) {
        out.println("<select id=\"all\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objlist_sql\" />");
            out.leave();
        }
        out.println("</select>");
    }

    void select_filter(ITableMetadata table) {
        out.println("<select id=\"filter\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objlist_sql\" />");
            out.println("<where>");
            out.enter();
            {
                out.println("<include refid=\"filtconds\" />");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
    }

    void select(ITableMetadata table) {
        out.println("<select id=\"select\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objedit_sql\" />");
            out.println("<where>");
            out.enter();
            {
                out.println("<if test=\"_parameter != null\">a.id = #{id}</if>");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
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

    void select_count(ITableMetadata table) {
        out.println("<select id=\"count\" resultType=\"hashmap\">");
        out.enter();
        {
            out.println("select count(*) \"rows\" from " + table.getCompactName());
            out.println("<where>");
            out.enter();
            {
                out.println("<if test=\"_parameter != null\">");
                out.enter();
                {
                    out.println("<include refid=\"filtconds\" />");
                    out.leave();
                }
                out.println("</if>");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
    }

    void filter(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);

        // MaskFieldModel mask = column.mask;
        String maskName = colName;
        boolean hasMain = true;
        boolean hasRange = false;
        boolean hasPattern = false;

        switch (TypeKind.getTypeId(column.getType())) {
        case TypeId._byte:
        case TypeId._short:
        case TypeId._long:
        case TypeId._double:
        case TypeId._float:
        case TypeId._int:
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
        case TypeId.FLOAT:
        case TypeId.DOUBLE:

        case TypeId.BIG_INTEGER:
        case TypeId.BIG_DECIMAL:

        case TypeId.DATE:
        case TypeId.SQL_DATE:
        case TypeId.JODA_DATETIME:

            hasRange = true;

            if (hasMain) {
                out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                        maskName, col_name, maskName);
            }

            if (hasRange) {
                String range = maskName + "Range";
                out.printf("<if test=\"m.%s!= null\">\n", range);
                out.enter();
                out.printf("<if test=\"m.%s.hasStartIncl\">and a.%s >= #{m.%s.start}</if>\n", //
                        range, col_name, range);
                out.printf("<if test=\"m.%s.hasStartExcl\">and a.%s > #{m.%s.start}</if>\n", //
                        range, col_name, range);
                out.printf("<if test=\"m.%s.hasEndIncl\">and a.%s &lt;= #{m.%s.end}</if>\n", //
                        range, col_name, range);
                out.printf("<if test=\"m.%s.hasEndExcl\">and a.%s &lt; #{m.%s.end}</if>\n", //
                        range, col_name, range);
                out.leave();
                out.println("</if>");
            }
            break;

        case TypeId.STRING:
            hasPattern = true;

            if (hasMain)
                out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                        maskName, col_name, maskName);
            if (hasPattern)
                out.printf("<if test=\"m.%sPattern != null\">and %s like '${m.%sPattern}'</if>\n", //
                        maskName, col_name, maskName);
            break;

        // case TypeId.ENUM:

        case TypeId._char:
        case TypeId.CHARACTER:

        case TypeId._boolean:
        case TypeId.BOOLEAN:
        default:
            out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                    maskName, col_name, maskName);
        }
    }

}