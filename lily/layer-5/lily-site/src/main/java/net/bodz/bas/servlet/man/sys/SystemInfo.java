package net.bodz.bas.servlet.man.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemInfo {

    public List<ThreadInfo> getThreads() {
        Map<Thread, StackTraceElement[]> all = Thread.getAllStackTraces();
        List<ThreadInfo> list = new ArrayList<>(all.size());
        for (Thread thread : all.keySet()) {
            StackTraceElement[] stackTrace = all.get(thread);
            ThreadInfo info = new ThreadInfo(thread, stackTrace);
            list.add(info);
        }
        return list;
    }

}
