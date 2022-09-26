package net.bodz.bas.site.vhost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.db.ctx.IDefaultContextIdsResolver;
import net.bodz.bas.db.ctx.RepeatPrefixNames;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

public class FromCurrentVirtualHostName
        implements
            IDefaultContextIdsResolver {

    @Override
    public int getPriority() {
        return PRIORITY_VIRTUALHOST_NAME;
    }

    @Override
    public Collection<String> resolveContextIds(int level) {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;

        IVirtualHost vhost = VirtualHostManager.getInstance().get(request);
        if (vhost == null)
            return null;

        String vhostName = vhost.getName();
        int port = request.getServerPort();

        List<String> ids = new ArrayList<>();
        while (vhostName != null) {
            String vhostPath = vhostName.replace('.', '/');
            String vhostRpnPath = RepeatPrefixNames.DEFAULT.expand(vhostPath);

            ids.add("vhost/" + vhostRpnPath + "_" + port);
            ids.add("vhost/" + vhostName + "_" + port);
            ids.add("vhost/" + vhostRpnPath);
            ids.add("vhost/" + vhostName);
            vhostName = StringPart.beforeLast(vhostName, '.');
        }
        return ids;
    }

}
