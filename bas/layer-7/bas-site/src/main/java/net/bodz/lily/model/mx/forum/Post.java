package net.bodz.lily.model.mx.forum;

import net.bodz.lily.model.mx.base.Message;

public class Post
        extends Message {

    private static final long serialVersionUID = 1L;

    TopicPost topic;
    Post replyTo;

}
