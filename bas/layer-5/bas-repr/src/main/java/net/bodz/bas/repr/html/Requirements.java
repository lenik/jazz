package net.bodz.bas.repr.html;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.VersionRange;

public class Requirements
        implements IRequirements, Comparator<String> {

    Map<String, IRequirement> idMap;
    Map<String, Map<String, IRequirement>> typeMap;

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
    public void add(String type, String id, String minVersionStr)
            throws ConflictedVersionException {
        if (type == null)
            throw new NullPointerException("type");
        if (id == null)
            throw new NullPointerException("id");

        IVersion minVersion = null;
        if (minVersionStr != null)
            minVersion = IVersion.fn.parse(minVersionStr);

        VersionRange vr = new VersionRange(minVersion);

        Map<String, IRequirement> map = mapOfType(type);
        IRequirement existing = map.get(id);
        if (existing != null) {
            VersionRange vr0 = existing.getVersionRange();
            if (minVersion != null && vr0.contains(minVersion))
                throw new ConflictedVersionException();

        }

    }

    @Override
    public void add(IRequirement requirement)
            throws ConflictedVersionException {
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

}
