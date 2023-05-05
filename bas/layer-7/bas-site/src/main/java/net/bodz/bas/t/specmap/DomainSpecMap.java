package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.tuple.Split;

public class DomainSpecMap<val_t>
        extends DefaultSpecMap<String, val_t>
        implements
            IDomainMap<val_t> {

    Map<String, val_t> domainMap;
    // boolean maxLevels;

    public DomainSpecMap() {
        this(SortOrder.NONE);
    }

    public DomainSpecMap(SortOrder order) {
        super(order);
        domainMap = order.newMap();
    }

    @Override
    protected val_t _find(String key) {
        String name = findDomainNameFor(key);
        if (name != null)
            return domainMap.get(name);
        return super._find(key);
    }

    @Override
    protected void _findAll(String key, List<ILayerKeyValue<val_t>> list) {
        for (String parent = Split.headDomain(key).b; parent != null; parent = Split.headDomain(parent).b)
            if (domainMap.containsKey(parent))
                list.add(LayerKeyValue.domain(parent, domainMap.get(parent)));
        super._findAll(key, list);
    }

    @Override
    protected SpecKeyLocation _whichKey(String key) {
        String name = findDomainNameFor(key);
        if (name != null)
            return SpecKeyLocation.domain(name);
        return super._whichKey(key);
    }

    @Override
    protected ILayerKeyValue<val_t> _removeFromAnyLayer(String key) {
        String domain = findDomainNameFor(key);
        if (domain != null)
            return LayerKeyValue.domain(domain, domainMap.remove(domain));
        return super._removeFromAnyLayer(key);
    }

    @Override
    public String findDomainNameFor(String key) {
        String parent = key;
        while (parent != null) {
            if (domainMap.containsKey(parent))
                return parent;
            Split split = Split.headDomain(parent);
            parent = split.b;
        }
        return null;
    }

    @Override
    public Set<String> domainNames() {
        return domainMap.keySet();
    }

    @Override
    public boolean containsDomain(String name) {
        return domainMap.containsKey(name);
    }

    @Override
    public val_t getDomain(String name) {
        return domainMap.get(name);
    }

    @Override
    public val_t putDomain(String name, val_t val) {
        return domainMap.put(name, val);
    }

    @Override
    public val_t removeDomain(String name) {
        return domainMap.remove(name);
    }

    @Override
    public void removeAllDomains() {
        domainMap.clear();
    }

}
