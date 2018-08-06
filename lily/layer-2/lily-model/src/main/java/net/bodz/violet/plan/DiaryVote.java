package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "diary_vote")
public class DiaryVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Diary diary;

    public DiaryVote() {
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

}
