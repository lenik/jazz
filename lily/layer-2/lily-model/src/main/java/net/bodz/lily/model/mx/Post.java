package net.bodz.lily.model.mx;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.base.CoMessage;

@IdType(Long.class)
public class Post
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    TopicPost topic;
    Post replyTo;

}
