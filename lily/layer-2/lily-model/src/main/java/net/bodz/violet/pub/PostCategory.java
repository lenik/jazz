package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

/**
 * 讨论区
 */
@Table(name = "postcat")
@IdType(Integer.class)
public class PostCategory
        extends CoCategory<PostCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public PostCategory() {
    }

}
