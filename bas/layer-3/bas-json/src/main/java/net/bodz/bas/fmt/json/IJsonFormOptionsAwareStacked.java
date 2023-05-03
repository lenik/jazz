package net.bodz.bas.fmt.json;

import net.bodz.bas.t.list.StackList;

public interface IJsonFormOptionsAwareStacked {

    StackList<JsonFormOptions> getJsonFormOptionsStack();

    default JsonFormOptions getDefaultJsonFormOptions() {
        return JsonFormOptions.DEFAULT;
    }

    default JsonFormOptions getJsonFormOptions() {
        StackList<JsonFormOptions> stack = getJsonFormOptionsStack();
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
