package net.bodz.bas.fmt.json;

import net.bodz.bas.t.list.Stack;

public interface IJsonFormOptionsAwareStacked {

    Stack<JsonFormOptions> getJsonFormOptionsStack();

    default JsonFormOptions getDefaultJsonFormOptions() {
        return JsonFormOptions.DEFAULT;
    }

    default JsonFormOptions getJsonFormOptions() {
        Stack<JsonFormOptions> stack = getJsonFormOptionsStack();
        if (stack.isEmpty())
            return getDefaultJsonFormOptions();
        else
            return stack.top();
    }

    default void pushOptions(JsonFormOptions options) {
        getJsonFormOptionsStack().push(options);
    }

    default JsonFormOptions popOptions() {
        return getJsonFormOptionsStack().pop();
    }

}
