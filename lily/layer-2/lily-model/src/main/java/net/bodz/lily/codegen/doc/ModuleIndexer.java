package net.bodz.lily.codegen.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.lily.contact.Person;
import net.bodz.lily.model.base.CoIndex;

public class ModuleIndexer {

    Map<String, ModuleInfo> classModule = new HashMap<>();

    Map<String, EntityInfo> nameEntity = new HashMap<>();

    public ModuleIndexer() {
    }

    @SuppressWarnings("rawtypes")
    public void run()
            throws Exception {

        for (IJazzModule mod : ServiceLoader.load(IJazzModule.class)) {
            ModuleInfo modInfo = new ModuleInfo(mod);
            for (String cn : mod.getManagedClassNames())
                classModule.put(cn, modInfo);
        }

        for (Class<? extends CoIndex> indexClass : IndexedTypes.list(CoIndex.class, true))
            addIndexClass(indexClass);

        for (Class<? extends IMapper> mapperClass : IndexedTypes.list(IMapper.class, true))
            addMapperClass(mapperClass);

        addIndexClass(CoIndex.class);
        addMapperClass(IMapperTemplate.class);

        for (EntityInfo entity : new ArrayList<>(nameEntity.values()))
            if (entity.parent == null)
                entity.parent = loadRec(entity.declaredClass.getSuperclass());

        for (EntityInfo entity : nameEntity.values())
            entity.parseUptoParent();
    }

    void addIndexClass(Class<?> indexClass) {
        Class<?>[] pv = TypeParam.infer(indexClass, CoIndex.class);
        Class<?> entityType = pv[0];
        Class<?> maskType = pv[1];
        EntityInfo entity = resolveEntity(entityType, maskType);
        entity.setIndexClass(indexClass);
    }

    void addMapperClass(Class<?> mapperClass) {
        if (IMapperTemplate.class.isAssignableFrom(mapperClass)) {
            Class<?>[] pv = TypeParam.infer(mapperClass, IMapperTemplate.class);
            Class<?> entityType = pv[0];
            Class<?> maskType = pv[1];
            EntityInfo entity = resolveEntity(entityType, maskType);
            entity.setMapperClass(mapperClass);
        }
    }

    EntityInfo resolveEntity(Class<?> entityType, Class<?> maskType) {
        String fqcn = entityType.getName();
        EntityInfo entity = nameEntity.get(fqcn);
        if (entity == null) {
            entity = new EntityInfo(entityType, maskType);
            nameEntity.put(fqcn, entity);
            ModuleInfo modInfo = classModule.get(entityType.getName());
            if (modInfo != null) {
                entity.module = modInfo;
                modInfo.add(entity);
            }
        }
        return entity;
    }

    EntityInfo loadRec(Class<?> clazz) {
        if (clazz == null)
            return null;
        if (clazz == Object.class)
            return null;

        String fqcn = clazz.getName();
        EntityInfo entity = nameEntity.get(fqcn);
        if (entity != null)
            return entity;

        String maskName = fqcn + "Mask";
        Class<?> maskClass = null;
        try {
            maskClass = clazz.getClassLoader().loadClass(maskName);
        } catch (ClassNotFoundException e) {
        }
        entity = resolveEntity(clazz, maskClass);
        entity.parent = loadRec(clazz.getSuperclass());
        return entity;
    }

    public static void main(String[] args)
            throws Exception {
        ModuleIndexer indexer = new ModuleIndexer();
        indexer.run();

        EntityInfo info = indexer.nameEntity.get(Person.class.getName());
        System.out.println(info);
    }

}
