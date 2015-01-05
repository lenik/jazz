package user;

import java.util.Random;

import org.junit.Assert;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

import net.bodz.lily.model.base.security.Group;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.base.security.impl.GroupMapper;

public class GroupPlayer
        extends Assert {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws Exception {
        GroupMapper groupMapper = TestEnv.getMapper(GroupMapper.class);

        Group group = new Group();
        group.setCodeName("Test" + random.nextInt());
        group.setLabel("Test1");
        int id = (int) groupMapper.insert(group);

        group.setId(id);
        group.setLabel("Changed");
        groupMapper.update(group);

        groupMapper.delete(id);
    }

    static void dump(Group group) {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        out.println(group.getId() + ": " + group.getCodeName() + ": " + group.getLabel());

        out.enter();
        for (User u : group.getUsers())
            out.println("Member User: " + u.getId() + "-" + u.getLabel());

        out.leave();
    }

}