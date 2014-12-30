package net.bodz.bas.c.type;

/**
 * Keep the id compatible with eclipse jdt TypeIds.
 */
public interface TypeId {

    int UNDEFINED = 0;
    int OBJECT = 1;
    int _char = 2;
    int _byte = 3;
    int _short = 4;
    int _boolean = 5;
    int _void = 6;
    int _long = 7;
    int _double = 8;
    int _float = 9;
    int _int = 10;
    int STRING = 11;
    int _null = 12;

    int CLASS = 16;
    int STRING_BUFFER = 17;
    int SYSTEM = 18;

    int ERROR = 19;
    int THROWABLE = 21;
    int RUNTIME_EXCEPTION = 24;
    int EXCEPTION = 25;

    int BYTE = 26;
    int SHORT = 27;
    int CHARACTER = 28;
    int INTEGER = 29;
    int LONG = 30;
    int FLOAT = 31;
    int DOUBLE = 32;
    int BOOLEAN = 33;
    int VOID = 34;

    int ITERABLE = 38;
    int STRING_BUILDER = 40;
    int ENUM = 41;
    int DEPRECATED = 44;
    int OVERRIDE = 47;
    int SUPPRESS_WARNINGS = 49;

    int DATE = 1000;
    int SQL_DATE = 1001;
    int CALENDAR = 1002;

    int Z_PREDEF = 2000;
}
