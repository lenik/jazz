package net.bodz.xml.models.pdb;

import net.bodz.xml.models.pdb.View.GroupBy;
import net.bodz.xml.models.pdb.View.Sort;
import net.bodz.xml.util.XMLTest;

import org.junit.Test;

public class ViewTest {

    static View productSumView;
    static {
        productSumView = new View();
        productSumView.setName("productSale");
        productSumView.setLabel("产品分类销售汇总");
        productSumView.setDoc("按照产品分类统计销售额");
        productSumView.setDistinct(true);
        productSumView.setOpts("D");

        View.Field groupId = new View.Field();
        groupId.setValue("class");
        View.Field groupName = new View.Field();
        groupName.setValue("name");
        groupName.setLabel("类别");
        View.Field total = new View.Field();
        total.setValue("sum(price)");
        total.setName("total");
        productSumView.getFields().add(groupId);
        productSumView.getFields().add(groupName);
        productSumView.getFields().add(total);

        GroupBy groupBy = new GroupBy();
        groupBy.setFields("class");
        productSumView.setGroupBy(groupBy);

        Sort sort = new View.Sort();
        sort.setValue("class");
        productSumView.setSort(sort);
    }

    @Test
    public void test() throws Exception {
        XMLTest.testMarshal(productSumView);
    }

}
