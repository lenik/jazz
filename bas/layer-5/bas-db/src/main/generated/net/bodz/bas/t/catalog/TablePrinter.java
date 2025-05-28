package net.bodz.bas.t.catalog;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.db.meta.IGenericRowType;
import net.bodz.bas.fn.SupplierThrows;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.NotNull;

public abstract class TablePrinter<This extends TablePrinter<This, RowType, Row>, RowType, Row> {

    final IPrintOut out;
    final IGenericRowType<RowType, Row> ROW_TYPE;

    public static final int HEADER_NONE = 0;
    public static final int HEADER_SQL_TYPE = 1;
    public static final int HEADER_SQL_TYPE_NAME = 2;
    public static final int HEADER_CLASS_NAME = 3;
    public static final int HEADER_CLASS_SIMPLE_NAME = 4;
    int showTypeHeader = HEADER_NONE;

    boolean showHeader = true;
    boolean showHr = true;
    String delimiter = "  ";

    boolean showPageInfo = false;
    int pageSize = 0;

    Function<String, Integer> length = String::length;
    Predicate<IResultColumnMetaData> filter = c -> true;
    Map<Integer, Function<Object, String>> sqlTypeFormats = new HashMap<>();
    Map<String, Function<Object, String>> sqlTypeNameFormats = new HashMap<>();
    TypePoMap<Function<Object, String>> typeFormats = new TypePoMap<>();

    int columnCount;
    boolean[] selection;

    int[] sqlTypes;
    String[] sqlTypeNames;
    Class<?>[] types;

    char[] aligns;
    String[] typeHeader;
    String[] header;

    public TablePrinter(IPrintOut out, IGenericRowType<RowType, Row> ROW_TYPE)
            throws SQLException {
        this.out = out;
        this.ROW_TYPE = ROW_TYPE;
    }

    @SuppressWarnings("unchecked")
    protected This _this() {
        return (This) this;
    }

    protected int len(String s) {
        return length.apply(s);
    }

    public void format(int sqlType, Function<Object, String> format) {
        sqlTypeFormats.put(sqlType, format);
    }

    public void format(String sqlTypeName, Function<Object, String> format) {
        sqlTypeNameFormats.put(sqlTypeName, format);
    }

    @SuppressWarnings("unchecked")
    public <T> void format(Class<T> type, Function<T, String> format) {
        typeFormats.put(type, (Function<Object, String>) format);
    }

    public void setUp(RowType metaData)
            throws SQLException {
        columnCount = ROW_TYPE.getColumnCount(metaData);

        selection = new boolean[columnCount];
        typeHeader = new String[columnCount];
        header = new String[columnCount];

        sqlTypes = new int[columnCount];
        sqlTypeNames = new String[columnCount];
        types = new Class<?>[columnCount];

        aligns = new char[columnCount];
        for (int col = 0; col < columnCount; col++) {
            IResultColumnMetaData md = ROW_TYPE.getColumn(metaData, col);
            selection[col] = filter.test(md);

            String name = md.getColumnName();
            if (name == null)
                throw new NullPointerException("name");
            header[col] = name;

            int sqlType = md.getColumnType();
            String sqlTypeName = md.getColumnTypeName();
            assert sqlTypeName != null;

            sqlTypes[col] = sqlType;
            sqlTypeNames[col] = sqlTypeName;

            String columnClassName = md.getColumnClassName();
            if (columnClassName == null)
                throw new NullPointerException("columnClassName");

            SqlTypeEnum typeEnum = SqlTypeEnum.forSQLTypeName(sqlType, sqlTypeName);
            Class<?> type;
            try {
                type = Class.forName(columnClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            types[col] = type;

            switch (showTypeHeader) {
                case HEADER_SQL_TYPE:
                    typeHeader[col] = Nullables.toString(typeEnum);
                    break;
                case HEADER_SQL_TYPE_NAME:
                    typeHeader[col] = sqlTypeName;
                    break;
                case HEADER_CLASS_NAME:
                    typeHeader[col] = type.getName();
                    break;
                case HEADER_CLASS_SIMPLE_NAME:
                    typeHeader[col] = type.getSimpleName();
                    break;
            }

            char align = 'L';
            if (Number.class.isAssignableFrom(type))
                align = 'R';
            aligns[col] = align;
        }
    }

    @NotNull
    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(@NotNull String delimiter) {
        this.delimiter = delimiter;
    }

    @NotNull
    public Function<String, Integer> getLength() {
        return length;
    }

    public void setLength(@NotNull Function<String, Integer> length) {
        this.length = length;
    }

    @NotNull
    public Predicate<IResultColumnMetaData> getFilter() {
        return filter;
    }

    public void setFilter(@NotNull Predicate<IResultColumnMetaData> filter) {
        this.filter = filter;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getShowTypeHeader() {
        return showTypeHeader;
    }

    public void setShowTypeHeader(int showTypeHeader) {
        this.showTypeHeader = showTypeHeader;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isShowHr() {
        return showHr;
    }

    public void setShowHr(boolean showHr) {
        this.showHr = showHr;
    }

    public boolean isShowPageInfo() {
        return showPageInfo;
    }

    public void setShowPageInfo(boolean showPageInfo) {
        this.showPageInfo = showPageInfo;
    }

    public This delimiter(@NotNull String delimiter) {
        this.delimiter = delimiter;
        return _this();
    }

    public This length(@NotNull Function<String, Integer> length) {
        this.length = length;
        return _this();
    }

    static final Charset GBK = Charset.forName("GBK");

    public This lengthGBK() {
        this.length = s -> s.getBytes(GBK).length;
        return _this();
    }

    public This filter(@NotNull Predicate<IResultColumnMetaData> filter) {
        this.filter = filter;
        return _this();
    }

    public This select(String... columnNames) {
        Set<String> set = new HashSet<>(Arrays.asList(columnNames));
        this.filter = c -> set.contains(c.getColumnName());
        return _this();
    }

    public This pageSize(int pageSize) {
        this.pageSize = pageSize;
        return _this();
    }

    public This showTypeHeader(int showTypeHeader) {
        this.showTypeHeader = showTypeHeader;
        return _this();
    }

    public This showHeader(boolean showHeader) {
        this.showHeader = showHeader;
        return _this();
    }

    public This showHr(boolean showHr) {
        this.showHr = showHr;
        return _this();
    }

    public This showPageInfo(boolean showPageInfo) {
        this.showPageInfo = showPageInfo;
        return _this();
    }

    public void print(RowType rowType, SupplierThrows<Row, SQLException> rows)
            throws SQLException {
        setUp(rowType);

        List<String[]> cellsList = new ArrayList<>();

        Row row;
        while ((row = rows.get()) != null) {
            String[] cells = new String[columnCount];
            for (int col = 0; col < columnCount; col++) {
                Function<Object, String> format = typeFormats.meet(types[col]);
                if (format == null) {
                    format = sqlTypeNameFormats.get(sqlTypeNames[col]);
                    if (format == null)
                        format = sqlTypeFormats.get(sqlTypes[col]);
                }

                Object val = ROW_TYPE.getColumnData(row, col);

                String cell;
                if (format == null)
                    cell = Nullables.toString(val);
                else
                    cell = format.apply(val);
                cells[col] = cell;
            }
            cellsList.add(cells);
        }

        int[] maxLens = new int[columnCount];
        for (int col = 0; col < columnCount; col++)
            if (showTypeHeader != HEADER_NONE)
                maxLens[col] = Math.max(len(header[col]), len(typeHeader[col]));
            else
                maxLens[col] = len(header[col]);

        for (String[] cells : cellsList) {
            for (int col = 0; col < cells.length; col++) {
                String cell = cells[col];
                int len = cell == null ? 0 : len(cell);
                if (len > maxLens[col])
                    maxLens[col] = len;
            }
        }

        String[] headerHr = new String[columnCount];
        for (int col = 0; col < columnCount; col++)
            headerHr[col] = Strings.repeat(maxLens[col], '-');

        int rowIndex = 0;
        for (String[] cells : cellsList) {
            int page = 0;
            int rowInPage = rowIndex;
            if (pageSize > 0) {
                page = rowIndex / pageSize;
                rowInPage = rowIndex % pageSize;
            }

            if (showPageInfo && rowInPage == 0) {
                if (page != 0)
                    out.println();
                out.println("Page " + (page + 1));
            }
            if (showHeader && rowInPage == 0) {
                if (showTypeHeader != HEADER_NONE)
                    printRow(typeHeader, maxLens, aligns);
                printRow(header, maxLens, aligns);
                if (showHr)
                    printRow(headerHr, maxLens, aligns);
            }

            printRow(cells, maxLens, aligns);

            rowIndex++;
        }
    }

    void printRow(String[] cells, int[] maxLens, char[] aligns) {
        int displayCol = 0;
        for (int col = 0; col < cells.length; col++) {
            if (!selection[col])
                continue;

            String cell = cells[col];
            if (cell == null)
                cell = "-";

            switch (aligns[col]) {
                case 'L':
                    cell = Strings.padRight(cell, maxLens[col]);
                    break;
                case 'R':
                    cell = Strings.padLeft(cell, maxLens[col]);
                    break;
            }

            if (displayCol != 0)
                out.print(delimiter);
            out.print(cell);
            displayCol++;
        }
        out.println();
    }

}
