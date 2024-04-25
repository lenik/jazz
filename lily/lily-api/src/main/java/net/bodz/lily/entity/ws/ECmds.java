package net.bodz.lily.entity.ws;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.entity.manager.IEntityCommandType;

public class ECmds {

    static final Logger logger = LoggerFactory.getLogger(ECmds.class);

    public static void checkConflict(IEntityCommandType cmd1, IEntityCommandType cmd2) {
        String id1 = cmd1.getUniqueId();
        String id2 = cmd2.getUniqueId();
        if (id1.equals(id2))
            throw new DuplicatedKeyException("conflict id: " + id1);

        if (cmd1.isContentCommand() != cmd2.isContentCommand())
            return;

        List<String> keys1 = new ArrayList<>();
        List<String> keys2 = new ArrayList<>();

        for (String name1 : cmd1.getCommandNames())
            for (String method1 : cmd1.getAcceptedMethods())
                keys1.add(method1 + ":" + name1);

        for (String name2 : cmd2.getCommandNames())
            for (String method2 : cmd2.getAcceptedMethods())
                keys2.add(method2 + ":" + name2);

        for (String key1 : keys1)
            for (String key2 : keys2)
                if (key1.equals(key2))
                    throw new DuplicatedKeyException("conflict key: " + key1);
    }

}
