package net.bodz.lily.concrete;

import java.time.OffsetDateTime;

import javax.persistence.Column;

import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@FieldGroupVue
@IncludeMapperXml
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public abstract class CoEvent<Id>
        extends IdEntity<Id>
        implements
            IMomentInterval {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";

    private OffsetDateTime beginTime;
    private OffsetDateTime endTime;
    private int year;

    public CoEvent() {
        beginTime = getCreationDate();
    }

    /**
     * <p lang="zh">
     * 事件的开始时间（具体的发生时间，或期待的发生时间）。
     * <p>
     * 当未指定时，表示尚未开始或开始于无限远以前。
     *
     * @label Begin Date
     * @label.zh 开始时间
     */
    @Column(name = FIELD_BEGIN_TIME)
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public OffsetDateTime getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(OffsetDateTime beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * <p lang="zh">
     * 事件的结束时间（具体的完成时间，或期待的完成时间）。
     * <p>
     * 当未指定时，表示尚未结束或结束于无限远以后。
     *
     * @label End Date
     * @label.zh 结束时间
     */
    @Column(name = FIELD_END_TIME)
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public OffsetDateTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        beginTime = o.getOffsetDateTime("beginTime", beginTime);
        endTime = o.getOffsetDateTime("endTime", endTime);
    }

}
