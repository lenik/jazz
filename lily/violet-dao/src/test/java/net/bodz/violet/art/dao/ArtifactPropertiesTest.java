package net.bodz.violet.art.dao;

import java.util.Arrays;

import net.bodz.bas.db.test.DatabaseTester;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.IArtifactExtras;
import net.bodz.violet.schema.art.dao.ArtifactMapper;

public class ArtifactPropertiesTest
        extends DatabaseTester {

    @Override
    public void main()
            throws Exception {
        ArtifactMapper mapper = dataContext.getMapper(ArtifactMapper.class);
        Artifact art = mapper.select(1000);
        IArtifactExtras extras = art.getExtras();
        System.out.println(extras);
        extras.setColor("Red");
        art.setAttribute("arr", Arrays.asList(1, 2, 3));
        mapper.update(art);
        System.out.println(extras);
    }

    public static void main(String[] args)
            throws Exception {
        new ArtifactPropertiesTest().main();
    }

}
