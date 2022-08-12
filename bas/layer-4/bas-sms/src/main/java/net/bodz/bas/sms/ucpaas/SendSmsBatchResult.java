package net.bodz.bas.sms.ucpaas;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class SendSmsBatchResult
        extends AbstractUcpaasResult {

    /**
     * 总计费条数
     */
    public int countSum;
    public final List<SendSmsResult> reportList = new ArrayList<SendSmsResult>();

    public int getCountSum() {
        return countSum;
    }

    public void setCountSum(int countSum) {
        this.countSum = countSum;
    }

    public List<SendSmsResult> getReportList() {
        return reportList;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        String _countSum = o.getString("count_sum");
        countSum = Integer.parseInt(_countSum);

        JsonArray report = o.getJsonArray("report");
        int n = report.length();
        reportList.clear();
        for (int i = 0; i < n; i++) {
            JsonObject node = report.getJsonObject(i);
            SendSmsResult item = new SendSmsResult();
            item.jsonIn(node, opts);
            reportList.add(item);
        }
    }

}
