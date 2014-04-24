package net.bodz.bas.html;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.meta.build.VersionRange;

public class Requirements
        implements IRequirements, Comparator<String> {

    private Map<String, IRequirement> idMap;
    private Map<String, Map<String, IRequirement>> typeMap;

    public Requirements() {
        idMap = new TreeMap<String, IRequirement>(this);
        typeMap = new HashMap<>();
    }

    @Override
    public IRequirement get(String id) {
        return idMap.get(id);
    }

    synchronized Map<String, IRequirement> mapOfType(String type) {
        Map<String, IRequirement> map = typeMap.get(type);
        if (map == null) {
            map = new TreeMap<>(this);
            typeMap.put(type, map);
        }
        return map;
    }

    @Override
    public Collection<IRequirement> typeOf(String type) {
        if (type == null)
            throw new NullPointerException("type");
        return mapOfType(type).values();
    }

    @Override
    public final void add(String type, String id, String minVersionStr)
            throws ConflictedVersionException {
        if (type == null)
            throw new NullPointerException("type");
        if (id == null)
            throw new NullPointerException("id");
        add(new MutableRequirement(id, type, VersionRange.parse(minVersionStr)));
    }

    @Override
    public void add(IRequirement requirement)
            throws ConflictedVersionException {
        if (requirement == null)
            throw new NullPointerException("requirement");

        String id = requirement.getId();
        String type = requirement.getType();
        VersionRange range = requirement.getVersionRange();

        Map<String, IRequirement> map = mapOfType(type);
        IRequirement prev = map.get(id);
        if (prev != null) {
            VersionRange prevRange = prev.getVersionRange();
            VersionRange intersection = prevRange.intersect(range);
            if (intersection.isEmpty())
                throw new ConflictedVersionException();

            MutableRequirement merged = new MutableRequirement(id, type, intersection);
            merged.setOptional(prev.isOptional() && requirement.isOptional());
            merged.setURL(prev.getURL());
            merged.setData(prev.getData());
            requirement = merged;
        }
        map.put(id, requirement);
        idMap.put(id, requirement);
    }

    @Override
    public boolean remove(String id) {
        IRequirement requirement = idMap.remove(id);
        if (requirement == null)
            return false;

        String type = requirement.getType();
        Map<String, IRequirement> map = mapOfType(type);
        map.remove(id);
        return true;
    }

    @Override
    public int compare(String o1, String o2) {
        IRequirement r1 = idMap.get(o1);
        IRequirement r2 = idMap.get(o2);
        return Nullables.compare(r1, r2);
    }

}
