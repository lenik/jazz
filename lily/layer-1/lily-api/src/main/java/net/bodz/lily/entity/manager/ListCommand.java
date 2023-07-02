package net.bodz.lily.entity.manager;

import java.util.List;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.StructRowMask;

@ForEntityType(IJsonForm.class)
@ForEntityCriteriaType(IVarMapForm.class)
public class ListCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "list";

    public ListCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public IEntityCommandProcess createProcess(IEntityCommandContext context) {
        return new ListProcess(this, context);
    }

}

class ListProcess
        extends AbstractEntityCommandProcess<ListCommand> {

    TableOfPathProps tableData;
    final StructRowMask criteria;
    final SelectOptions selectOptions;

    public ListProcess(ListCommand type, IEntityCommandContext context) {
        super(type, context);

        Class<?> criteriaClass = typeInfo.getCrtieriaClass();
        try {
            criteria = (StructRowMask) criteriaClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(String.format(//
                    "Can't instantiate criteria: %s", e.getMessage()), e);
        }

        selectOptions = new SelectOptions();
    }

    public IVarMapForm getCriteria() {
        return criteria;
    }

    public SelectOptions getSelectOptions() {
        return selectOptions;
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
        return getEntityMapper().filter(criteria, selectOptions);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        tableData = new TableOfPathProps(typeInfo.getEntityClass());
        tableData.readObject(map);
        criteria.readObject(map);
        selectOptions.readObject(map);
    }

}
