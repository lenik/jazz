package net.bodz.bas.db.ibatis;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.type.ClassNameComparator;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

/**
 * Eagerly load the mapper xml here, to support xml patches.
 */
public class MapperClassXmls
        implements IMapperXmlProvider {

    static final Logger logger = LoggerFactory.getLogger(MapperClassXmls.class);

    Map<String, MapperXml> map = new LinkedHashMap<String, MapperXml>();
    Set<Class<?>> withoutXmls = new LinkedHashSet<Class<?>>();

    public MapperClassXmls() {
        lastInstance = this;
        load();
    }

    void load() {
        Set<Class<?>> mappers = new TreeSet<Class<?>>(ClassNameComparator.getInstance());
        for (Class<?> mapper : IndexedTypes.list(IMapper.class, true)) {
            mappers.add(mapper);
        }
        logger.infof("Total %d mapper classes.", mappers.size());

        for (Class<?> mapperClass : mappers) {
            String fqcn = mapperClass.getName();
            String resource = fqcn.replace('.', '/') + ".xml";

            try {
                URL url = mapperClass.getResource(mapperClass.getSimpleName() + ".xml");
                if (url == null) {
                    withoutXmls.add(mapperClass);
                } else {
                    logger.debug("Mapper-Resource: ", resource);
                    byte[] content = new URLResource(url).to(StreamReading.class).read();
                    MapperXml xml = new MapperXml();
                    xml.mapperClass = mapperClass;
                    xml.resourceName = resource;
                    xml.url = url;
                    xml.content = content;
                    map.put(resource, xml);
                }
            } catch (IOException e) {
                logger.error("Failed to read mapper xml: " + mapperClass, e);
            }
        }
        logger.infof("Total %d mapper xmls added.", map.size());
    }

    @Override
    public Set<String> getNames() {
        return map.keySet();
    }

    @Override
    public MapperXml getXml(String name) {
        return map.get(name);
    }

    public Set<Class<?>> getClassesWithoutXmls() {
        return withoutXmls;
    }

    static MapperClassXmls lastInstance;

    public static MapperClassXmls getLastInstance() {
        if (lastInstance == null)
            throw new IllegalStateException("No instance, yet.");
        return lastInstance;
    }

}
