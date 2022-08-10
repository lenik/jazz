package net.bodz.violet.art.impl;

import java.util.Arrays;

import net.bodz.bas.db.test.DatabaseTester;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactProperties;

public class ArtifactPropertiesTest
        extends DatabaseTester {

    @Override
    public void main()
            throws Exception {
        ArtifactMapper mapper = dataContext.getMapper(ArtifactMapper.class);
        Artifact art = mapper.select(1000);
        ArtifactProperties props = art.getProperties();
        System.out.println(props);
        props.setColor("Red");
        props.setAttribute("arr", Arrays.asList(1, 2, 3));
        mapper.update(art);
        System.out.println(props);
    }

    public static void main(String[] args)
            throws Exception {
        new ArtifactPropertiesTest().main();
    }

}
