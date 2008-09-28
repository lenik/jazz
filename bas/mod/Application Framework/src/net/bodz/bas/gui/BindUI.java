package net.bodz.bas.gui;

import net.bodz.bas.lang.script.ScriptClass;
import net.bodz.bas.lang.script.ScriptException;
import net.bodz.bas.lang.script.ScriptField;
import net.bodz.bas.lang.script.Scripts;

public class BindUI {

    private Class<?> uiClass;

    public void bind() throws ScriptException {
        uiClass.getDeclaredFields();
        ScriptClass<Object> sclass = Scripts.convertClass(uiClass);
        ScriptField<?>[] fields = sclass.getFields();
        // fields[0].
    }

}
