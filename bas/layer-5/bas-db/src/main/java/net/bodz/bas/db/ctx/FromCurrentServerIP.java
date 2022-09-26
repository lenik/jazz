package net.bodz.bas.db.ctx;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

public class FromCurrentServerIP
        implements
            IDefaultContextIdsResolver {

    public static final Pattern IP4_PATTERN = Pattern.compile("^\\d+(\\.\\d+){3}$");
    public static final Pattern IP6_PATTERN = Pattern.compile("^[0-9a-f]+(:[0-9a-f]+){5}", Pattern.CASE_INSENSITIVE);

    @Override
    public int getPriority() {
        return PRIORITY_SERVER_NAME;
    }

    @Override
    public Collection<String> resolveContextIds(int level) {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;

        String ipAddr = request.getServerName();
        if (!(IP4_PATTERN.matcher(ipAddr).matches() //
                || IP6_PATTERN.matcher(ipAddr).matches()))
            return null;

        int port = request.getServerPort();

        Set<String> ids = new LinkedHashSet<>();
        while (ipAddr != null) {
            String ipPath = ipAddr.replace('.', '/');
            String ipRpnPath = RepeatPrefixNames.DEFAULT.expand(ipPath);

            ids.add("ip/" + ipRpnPath + "_" + port);
            ids.add("ip/" + ipAddr + "_" + port);
            ids.add("ip/" + ipRpnPath);
            ids.add("ip/" + ipAddr);
            ipAddr = StringPart.beforeLast(ipAddr, '.');
        }
        return ids;
    }

}
