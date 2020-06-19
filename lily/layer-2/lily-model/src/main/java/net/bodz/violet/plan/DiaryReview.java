package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@Table(name = "diaryrev")
@IdType(Long.class)
public class DiaryReview
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Diary diary;
    DiaryReview parent;

    public DiaryReview() {
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public DiaryReview getParent() {
        return parent;
    }

    public void setParent(DiaryReview parent) {
        this.parent = parent;
    }

}
