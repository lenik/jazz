package net.bodz.violet.schema.plan;

import javax.persistence.Table;

/**
 * 日记分类
 */
@Table(schema = "violet", name = "diarycat")
public class DiaryCategory
        extends _DiaryCategory_stuff<DiaryCategory> {

    private static final long serialVersionUID = 1L;

}
