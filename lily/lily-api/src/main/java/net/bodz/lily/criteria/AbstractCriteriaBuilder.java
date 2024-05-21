package net.bodz.lily.criteria;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.criterion.Composite;
import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.ITypeInferrer;
import net.bodz.lily.criterion.Junction;
import net.bodz.lily.criterion.Not;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractCriteriaBuilder<This>
        extends CriteriaBuilderDSL<This>
        implements
            ICriteriaBuilder<This>,
            IVarMapForm {

    protected final IEntityTypeInfo typeInfo;
    final ITypeInferrer typeInferrer;

    CompositeStack stack = new CompositeStack();

    @Deprecated
    final IType cbType = PotatoTypes.getInstance().getType(getClass());

    protected AbstractCriteriaBuilder() {
        ForEntityType aForEntityType = getClass().getAnnotation(ForEntityType.class);
        if (aForEntityType == null)
            throw new IllegalUsageException("ForEntityType isn't present: " + getClass());

        Class<?>[] entityTypes = aForEntityType.value();
        if (entityTypes.length != 1)
            throw new IllegalUsageException("expect single type: " + Arrays.asList(entityTypes));

        Class<?> entityType = entityTypes[0];
        typeInfo = EntityTypes.getTypeInfo(entityType);
        typeInferrer = new ColumnAndThenProps(typeInfo);

        create();
    }

    protected void create() {
        stack.push(new Junction());
    }

    @SuppressWarnings("unchecked")
    private final This _this() {
        return (This) this;
    }

    @Override
    public ICriterion get() {
        return stack.top().composite;
    }

    Composite defaultCombine() {
        return new Junction();
    }

    @Override
    public This not() {
        Composite top = stack.top().composite;
        Composite other = defaultCombine();
        Not not = new Not();
        // not.add(other);
        top.add(not);

        stack.push(other);

        return _this();
    }

    @Override
    public This aboveOr() {
        Composite other = new Junction();
        stack.push(other, ReduceMode.OR);
        return _this();
    }

    @Override
    public This beginOr() {
        Disjunction or = new Disjunction();
        stack.push(or);
        return _this();
    }

    @Override
    public This end() {
        CriterionStackFrame frame = stack.pop();
        ICriterion ret = frame.composite.reduce();
        if (ret == null)
            return _this();

        CriterionStackFrame topFrame = stack.top();
        Composite top = topFrame.composite;
        switch (frame.mode) {
        case APPEND:
            top.add(ret);
            break;
        case OR:
            if (top instanceof Disjunction) {
                top.add(ret);
            } else {
                Disjunction or = new Disjunction();
                or.add(top.reduce());
                or.add(ret);
                top = new Junction();
                top.add(or.reduce());
                topFrame.composite = top; // replace stack top.
            }
            break;
        }
        return _this();
    }

    @Override
    public void receive(ICriterion value) {
        if (stack.isEmpty())
            throw new IllegalUsageException("stack empty");
        Composite top = stack.top().composite;
        top.add(value);
    }

    @Override
    public FieldCriterionBuilder<?, ?, Object> getField(String name) {
        IProperty property = cbType.getProperty(name);
        if (property == null)
            return null;
        if (! FieldCriterionBuilder.class.isAssignableFrom(property.getPropertyClass()))
            return null;

        try {
            @SuppressWarnings("unchecked")
            FieldCriterionBuilder<?, ?, Object> value = (FieldCriterionBuilder<?, ?, Object>) property.getValue(this);
            return value;
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException("can't get value for property " + name, e);
        }
    }

    public <T> List<T> filter(IGenericMapper<T> mapper) {
        return filter(mapper, SelectOptions.ALL);
    }

    public <T> List<T> filter(IGenericMapper<T> mapper, SelectOptions options) {
        ICriterion criterion = get();
        return mapper.filter(criterion, options);
    }

    @Override
    public String toString() {
        return Nullables.toString(get());
    }

    @Override
    public final void readObject(IVariantMap<String> map)
            throws ParseException {
        readObject(map, typeInferrer);
    }

    public void readObject(IVariantMap<String> map, ITypeInferrer typeInferrer)
            throws ParseException {
        Function<String, String> qualifier = (String name) -> qualify(name);

        if (stack.isEmpty())
            stack.push(defaultCombine());

        Composite composite = stack.top().composite;
        composite.readObject(map, qualifier, typeInferrer);

        for (String key : map.keySet()) {
            FieldCriterionBuilder<?, ?, Object> field = getField(key);
            if (field != null) {
                String param = map.getString(key);
                Object val = field.parse(param);
                field.eq(val);
            }
        }
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

}