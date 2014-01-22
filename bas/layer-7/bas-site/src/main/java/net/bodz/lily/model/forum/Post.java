package net.bodz.lily.model.forum;

import net.bodz.lily.model.base.Message;

public class Post
        extends Message {

    private static final long serialVersionUID = 1L;

    TopicPost topic;
    Post replyTo;

}
