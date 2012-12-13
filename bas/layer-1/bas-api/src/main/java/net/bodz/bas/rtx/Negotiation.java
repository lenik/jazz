package net.bodz.bas.rtx;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import net.bodz.bas.rtx.INegotiation.IParameter;

public class Negotiation {

    public static ListNegotiation list(List<IParameter> parameters) {
        return new ListNegotiation(parameters);
    }

    public static ListNegotiation list(IParameter... parameters) {
        return new ListNegotiation(Arrays.asList(parameters));
    }

    public static ListNegotiation list(final Object... typedParameters) {
        if (typedParameters == null)
            throw new NullPointerException("typedParameters");

        List<IParameter> parameters = new AbstractList<IParameter>() {

            @Override
            public IParameter get(int index) {
                Object typedObj = typedParameters[index];
                return new Option(typedObj);
            }

            @Override
            public int size() {
                return typedParameters.length;
            }

        };
        return new ListNegotiation(parameters);
    }

    public static MapNegotiation map() {
        return new MapNegotiation(new LinkedHashMap<String, IParameter>());
    }

    public static <T> Option option(Class<T> type, T value) {
        return new Option(type, value);
    }

    public static Option option(Object typedValue) {
        return new Option(typedValue);
    }

    public static Option option(String id, Object value) {
        return new Option(id, value);
    }

    public static <T> Parameter parameter(Class<T> type, T value) {
        return new Parameter(type, value);
    }

    public static Parameter parameter(Object typedValue) {
        return new Parameter(typedValue);
    }

    public static Parameter parameter(String id, Object value) {
        return new Parameter(id, value);
    }

}
