package net.bodz.lily.geo;

public class ZoneSamples {

    public static Zone build(ZoneCategory category) {
        Zone a = new Zone();
        a.setCategory(category);
        a.setLabel("zone-1");
        a.setDescription("A zone named zone-1.");
        return a;
    }

}
