package net.bodz.bas.lang.script;

public interface ScriptObject {

    ScriptClass<?> getScriptClass();

    Object getField(String fieldName);

    Object getField(String fieldName, Object... indexes);

    ScriptObject setField(String fieldName, Object fieldValue);

    ScriptObject setField(String fieldName, Object fieldValue,
            Object... indexes);

    Object invokeMethod(String methodName, Object... arguments);

}
