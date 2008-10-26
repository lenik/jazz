package net.bodz.mda.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.a.ReflectField;
import net.bodz.bas.lang.a.ReflectMethod;
import net.bodz.bas.lang.util.Reflects;

public abstract class ByaccjParser extends _Parser {

    @ReflectField
    private Field  yystate;

    @ReflectMethod(value = "state_peek", parameters = int.class)
    private Method state_peek;

    @ReflectField
    private Field  yylval;

    @ReflectMethod(value = "val_peek", parameters = int.class)
    private Method val_peek;

    @ReflectField(value = "yyname", type = String[].class)
    private Field  tokenNames;

    @ReflectField(value = "yyrule", type = String[].class)
    private Field  ruleNames;

    @Override
    public int state(int stackRelative) {
        if (stackRelative == 0)
            return (Integer) Reflects.get(this, yystate);
        else
            return (Integer) Reflects.invoke(this, state_peek,
                    (stackRelative - 1));
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object value) {
    }

}
