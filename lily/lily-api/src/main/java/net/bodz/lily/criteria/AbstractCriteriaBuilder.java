package net.bodz.lily.criteria;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;
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
    protected IStack<Composite> stack = new ArrayStack<>();

    public AbstractCriteriaBuilder() {
        ForEntityType aForEntityType = getClass().getAnnotation(ForEntityType.class);
        if (aForEntityType == null)
            throw new IllegalUsageException("ForEntityType isn't present: " + getClass());

        Class<?>[] entityTypes = aForEntityType.value();
        if (entityTypes.length != 1)
            throw new IllegalUsageException("expect single type: " + Arrays.asList(entityTypes));

        Class<?> entityType = entityTypes[0];
        typeInfo = EntityTypes.getTypeInfo(entityType);

        stack.push(new Junction());
    }

    @SuppressWarnings("unchecked")
    private final This _this() {
        return (This) this;
    }

    @Override
    public ICriterion get() {
        return stack.top();
    }

    Composite defaultCombine() {
        return new Junction();
    }

    @Override
    public This not() {
        Composite top = stack.top();
        Composite other = defaultCombine();
        Not not = new Not();
        // not.add(other);
        top.add(not);

        stack.push(other);

        return _this();
    }

    @Override
    public This or() {
        Composite other = defaultCombine();

        ICriterion top = stack.top(); // .reduce();
        if (top instanceof Disjunction) {
            // Disjunction disj = (Disjunction) top;
            // disj.add(other);
        } else {
            Disjunction disj = new Disjunction();
            disj.add(top);
            // disj.add(other);
            Composite restore = defaultCombine();
            restore.add(disj);
            stack.replaceTop(restore);
        }

        stack.push(other);

        return _this();
    }

    @Override
    public This end() {
        ICriterion other = stack.pop(); // .reduce();
        Composite top = stack.top();
        if (top instanceof Disjunction) {
            top.add(other);
        } else if (top instanceof Junction) { // restore-and
            ICriterion last = top.getLast();
            if (last instanceof Composite) {
                Composite lastComposite = (Composite) last;
                lastComposite.add(other);
            } else
                throw new IllegalUsageError("top.last isn't composite");
        } else
            throw new IllegalUsageError("unexpected top type");
        // stack.replaceTop(top.reduce());
        return _this();
    }

    @Override
    public void receive(ICriterion value) {
        Composite top = stack.top();
        top.add(value);
    }

    public <T> List<T> filter(IGenericMapper<T> mapper) {
        return filter(mapper, SelectOptions.ALL);
    }

    public <T> List<T> filter(IGenericMapper<T> mapper, SelectOptions options) {
        ICriterion criterion = get();
        return mapper.filter(criterion, options);
    }

    class ColumnAndThenProps
            implements
                ITypeInferrer {

        @Override
        public Class<?> getFieldType(List<String> fieldNames) {
            if (fieldNames == null)
                throw new NullPointerException("fieldNames");
            if (fieldNames.isEmpty())
                throw new IllegalArgumentException("no field name");
            if (fieldNames.size() != 1)
                throw new NotImplementedException();

            // column-name, property-path
            String head = fieldNames.get(0);

            IProperty property = typeInfo.getPropertyForColumn(head);
            if (property == null)
                throw new IllegalArgumentException("invalid column name: " + head);

            Class<?> propertyType = property.getPropertyClass();
            return propertyType;
        }

    }

    final ITypeInferrer columnAndThenProps = new ColumnAndThenProps();

    @Deprecated
    @Override
    public final void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        readObject(map, columnAndThenProps);
    }

    public void readObject(IVariantMap<String> map, ITypeInferrer typeInferrer)
            throws LoaderException, ParseException {
        stack.clear();
        Composite composite = defaultCombine();
        composite.readObject(map, typeInferrer);
        stack.push(composite);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

}