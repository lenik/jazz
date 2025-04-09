package net.bodz.bas.net.serv;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * A utility network server.
 *
 * @label A utility network server.
 */
@ProgramName("netserv")
public class NetServerCLI
        extends BasicCLI {

    /**
     * @option -s --sourceaddr =ADDR
     */
    List<String> localAddrs = new ArrayList<>();

    /**
     * @option -p --port =PORT
     */
    List<Integer> localPorts = new ArrayList<>();

    NetServer server = new NetServer();

    void makeUp() {
        if (localAddrs.isEmpty())
            localAddrs.add("localhost");

        if (localPorts.isEmpty())
            localPorts.add(2000);

        for (String localAddr : localAddrs) {
            for (Integer localPort : localPorts) {
                InetSocketAddress addr = new InetSocketAddress(localAddr, localPort);
                server.localAddrs.add(addr);
            }
        }
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        makeUp();

        server.start();
    }

    public static void main(String[] args)
            throws Exception {
        new NetServerCLI().execute(args);
    }

}