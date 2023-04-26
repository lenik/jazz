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

    public DomainSpecMap() {
        this(SortOrder.NONE);
    }

    public DomainSpecMap(SortOrder order) {
        super(order);
        domainMap = order.newMap();
    }

    @Override
    protected val_t _find(String key) {
        String domain = findDomainFor(key);
        if (domain != null)
            return domainMap.get(domain);
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
        String domain = findDomainFor(key);
        if (domain != null)
            return SpecKeyLocation.domain(domain);
        return super._whichKey(key);
    }

    @Override
    protected ILayerKeyValue<val_t> _removeFromAnyLayer(String key) {
        String domain = findDomainFor(key);
        if (domain != null)
            return LayerKeyValue.domain(domain, domainMap.remove(domain));
        return super._removeFromAnyLayer(key);
    }

    @Override
    public String findDomainFor(String key) {
        String p = key;
        while (p != null) {
            if (domainMap.containsKey(p))
                return p;
            Split split = Split.headDomain(p);
            p = split.b;
        }
        return null;
    }

    @Override
    public Set<String> domainKeySet() {
        return domainMap.keySet();
    }

    @Override
    public boolean containsDomain(String domain) {
        return domainMap.containsKey(domain);
    }

    @Override
    public val_t getDomain(String domain) {
        return domainMap.get(domain);
    }

    @Override
    public val_t putDomain(String domain, val_t val) {
        return domainMap.put(domain, val);
    }

    @Override
    public val_t removeDomain(String domain) {
        return domainMap.remove(domain);
    }

    @Override
    public void removeAllDomains() {
        domainMap.clear();
    }

}
