package net.bodz.lily.model.mx.forum;

import net.bodz.lily.model.mx.base.CoMessage;

public class Post
        extends CoMessage {

    private static final long serialVersionUID = 1L;

    TopicPost topic;
    Post replyTo;

}
