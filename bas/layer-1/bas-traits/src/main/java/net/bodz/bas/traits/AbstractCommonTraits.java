package net.bodz.bas.traits;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.lang.AbstractQueryable;
import net.bodz.bas.lang.QueryException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.codereview.GeneratedBy;
import net.bodz.bas.meta.codereview.ThreadUnsafe;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.NotImplementedException;
import net.bodz.bas.util.exception.ParseException;

@ThreadUnsafe
public abstract class AbstractCommonTraits<T>
        extends AbstractQueryable
        implements ICommonTraits<T>, IAttributes, ITextForm<T>, IValidator<T>, IClassifier<T>, ISearcher<T>,
        ISampleGenerator<T>, IInstanceStore<T> {

    protected final Class<T> type;
    private Attributes attributes;
    private Map<String, IAttributes> classicationAttributes;
    private Map<String, T> storeInstances;

    protected static final Random random = new Random();

    public AbstractCommonTraits(Class<T> type) {
        if (type == null)
            throw new NullPointerException("clazz");
        this.type = type;
    }

    @GeneratedBy(CalcPreferredId.class)
    static final Map<Class<?>, Integer> commonTraitsIndex;
    static {
        commonTraitsIndex = new HashMap<Class<?>, Integer>();
        commonTraitsIndex.put(IAttributes.class, IAttributes.traitsIndex);
        commonTraitsIndex.put(IClassifier.class, IClassifier.traitsIndex);
        commonTraitsIndex.put(ICommonTraits.class, ICommonTraits.traitsIndex);
        commonTraitsIndex.put(IFormatter.class, IFormatter.traitsIndex);
        commonTraitsIndex.put(IInstanceStore.class, IInstanceStore.traitsIndex);
        commonTraitsIndex.put(IParser.class, IParser.traitsIndex);
        commonTraitsIndex.put(ISampleGenerator.class, ISampleGenerator.traitsIndex);
        commonTraitsIndex.put(ISearcher.class, ISearcher.traitsIndex);
        commonTraitsIndex.put(ITextForm.class, ITextForm.traitsIndex);
        commonTraitsIndex.put(IValidator.class, IValidator.traitsIndex);
    }

    @Override
    public <X> X query(Class<X> specificationType) {
        Integer traitsIndex = commonTraitsIndex.get(specificationType);
        if (traitsIndex == null)
            return super.query(specificationType);
        Object traits = queryRemix(traitsIndex);
        return specificationType.cast(traits);
    }

    protected Object queryRemix(int traitsIndex) {
        Object traits = query(traitsIndex);
        if (traits != null)
            return traits;

        switch (traitsIndex) {
        case ICommonTraits.traitsIndex:
            return this;

        case IAttributes.traitsIndex:
            if (attributes != null)
                return this;
            break;

        case ITextForm.traitsIndex:
            Object parser = query(IParser.traitsIndex);
            Object formatter = query(IFormatter.traitsIndex);
            if (parser == formatter && parser != null)
                return parser;
            break;

        case IInstanceStore.traitsIndex:
            if (storeInstances != null)
                return this;
            break;
        }
        return null;
    }

    protected abstract Object query(int traitsIndex);

    @Override
    public IAttributes getAttributes() {
        return this;
    }

    @Override
    public IClassifier<T> getClassifier() {
        return this;
    }

    @Override
    public IFormatter<T> getFormatter() {
        return this;
    }

    @Override
    public IInstanceStore<T> getInstanceStore() {
        return this;
    }

    @Override
    public IParser<T> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<T> getSampleGenerator() {
        return this;
    }

    @Override
    public ISearcher<T> getSearcher() {
        return this;
    }

    @Override
    public ITextForm<T> getTextForm() {
        return this;
    }

    @Override
    public IValidator<T> getValidator() {
        return this;
    }

    @Override
    public Object getAttribute(String attributeName) {
        return attributes.getAttribute(attributeName);
    }

    @Override
    public Collection<String> getAttributeNames() {
        return attributes.getAttributeNames();
    }

    @Override
    public ICommonTraits<?> getAttributeTraits(String attributeName) {
        return attributes.getAttributeTraits(attributeName);
    }

    @Override
    public String format(T object, INegotiation negotiation)
            throws NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return format(object);
    }

    @Override
    public T parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

    @Override
    public void validate(T object, INegotiation negotiation)
            throws ValidateException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        validate(object);
    }

    @Override
    public Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return classify(object);
    }

    @Override
    public IAttributes getClassAnnotations(String classId) {
        if (classicationAttributes == null)
            return null;
        IAttributes annotations = classicationAttributes.get(classId);
        return annotations;
    }

    protected final void addClassAnnotation(String classId, IAttributes annotations) {
        if (classicationAttributes == null)
            classicationAttributes = new TreeMap<String, IAttributes>();
        classicationAttributes.put(classId, annotations);
    }

    @Override
    public Iterator<?> search(T object, String query, INegotiation negotiation)
            throws NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return search(object, query);
    }

    @Override
    public T newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        if (classification != null)
            if (!classification.isEmpty())
                return null;
        if (negotiation != null)
            negotiation.bypass();
        return newSample();
    }

    @Override
    public T getInstance(String name) {
        if (storeInstances == null)
            return null;
        return storeInstances.get(name);
    }

    @Override
    public Set<String> getInstanceNames() {
        if (storeInstances == null)
            return Collections.emptySet();
        return Collections.unmodifiableSet(storeInstances.keySet());
    }

    static final String autoNameFormat = "(%d)";
    private int nextInstanceIndex = 0;

    protected final void addInstance(T instance) {
        if (instance == null)
            throw new NullPointerException("instance");
        String name = String.format(autoNameFormat, nextInstanceIndex++);
        addInstance(name, instance);
    }

    protected final void addInstance(String name, T instance) {
        if (name == null)
            throw new NullPointerException("name");
        if (instance == null)
            throw new NullPointerException("instance");
        if (storeInstances == null)
            storeInstances = new TreeMap<String, T>();
        storeInstances.put(name, instance);
    }

    protected final void addStaticInstances(Class<?> declaringClass) {
        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        for (Field field : declaringClass.getFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                try {
                    String name = field.getName();
                    Object object = Jdk7Reflect.get(field, null);
                    if (type.isInstance(object)) {
                        T instance = type.cast(object);
                        addInstance(name, instance);
                    }
                } catch (IllegalAccessException e) {
                    throw new QueryException();
                }
            }
        }
    }

    // Default Implementatons

    @Override
    public T parse(String text)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public String format(T object) {
        return String.valueOf(object);
    }

    public void validate(T object)
            throws ValidateException {
    }

    @Override
    public T newSample()
            throws CreateException {
        try {
            return Jdk7Reflect.newInstance(type);
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

    @Override
    public Iterator<?> search(T object, String query) {
        return Collections.emptyList().iterator();
    }

    @Override
    public Map<String, Object> classify(T object)
            throws ClassifyException {
        return Collections.emptyMap();
    }

}
