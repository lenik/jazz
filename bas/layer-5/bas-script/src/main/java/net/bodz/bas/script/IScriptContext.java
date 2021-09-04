package net.bodz.bas.script;

import java.io.IOException;

import javax.script.Invocable;

import net.bodz.bas.fn.EvalException;

public interface IScriptContext
        extends
            Invocable {

    Object getGlobalObject();

    Object get(String name);

    void put(String name, Object value);

    Object include(String filename)
            throws EvalException, IOException;

    Object eval(String code)
            throws EvalException, IOException;

    Object eval(String code, String fileName)
            throws EvalException, IOException;

    // Promise.
    Object loadModuleCode(String moduleCode)
            throws EvalException, IOException;

}
