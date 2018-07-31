package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

@Table(name = "diaryrev")
@IdType(Long.class)
public class DiaryReview
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public DiaryReview() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReview: ...");
        return sb.toString();
    }

}
