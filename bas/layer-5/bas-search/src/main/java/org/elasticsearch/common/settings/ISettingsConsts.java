package org.elasticsearch.common.settings;

import net.bodz.bas.sugar.IConstants;

/**
 * @see org.elasticsearch.common.settings.Settings
 */
public interface ISettingsConsts
        extends IConstants {

    String clusterName = "cluster.name";
    String clientTransportSniff = "client.transport.sniff";

    /** Set to true to ignore cluster name validation of connected nodes. (since 0.19.4) */
    String clientTransportIgnoreClusterName = "client.transport.ignore_cluster_name";

    /** The time to wait for a ping response from a node. Defaults to 5s. */
    String clientTransportPingTimeout = "client.transport.ping_timeout";

    /** How often to sample / ping the nodes listed and connected. Defaults to 5s. */
    String clientTransportNodesSamplerInterval = "client.transport.nodes_sampler_interval";

}
