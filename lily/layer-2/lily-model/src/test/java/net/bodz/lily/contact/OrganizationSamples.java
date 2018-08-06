package net.bodz.lily.contact;

import net.bodz.lily.test.TestSamples;

public class OrganizationSamples
        extends TestSamples {

    public static Organization build() {
        Organization a = new Organization();
        a.setLabel("organization-1");
        a.setDescription("A organization named organization-1.");
        return a;
    }

}
