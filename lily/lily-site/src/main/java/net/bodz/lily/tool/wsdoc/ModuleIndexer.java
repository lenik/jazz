package net.bodz.lily.tool.wsdoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.ws.AbstractEntityController;
import net.bodz.lily.entity.ws.IEntityController;

public class ModuleIndexer
        implements IPathDispatchable {

    static final Logger logger = LoggerFactory.getLogger(ModuleIndexer.class);

    static final int INIT = 0;
    static final int LOADING = 1;
    static final int LOADED = 2;
    static final int STOP = 3;
    int loadState = INIT;

    private List<ModuleInfo> modules = new ArrayList<>();
    private Map<String, ModuleInfo> classModule = new HashMap<>();

    private EntityIndex entityIndex = new EntityIndex(this);

    private Map<String, EntityInfo> nameEntity = new HashMap<>();
    private Map<String, CriteriaBuilderInfo> nameCriteriaBuilder = new HashMap<>();

    // private Map<String, MapperInfo> nameMapper= new HashMap<>();
    // private Map<String, IndexInfo> nameIndex = new HashMap<>();

    synchronized void lazyLoad() {
        if (loadState != INIT)
            return;
        loadState = LOADING;
        try {
            _index();
        } catch (Exception e) {
            loadState = STOP;
            throw new LoadException(e.getMessage(), e);
        }
        loadState = LOADED;
    }

    void _index()
            throws Exception {

        for (IJazzModule mod : ServiceLoader.load(IJazzModule.class)) {
            ModuleInfo modInfo = new ModuleInfo(mod);
            modules.add(modInfo);
            for (String cn : mod.getManagedClassNames())
                classModule.put(cn, modInfo);
        }

        for (Class<? extends IEntityController> indexClass : IndexedTypes.list(IEntityController.class, true))
            addIndexClass(indexClass);

        for (Class<? extends IMapper> mapperClass : IndexedTypes.list(IMapper.class, true))
            addMapperClass(mapperClass);

        // addMapperClass(IMapperTemplate.class);

        // load/wire parents
        for (EntityInfo entity : new ArrayList<>(nameEntity.values()))
            if (entity.parent == null)
                entity.parent = loadRec(entity.declaredClass.getSuperclass());

        for (CriteriaBuilderInfo mask : new ArrayList<>(nameCriteriaBuilder.values()))
            if (mask.parent == null)
                mask.parent = loadCriteriaBuilderRec(mask.declaredClass.getSuperclass());

        // parse properties
        for (EntityInfo entity : nameEntity.values())
            entity.parseUptoParent(this);

        for (CriteriaBuilderInfo mask : nameCriteriaBuilder.values())
            mask.parseUptoParent(this);

        // analyze dependencies
        for (EntityInfo entity : nameEntity.values()) {
            for (EntityInfo dep : entity.getDependencies()) {
                if (dep == null) {
                    logger.error("null dependency in entity " + entity);
                    continue;
                }
                if (entity.module != dep.module)
                    entity.module.addDependency(dep.module);
            }
        }

        Collections.sort(modules, ModuleDepOrder.INSTANCE);
    }

    void addIndexClass(Class<?> indexClass) {
        Class<?>[] pv = TypeParam.infer(indexClass, AbstractEntityController.class);
        Class<?> entityType = pv[0];
        Class<?> maskType = pv[1];
        EntityInfo entity = resolveEntity(entityType, maskType);
        entity.setIndexClass(indexClass);
        // IndexInfo index = new IndexInfo(indexClass);
        // nameIndex.put(indexClass.getName(), index);
    }

    void addMapperClass(Class<?> mapperClass) {
        if (IEntityMapper.class.isAssignableFrom(mapperClass)) {
            Class<?>[] pv = TypeParam.infer(mapperClass, IEntityMapper.class);
            Class<?> entityType = pv[0];
            Class<?> maskType = pv[1];
            EntityInfo entity = resolveEntity(entityType, maskType);
            entity.setMapperClass(mapperClass);
        }
    }

    EntityInfo resolveEntity(Class<?> entityType, Class<?> maskType) {
        if (entityType == null)
            throw new NullPointerException("entityType");
        if (maskType == null)
            throw new NullPointerException("maskType");

        String fqcn = entityType.getName();
        EntityInfo entity = nameEntity.get(fqcn);
        if (entity == null) {
            CriteriaBuilderInfo mask = loadCriteriaBuilderRec(maskType);
            if (mask == null)
                throw new NullPointerException("mask");

            ModuleInfo modInfo = classModule.get(entityType.getName());
            if (modInfo == null)
                throw new IllegalUsageException("No module defined for " + entityType);

            entity = new EntityInfo(entityType, mask);
            nameEntity.put(fqcn, entity);

            entity.module = modInfo;
            modInfo.add(entity);
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

        String maskName = fqcn + "CriteriaBuilder";
        Class<?> maskClass = null;
        try {
            maskClass = clazz.getClassLoader().loadClass(maskName);
        } catch (ClassNotFoundException e) {
            maskClass = CoObjectCriteriaBuilder.class;
        }
        entity = resolveEntity(clazz, maskClass);
        entity.parent = loadRec(clazz.getSuperclass());
        return entity;
    }

    CriteriaBuilderInfo loadCriteriaBuilderRec(Class<?> maskClass) {
        if (maskClass == null)
            return null;
        if (maskClass == Object.class)
            return null;

        String fqcn = maskClass.getName();
        CriteriaBuilderInfo mask = nameCriteriaBuilder.get(fqcn);
        if (mask != null)
            return mask;

        mask = new CriteriaBuilderInfo(maskClass);
        mask.parent = loadCriteriaBuilderRec(maskClass.getSuperclass());
        nameCriteriaBuilder.put(fqcn, mask);
        return mask;
    }

    public List<ModuleInfo> getModules() {
        lazyLoad();
        return modules;
    }

    public EntityIndex getEntityIndex() {
        lazyLoad();
        return entityIndex;
    }

    public EntityInfo getEntity(String name) {
        lazyLoad();
        return nameEntity.get(name);
    }

    public EntityInfo getEntity(Class<?> entityType) {
        lazyLoad();
        return nameEntity.get(entityType.getName());
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
        return PathArrival.shift(previous, this, target, tokens);
    }

    static ModuleIndexer instance = new ModuleIndexer();

    public static ModuleIndexer getInstance() {
        return instance;
    }

}
