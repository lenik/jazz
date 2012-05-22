package net.bodz.bas.cli.opt;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.meta.util.ComponentType;
import net.bodz.bas.potato.traits.AbstractElement;

public abstract class AbstractOption
        extends AbstractElement
        implements IOption {

    public static final int WEAK_PRIORITY = 10;

    int priority;
    IOptionGroup group;

    String friendlyName;
    Set<String> aliases = new TreeSet<String>();
    int argPosition;

    String valueHint;
    boolean hidden;
    boolean required;
    Object defaultValue; // Not used

    Class<?> type;

    boolean multiple;
    Class<?> itemType;
    Object defaultItemValue; // Not used

    public AbstractOption(Class<?> declaringType, String name, AnnotatedElement member, Class<?> type) {
        super(declaringType, name);
        friendlyName = Strings.hyphenatize(name);

        if (type == null)
            throw new NullPointerException("type");
        this.type = type;

        ComponentType _componentType = member.getAnnotation(ComponentType.class);
        if (_componentType != null)
            itemType = (Class<?>) _componentType.value();
        else if (type.isArray())
            itemType = (Class<?>) type.getComponentType();
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
    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public Set<String> getAliases() {
        return aliases;
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
    public boolean isWeak() {
        return priority >= WEAK_PRIORITY;
    }

    public void setWeak(boolean weak) {
        priority = WEAK_PRIORITY;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
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
    public boolean isArray() {
        return multiple && type.isArray();
    }

    @Override
    public boolean isCollection() {
        return multiple && Collection.class.isAssignableFrom(type);
    }

    @Override
    public boolean isMap() {
        return multiple && Map.class.isAssignableFrom(type);
    }

    @Override
    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public Class<?> getItemType() {
        return itemType;
    }

    public void setItemType(Class<?> itemType) {
        this.itemType = itemType;
    }

    @Override
    public Object getDefaultItemValue() {
        return defaultItemValue;
    }

    public void setDefaultItemValue(Object defaultItemValue) {
        this.defaultItemValue = defaultItemValue;
    }

    @Override
    public int getParameterCount() {
        if (TypeKind.isBoolean(type))
            return 0;
        else
            return 1;
    }

}
