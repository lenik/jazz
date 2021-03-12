package net.bodz.bas.sms.ucpaas;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonArray;
import net.bodz.bas.fmt.json.JsonObject;

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
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        String _countSum = o.getString("count_sum");
        countSum = Integer.parseInt(_countSum);

        JsonArray report = o.getJsonArray("report");
        int n = report.length();
        reportList.clear();
        for (int i = 0; i < n; i++) {
            JsonObject node = report.getJsonObject(i);
            SendSmsResult item = new SendSmsResult();
            item.readObject(node);
            reportList.add(item);
        }
    }

}
