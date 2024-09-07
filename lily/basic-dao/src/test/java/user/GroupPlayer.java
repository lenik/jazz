package user;

import java.util.Random;

import net.bodz.bas.db.test.DatabaseTester;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;

public class GroupPlayer
        extends DatabaseTester {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws Exception {
        new GroupPlayer().main();
    }

    @Override
    public void main()
            throws Exception {
        GroupMapper groupMapper = getMapper(GroupMapper.class);

        Group group = new Group();
        group.setName("Test" + random.nextInt());
        group.setLabel("Test1");
        groupMapper.insert(group);
        int id = group.id();

        group.setLabel("Changed");
        groupMapper.update(group);

        groupMapper.delete(id);
    }

    static void dump(Group group) {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        out.println(group.id() + ": " + group.getUniqName() + ": " + group.getLabel());

        out.enter();
        for (User u : group.getUsers())
            out.println("Member User: " + u.id() + "-" + u.getLabel());

        out.leave();
    }

}