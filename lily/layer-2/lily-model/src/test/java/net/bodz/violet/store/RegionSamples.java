package net.bodz.violet.store;

public class RegionSamples {

    public static Region build() {
        Region a = new Region();
        a.setLabel("region-1");
        a.setDescription("A region named region-1.");
        String path = a.getNodePath();
        a.setFullPath(path);
        return a;
    }

}
