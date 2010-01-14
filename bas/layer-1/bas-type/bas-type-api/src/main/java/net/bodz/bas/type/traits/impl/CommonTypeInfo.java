package net.bodz.bas.type.traits.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.hint.ThreadUnsafe;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;
import net.bodz.bas.type.traits.ClassifyException;
import net.bodz.bas.type.traits.IAttributes;
import net.bodz.bas.type.traits.IClassifier;
import net.bodz.bas.type.traits.IInstanceStore;
import net.bodz.bas.type.traits.ISampleGenerator;
import net.bodz.bas.type.traits.ITextForm;
import net.bodz.bas.type.traits.IValidator;
import net.bodz.bas.type.traits.ValidateException;

@ThreadUnsafe
public abstract class CommonTypeInfo<T>
        extends SelfDescribedTypeInfo
        implements ITextForm<T>, IValidator<T>, IClassifier<T>, ISampleGenerator<T>, IInstanceStore<T> {

    private List<T> instances;
    private Map<String, Integer> instanceNames;

    private Map<String, IAttributes> classDict;

    public CommonTypeInfo() {
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

    public void validate(T object)
            throws ValidateException {
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
        if (classDict == null)
            return null;
        IAttributes annotations = classDict.get(classId);
        return annotations;
    }

    protected final void addClassAnnotation(String classId, IAttributes annotations) {
        if (classDict == null)
            classDict = new TreeMap<String, IAttributes>();
        classDict.put(classId, annotations);
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
    public int getInstanceCount() {
        if (instances == null)
            return 0;
        return instances.size();
    }

    @Override
    public T getInstance(int index) {
        if (instances == null)
            throw new IndexOutOfBoundsException();
        return instances.get(index);
    }

    @Override
    public T getInstance(String name) {
        if (instanceNames == null)
            return null;
        Integer index = instanceNames.get(name);
        if (index == null)
            return null;
        return instances.get(index);
    }

    protected final void addInstance(T instance) {
        if (instance == null)
            throw new NullPointerException("instance");
        if (instances == null)
            instances = new ArrayList<T>();
        instances.add(instance);
    }

    protected final void addInstance(String name, T instance) {
        addInstance(instance);
        int lastIndex = instances.size() - 1;
        if (instanceNames == null)
            instanceNames = new TreeMap<String, Integer>();
        instanceNames.put(name, lastIndex);
    }

}
