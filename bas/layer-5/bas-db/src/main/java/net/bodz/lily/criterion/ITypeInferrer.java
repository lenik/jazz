package net.bodz.lily.criterion;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface ITypeInferrer {

    Class<?> getFieldType(List<String> fieldNames);

    default Class<?> getFieldType(String... fieldNames) {
        return getFieldType(Arrays.asList(fieldNames));
    }

}
