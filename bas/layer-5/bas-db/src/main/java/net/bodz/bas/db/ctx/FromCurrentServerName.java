package net.bodz.bas.db.ctx;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

public class FromCurrentServerName
        implements
            IDefaultContextIdsResolver {

    static final Pattern IP4_PATTERN = FromCurrentServerIP.IP4_PATTERN;
    static final Pattern IP6_PATTERN = FromCurrentServerIP.IP6_PATTERN;

    @Override
    public int getPriority() {
        return PRIORITY_SERVER_NAME;
    }

    @Override
    public Collection<String> resolveContextIds(int level) {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;

        String hostName = request.getServerName();
        if ((IP4_PATTERN.matcher(hostName).matches() //
                || IP6_PATTERN.matcher(hostName).matches()))
            return null;

        int port = request.getServerPort();

        Set<String> ids = new LinkedHashSet<>();
        String qName = DomainName.reverse(hostName);
        while (qName != null) {
            String qPath = qName.replace('.', '/');
            String qRpnPath = RepeatPrefixNames.DEFAULT.expand(qPath);

            ids.add("host/" + qRpnPath + "_" + port);
            ids.add("host/" + qName + "_" + port);
            ids.add("host/" + qRpnPath);
            ids.add("host/" + qName);
            qName = StringPart.beforeLast(qName, '.');
        }
        return ids;
    }

}
