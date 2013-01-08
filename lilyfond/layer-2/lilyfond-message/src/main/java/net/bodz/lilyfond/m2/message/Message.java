package net.bodz.lilyfond.m2.message;

import java.util.List;

import net.bodz.lilyfond.m2.ManagedEntity;
import net.bodz.lilyfond.m2.user.User;

public class Message
        extends ManagedEntity {

    private static final long serialVersionUID = 1L;

    String label;
    String description;

    User sender;
    List<Object> attachments;

    int priority;
    Object emotion;

}
