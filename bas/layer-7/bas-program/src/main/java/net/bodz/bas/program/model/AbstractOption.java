package net.bodz.bas.program.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import net.bodz.bas.c.object.TrueValues;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.c.type.addor.ArrayAddor;
import net.bodz.bas.c.type.addor.CollectionAddor;
import net.bodz.bas.c.type.addor.IAddor;
import net.bodz.bas.c.type.addor.MapAddor;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.order.IMutablePriority;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.ExternXjdocElement;

public abstract class AbstractOption
        extends ExternXjdocElement
        implements
            IOption,
            IMutablePriority {

    String id;
    String longName;

    int priority;
    IOptionGroup group;

    Set<String> aliases = new TreeSet<String>();
    int argPosition;

    String valueHint;
    boolean hidden;
    boolean required;
    int parameterCount;

    Type genericType;
    Class<?> type;
    Class<?> valueType;
    IAddor addor = IAddor.REPLACE;

    Object defaultValue;

    public AbstractOption(String id, Type _type, IElementDoc doc) {
        this(id, id, _type, doc);
    }

    public AbstractOption(String id, String longName, Type _type, IElementDoc doc) {
        super(doc);

        this.id = id;
        this.longName = StringId.HYPHEN.breakCamel(longName);

        if (_type == null)
            throw new NullPointerException("type");
        this.genericType = _type;

        if (_type instanceof Class<?>)
            this.type = (Class<?>) _type;
        else if (_type instanceof ParameterizedType)
            this.type = (Class<?>) ((ParameterizedType) _type).getRawType();
        else
            throw new IllegalUsageException("Unsupported generic type: " + _type);

        if (type.isArray()) {
            valueType = type.getComponentType();
            addor = new ArrayAddor(valueType);
        }

        else if (List.class.isAssignableFrom(type)) {
            valueType = TypeParam.infer1(genericType, List.class, 0);

            Class<?> listType;
            if (type.isInterface())
                listType = ArrayList.class;
            else
                listType = type;

            addor = new CollectionAddor(listType);
        }

        else if (Set.class.isAssignableFrom(type)) {
            valueType = TypeParam.infer1(genericType, Set.class, 0);

            Class<?> setType;
            if (type.isInterface())
                setType = LinkedHashSet.class;
            else
                setType = type;

            addor = new CollectionAddor(setType);
        }

        else if (Map.class.isAssignableFrom(type)) {
            valueType = TypeParam.infer1(genericType, Map.class, 1);

            Class<?> mapType;
            if (type.isInterface())
                mapType = LinkedHashMap.class;
            else
                mapType = type;

            addor = new MapAddor(mapType);
        }

        else {
            valueType = type;
            addor = IAddor.REPLACE;
        }

        defaultValue = TrueValues.getTrueValue(valueType);
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public String getPreferredLongName() {
        return longName;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public IOptionGroup getGroup() {
        return group;
    }

    public void setGroup(IOptionGroup group) {
        this.group = group;
    }

    @Override
    public Set<String> getAliases() {
        return aliases;
    }

    public void addAlias(String alias) {
        aliases.add(alias);
    }

    public void removeAlias(String alias) {
        aliases.remove(alias);
    }

    @Override
    public int getArgPosition() {
        return argPosition;
    }

    public void setArgPosition(int argPosition) {
        this.argPosition = argPosition;
    }

    @Override
    public String getValueHint() {
        return valueHint;
    }

    public void setValueHint(String valueHint) {
        this.valueHint = valueHint;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public int getParameterCount() {
        if (TypeKind.isBoolean(valueType))
            return 0;
        else
            return 1;
    }

    @Override
    public Type getGenericType() {
        return genericType;
    }

    public void setGenericType(Type genericType) {
        if (genericType == null)
            throw new NullPointerException("genericType");
        this.genericType = genericType;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

    @Override
    public IAddor getAddor() {
        return addor;
    }

    public void setAddor(IAddor addor) {
        if (addor == null)
            throw new NullPointerException("addor");
        this.addor = addor;
    }

    @Override
    public Object parseValue(Object context, String... parameters)
            throws ParseException {
        String param1 = parameters[0];

        Class<?> valueType = getValueType();
        IParser<?> parser = Typers.getTyper(valueType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + valueType);

        IOptions options = new Options()//
                .addOption(IParser.PARSE_CONTEXT, context);

        Object value = parser.parse(param1, options);
        return value;
    }

    @Override
    public String[] formatValue(Object context, Object value)
            throws FormatException {
        Class<?> valueType = getValueType();
        IFormatter<Object> formatter = Typers.getTyper(valueType, IFormatter.class);
        String param1 = formatter.format(value);
        return new String[] { param1 };
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

}
