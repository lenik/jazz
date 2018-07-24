package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 讨论区
 */
@Table(name = "postcat")
@IdType(Integer.class)
public class PostCategory
        extends CoNode<PostCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public PostCategory() {
    }

}
