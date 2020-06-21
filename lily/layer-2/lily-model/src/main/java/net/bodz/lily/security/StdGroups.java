package net.bodz.lily.security;

public class StdGroups {

    public static Group root = new Group(0, "root", "Super privilege users");

    public static Group admin = new Group(1, "admin", "Administrators");

    public static Group user = new Group(2, "user", "Normal users");

    public static Group guest = new Group(3, "guest", "Guests");

}
