package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;

@ForEntityType(IId.class)
public class DeleteCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "delete";
    public static final String[] NAMES = { NAME, "del", "rm" };

    public DeleteCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public DeleteProcess createProcess(IEntityCommandContext context) {
        return new DeleteProcess(this, context);
    }

}

class DeleteProcess
        extends AbstractEntityCommandProcess<DeleteCommand> {

    boolean transaction = false;

    List<Object> idList = new ArrayList<>();

    public DeleteProcess(DeleteCommand type, IEntityCommandContext context) {
        super(type, context);
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
            Object id = context.parseId(idString);
            idList.add(id);
        }

        String idStrings = map.getString("ids");
        if (idStrings != null) {
            for (String idStr : idStrings.split(",")) {
                Object id = context.parseId(idStr.trim());
                idList.add(id);
            }
        }

        this.idList = idList;
    }

}