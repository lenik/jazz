package net.bodz.bas.db.sql.dialect;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.bodz.bas.db.sql.DataType;
import net.bodz.bas.db.sql.IStdDataTypes;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.JsonVariant;

import static net.bodz.bas.db.sql.DataType.newType;

public class PostgreSQL
        extends AbstractSqlDialect {

    public static final DataType BPCHAR = newType(String.class, Types.CHAR, "bpchar").build();
    public static final DataType INT2 = newType(Short.class, Types.SMALLINT, "int2").build();
    public static final DataType INT4 = newType(Integer.class, Types.INTEGER, "int4").build();
    public static final DataType INT8 = newType(Long.class, Types.BIGINT, "int8").build();
    public static final DataType FLOAT4 = newType(Float.class, Types.REAL, "float4").build();
    public static final DataType FLOAT8 = newType(Double.class, Types.DOUBLE, "float8").build();

    public static final DataType BYTEA = newType(byte[].class, Types.OTHER, "bytea").build();
    public static final DataType JSON = newType(JsonVariant.class, Types.OTHER, "json").build();
    public static final DataType JSONB = newType(JsonVariant.class, Types.OTHER, "jsonb").build();
    public static final DataType INET = newType(InetAddress.class, Types.OTHER, "inet").build();
    public static final DataType UUID = newType(UUID.class, Types.OTHER, "uuid").build();

    public static final DataType SQL_IDENTIFIER = newType(String.class, Types.DISTINCT, "information_schema.sql_identifier").build();
    public static final DataType YES_OR_NO = newType(Boolean.class, Types.DISTINCT, "information_schema.yes_or_no").build();
    public static final DataType CHARACTER_DATA = newType(String.class, Types.DISTINCT, "information_schema.character_data").build();

    public PostgreSQL() {
    }

    @Override
    protected void declareTypes() {
        super.declareTypes();
        dataTypes.add(BPCHAR);
        dataTypes.add(INT2, INT4, INT8);
        dataTypes.add(FLOAT4, FLOAT8);
        dataTypes.add(BYTEA, JSON, JSONB, INET, UUID);
        dataTypes.add(SQL_IDENTIFIER, YES_OR_NO, CHARACTER_DATA);

        DataType ARRAY = newType(java.sql.Array.class, Types.ARRAY, "array") //
                .refiner(column -> {
                    String sqlTypeName = column.getColumnTypeName();
                    if (!sqlTypeName.startsWith("_"))
                        throw new UnexpectedException();

                    String componentSqlTypeName = sqlTypeName.substring(1);

                    DataType componentType = getDefaultType(componentSqlTypeName);
                    if (componentType == null)
                        throw new UnexpectedException("unknown component type: " + componentSqlTypeName);

                    Class<?> componentClass = componentType.getJavaClass();
                    Class<?> arrayClass = Array.newInstance(componentClass, 0).getClass();
                    return arrayClass;
                }).build();
        dataTypes.replace(IStdDataTypes.ARRAY, ARRAY);
    }

    @Override
    public boolean isReservedKeyword(String token) {
        if (reserved.contains(token))
            return true;
        return super.isReservedKeyword(token);
    }

    static final Set<String> reserved;

    static {
        String[] data = { "a", "abs", "absent", "absolute", "according", "acos", "action", "ada", "add", "admin", "after", "all", "allocate", "alter", "always", "analyse", "analyze", "and", "any", "are", "array", "array_agg", "array_max_cardinality", "as", "asc", "asensitive", "asin", "assertion", "assignment", "asymmetric", "at", "atan", "atomic", "attribute", "attributes", "authorization", "avg", "base64", "before", "begin", "begin_frame", "begin_partition", "bernoulli", "between", "bigint", "binary", "bit", "bit_length", "blob", "blocked", "bom", "boolean", "both", "breadth", "by", "c", "call", "called", "cardinality", "cascade", "cascaded", "case", "cast", "catalog", "catalog_name", "ceil", "ceiling", "chain", "char", "character", "characteristics", "characters", "character_length", "character_set_catalog", "character_set_name", "character_set_schema", "char_length", "check", "classifier", "class_origin", "clob", "close", "coalesce", "cobol", "collate", "collation", "collation_catalog", "collation_name", "collation_schema", "collect", "column", "columns", "column_name", "command_function", "command_function_code", "commit", "committed", "concurrently", "condition", "condition_number", "connect", "connection", "connection_name", "constraint", "constraints", "constraint_catalog", "constraint_name", "constraint_schema", "constructor", "contains", "content", "continue", "control", "convert", "copy", "corr", "corresponding", "cos", "cosh", "count", "covar_pop", "covar_samp", "create", "cross", "cube", "cume_dist", "current", "current_catalog", "current_date", "current_default_transform_group", "current_path", "current_role", "current_row", "current_schema", "current_time", "current_timestamp", "current_transform_group_for_type", "current_user", "cursor", "cursor_name", "cycle", "data", "datalink", "date", "datetime_interval_code", "datetime_interval_precision", "day", "db", "deallocate", "dec", "decfloat", "decimal", "declare", "default", "defaults", "deferrable", "deferred", "define", "defined", "definer", "degree", "delete", "dense_rank", "depth", "deref", "derived", "desc", "describe", "descriptor", "deterministic", "diagnostics", "disconnect", "dispatch", "distinct", "dlnewcopy", "dlpreviouscopy", "dlurlcomplete", "dlurlcompleteonly", "dlurlcompletewrite", "dlurlpath", "dlurlpathonly", "dlurlpathwrite", "dlurlscheme", "dlurlserver", "dlvalue", "do", "document", "domain", "double", "drop", "dynamic", "dynamic_function", "dynamic_function_code", "each", "element", "else", "empty", "encoding", "end", "end-exec", "end_frame", "end_partition", "enforced", "equals", "escape", "every", "except", "exception", "exclude", "excluding", "exec", "execute", "exists", "exp", "expression", "external", "extract", "false", "fetch", "file", "filter", "final", "first", "first_value", "flag", "float", "floor", "following", "for", "foreign", "fortran", "found", "frame_row", "free", "freeze", "from", "fs", "full", "function", "fusion", "g", "general", "generated", "get", "global", "go", "goto", "grant", "granted", "group", "grouping", "groups", "having", "hex", "hierarchy", "hold", "hour",
                // "id",
                "identity", "ignore", "ilike", "immediate", "immediately", "implementation", "import", "in", "including", "increment", "indent", "indicator", "initial", "initially", "inner", "inout", "input", "insensitive", "insert", "instance", "instantiable", "instead", "int", "integer", "integrity", "intersect", "intersection", "interval", "into", "invoker", "is", "isnull", "isolation", "join", "json_array", "json_arrayagg", "json_exists", "json_object", "json_objectagg", "json_query", "json_table", "json_table_primitive", "json_value", "k", "key", "key_member", "key_type", "lag", "language", "large", "last", "last_value", "lateral", "lead", "leading", "left", "length", "level", "library", "like", "like_regex", "limit", "link", "listagg", "ln", "local", "localtime", "localtimestamp", "location", "locator", "log", "log10", "lower", "m", "map", "mapping", "match", "matched", "matches", "match_number", "match_recognize", "max", "maxvalue", "measures", "member", "merge", "message_length", "message_octet_length", "message_text", "method", "min", "minute", "minvalue", "mod", "modifies", "module", "month", "more", "multiset", "mumps", "name", "names", "namespace", "national", "natural", "nchar", "nclob", "nesting", "new", "next", "nfc", "nfd", "nfkc", "nfkd", "nil", "no", "none", "normalize", "normalized", "not", "notnull", "nth_value", "ntile", "null", "nullable", "nullif", "nulls", "number", "numeric", "object", "occurrences_regex", "octets", "octet_length", "of", "off", "offset", "old", "omit", "on", "one", "only", "open", "option", "options", "or", "order", "ordering", "ordinality", "others", "out", "outer", "output", "over", "overlaps", "overlay", "overriding", "p", "pad", "parameter", "parameter_mode", "parameter_name", "parameter_ordinal_position", "parameter_specific_catalog", "parameter_specific_name", "parameter_specific_schema", "partial", "partition", "pascal", "passing", "passthrough", "path", "pattern", "per", "percent", "percentile_cont", "percentile_disc", "percent_rank", "period", "permission", "permute", "placing", "pli", "portion", "position", "position_regex", "power", "precedes", "preceding", "precision", "prepare", "preserve", "primary", "prior", "privileges", "procedure", "ptf", "public", "range", "rank", "read", "reads", "real", "recovery", "recursive", "ref", "references", "referencing", "regr_avgx", "regr_avgy", "regr_count", "regr_intercept", "regr_r2", "regr_slope", "regr_sxx", "regr_sxy", "regr_syy", "relative", "release", "repeatable", "requiring", "respect", "restart", "restore", "restrict", "result", "return", "returned_cardinality", "returned_length", "returned_octet_length", "returned_sqlstate", "returning", "returns", "revoke", "right", "role", "rollback", "rollup", "routine", "routine_catalog", "routine_name", "routine_schema", "row", "rows", "row_count", "row_number", "running", "savepoint", "scale", "schema", "schema_name", "scope", "scope_catalog", "scope_name", "scope_schema", "scroll", "search", "second", "section", "security", "seek", "select", "selective", "self", "sensitive", "sequence", "serializable", "server", "server_name", "session", "session_user", "set", "sets", "show", "similar", "simple", "sin", "sinh", "size", "skip", "smallint", "some", "source", "space", "specific", "specifictype", "specific_name", "sql", "sqlcode", "sqlerror", "sqlexception", "sqlstate", "sqlwarning", "sqrt", "standalone", "start", "state", "statement", "static", "stddev_pop", "stddev_samp", "strip", "structure", "style", "subclass_origin", "submultiset", "subset", "substring", "substring_regex", "succeeds", "sum", "symmetric", "system", "system_time", "system_user", "t", "table", "tablesample", "table_name", "tan", "tanh", "temporary", "then", "ties", "time", "timestamp", "timezone_hour", "timezone_minute", "to", "token", "top_level_count", "trailing", "transaction", "transactions_committed", "transactions_rolled_back", "transaction_active", "transform", "transforms", "translate", "translate_regex", "translation", "treat", "trigger", "trigger_catalog", "trigger_name", "trigger_schema", "trim", "trim_array", "true", "truncate", "type", "uescape", "unbounded", "uncommitted", "under", "union", "unique", "unknown", "unlink", "unmatched", "unnamed", "unnest", "untyped", "update", "upper", "uri", "usage", "user", "user_defined_type_catalog", "user_defined_type_code", "user_defined_type_name", "user_defined_type_schema", "using", "valid", "value", "values", "value_of", "varbinary", "varchar", "variadic", "varying", "var_pop", "var_samp", "verbose", "version", "versioning", "view", "when", "whenever", "where", "whitespace", "width_bucket", "window", "with", "within", "without", "work", "wrapper", "write", "xml", "xmlagg", "xmlattributes", "xmlbinary", "xmlcast", "xmlcomment", "xmlconcat", "xmldeclaration", "xmldocument", "xmlelement", "xmlexists", "xmlforest", "xmliterate", "xmlnamespaces", "xmlparse", "xmlpi", "xmlquery", "xmlschema", "xmlserialize", "xmltable", "xmltext", "xmlvalidate", "year", "yes", "zone", };
        reserved = new HashSet<>();
        for (String k : data)
            reserved.add(k.toLowerCase());
    }

}
