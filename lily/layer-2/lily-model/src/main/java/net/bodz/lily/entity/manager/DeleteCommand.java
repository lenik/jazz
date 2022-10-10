package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IId.class)
public class DeleteCommand
        extends AbstractEntityCommand {

    boolean transaction = false;

    List<Object> idList = new ArrayList<>();

    public DeleteCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    public Object execute()
            throws Exception {
        int nUpdates = 0;
        if (idList.isEmpty()) {
            result.fail("Id isn't specified.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Object id : idList) {
                if (getEntityMapper().delete(id))
                    nUpdates++;
                else
                    sb.append(id + ",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                result.fail("Not deleted: " + sb);
            }
        }
        return nUpdates;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        List<Object> idList = new ArrayList<>();

        String idString = map.getString("id");
        if (idString != null) {
            Object id = parseId(idString);
            idList.add(id);
        }

        String idStrings = map.getString("ids");
        if (idStrings != null) {
            for (String idStr : idStrings.split(",")) {
                Object id = parseId(idStr.trim());
                idList.add(id);
            }
        }

        this.idList = idList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(DeleteCommand.class);
        }

        @Override
        public DeleteCommand build() {
            return new DeleteCommand(typeInfo);
        }
    }

}