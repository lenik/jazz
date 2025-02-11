package net.bodz.lily.entity.manager.cmd;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandType;
import net.bodz.lily.entity.manager.IJdbcRowOpListener;
import net.bodz.lily.entity.manager.JdbcRowOpEvent;
import net.bodz.lily.entity.manager.JdbcRowOpListeners;
import net.bodz.lily.entity.manager.JdbcRowOpType;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.entity.manager.RowOpAwares;
import net.bodz.lily.entity.type.EntityOpListeners;

public class DeleteProcess
        extends AbstractEntityCommandProcess {

    boolean transaction = false;

    ResolvedEntity resolvedEntity;
    List<Object> idList = new ArrayList<>();

    public DeleteProcess(IEntityCommandType type, IEntityCommandContext context) {
        super(type, context);
    }

    public DeleteProcess(IEntityCommandType type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context);
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    @Override
    public Object execute()
            throws Exception {
        int nUpdates = 0;
        StringBuilder errorIds = new StringBuilder();

        Map<Object, Object> idMap = new LinkedHashMap<>();

        if (resolvedEntity != null) {
            IId<?> obj = (IId<?>) resolvedEntity.entity;
            idMap.put(obj.id(), obj);
        } else {
            if (idList.isEmpty()) {
                result.fail("Id isn't specified.");
                return 0;
            } else {
                for (Object id : idList)
                    idMap.put(id, true);
            }
        }

        Object prevent = canDelete(idMap);
        if (prevent != null) {
            result.fail("Can not be deleted: " + prevent);
            return 0;
        }

        prevent = beforeDelete(idMap);
        if (prevent != null) {
            result.fail("Failed before delete: " + prevent);
            return 0;
        }

        for (Object id : idMap.keySet()) {
            if (getEntityMapper().delete(id))
                nUpdates++;
            else
                errorIds.append(id).append(",");
        }

        afterDelete(idMap);

        if (errorIds.length() > 0) {
            errorIds.setLength(errorIds.length() - 1);
            result.fail("Not deleted: " + errorIds);
        }
        return nUpdates;
    }

    Object canDelete(Map<?, ?> idMap)
            throws Exception {
        for (Object id : idMap.keySet()) {
            Object o = idMap.get(id);
            if (o == Boolean.TRUE)
                o = null;
            if (!canDelete(o))
                return o;
        }
        return null;
    }

    boolean canDelete(Object obj)
            throws Exception {

        if (obj != null)
            if (!EntityOpListeners.canDelete(obj))
                return false;

        return true;
    }

    Object beforeDelete(Map<?, ?> idMap)
            throws Exception {
        for (Object id : idMap.keySet()) {
            Object o = idMap.get(id);
            if (o == Boolean.TRUE)
                o = null;
            JdbcRowOpEvent event = new JdbcRowOpEvent(o, JdbcRowOpType.DELETE);
            if (!beforeDelete(event, o))
                return o;
        }
        return null;
    }

    boolean beforeDelete(JdbcRowOpEvent event, Object obj)
            throws Exception {

        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            if (!l.beforeRowOperation(event, obj))
                return false;
        }

        if (obj != null) {
            if (!RowOpAwares.beforeRowOperation(event, obj))
                return false;
            EntityOpListeners.beforeDelete(obj);
        }

        if (!JdbcRowOpListeners.beforeRowOperation(event, obj))
            return false;

        return true;
    }

    void afterDelete(Map<?, ?> idMap)
            throws Exception {
        for (Object id : idMap.keySet()) {
            Object o = idMap.get(id);
            if (o == Boolean.TRUE)
                o = null;
            JdbcRowOpEvent event = new JdbcRowOpEvent(o, JdbcRowOpType.DELETE);
            afterDelete(event, o);
        }
    }

    void afterDelete(JdbcRowOpEvent event, Object obj)
            throws Exception {

        if (obj instanceof IJdbcRowOpListener) {
            IJdbcRowOpListener l = (IJdbcRowOpListener) obj;
            l.afterRowOperation(event, obj);
        }

        if (obj != null) {
            RowOpAwares.afterRowOperation(event, obj);
            EntityOpListeners.afterDelete(obj);
        }

        JdbcRowOpListeners.afterRowOperation(event, obj);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        // common part:

        if (resolvedEntity != null)
            return;

        // not resolved part:
        List<Object> idList = new ArrayList<>();

        String idString = map.getString("id");
        if (idString != null) {
            Object id = typeInfo.parseSimpleId(idString);
            idList.add(id);
        }

        String idStrings = map.getString("ids");
        if (idStrings != null) {
            for (String idStr : idStrings.split(",")) {
                Object id = typeInfo.parseSimpleId(idStr.trim());
                idList.add(id);
            }
        }

        this.idList = idList;
    }

}