package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "diary_vote")
@IdType(Integer.class)
public class DiaryVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public DiaryVote() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryVote: ...");
        return sb.toString();
    }

}
