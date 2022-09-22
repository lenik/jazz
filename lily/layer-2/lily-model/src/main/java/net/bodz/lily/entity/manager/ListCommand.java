package net.bodz.lily.entity.manager;

import java.util.List;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IJsonForm.class)
@ForEntityCriteriaType(IVarMapForm.class)
public class ListCommand
        extends AbstractEntityCommand {

    TableOfPathProps tableData;
    IVarMapForm criteria;
    SelectOptions opts;

    public ListCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    public String getPreferredName() {
        return "__data__";
    }

    @Override
    public Object execute()
            throws Exception {
        List<Object> dataList = buildDataList();
        tableData.setList(dataList);

        if (tableData.isWantTotalCount()) {
            long totalCount = getEntityMapper().count(criteria);
            tableData.setTotalCount(totalCount);
        }

        return tableData;
    }

    protected List<Object> buildDataList() {
        return getEntityMapper().filter(criteria, opts);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        tableData = new TableOfPathProps(typeInfo.getEntityClass());
        tableData.readObject(map);

        try {
            criteria = (IVarMapForm) typeInfo.getCrtieriaClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(e.getMessage(), e);
        }
        criteria.readObject(map);

        opts = new SelectOptions();
        opts.readObject(map);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(ListCommand.class);
        }

        @Override
        public ListCommand build() {
            return new ListCommand(typeInfo);
        }
    }

}
