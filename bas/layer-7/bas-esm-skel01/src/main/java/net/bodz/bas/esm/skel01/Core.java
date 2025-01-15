package net.bodz.bas.esm.skel01;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.SourcePath;

public class Core
        extends EsmModule {

    public Core(int priority) {
        super("skel01-core", "src", priority);
    }

    @SourcePath("lang/basetype")
    public class BaseType
            extends Source {
        public final EsmName _byte = type("byte");
        public final EsmName _short = type("short");
        public final EsmName _int = type("int");
        public final EsmName _long = type("long");
        public final EsmName _float = type("float");
        public final EsmName _double = type("double");
        public final EsmName _char = type("char");
        public final EsmName _boolean = type("_boolean");
        public final EsmName _string = type("_string");
        public final EsmName BigInteger = type("BigInteger");
        public final EsmName BigDecimal = type("BigDecimal");
//        public final EsmName _Date = type("_Date");
//        public final EsmName SQLDate = type("SQLDate");
//        public final EsmName Timestamp = type("Timestamp");

        public final EsmName Enum = type("Enum");
        public final EsmName List = type("List");
        public final EsmName _Set = type("_Set");
        public final EsmName _Map = type("_Map");

        public final EsmName InetAddress = type("InetAddress");
    }

    @SourcePath("lang/baseinfo")
    public class BaseInfo
            extends Source {
        public final EsmName BYTE = value("BYTE");
        public final EsmName SHORT = value("SHORT");
        public final EsmName INT = value("INT");
        public final EsmName LONG = value("LONG");
        public final EsmName FLOAT = value("FLOAT");
        public final EsmName DOUBLE = value("DOUBLE");
        public final EsmName BIG_INTEGER = value("BIG_INTEGER");
        public final EsmName BIG_DECIMAL = value("BIG_DECIMAL");
        public final EsmName BOOLEAN = value("BOOLEAN");
        public final EsmName CHAR = value("CHAR");
        public final EsmName STRING = value("STRING");
        public final EsmName ENUM = value("ENUM");
//        public final EsmName DATE = value("DATE");

        public final EsmName ARRAY = value("ARRAY");

        public final EsmName ListType = type("ListType");
        public final EsmName SetType = type("SetType");
        public final EsmName MapType = type("MapType");
        public final EsmName LIST = value("LIST");
        public final EsmName SET = value("SET");
        public final EsmName MAP = value("MAP");

        public final EsmName INET_ADDRESS = value("INET_ADDRESS");
    }

    @SourcePath("lang/bas-type")
    public class BasType
            extends Source {
        public final EsmName JsonVariant = type("JsonVariant");
//        public final EsmName State = value("State");
        public final EsmName Predef = value("Predef");
    }

    @SourcePath("lang/bas-info")
    public class BasInfo
            extends Source {
        public final EsmName JSON_VARIANT = value("JSON_VARIANT");
//        public final EsmName STATE = value("STATE"); // reserved. use int instead.
        public final EsmName PREDEF = value("PREDEF"); // don't use abstract type.
    }

    @SourcePath("lang/time")
    public class LangTime
            extends Source {
        public final EsmName JavaDate = type("JavaDate");
        public final EsmName SQLDate = type("SQLDate");
        public final EsmName SQLTime = type("SQLTime");
        public final EsmName Timestamp = type("Timestamp");
        public final EsmName JAVA_DATE = value("JAVA_DATE");
        public final EsmName SQL_DATE = value("SQL_DATE");
        public final EsmName SQL_TIME = value("SQL_TIME");
        public final EsmName TIMESTAMP = value("TIMESTAMP");

        public final EsmName Instant = childDefault("Instant");
        public final EsmName LocalDate = childDefault("LocalDate");
        public final EsmName LocalTime = childDefault("LocalTime");
        public final EsmName LocalDateTime = childDefault("LocalDateTime");
        public final EsmName OffsetTime = childDefault("OffsetTime");
        public final EsmName OffsetDateTime = childDefault("OffsetDateTime");
        public final EsmName ZonedDateTime = childDefault("ZonedDateTime");
        public final EsmName LunarDate = childDefault("LunarDate");
    }

    @SourcePath("logging/api")
    public class LoggingApi
            extends Source {

        public final EsmName LogLevel = type("LogLevel");
        public final EsmName Exception = _interface("Exception");
        public final EsmName StackTrackElement = _interface("StackTrackElement");
        public final EsmName LogEntry = _interface("LogEntry");
        public final EsmName levelIcon = name("levelIcon");
        public final EsmName simpleName = name("simpleName");
        public final EsmName typeLabel = name("typeLabel");
        public final EsmName causes = name("causes");
        public final EsmName parseException = name("parseException");
        public final EsmName Logger = _interface("Logger");

    }

    @SourcePath("net/bodz/bas/repr/state")
    public class State
            extends Source {

        public final EsmName DefaultState = childDefault("DefaultState");

    }

    @SourcePath("ui/types")
    public class UiTypes
            extends Source {

        public final EsmName bool = name("bool");
        public final EsmName UiGroupItem = _interface("UiGroupItem");
        public final EsmName UiComponent = _interface("UiComponent");
        public final EsmName NameSet = _interface("NameSet");
        public final EsmName Selector = _interface("Selector");
        public final EsmName select = name("select");
        public final EsmName UiGroup = _interface("UiGroup");
        public final EsmName UiGroupMap = _interface("UiGroupMap");
        public final EsmName group = name("group");
        public final EsmName DialogAction = type("DialogAction");
        public final EsmName EventHandler = type("EventHandler");
        public final EsmName Href = type("Href");
        public final EsmName Command = _interface("Command");
        public final EsmName DialogSelectCallback = type("DialogSelectCallback");
        public final EsmName AsyncDialogSelectCallback = type("AsyncDialogSelectCallback");
        public final EsmName CommandBehavior = _interface("CommandBehavior");
        public final EsmName getDialogCmds = name("getDialogCmds");
        public final EsmName IValidateResult = _interface("IValidateResult");
        public final EsmName ValidateResult = _interface("ValidateResult");
        public final EsmName Validator = type("Validator");
        public final EsmName MessageFunc = type("MessageFunc");
        public final EsmName Status = _interface("Status");

    }

    @SourcePath("ui/FieldRow.vue")
    public class FieldRow
            extends Source {
        public final EsmName FieldRow = defaultExport("FieldRow");
        public final EsmName FIELD_ROW_PROPS = name("FIELD_ROW_PROPS");
    }

    @SourcePath("lang/VarMap")
    public class VarMap
            extends Source {

        public final EsmName INameMap = _interface("INameMap");
        public final EsmName INamedStrings = _interface("INamedStrings");
        public final EsmName VarMap = _interface("VarMap");

    }

    public final EsmName TypeInfo = defaultClass("lang/TypeInfo");
    public final EsmName Predef = defaultClass("lang/Predef");
    public final EsmName PredefType = defaultClass("lang/PredefType");

    public final BaseType baseType = add(new BaseType());
    public final BaseInfo baseInfo = add(new BaseInfo());
    public final BasType basType = add(new BasType());
    public final BasInfo basInfo = add(new BasInfo());
    public final LangTime time = new LangTime();

    public final LoggingApi loggingApi = add(new LoggingApi());
    public final State state = add(new State());

    public final UiTypes uiTypes = add(new UiTypes());
    public final VarMap VarMap = add(new VarMap());

    public final EsmName Dialog = vue("ui/Dialog.vue");
    public final EsmName DateTime = vue("ui/input/DateTime.vue");
    public final FieldRow FieldRow = add(new FieldRow());
    public final EsmName JsonEditor = vue("ui/input/JsonEditor.vue");

    static final String[] domainPatterns = { //
            "lang", //
            // "java.time", // lang.time
            "net.bodz.bas.i18n", //
            "net.bodz.bas.repr", //
    };

    @Override
    public String[] getExclusiveDomains() {
        return domainPatterns;
    }

}
