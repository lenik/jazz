package net.bodz.bas.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import net.bodz.bas.meta.decl.NotNull;

public class GenericType {

    final Class<?> type;
    final List<GenericType> parameters;

    public GenericType(@NotNull Class<?> type) {
        this.type = type;
        this.parameters = Collections.emptyList();
    }

    public GenericType(@NotNull Class<?> type, @NotNull GenericType... parameters) {
        this.type = type;
        this.parameters = Arrays.asList(parameters);
    }

    public GenericType(@NotNull Class<?> type, @NotNull Class<?>... parameters) {
        this.type = type;
        this.parameters = new ArrayList<>(parameters.length);
        for (Class<?> parameter : parameters)
            this.parameters.add(new GenericType(parameter));
    }

    @NotNull
    public Class<?> getType() {
        return type;
    }

    @NotNull
    public List<GenericType> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        GenericType that = (GenericType) o;
        return Objects.equals(type, that.type) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, parameters);
    }

    public String getName(Function<Class<?>, String> format) {
        StringBuilder buf = new StringBuilder();
        buf.append(format.apply(type));
        if (!parameters.isEmpty()) {
            buf.append('<');
            int i = 0;
            for (GenericType parameter : parameters) {
                if (i++ != 0)
                    buf.append(", ");
                buf.append(parameter.getName(format));
            }
            buf.append('>');
        }
        return buf.toString();
    }

    public String getName() {
        return getName(Class::getName);
    }

    public String getSimpleName() {
        return getName(Class::getSimpleName);
    }

    @Override
    public String toString() {
        return getName();
    }

}
