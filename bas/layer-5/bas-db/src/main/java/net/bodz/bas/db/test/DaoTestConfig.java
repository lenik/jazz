package net.bodz.bas.db.test;

public class DaoTestConfig {

    public boolean testEnabled = true;
    public boolean testList = testEnabled;
    public boolean testSelect = testEnabled;
    public boolean testInsert = testEnabled;
    public boolean testUpdate = testEnabled;
    public boolean testDelete = testEnabled;

    public boolean purge = false;

    public static final DaoTestConfig global = new DaoTestConfig();

}
