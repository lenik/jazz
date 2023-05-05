package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.tuple.Split;

public class PackageSpecMap<val_t>
        extends DefaultSpecMap<String, val_t>
        implements
            IPackageNameMap<val_t> {

    Map<String, val_t> packageMap;
    // boolean maxLevels;

    public PackageSpecMap() {
        this(SortOrder.NONE);
    }

    public PackageSpecMap(SortOrder order) {
        super(order);
        packageMap = order.newMap();
    }

    @Override
    protected val_t _find(String key) {
        String name = findPackageNameFor(key);
        if (name != null)
            return packageMap.get(name);
        return super._find(key);
    }

    @Override
    protected void _findAll(String key, List<ILayerKeyValue<val_t>> list) {
        for (String parent = Split.packageName(key).a; //
                parent != null; parent = Split.packageName(parent).a)
            if (packageMap.containsKey(parent))
                list.add(LayerKeyValue.domain(parent, packageMap.get(parent)));
        super._findAll(key, list);
    }

    @Override
    protected SpecKeyLocation _whichKey(String key) {
        String name = findPackageNameFor(key);
        if (name != null)
            return SpecKeyLocation.domain(name);
        return super._whichKey(key);
    }

    @Override
    protected ILayerKeyValue<val_t> _removeFromAnyLayer(String key) {
        String domain = findPackageNameFor(key);
        if (domain != null)
            return LayerKeyValue.domain(domain, packageMap.remove(domain));
        return super._removeFromAnyLayer(key);
    }

    @Override
    public String findPackageNameFor(String key) {
        String parent = key;
        while (parent != null) {
            if (packageMap.containsKey(parent))
                return parent;
            Split split = Split.packageName(parent);
            parent = split.a;
        }
        return null;
    }

    @Override
    public Set<String> packageNames() {
        return packageMap.keySet();
    }

    @Override
    public boolean containsPackage(String name) {
        return packageMap.containsKey(name);
    }

    @Override
    public val_t getPackage(String name) {
        return packageMap.get(name);
    }

    @Override
    public val_t putPackage(String name, val_t val) {
        return packageMap.put(name, val);
    }

    @Override
    public val_t removePackage(String name) {
        return packageMap.remove(name);
    }

    @Override
    public void removeAllPackages() {
        packageMap.clear();
    }

}
