package net.bodz.bas.db.ibatis.sql;

import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class SelectionRange
        implements
            ISelectionRange,
            IPagination,
            IVarMapForm {

    public static final String K_OFFSET = "offset";
    public static final String K_LIMIT = "limit";
    public static final String K_PAGE_INDEX[] = { "pageIndex", "pageInd" };
    public static final String K_PAGE_NUMBER[] = { "pageNumber", "pageNum" };
    public static final String K_PAGE_SIZE = "pageSize";

    public static final String UNLIMIT_LITERAL = "unlimit";

    long offset = UNDEFINED;
    long limit = UNDEFINED;
    long countLimit = UNDEFINED;

    public SelectionRange() {
    }

    public SelectionRange(long offset) {
        this.offset = offset;
        this.limit = UNLIMIT;
    }

    public SelectionRange(long offset, long limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public boolean isRangeSpecified() {
        if (offset != UNDEFINED && offset != 0)
            return true;
        if (isLimited())
            return true;
        return false;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public IPagination offset(long offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public IPagination limit(long limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public boolean isLimited() {
        return limit != UNDEFINED && limit != UNLIMIT;
    }

    @Override
    public boolean isUnlimited() {
        return limit == UNDEFINED || limit == UNLIMIT;
    }

    @Override
    public long getCountLimit() {
        return countLimit;
    }

    public void setCountLimit(long countLimit) {
        this.countLimit = countLimit;
    }

    public IPagination countLimit(long countLimit) {
        this.countLimit = countLimit;
        return this;
    }

    @Override
    public boolean isCountLimited() {
        return countLimit != UNDEFINED && countLimit != UNLIMIT;
    }

    @Override
    public boolean isCountUnlimited() {
        return countLimit == UNDEFINED || countLimit == UNLIMIT;
    }

    public boolean isCountNeeded() {
        if (offset != 0)
            return true;

        if (isLimited()) {
            if (isCountLimited())
                return limit < countLimit;
            else
                return true;
        }

        return false;
    }

    @Override
    public long getPageIndex() {
        if (limit == 0)
            return 0; // DIV0, fallback to first page
        if (limit == UNDEFINED)
            return 0; // always the first page
        if (limit == UNLIMIT)
            return 0; // only one page.
        return offset / limit;
    }

    public void setPageIndex(long pageIndex) {
        if (limit == UNDEFINED)
            offset = 0;
        else if (limit == UNLIMIT)
            offset = 0;
        else
            offset = pageIndex * limit;
    }

    @Override
    public long getPageNumber() {
        return getPageIndex() + 1;
    }

    public void setPageNumber(long pageNumber) {
        setPageIndex(pageNumber - 1);
    }

    @Override
    public long getPageSize() {
        return limit;
    }

    public void setPageSize(long pageSize) {
        this.limit = pageSize;
    }

    public IPagination pageByIndex(int pageIndex, int pageSize) {
        setPageSize(pageSize);
        setPageIndex(pageIndex);
        return this;
    }

    public IPagination pageByNumber(int pageIndex, int pageSize) {
        setPageSize(pageSize);
        setPageNumber(pageIndex);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // offset # limit #
        sb.append("offset");
        sb.append(offset);

        if (limit != UNDEFINED) {
            if (limit == UNLIMIT)
                sb.append(" unlimit");
            else {
                sb.append(" limit");
                sb.append(limit);
            }
        }

        if (limit != UNDEFINED) {
            // page # of # rows
            long pageSize = limit;
            long pageIndex = offset / limit;
            sb.append(", page ");
            sb.append(pageIndex);
            sb.append(" of ");
            sb.append(pageSize);
            sb.append(" rows");
        }

        if (countLimit != UNDEFINED) {
            sb.append(", count ");
            if (countLimit == UNLIMIT)
                sb.append(" unlimit");
            else {
                sb.append(" limit ");
                sb.append(countLimit);
            }
        }
        return sb.toString();
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {

        offset = map.getLong(K_OFFSET, offset);

        if (UNLIMIT_LITERAL.equals(map.getString(K_LIMIT)))
            limit = UNLIMIT;
        else
            limit = map.getLong(K_LIMIT, limit);

        Long _pageIndex = null;
        Long _pageNumber = null;
        for (String k : K_PAGE_INDEX) {
            Long val = map.getLong(k);
            if (val != null) {
                _pageIndex = val;
                break;
            }
        }
        for (String k : K_PAGE_NUMBER) {
            Long val = map.getLong(k);
            if (val != null) {
                _pageNumber = val;
                break;
            }
        }

        long pageSize = UNDEFINED;
        if (UNLIMIT_LITERAL.equals(map.getString(K_PAGE_SIZE)))
            limit = pageSize = UNLIMIT;
        else {
            Long _pageSize = map.getLong(K_PAGE_SIZE);
            pageSize = _pageSize == null ? UNDEFINED : _pageSize.longValue();
        }
        if (_pageIndex != null || _pageNumber != null) {
            if (pageSize == UNDEFINED)
                pageSize = 100;
        }
        if (pageSize != UNDEFINED)
            setPageSize(pageSize);

        if (_pageIndex != null)
            setPageIndex(_pageIndex.longValue());

        if (_pageNumber != null)
            setPageNumber(_pageNumber.longValue());
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

}
