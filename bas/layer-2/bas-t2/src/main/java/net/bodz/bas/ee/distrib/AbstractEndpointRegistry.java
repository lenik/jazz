package net.bodz.bas.ee.distrib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.bodz.bas.t.set.FullSearchTaggedSet;
import net.bodz.bas.t.set.TaggedSet;

public abstract class AbstractEndpointRegistry<config_t extends IEndpointConfig>
        implements IEndpointRegistry<config_t> {

    Map<String, config_t> map;
    TaggedSet<String> tagmap;
    List<String> keys;

    public AbstractEndpointRegistry() {
        map = new HashMap<String, config_t>();
        tagmap = new FullSearchTaggedSet<String>();
        keys = new ArrayList<String>();
    }

    @Override
    public Set<String> getConfigNames() {
        return map.keySet();
    }

    @Override
    public Collection<config_t> getConfigs() {
        return map.values();
    }

    Random random = new Random();

    public synchronized config_t getAnyConfig() {
        int rand = random.nextInt(keys.size());
        String key = keys.get(rand);
        return map.get(key);
    }

    @Override
    public config_t getConfig(String name) {
        return map.get(name);
    }

    @Override
    public Set<String> getConfigTags(String name) {
        return tagmap.getTags(name);
    }

    @Override
    public List<config_t> selectConfigs(String... tags) {
        Collection<String> names = tagmap.selectForAll(tags);
        List<config_t> list = new ArrayList<config_t>(names.size());
        for (String name : names)
            list.add(getConfig(name));
        return list;
    }

    @Override
    public void addConfig(String name, config_t config, Iterable<String> tags) {
        map.put(name, config);
        tagmap.add(name, tags);
        keys.add(name);
    }

    @Override
    public void addConfig(String name, config_t config, String... tags) {
        addConfig(name, config, Arrays.asList(tags));
    }

    @Override
    public config_t removeConfig(String name) {
        config_t config = map.remove(name);
        tagmap.remove(name);
        keys.remove(name);
        return config;
    }

}
