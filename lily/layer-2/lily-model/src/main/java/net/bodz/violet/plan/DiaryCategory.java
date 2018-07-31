package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCategory;

/**
 * 日记分类
 */
@Table(name = "diarycat")
@IdType(Integer.class)
public class DiaryCategory
        extends CoCategory<DiaryCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public DiaryCategory() {
        super();
    }

    public DiaryCategory(DiaryCategory parent) {
        super(parent);
    }

}
