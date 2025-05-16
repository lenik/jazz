package net.bodz.bas.rtx;

import net.bodz.bas.meta.decl.NotNull;

public interface IMutableOptions
        extends IOptions {

    IMutableOptions addOption(IOption option);

    default IMutableOptions addOption(String id, Object value) {
        if (id == null)
            throw new NullPointerException("id");
        Option option = new Option(id, value);
        addOption(option);
        return this;
    }

    default <T> IMutableOptions addOption(Class<T> type, T value) {
        if (type == null)
            throw new NullPointerException("type");
        Option option = new Option(type, value);
        addOption(option);
        return this;
    }

    default IMutableOptions addOption(Object typedValue) {
        if (typedValue == null)
            throw new NullPointerException("typedValue");
        Option option = new Option(typedValue);
        addOption(option);
        return this;
    }

    IOption removeOption(@NotNull String id);

    default IOption removeOption(@NotNull IOption option) {
        return removeOption(option.getId());
    }

    default IOption removeOption(Class<?> type) {
        return removeOption(type.getName());
    }

}
