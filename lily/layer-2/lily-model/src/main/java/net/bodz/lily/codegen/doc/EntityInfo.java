package net.bodz.lily.codegen.doc;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObject;

/**
 * @see ModuleInfo
 */
public class EntityInfo
        extends AbstractTypeInfo<EntityInfo>
        implements IPathDispatchable {

    ModuleIndexer indexer;

    MaskInfo maskInfo;
    IndexInfo indexInfo;

    ReferenceMap refmap = new ReferenceMap();

    Set<EntityInfo> dependencies = new LinkedHashSet<>();

    public EntityInfo(Class<?> clazz, MaskInfo maskInfo) {
        super(clazz);
        if (maskInfo == null)
            throw new NullPointerException("maskInfo");
        this.maskInfo = maskInfo;
    }

    public MaskInfo getMaskInfo() {
        return maskInfo;
    }

    public void setMapperClass(Class<?> mapperClass) {
        // skip
    }

    public void setIndexClass(Class<?> indexClass) {
        this.indexInfo = new IndexInfo(indexClass);
    }

    public ReferenceMap getReferenceMap() {
        return refmap;
    }

    @Override
    public Map<String, IMethod> getMethodMap() {
        return Collections.emptyMap();
    }

    public Set<EntityInfo> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return declaredClass.getName();
    }

    @Override
    protected void parse(IType declaredType, ModuleIndexer indexer) {
        for (IProperty property : declaredType.getProperties()) {
            addProperty(property);
            parseIfAnyRef(property, indexer);
        }
    }

    void parseIfAnyRef(IProperty property, ModuleIndexer indexer) {
        Class<?> type = property.getPropertyType();
        if (CoObject.class.isAssignableFrom(type)) { // is a relation reference.
            refmap.put(type, ReferenceType.ManyToOne);
            EntityInfo target = indexer.getEntity(type);
            if (target != this)
                dependencies.add(target);
            return;
        }
        if (Collection.class.isAssignableFrom(type)) {
            Class<?> param = TypeParam.infer1(type, Collection.class, 0);
            if (CoObject.class.isAssignableFrom(param)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
        }
        if (Map.class.isAssignableFrom(type)) {
            Class<?> kp = TypeParam.infer1(type, Map.class, 0);
            Class<?> vp = TypeParam.infer1(type, Map.class, 1);
            if (CoObject.class.isAssignableFrom(kp)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
            if (CoObject.class.isAssignableFrom(vp)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
        }
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

}
