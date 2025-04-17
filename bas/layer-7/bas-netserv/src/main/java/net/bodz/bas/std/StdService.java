package net.bodz.bas.std;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.meta.decl.NotNull;

public class StdService {

    final String name;
    final Set<TransportType> transports = new HashSet<>();
    final int port;
    String description;

    public StdService(@NotNull String name, @NotNull TransportType transport, int port, String description) {
        this.name = name;
        this.transports.add(transport);
        this.port = port;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getDescription() {
        return description;
    }

    public Set<TransportType> getTransports() {
        return Collections.unmodifiableSet(transports);
    }

    public boolean isTCP() {
        return transports.contains(TransportType.TCP);
    }

    public boolean isUDP() {
        return transports.contains(TransportType.UDP);
    }

    static Map<String, StdService> byName = new TreeMap<>();
    static Map<Integer, StdService> byTcpPort = new TreeMap<>();
    static Map<Integer, StdService> byUdpPort = new TreeMap<>();

    static StdService def(String name, TransportType transport, int port, String description) {
        StdService service = byName.get(name);
        if (service == null) {
            service = new StdService(name, transport, port, description);
            byName.put(name, service);
        } else {
            service.transports.add(transport);
            if (service.description == null)
                if (description != null)
                    service.description = description;
        }
        switch (transport) {
            case TCP:
                byTcpPort.put(port, service);
                break;
            case UDP:
                byUdpPort.put(port, service);
                break;
        }
        return service;
    }

    public static Set<String> getServiceNames() {
        return byName.keySet();
    }

    public static Collection<StdService> getServices() {
        return byName.values();
    }

    public static StdService getService(String name) {
        return byName.get(name);
    }

    public static Integer getTcpPort(String name) {
        StdService service = byName.get(name);
        if (service == null)
            return null;
        if (!service.transports.contains(TransportType.TCP))
            return null;
        return service.port;
    }

    public static Integer getUdpPort(String name) {
        StdService service = byName.get(name);
        if (service == null)
            return null;
        if (!service.transports.contains(TransportType.UDP))
            return null;
        return service.port;
    }

    public static void main(String[] args) {
        Path file = Paths.get("/etc/services");
        Set<String> names = new HashSet<>();
        for (String line : ResFn.path(file).read().lines()) {
            String comment = null;
            int sharp = line.indexOf('#');
            if (sharp != -1) {
                comment = line.substring(sharp + 1).trim();
                line = line.substring(0, sharp);
            }
            line = line.trim();
            if (line.isEmpty())
                continue;

            StringTokenizer tokenizer = new StringTokenizer(line);
            List<String> tokens = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                tokens.add(token);
            }

            String name = tokens.get(0);
            String def = tokens.get(1);
            int slash = def.indexOf('/');
            String port = def.substring(0, slash);
            String transport = def.substring(slash + 1);

            boolean defined = !names.add(name);

            if (comment != null)
                System.out.printf("    /** %s */\n", comment);

            String id = name.replace('-', '_').toUpperCase();
            if (defined)
                id += "_" + transport.toUpperCase();
            System.out.printf("    public static final StdService %s = def(%s, %s, %s, %s);\n", //
                    id, //
                    StringQuote.qq(name), //
                    "TransportType." + transport.toUpperCase(), //
                    port, //
                    comment == null ? "null" : StringQuote.qq(comment));
        }
    }

    /** TCP port service multiplexer */
    public static final StdService TCPMUX = def("tcpmux", TransportType.TCP, 1, "TCP port service multiplexer");
    public static final StdService ECHO = def("echo", TransportType.TCP, 7, null);
    public static final StdService ECHO_UDP = def("echo", TransportType.UDP, 7, null);
    public static final StdService DISCARD = def("discard", TransportType.TCP, 9, null);
    public static final StdService DISCARD_UDP = def("discard", TransportType.UDP, 9, null);
    public static final StdService SYSTAT = def("systat", TransportType.TCP, 11, null);
    public static final StdService DAYTIME = def("daytime", TransportType.TCP, 13, null);
    public static final StdService DAYTIME_UDP = def("daytime", TransportType.UDP, 13, null);
    public static final StdService NETSTAT = def("netstat", TransportType.TCP, 15, null);
    public static final StdService QOTD = def("qotd", TransportType.TCP, 17, null);
    public static final StdService CHARGEN = def("chargen", TransportType.TCP, 19, null);
    public static final StdService CHARGEN_UDP = def("chargen", TransportType.UDP, 19, null);
    public static final StdService FTP_DATA = def("ftp-data", TransportType.TCP, 20, null);
    public static final StdService FTP = def("ftp", TransportType.TCP, 21, null);
    public static final StdService FSP = def("fsp", TransportType.UDP, 21, null);
    /** SSH Remote Login Protocol */
    public static final StdService SSH = def("ssh", TransportType.TCP, 22, "SSH Remote Login Protocol");
    public static final StdService TELNET = def("telnet", TransportType.TCP, 23, null);
    public static final StdService SMTP = def("smtp", TransportType.TCP, 25, null);
    public static final StdService TIME = def("time", TransportType.TCP, 37, null);
    public static final StdService TIME_UDP = def("time", TransportType.UDP, 37, null);
    public static final StdService WHOIS = def("whois", TransportType.TCP, 43, null);
    /** Login Host Protocol (TACACS) */
    public static final StdService TACACS = def("tacacs", TransportType.TCP, 49, "Login Host Protocol (TACACS)");
    public static final StdService TACACS_UDP = def("tacacs", TransportType.UDP, 49, null);
    /** Domain Name Server */
    public static final StdService DOMAIN = def("domain", TransportType.TCP, 53, "Domain Name Server");
    public static final StdService DOMAIN_UDP = def("domain", TransportType.UDP, 53, null);
    public static final StdService BOOTPS = def("bootps", TransportType.UDP, 67, null);
    public static final StdService BOOTPC = def("bootpc", TransportType.UDP, 68, null);
    public static final StdService TFTP = def("tftp", TransportType.UDP, 69, null);
    /** Internet Gopher */
    public static final StdService GOPHER = def("gopher", TransportType.TCP, 70, "Internet Gopher");
    public static final StdService FINGER = def("finger", TransportType.TCP, 79, null);
    /** WorldWideWeb HTTP */
    public static final StdService HTTP = def("http", TransportType.TCP, 80, "WorldWideWeb HTTP");
    /** Kerberos v5 */
    public static final StdService KERBEROS = def("kerberos", TransportType.TCP, 88, "Kerberos v5");
    /** Kerberos v5 */
    public static final StdService KERBEROS_UDP = def("kerberos", TransportType.UDP, 88, "Kerberos v5");
    /** part of ISODE */
    public static final StdService ISO_TSAP = def("iso-tsap", TransportType.TCP, 102, "part of ISODE");
    /** Digital Imag. & Comm. 300 */
    public static final StdService ACR_NEMA = def("acr-nema", TransportType.TCP, 104, "Digital Imag. & Comm. 300");
    /** POP version 3 */
    public static final StdService POP3 = def("pop3", TransportType.TCP, 110, "POP version 3");
    /** RPC 4.0 portmapper */
    public static final StdService SUNRPC = def("sunrpc", TransportType.TCP, 111, "RPC 4.0 portmapper");
    public static final StdService SUNRPC_UDP = def("sunrpc", TransportType.UDP, 111, null);
    public static final StdService AUTH = def("auth", TransportType.TCP, 113, null);
    /** USENET News Transfer Protocol */
    public static final StdService NNTP = def("nntp", TransportType.TCP, 119, "USENET News Transfer Protocol");
    /** Network Time Protocol */
    public static final StdService NTP = def("ntp", TransportType.UDP, 123, "Network Time Protocol");
    /** DCE endpoint resolution */
    public static final StdService EPMAP = def("epmap", TransportType.TCP, 135, "DCE endpoint resolution");
    /** NETBIOS Name Service */
    public static final StdService NETBIOS_NS = def("netbios-ns", TransportType.UDP, 137, "NETBIOS Name Service");
    /** NETBIOS Datagram Service */
    public static final StdService NETBIOS_DGM = def("netbios-dgm", TransportType.UDP, 138, "NETBIOS Datagram Service");
    /** NETBIOS session service */
    public static final StdService NETBIOS_SSN = def("netbios-ssn", TransportType.TCP, 139, "NETBIOS session service");
    /** Interim Mail Access P 2 and 4 */
    public static final StdService IMAP2 = def("imap2", TransportType.TCP, 143, "Interim Mail Access P 2 and 4");
    /** Simple Net Mgmt Protocol */
    public static final StdService SNMP = def("snmp", TransportType.TCP, 161, "Simple Net Mgmt Protocol");
    public static final StdService SNMP_UDP = def("snmp", TransportType.UDP, 161, null);
    /** Traps for SNMP */
    public static final StdService SNMP_TRAP = def("snmp-trap", TransportType.TCP, 162, "Traps for SNMP");
    public static final StdService SNMP_TRAP_UDP = def("snmp-trap", TransportType.UDP, 162, null);
    /** ISO mgmt over IP (CMOT) */
    public static final StdService CMIP_MAN = def("cmip-man", TransportType.TCP, 163, "ISO mgmt over IP (CMOT)");
    public static final StdService CMIP_MAN_UDP = def("cmip-man", TransportType.UDP, 163, null);
    public static final StdService CMIP_AGENT = def("cmip-agent", TransportType.TCP, 164, null);
    public static final StdService CMIP_AGENT_UDP = def("cmip-agent", TransportType.UDP, 164, null);
    /** Mailer transport queue for Zmailer */
    public static final StdService MAILQ = def("mailq", TransportType.TCP, 174, "Mailer transport queue for Zmailer");
    /** X Display Manager Control Protocol */
    public static final StdService XDMCP = def("xdmcp", TransportType.UDP, 177, "X Display Manager Control Protocol");
    /** Border Gateway Protocol */
    public static final StdService BGP = def("bgp", TransportType.TCP, 179, "Border Gateway Protocol");
    /** SNMP Unix Multiplexer */
    public static final StdService SMUX = def("smux", TransportType.TCP, 199, "SNMP Unix Multiplexer");
    /** Quick Mail Transfer Protocol */
    public static final StdService QMTP = def("qmtp", TransportType.TCP, 209, "Quick Mail Transfer Protocol");
    /** NISO Z39.50 database */
    public static final StdService Z3950 = def("z3950", TransportType.TCP, 210, "NISO Z39.50 database");
    /** IPX [RFC1234] */
    public static final StdService IPX = def("ipx", TransportType.UDP, 213, "IPX [RFC1234]");
    public static final StdService PTP_EVENT = def("ptp-event", TransportType.UDP, 319, null);
    public static final StdService PTP_GENERAL = def("ptp-general", TransportType.UDP, 320, null);
    /** Perf Analysis Workbench */
    public static final StdService PAWSERV = def("pawserv", TransportType.TCP, 345, "Perf Analysis Workbench");
    /** Zebra server */
    public static final StdService ZSERV = def("zserv", TransportType.TCP, 346, "Zebra server");
    public static final StdService RPC2PORTMAP = def("rpc2portmap", TransportType.TCP, 369, null);
    /** Coda portmapper */
    public static final StdService RPC2PORTMAP_UDP = def("rpc2portmap", TransportType.UDP, 369, "Coda portmapper");
    public static final StdService CODAAUTH2 = def("codaauth2", TransportType.TCP, 370, null);
    /** Coda authentication server */
    public static final StdService CODAAUTH2_UDP = def("codaauth2", TransportType.UDP, 370, "Coda authentication server");
    public static final StdService CLEARCASE = def("clearcase", TransportType.UDP, 371, null);
    /** Lightweight Directory Access Protocol */
    public static final StdService LDAP = def("ldap", TransportType.TCP, 389, "Lightweight Directory Access Protocol");
    public static final StdService LDAP_UDP = def("ldap", TransportType.UDP, 389, null);
    /** Server Location */
    public static final StdService SVRLOC = def("svrloc", TransportType.TCP, 427, "Server Location");
    public static final StdService SVRLOC_UDP = def("svrloc", TransportType.UDP, 427, null);
    /** http protocol over TLS/SSL */
    public static final StdService HTTPS = def("https", TransportType.TCP, 443, "http protocol over TLS/SSL");
    /** HTTP/3 */
    public static final StdService HTTPS_UDP = def("https", TransportType.UDP, 443, "HTTP/3");
    /** Simple Network Paging Protocol */
    public static final StdService SNPP = def("snpp", TransportType.TCP, 444, "Simple Network Paging Protocol");
    /** Microsoft Naked CIFS */
    public static final StdService MICROSOFT_DS = def("microsoft-ds", TransportType.TCP, 445, "Microsoft Naked CIFS");
    public static final StdService KPASSWD = def("kpasswd", TransportType.TCP, 464, null);
    public static final StdService KPASSWD_UDP = def("kpasswd", TransportType.UDP, 464, null);
    /** Submission over TLS [RFC8314] */
    public static final StdService SUBMISSIONS = def("submissions", TransportType.TCP, 465, "Submission over TLS [RFC8314]");
    /** Simple Asynchronous File Transfer */
    public static final StdService SAFT = def("saft", TransportType.TCP, 487, "Simple Asynchronous File Transfer");
    /** IPSEC key management */
    public static final StdService ISAKMP = def("isakmp", TransportType.UDP, 500, "IPSEC key management");
    /** Real Time Stream Control Protocol */
    public static final StdService RTSP = def("rtsp", TransportType.TCP, 554, "Real Time Stream Control Protocol");
    public static final StdService RTSP_UDP = def("rtsp", TransportType.UDP, 554, null);
    /** Network Queuing system */
    public static final StdService NQS = def("nqs", TransportType.TCP, 607, "Network Queuing system");
    /** ASF Remote Management and Control Protocol */
    public static final StdService ASF_RMCP = def("asf-rmcp", TransportType.UDP, 623, "ASF Remote Management and Control Protocol");
    public static final StdService QMQP = def("qmqp", TransportType.TCP, 628, null);
    /** Internet Printing Protocol */
    public static final StdService IPP = def("ipp", TransportType.TCP, 631, "Internet Printing Protocol");
    /** Label Distribution Protocol */
    public static final StdService LDP = def("ldp", TransportType.TCP, 646, "Label Distribution Protocol");
    public static final StdService LDP_UDP = def("ldp", TransportType.UDP, 646, null);
    public static final StdService EXEC = def("exec", TransportType.TCP, 512, null);
    public static final StdService BIFF = def("biff", TransportType.UDP, 512, null);
    public static final StdService LOGIN = def("login", TransportType.TCP, 513, null);
    public static final StdService WHO = def("who", TransportType.UDP, 513, null);
    /** no passwords used */
    public static final StdService SHELL = def("shell", TransportType.TCP, 514, "no passwords used");
    public static final StdService SYSLOG = def("syslog", TransportType.UDP, 514, null);
    /** line printer spooler */
    public static final StdService PRINTER = def("printer", TransportType.TCP, 515, "line printer spooler");
    public static final StdService TALK = def("talk", TransportType.UDP, 517, null);
    public static final StdService NTALK = def("ntalk", TransportType.UDP, 518, null);
    /** RIP */
    public static final StdService ROUTE = def("route", TransportType.UDP, 520, "RIP");
    /** GNUstep distributed objects */
    public static final StdService GDOMAP = def("gdomap", TransportType.TCP, 538, "GNUstep distributed objects");
    public static final StdService GDOMAP_UDP = def("gdomap", TransportType.UDP, 538, null);
    /** uucp daemon */
    public static final StdService UUCP = def("uucp", TransportType.TCP, 540, "uucp daemon");
    /** Kerberized `rlogin' (v5) */
    public static final StdService KLOGIN = def("klogin", TransportType.TCP, 543, "Kerberized `rlogin' (v5)");
    /** Kerberized `rsh' (v5) */
    public static final StdService KSHELL = def("kshell", TransportType.TCP, 544, "Kerberized `rsh' (v5)");
    public static final StdService DHCPV6_CLIENT = def("dhcpv6-client", TransportType.UDP, 546, null);
    public static final StdService DHCPV6_SERVER = def("dhcpv6-server", TransportType.UDP, 547, null);
    /** AFP over TCP */
    public static final StdService AFPOVERTCP = def("afpovertcp", TransportType.TCP, 548, "AFP over TCP");
    /** NNTP over SSL */
    public static final StdService NNTPS = def("nntps", TransportType.TCP, 563, "NNTP over SSL");
    /** Submission [RFC4409] */
    public static final StdService SUBMISSION = def("submission", TransportType.TCP, 587, "Submission [RFC4409]");
    /** LDAP over SSL */
    public static final StdService LDAPS = def("ldaps", TransportType.TCP, 636, "LDAP over SSL");
    public static final StdService LDAPS_UDP = def("ldaps", TransportType.UDP, 636, null);
    /** tinc control port */
    public static final StdService TINC = def("tinc", TransportType.TCP, 655, "tinc control port");
    public static final StdService TINC_UDP = def("tinc", TransportType.UDP, 655, null);
    public static final StdService SILC = def("silc", TransportType.TCP, 706, null);
    /** Kerberos `kadmin' (v5) */
    public static final StdService KERBEROS_ADM = def("kerberos-adm", TransportType.TCP, 749, "Kerberos `kadmin' (v5)");
    /** DNS over TLS [RFC7858] */
    public static final StdService DOMAIN_S = def("domain-s", TransportType.TCP, 853, "DNS over TLS [RFC7858]");
    /** DNS over DTLS [RFC8094] */
    public static final StdService DOMAIN_S_UDP = def("domain-s", TransportType.UDP, 853, "DNS over DTLS [RFC8094]");
    public static final StdService RSYNC = def("rsync", TransportType.TCP, 873, null);
    /** FTP over SSL (data) */
    public static final StdService FTPS_DATA = def("ftps-data", TransportType.TCP, 989, "FTP over SSL (data)");
    public static final StdService FTPS = def("ftps", TransportType.TCP, 990, null);
    /** Telnet over SSL */
    public static final StdService TELNETS = def("telnets", TransportType.TCP, 992, "Telnet over SSL");
    /** IMAP over SSL */
    public static final StdService IMAPS = def("imaps", TransportType.TCP, 993, "IMAP over SSL");
    /** POP-3 over SSL */
    public static final StdService POP3S = def("pop3s", TransportType.TCP, 995, "POP-3 over SSL");
    /** socks proxy server */
    public static final StdService SOCKS = def("socks", TransportType.TCP, 1080, "socks proxy server");
    public static final StdService PROOFD = def("proofd", TransportType.TCP, 1093, null);
    public static final StdService ROOTD = def("rootd", TransportType.TCP, 1094, null);
    public static final StdService OPENVPN = def("openvpn", TransportType.TCP, 1194, null);
    public static final StdService OPENVPN_UDP = def("openvpn", TransportType.UDP, 1194, null);
    /** Java RMI Registry */
    public static final StdService RMIREGISTRY = def("rmiregistry", TransportType.TCP, 1099, "Java RMI Registry");
    /** Lotus Note */
    public static final StdService LOTUSNOTE = def("lotusnote", TransportType.TCP, 1352, "Lotus Note");
    /** Microsoft SQL Server */
    public static final StdService MS_SQL_S = def("ms-sql-s", TransportType.TCP, 1433, "Microsoft SQL Server");
    /** Microsoft SQL Monitor */
    public static final StdService MS_SQL_M = def("ms-sql-m", TransportType.UDP, 1434, "Microsoft SQL Monitor");
    public static final StdService INGRESLOCK = def("ingreslock", TransportType.TCP, 1524, null);
    public static final StdService DATAMETRICS = def("datametrics", TransportType.TCP, 1645, null);
    public static final StdService DATAMETRICS_UDP = def("datametrics", TransportType.UDP, 1645, null);
    public static final StdService SA_MSG_PORT = def("sa-msg-port", TransportType.TCP, 1646, null);
    public static final StdService SA_MSG_PORT_UDP = def("sa-msg-port", TransportType.UDP, 1646, null);
    public static final StdService KERMIT = def("kermit", TransportType.TCP, 1649, null);
    public static final StdService GROUPWISE = def("groupwise", TransportType.TCP, 1677, null);
    public static final StdService L2F = def("l2f", TransportType.UDP, 1701, null);
    public static final StdService RADIUS = def("radius", TransportType.TCP, 1812, null);
    public static final StdService RADIUS_UDP = def("radius", TransportType.UDP, 1812, null);
    /** Radius Accounting */
    public static final StdService RADIUS_ACCT = def("radius-acct", TransportType.TCP, 1813, "Radius Accounting");
    public static final StdService RADIUS_ACCT_UDP = def("radius-acct", TransportType.UDP, 1813, null);
    /** Cisco SCCP */
    public static final StdService CISCO_SCCP = def("cisco-sccp", TransportType.TCP, 2000, "Cisco SCCP");
    /** Network File System */
    public static final StdService NFS = def("nfs", TransportType.TCP, 2049, "Network File System");
    /** Network File System */
    public static final StdService NFS_UDP = def("nfs", TransportType.UDP, 2049, "Network File System");
    public static final StdService GNUNET = def("gnunet", TransportType.TCP, 2086, null);
    public static final StdService GNUNET_UDP = def("gnunet", TransportType.UDP, 2086, null);
    /** RTCM SC-104 IANA 1/29/99 */
    public static final StdService RTCM_SC104 = def("rtcm-sc104", TransportType.TCP, 2101, "RTCM SC-104 IANA 1/29/99");
    public static final StdService RTCM_SC104_UDP = def("rtcm-sc104", TransportType.UDP, 2101, null);
    public static final StdService GSIGATEKEEPER = def("gsigatekeeper", TransportType.TCP, 2119, null);
    /** Grid Resource Information Server */
    public static final StdService GRIS = def("gris", TransportType.TCP, 2135, "Grid Resource Information Server");
    /** CVS client/server operations */
    public static final StdService CVSPSERVER = def("cvspserver", TransportType.TCP, 2401, "CVS client/server operations");
    /** codacon port */
    public static final StdService VENUS = def("venus", TransportType.TCP, 2430, "codacon port");
    /** Venus callback/wbc interface */
    public static final StdService VENUS_UDP = def("venus", TransportType.UDP, 2430, "Venus callback/wbc interface");
    /** tcp side effects */
    public static final StdService VENUS_SE = def("venus-se", TransportType.TCP, 2431, "tcp side effects");
    /** udp sftp side effect */
    public static final StdService VENUS_SE_UDP = def("venus-se", TransportType.UDP, 2431, "udp sftp side effect");
    /** not used */
    public static final StdService CODASRV = def("codasrv", TransportType.TCP, 2432, "not used");
    /** server port */
    public static final StdService CODASRV_UDP = def("codasrv", TransportType.UDP, 2432, "server port");
    /** tcp side effects */
    public static final StdService CODASRV_SE = def("codasrv-se", TransportType.TCP, 2433, "tcp side effects");
    /** udp sftp side effect */
    public static final StdService CODASRV_SE_UDP = def("codasrv-se", TransportType.UDP, 2433, "udp sftp side effect");
    /** MON traps */
    public static final StdService MON = def("mon", TransportType.TCP, 2583, "MON traps");
    public static final StdService MON_UDP = def("mon", TransportType.UDP, 2583, null);
    /** Dictionary server */
    public static final StdService DICT = def("dict", TransportType.TCP, 2628, "Dictionary server");
    public static final StdService F5_GLOBALSITE = def("f5-globalsite", TransportType.TCP, 2792, null);
    public static final StdService GSIFTP = def("gsiftp", TransportType.TCP, 2811, null);
    public static final StdService GPSD = def("gpsd", TransportType.TCP, 2947, null);
    /** InterBase server */
    public static final StdService GDS_DB = def("gds-db", TransportType.TCP, 3050, "InterBase server");
    /** Internet Cache Protocol */
    public static final StdService ICPV2 = def("icpv2", TransportType.UDP, 3130, "Internet Cache Protocol");
    /** iSNS Server Port */
    public static final StdService ISNS = def("isns", TransportType.TCP, 3205, "iSNS Server Port");
    /** iSNS Server Port */
    public static final StdService ISNS_UDP = def("isns", TransportType.UDP, 3205, "iSNS Server Port");
    public static final StdService ISCSI_TARGET = def("iscsi-target", TransportType.TCP, 3260, null);
    public static final StdService MYSQL = def("mysql", TransportType.TCP, 3306, null);
    public static final StdService MS_WBT_SERVER = def("ms-wbt-server", TransportType.TCP, 3389, null);
    /** Network UPS Tools */
    public static final StdService NUT = def("nut", TransportType.TCP, 3493, "Network UPS Tools");
    public static final StdService NUT_UDP = def("nut", TransportType.UDP, 3493, null);
    /** distributed compiler */
    public static final StdService DISTCC = def("distcc", TransportType.TCP, 3632, "distributed compiler");
    /** Digital Audio Access Protocol */
    public static final StdService DAAP = def("daap", TransportType.TCP, 3689, "Digital Audio Access Protocol");
    /** Subversion protocol */
    public static final StdService SVN = def("svn", TransportType.TCP, 3690, "Subversion protocol");
    /** UUCP over SSL */
    public static final StdService SUUCP = def("suucp", TransportType.TCP, 4031, "UUCP over SSL");
    /** sysrq daemon */
    public static final StdService SYSRQD = def("sysrqd", TransportType.TCP, 4094, "sysrq daemon");
    /** ManageSieve Protocol */
    public static final StdService SIEVE = def("sieve", TransportType.TCP, 4190, "ManageSieve Protocol");
    /** Erlang Port Mapper Daemon */
    public static final StdService EPMD = def("epmd", TransportType.TCP, 4369, "Erlang Port Mapper Daemon");
    /** Remote Authenticated Command Service */
    public static final StdService REMCTL = def("remctl", TransportType.TCP, 4373, "Remote Authenticated Command Service");
    /** F5 iQuery */
    public static final StdService F5_IQUERY = def("f5-iquery", TransportType.TCP, 4353, "F5 iQuery");
    /** Network Time Security Key Establishment */
    public static final StdService NTSKE = def("ntske", TransportType.TCP, 4460, "Network Time Security Key Establishment");
    /** IPsec NAT-Traversal [RFC3947] */
    public static final StdService IPSEC_NAT_T = def("ipsec-nat-t", TransportType.UDP, 4500, "IPsec NAT-Traversal [RFC3947]");
    /** Inter-Asterisk eXchange */
    public static final StdService IAX = def("iax", TransportType.UDP, 4569, "Inter-Asterisk eXchange");
    /** monotone Netsync Protocol */
    public static final StdService MTN = def("mtn", TransportType.TCP, 4691, "monotone Netsync Protocol");
    /** RAdmin Port */
    public static final StdService RADMIN_PORT = def("radmin-port", TransportType.TCP, 4899, "RAdmin Port");
    /** Session Initiation Protocol */
    public static final StdService SIP = def("sip", TransportType.TCP, 5060, "Session Initiation Protocol");
    public static final StdService SIP_UDP = def("sip", TransportType.UDP, 5060, null);
    public static final StdService SIP_TLS = def("sip-tls", TransportType.TCP, 5061, null);
    public static final StdService SIP_TLS_UDP = def("sip-tls", TransportType.UDP, 5061, null);
    /** Jabber Client Connection */
    public static final StdService XMPP_CLIENT = def("xmpp-client", TransportType.TCP, 5222, "Jabber Client Connection");
    /** Jabber Server Connection */
    public static final StdService XMPP_SERVER = def("xmpp-server", TransportType.TCP, 5269, "Jabber Server Connection");
    public static final StdService CFENGINE = def("cfengine", TransportType.TCP, 5308, null);
    /** Multicast DNS */
    public static final StdService MDNS = def("mdns", TransportType.UDP, 5353, "Multicast DNS");
    /** PostgreSQL Database */
    public static final StdService POSTGRESQL = def("postgresql", TransportType.TCP, 5432, "PostgreSQL Database");
    /** Freeciv gameplay */
    public static final StdService FREECIV = def("freeciv", TransportType.TCP, 5556, "Freeciv gameplay");
    /** AMQP protocol over TLS/SSL */
    public static final StdService AMQPS = def("amqps", TransportType.TCP, 5671, "AMQP protocol over TLS/SSL");
    public static final StdService AMQP = def("amqp", TransportType.TCP, 5672, null);
    public static final StdService AMQP_SCTP = def("amqp", TransportType.SCTP, 5672, null);
    /** Constrained Application Protocol */
    public static final StdService COAP = def("coap", TransportType.TCP, 5683, "Constrained Application Protocol");
    /** Constrained Application Protocol */
    public static final StdService COAP_UDP = def("coap", TransportType.UDP, 5683, "Constrained Application Protocol");
    /** TLS-secured CoAP */
    public static final StdService COAPS = def("coaps", TransportType.TCP, 5684, "TLS-secured CoAP");
    /** DTLS-secured CoAP */
    public static final StdService COAPS_UDP = def("coaps", TransportType.UDP, 5684, "DTLS-secured CoAP");
    /** X Window System */
    public static final StdService X11 = def("x11", TransportType.TCP, 6000, "X Window System");
    public static final StdService X11_1 = def("x11-1", TransportType.TCP, 6001, null);
    public static final StdService X11_2 = def("x11-2", TransportType.TCP, 6002, null);
    public static final StdService X11_3 = def("x11-3", TransportType.TCP, 6003, null);
    public static final StdService X11_4 = def("x11-4", TransportType.TCP, 6004, null);
    public static final StdService X11_5 = def("x11-5", TransportType.TCP, 6005, null);
    public static final StdService X11_6 = def("x11-6", TransportType.TCP, 6006, null);
    public static final StdService X11_7 = def("x11-7", TransportType.TCP, 6007, null);
    /** gnutella */
    public static final StdService GNUTELLA_SVC = def("gnutella-svc", TransportType.TCP, 6346, "gnutella");
    public static final StdService GNUTELLA_SVC_UDP = def("gnutella-svc", TransportType.UDP, 6346, null);
    /** gnutella */
    public static final StdService GNUTELLA_RTR = def("gnutella-rtr", TransportType.TCP, 6347, "gnutella");
    public static final StdService GNUTELLA_RTR_UDP = def("gnutella-rtr", TransportType.UDP, 6347, null);
    public static final StdService REDIS = def("redis", TransportType.TCP, 6379, null);
    /** Grid Engine Qmaster Service */
    public static final StdService SGE_QMASTER = def("sge-qmaster", TransportType.TCP, 6444, "Grid Engine Qmaster Service");
    /** Grid Engine Execution Service */
    public static final StdService SGE_EXECD = def("sge-execd", TransportType.TCP, 6445, "Grid Engine Execution Service");
    /** MySQL Proxy */
    public static final StdService MYSQL_PROXY = def("mysql-proxy", TransportType.TCP, 6446, "MySQL Proxy");
    /** Babel Routing Protocol */
    public static final StdService BABEL = def("babel", TransportType.UDP, 6696, "Babel Routing Protocol");
    /** Internet Relay Chat via TLS/SSL */
    public static final StdService IRCS_U = def("ircs-u", TransportType.TCP, 6697, "Internet Relay Chat via TLS/SSL");
    public static final StdService BBS = def("bbs", TransportType.TCP, 7000, null);
    public static final StdService AFS3_FILESERVER = def("afs3-fileserver", TransportType.UDP, 7000, null);
    /** callbacks to cache managers */
    public static final StdService AFS3_CALLBACK = def("afs3-callback", TransportType.UDP, 7001, "callbacks to cache managers");
    /** users & groups database */
    public static final StdService AFS3_PRSERVER = def("afs3-prserver", TransportType.UDP, 7002, "users & groups database");
    /** volume location database */
    public static final StdService AFS3_VLSERVER = def("afs3-vlserver", TransportType.UDP, 7003, "volume location database");
    /** AFS/Kerberos authentication */
    public static final StdService AFS3_KASERVER = def("afs3-kaserver", TransportType.UDP, 7004, "AFS/Kerberos authentication");
    /** volume managment server */
    public static final StdService AFS3_VOLSER = def("afs3-volser", TransportType.UDP, 7005, "volume managment server");
    /** basic overseer process */
    public static final StdService AFS3_BOS = def("afs3-bos", TransportType.UDP, 7007, "basic overseer process");
    /** server-to-server updater */
    public static final StdService AFS3_UPDATE = def("afs3-update", TransportType.UDP, 7008, "server-to-server updater");
    /** remote cache manager service */
    public static final StdService AFS3_RMTSYS = def("afs3-rmtsys", TransportType.UDP, 7009, "remote cache manager service");
    /** X Font Service */
    public static final StdService FONT_SERVICE = def("font-service", TransportType.TCP, 7100, "X Font Service");
    /** WWW caching service */
    public static final StdService HTTP_ALT = def("http-alt", TransportType.TCP, 8080, "WWW caching service");
    /** The Puppet master service */
    public static final StdService PUPPET = def("puppet", TransportType.TCP, 8140, "The Puppet master service");
    /** Bacula Director */
    public static final StdService BACULA_DIR = def("bacula-dir", TransportType.TCP, 9101, "Bacula Director");
    /** Bacula File Daemon */
    public static final StdService BACULA_FD = def("bacula-fd", TransportType.TCP, 9102, "Bacula File Daemon");
    /** Bacula Storage Daemon */
    public static final StdService BACULA_SD = def("bacula-sd", TransportType.TCP, 9103, "Bacula Storage Daemon");
    /** Cross-platform Music Multiplexing System */
    public static final StdService XMMS2 = def("xmms2", TransportType.TCP, 9667, "Cross-platform Music Multiplexing System");
    /** Linux Network Block Device */
    public static final StdService NBD = def("nbd", TransportType.TCP, 10809, "Linux Network Block Device");
    /** Zabbix Agent */
    public static final StdService ZABBIX_AGENT = def("zabbix-agent", TransportType.TCP, 10050, "Zabbix Agent");
    /** Zabbix Trapper */
    public static final StdService ZABBIX_TRAPPER = def("zabbix-trapper", TransportType.TCP, 10051, "Zabbix Trapper");
    /** amanda backup services */
    public static final StdService AMANDA = def("amanda", TransportType.TCP, 10080, "amanda backup services");
    public static final StdService DICOM = def("dicom", TransportType.TCP, 11112, null);
    /** OpenPGP HTTP Keyserver */
    public static final StdService HKP = def("hkp", TransportType.TCP, 11371, "OpenPGP HTTP Keyserver");
    /** Dropbox LanSync Protocol */
    public static final StdService DB_LSP = def("db-lsp", TransportType.TCP, 17500, "Dropbox LanSync Protocol");
    /** dCache Access Protocol */
    public static final StdService DCAP = def("dcap", TransportType.TCP, 22125, "dCache Access Protocol");
    /** GSI dCache Access Protocol */
    public static final StdService GSIDCAP = def("gsidcap", TransportType.TCP, 22128, "GSI dCache Access Protocol");
    /** wnn6 */
    public static final StdService WNN6 = def("wnn6", TransportType.TCP, 22273, "wnn6");
    /** Routing Table Maintenance Protocol */
    public static final StdService RTMP = def("rtmp", TransportType.DDP, 1, "Routing Table Maintenance Protocol");
    /** Name Binding Protocol */
    public static final StdService NBP = def("nbp", TransportType.DDP, 2, "Name Binding Protocol");
    /** AppleTalk Echo Protocol */
    public static final StdService ECHO_DDP = def("echo", TransportType.DDP, 4, "AppleTalk Echo Protocol");
    /** Zone Information Protocol */
    public static final StdService ZIP = def("zip", TransportType.DDP, 6, "Zone Information Protocol");
    /** Kerberos (server) */
    public static final StdService KERBEROS4 = def("kerberos4", TransportType.UDP, 750, "Kerberos (server)");
    public static final StdService KERBEROS4_TCP = def("kerberos4", TransportType.TCP, 750, null);
    /** Kerberos authentication */
    public static final StdService KERBEROS_MASTER = def("kerberos-master", TransportType.UDP, 751, "Kerberos authentication");
    public static final StdService KERBEROS_MASTER_TCP = def("kerberos-master", TransportType.TCP, 751, null);
    /** Kerberos passwd server */
    public static final StdService PASSWD_SERVER = def("passwd-server", TransportType.UDP, 752, "Kerberos passwd server");
    /** Kerberos slave propagation */
    public static final StdService KRB_PROP = def("krb-prop", TransportType.TCP, 754, "Kerberos slave propagation");
    /** Zephyr server */
    public static final StdService ZEPHYR_SRV = def("zephyr-srv", TransportType.UDP, 2102, "Zephyr server");
    /** Zephyr serv-hm connection */
    public static final StdService ZEPHYR_CLT = def("zephyr-clt", TransportType.UDP, 2103, "Zephyr serv-hm connection");
    /** Zephyr hostmanager */
    public static final StdService ZEPHYR_HM = def("zephyr-hm", TransportType.UDP, 2104, "Zephyr hostmanager");
    /** incremental propagation */
    public static final StdService IPROP = def("iprop", TransportType.TCP, 2121, "incremental propagation");
    /** Software Upgrade Protocol server */
    public static final StdService SUPFILESRV = def("supfilesrv", TransportType.TCP, 871, "Software Upgrade Protocol server");
    /** Software Upgrade Protocol debugging */
    public static final StdService SUPFILEDBG = def("supfiledbg", TransportType.TCP, 1127, "Software Upgrade Protocol debugging");
    /** Eudora */
    public static final StdService POPPASSD = def("poppassd", TransportType.TCP, 106, "Eudora");
    /** Moira database */
    public static final StdService MOIRA_DB = def("moira-db", TransportType.TCP, 775, "Moira database");
    /** Moira update protocol */
    public static final StdService MOIRA_UPDATE = def("moira-update", TransportType.TCP, 777, "Moira update protocol");
    /** Moira user registration */
    public static final StdService MOIRA_UREG = def("moira-ureg", TransportType.UDP, 779, "Moira user registration");
    /** spamassassin daemon */
    public static final StdService SPAMD = def("spamd", TransportType.TCP, 783, "spamassassin daemon");
    /** skk jisho server port */
    public static final StdService SKKSERV = def("skkserv", TransportType.TCP, 1178, "skk jisho server port");
    /** predict -- satellite tracking */
    public static final StdService PREDICT = def("predict", TransportType.UDP, 1210, "predict -- satellite tracking");
    /** Gracilis Packeten remote config server */
    public static final StdService RMTCFG = def("rmtcfg", TransportType.TCP, 1236, "Gracilis Packeten remote config server");
    /** french minitel */
    public static final StdService XTEL = def("xtel", TransportType.TCP, 1313, "french minitel");
    /** french minitel */
    public static final StdService XTELW = def("xtelw", TransportType.TCP, 1314, "french minitel");
    /** zebra service */
    public static final StdService ZEBRASRV = def("zebrasrv", TransportType.TCP, 2600, "zebra service");
    /** zebra vty */
    public static final StdService ZEBRA = def("zebra", TransportType.TCP, 2601, "zebra vty");
    /** ripd vty (zebra) */
    public static final StdService RIPD = def("ripd", TransportType.TCP, 2602, "ripd vty (zebra)");
    /** ripngd vty (zebra) */
    public static final StdService RIPNGD = def("ripngd", TransportType.TCP, 2603, "ripngd vty (zebra)");
    /** ospfd vty (zebra) */
    public static final StdService OSPFD = def("ospfd", TransportType.TCP, 2604, "ospfd vty (zebra)");
    /** bgpd vty (zebra) */
    public static final StdService BGPD = def("bgpd", TransportType.TCP, 2605, "bgpd vty (zebra)");
    /** ospf6d vty (zebra) */
    public static final StdService OSPF6D = def("ospf6d", TransportType.TCP, 2606, "ospf6d vty (zebra)");
    /** OSPF-API */
    public static final StdService OSPFAPI = def("ospfapi", TransportType.TCP, 2607, "OSPF-API");
    /** ISISd vty (zebra) */
    public static final StdService ISISD = def("isisd", TransportType.TCP, 2608, "ISISd vty (zebra)");
    /** FAX transmission service (old) */
    public static final StdService FAX = def("fax", TransportType.TCP, 4557, "FAX transmission service (old)");
    /** HylaFAX client-server protocol (new) */
    public static final StdService HYLAFAX = def("hylafax", TransportType.TCP, 4559, "HylaFAX client-server protocol (new)");
    /** Munin */
    public static final StdService MUNIN = def("munin", TransportType.TCP, 4949, "Munin");
    /** RPlay audio service */
    public static final StdService RPLAY = def("rplay", TransportType.UDP, 5555, "RPlay audio service");
    /** Nagios Remote Plugin Executor */
    public static final StdService NRPE = def("nrpe", TransportType.TCP, 5666, "Nagios Remote Plugin Executor");
    /** Nagios Agent - NSCA */
    public static final StdService NSCA = def("nsca", TransportType.TCP, 5667, "Nagios Agent - NSCA");
    /** cannaserver */
    public static final StdService CANNA = def("canna", TransportType.TCP, 5680, "cannaserver");
    /** Syslog over TLS [RFC5425] */
    public static final StdService SYSLOG_TLS = def("syslog-tls", TransportType.TCP, 6514, "Syslog over TLS [RFC5425]");
    /** SANE network scanner daemon */
    public static final StdService SANE_PORT = def("sane-port", TransportType.TCP, 6566, "SANE network scanner daemon");
    /** Internet Relay Chat */
    public static final StdService IRCD = def("ircd", TransportType.TCP, 6667, "Internet Relay Chat");
    /** zope management by ftp */
    public static final StdService ZOPE_FTP = def("zope-ftp", TransportType.TCP, 8021, "zope management by ftp");
    /** Transparent Proxy */
    public static final StdService TPROXY = def("tproxy", TransportType.TCP, 8081, "Transparent Proxy");
    /** OmniORB */
    public static final StdService OMNIORB = def("omniorb", TransportType.TCP, 8088, "OmniORB");
    /** Common lisp build daemon */
    public static final StdService CLC_BUILD_DAEMON = def("clc-build-daemon", TransportType.TCP, 8990, "Common lisp build daemon");
    public static final StdService XINETD = def("xinetd", TransportType.TCP, 9098, null);
    /** Git Version Control System */
    public static final StdService GIT = def("git", TransportType.TCP, 9418, "Git Version Control System");
    /** zope server */
    public static final StdService ZOPE = def("zope", TransportType.TCP, 9673, "zope server");
    public static final StdService WEBMIN = def("webmin", TransportType.TCP, 10000, null);
    /** amanda backup services (Kerberos) */
    public static final StdService KAMANDA = def("kamanda", TransportType.TCP, 10081, "amanda backup services (Kerberos)");
    /** amanda backup services */
    public static final StdService AMANDAIDX = def("amandaidx", TransportType.TCP, 10082, "amanda backup services");
    /** amanda backup services */
    public static final StdService AMIDXTAPE = def("amidxtape", TransportType.TCP, 10083, "amanda backup services");
    /** Cluster membership services daemon */
    public static final StdService SGI_CMSD = def("sgi-cmsd", TransportType.UDP, 17001, "Cluster membership services daemon");
    public static final StdService SGI_CRSD = def("sgi-crsd", TransportType.UDP, 17002, null);
    /** SGI Group membership daemon */
    public static final StdService SGI_GCD = def("sgi-gcd", TransportType.UDP, 17003, "SGI Group membership daemon");
    /** Cluster Admin daemon */
    public static final StdService SGI_CAD = def("sgi-cad", TransportType.TCP, 17004, "Cluster Admin daemon");
    /** binkp fidonet protocol */
    public static final StdService BINKP = def("binkp", TransportType.TCP, 24554, "binkp fidonet protocol");
    /** Address Search Protocol */
    public static final StdService ASP = def("asp", TransportType.TCP, 27374, "Address Search Protocol");
    public static final StdService ASP_UDP = def("asp", TransportType.UDP, 27374, null);
    /** cluster synchronization tool */
    public static final StdService CSYNC2 = def("csync2", TransportType.TCP, 30865, "cluster synchronization tool");
    /** Detachable IRC Proxy */
    public static final StdService DIRCPROXY = def("dircproxy", TransportType.TCP, 57000, "Detachable IRC Proxy");
    /** fidonet EMSI over telnet */
    public static final StdService TFIDO = def("tfido", TransportType.TCP, 60177, "fidonet EMSI over telnet");
    /** fidonet EMSI over TCP */
    public static final StdService FIDO = def("fido", TransportType.TCP, 60179, "fidonet EMSI over TCP");

}
