package net.bodz.bas.esm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.preorder.PackageMap;
import net.bodz.bas.t.preorder.PrefixMap;
import net.bodz.bas.t.tuple.QualifiedName;

public class DomainMap<E> {

    E defaultValue;
    final PackageMap<E> packageMap = new PackageMap<>();
    final PrefixMap<E> prefixMap = new PrefixMap<>();
    final Map<String, E> map = new HashMap<>();

    public E patternGet(String pattern) {
        Domain domain = Domain.parse(pattern);
        switch (domain.type) {
        case ALL:
            return defaultValue;
        case PACKAGE:
            return packageMap.get(domain.key);
        case PREFIX:
            return prefixMap.get(domain.key);
        case MATCH:
            return map.get(domain.key);
        default:
            throw new NotImplementedException();
        }
    }

    public void patternPut(String pattern, E value) {
        if (pattern == null) {
            defaultValue = value;
            return;
        }
        Domain domain = Domain.parse(pattern);
        switch (domain.type) {
        case ALL:
            defaultValue = value;
            break;
        case PACKAGE:
            addPackage(domain.key, value);
            break;
        case PREFIX:
            addPrefix(domain.key, value);
            break;
        case MATCH:
            add(domain.key, value);
            break;
        }
    }

    public void addAll(DomainMap<? extends E> o) {
        defaultValue = o.defaultValue;
        packageMap.putAll(o.packageMap);
        prefixMap.putAll(o.prefixMap);
        map.putAll(o.map);
    }

    public void addPackage(String packageName, E el) {
        if (packageName == null)
            throw new NullPointerException("packageName");
        if (el == null)
            throw new NullPointerException("el");
        packageMap.put(packageName, el);
    }

    public void addPrefix(String prefix, E el) {
        if (prefix == null)
            throw new NullPointerException("prefix");
        if (el == null)
            throw new NullPointerException("el");
        prefixMap.put(prefix, el);
    }

    public void add(String qName, E el) {
        if (qName == null)
            throw new NullPointerException("qName");
        if (el == null)
            throw new NullPointerException("el");
        map.put(qName, el);
    }

    public E find(QualifiedName qName) {
        String fullName = qName.getFullName();
        E module = map.get(fullName);
        if (module != null)
            return module;

        module = packageMap.meet(fullName);
        if (module != null)
            return module;

        module = prefixMap.meet(fullName);
        return module;
    }

    public void dump(ITreeOut out) {
        out.enterln("Dump of DomainMap:");

        if (defaultValue != null)
            out.println("default :" + defaultValue);

        out.enterln("packageMap:");
        dump(out, packageMap, null);

        out.enterln("prefixMap:");
        dump(out, prefixMap, "%");

        out.enterln("qNameMap:");
        dump(out, map, null);

        out.leaveln();
    }

    void dump(ITreeOut out, Map<String, E> map, String keySuffix) {
        out.enter();
        for (String key : map.keySet()) {
            E val = map.get(key);
            String keyDisp = key;
            if (keySuffix != null)
                keyDisp += keySuffix;
            out.println(keyDisp + " = " + val);
        }
        out.leave();
    }

}
