package net.bodz.lily.model.base;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

import net.bodz.lily.model.sea.QVariantMap;

public abstract class CoMomentInterval<Id>
        extends CoObject
        implements IMomentInterval, IId<Id> {

    private static final long serialVersionUID = 1L;

    private Id id;
    private Date beginDate;
    private Date endDate;

    @Override
    public Class<Id> idType() {
        return IId.fn._getIdType(getClass());
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @label Begin Date
     * @label.zh 开始时间
     */
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public Date getBeginDate() {
        return beginDate;
    }

    @Override
    public void setBeginDate(Date beginTime) {
        this.beginDate = beginTime;
    }

    /**
     * @label End Date
     * @label.zh 结束时间
     */
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endTime) {
        this.endDate = endTime;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
// id = map.getLong("id", id);
        beginDate = map.getDate("beginDate", beginDate);
        endDate = map.getDate("endDate", endDate);
    }

    @Override
    public Id persist(IHtmlViewContext ctx, IHtmlTag out)
            throws IOException {
        Id id = (Id) super.persist(ctx, out);
        setId(id);
        return id;
    }

}
