package net.bodz.lily.tool.javagen;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.XmlSourceBuffer;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class VFooMapper__xml
        extends JavaGen__xml {

    public VFooMapper__xml(JavaGenProject project) {
        super(project, project.FooMapper);
    }

    @Override
    protected void buildXmlBody(XmlSourceBuffer out, ITableViewMetadata table) {
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
            select_count(out, table);
            out.println();
            out.leave();
        }
        out.println("</mapper>");
    }

    void resultMap_objlist_map(XmlSourceBuffer out, ITableViewMetadata table) {
        out.printf("<resultMap id=\"objlist_map\" type=\"%s\">\n", project.Foo);
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

    void sql_objlist_sql(XmlSourceBuffer out, ITableViewMetadata table) {
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

    void sql_objedit_sql(XmlSourceBuffer out, ITableViewMetadata table) {
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

    void sql_filtconds(XmlSourceBuffer out, ITableViewMetadata table) {
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
                filter(out, column);
            }
            out.leave();
        }
        out.println("</sql>");
    }

    void select_all(XmlSourceBuffer out, ITableViewMetadata table) {
        out.println("<select id=\"all\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objlist_sql\" />");
            out.println("<include refid=\"co.opts\" />");
            out.leave();
        }
        out.println("</select>");
    }

    void select_filter(XmlSourceBuffer out, ITableViewMetadata table) {
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
            out.println("<include refid=\"co.opts\" />");
            out.leave();
        }
        out.println("</select>");
    }

    void select(XmlSourceBuffer out, ITableViewMetadata table) {
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

    void select_count(XmlSourceBuffer out, ITableViewMetadata table) {
        out.println("<select id=\"count\" resultType=\"long\">");
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

    void filter(XmlSourceBuffer out, IColumnMetadata column) {
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