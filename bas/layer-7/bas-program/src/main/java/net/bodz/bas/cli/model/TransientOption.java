package net.bodz.bas.cli.model;

import java.lang.reflect.AnnotatedElement;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.ListNegotiation;
import net.bodz.bas.lang.negotiation.Negotiation;
import net.bodz.bas.meta.decl.ItemType;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IFormatter;
import net.bodz.bas.traits.IParser;
import net.bodz.mda.xjdoc.model1.TransientArtifactElement;

public abstract class TransientOption
        extends TransientArtifactElement
        implements IOption {

    private static final long serialVersionUID = 1L;

    Class<?> declaringType;
    String name;

    int priority;
    IOptionGroup group;

    Set<String> aliases = new TreeSet<String>();
    int argPosition;

    String valueHint;
    boolean hidden;
    boolean required;
    int parameterCount;

    Class<?> type;
    Class<?> valueType;
    IAddor addor;

    Object defaultValue;

    public TransientOption(Class<?> declaringType, String name, AnnotatedElement member, Class<?> type) {
        this.declaringType = declaringType;
        this.name = Strings.hyphenatize(name);

        if (type == null)
            throw new NullPointerException("type");
        this.type = type;

        ItemType _itemType = member.getAnnotation(ItemType.class);
        if (_itemType != null)
            valueType = _itemType.value();
        else if (type.isArray())
            valueType = type.getComponentType();
        else
            valueType = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

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
        if (TypeKind.isBoolean(type))
            return 0;
        else
            return 1;
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
        IParser<?> parser = Traits.getTrait(valueType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + valueType);

        ListNegotiation negotiation = Negotiation.list(//
                Negotiation.option(IParser.PARSE_CONTEXT, context));

        Object value = parser.parse(param1, negotiation);
        return value;
    }

    @Override
    public String[] formatValue(Object context, Object value)
            throws FormatException {
        Class<?> valueType = getValueType();
        IFormatter<Object> formatter = Traits.getTrait(valueType, IFormatter.class);
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
