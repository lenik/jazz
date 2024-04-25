package net.bodz.lily.entity.manager.cmd;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandType;
import net.bodz.lily.entity.manager.ResolvedEntity;

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
    public Object execute()
            throws Exception {
        int nUpdates = 0;
        StringBuilder errorIds = new StringBuilder();

        if (resolvedEntity != null) {
            IId<?> obj = (IId<?>) resolvedEntity.entity;
            Object id = obj.id();
            if (getEntityMapper().delete(id))
                nUpdates++;
            else
                errorIds.append(id + ",");
        } else {
            if (idList.isEmpty()) {
                result.fail("Id isn't specified.");
            } else {
                for (Object id : idList) {
                    if (getEntityMapper().delete(id))
                        nUpdates++;
                    else
                        errorIds.append(id + ",");
                }
            }
        }

        if (errorIds.length() > 0) {
            errorIds.setLength(errorIds.length() - 1);
            result.fail("Not deleted: " + errorIds);
        }
        return nUpdates;
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