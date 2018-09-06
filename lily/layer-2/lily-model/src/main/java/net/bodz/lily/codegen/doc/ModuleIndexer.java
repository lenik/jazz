package net.bodz.lily.codegen.doc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.model.base.CoObjectMask;

public class ModuleIndexer
        implements IPathDispatchable {

    List<ModuleInfo> modules = new ArrayList<>();
    Map<String, ModuleInfo> classModule = new HashMap<>();

    EntityIndex entityIndex = new EntityIndex(this);

    Map<String, EntityInfo> nameEntity = new HashMap<>();
    Map<String, MaskInfo> nameMask = new HashMap<>();

    // Map<String, MapperInfo> nameMapper= new HashMap<>();
    // Map<String, IndexInfo> nameIndex = new HashMap<>();

    private ModuleIndexer() {
        try {
            index();
        } catch (Exception e) {
            throw new LoadException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("rawtypes")
    public void index()
            throws Exception {

        for (IJazzModule mod : ServiceLoader.load(IJazzModule.class)) {
            ModuleInfo modInfo = new ModuleInfo(mod);
            modules.add(modInfo);
            for (String cn : mod.getManagedClassNames())
                classModule.put(cn, modInfo);
        }

        for (Class<? extends CoIndex> indexClass : IndexedTypes.list(CoIndex.class, true))
            addIndexClass(indexClass);

        for (Class<? extends IMapper> mapperClass : IndexedTypes.list(IMapper.class, true))
            addMapperClass(mapperClass);

        addIndexClass(CoIndex.class);
        // addMapperClass(IMapperTemplate.class);

        // load/wire parents
        for (EntityInfo entity : new ArrayList<>(nameEntity.values()))
            if (entity.parent == null)
                entity.parent = loadRec(entity.declaredClass.getSuperclass());

        for (MaskInfo mask : new ArrayList<>(nameMask.values()))
            if (mask.parent == null)
                mask.parent = loadMaskRec(mask.declaredClass.getSuperclass());

        // parse properties
        for (EntityInfo entity : nameEntity.values())
            entity.parseUptoParent(this);

        for (MaskInfo mask : nameMask.values())
            mask.parseUptoParent(this);

        // analyze dependencies
        for (EntityInfo entity : nameEntity.values()) {
            for (EntityInfo dep : entity.getDependencies()) {
                if (entity.module != dep.module)
                    entity.module.addDependency(dep.module);
            }
        }

        Collections.sort(modules, ModuleDepOrder.INSTANCE);
    }

    void addIndexClass(Class<?> indexClass) {
        Class<?>[] pv = TypeParam.infer(indexClass, CoIndex.class);
        Class<?> entityType = pv[0];
        Class<?> maskType = pv[1];
        EntityInfo entity = resolveEntity(entityType, maskType);
        entity.setIndexClass(indexClass);
        // IndexInfo index = new IndexInfo(indexClass);
        // nameIndex.put(indexClass.getName(), index);
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

    public EntityInfo getEntity(Class<?> entityType) {
        return nameEntity.get(entityType.getName());
    }

    EntityInfo resolveEntity(Class<?> entityType, Class<?> maskType) {
        if (entityType == null)
            throw new NullPointerException("entityType");
        if (maskType == null)
            throw new NullPointerException("maskType");

        String fqcn = entityType.getName();
        EntityInfo entity = nameEntity.get(fqcn);
        if (entity == null) {
            MaskInfo mask = loadMaskRec(maskType);
            if (mask == null)
                throw new NullPointerException("mask");
            entity = new EntityInfo(entityType, mask);
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
            maskClass = CoObjectMask.class;
        }
        entity = resolveEntity(clazz, maskClass);
        entity.parent = loadRec(clazz.getSuperclass());
        return entity;
    }

    MaskInfo loadMaskRec(Class<?> maskClass) {
        if (maskClass == null)
            return null;
        if (maskClass == Object.class)
            return null;

        String fqcn = maskClass.getName();
        MaskInfo mask = nameMask.get(fqcn);
        if (mask != null)
            return mask;

        mask = new MaskInfo(maskClass);
        mask.parent = loadMaskRec(maskClass.getSuperclass());
        nameMask.put(fqcn, mask);
        return mask;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        switch (token) {
        case "test":
            target = 1;
            break;
        }

        if (target == null)
            return null;
        return PathArrival.shift(previous, target, tokens);
    }

    static ModuleIndexer instance = new ModuleIndexer();

    public static ModuleIndexer getInstance() {
        return instance;
    }

}
