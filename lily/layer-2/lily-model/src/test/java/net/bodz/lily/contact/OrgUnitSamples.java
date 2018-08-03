package net.bodz.lily.contact;

public class OrgUnitSamples {

    public static OrgUnit build(Organization org, OrgUnit parent) {
        OrgUnit a = new OrgUnit();
        a.setLabel("orgUnit-1");
        a.setDescription("A orgUnit named orgUnit-1.");
        a.setOrg(org);
        a.setParent(parent);
        return a;
    }

}
