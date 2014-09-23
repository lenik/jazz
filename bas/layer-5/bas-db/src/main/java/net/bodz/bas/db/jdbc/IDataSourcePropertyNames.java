package net.bodz.bas.db.jdbc;

import section.pseudo;

public interface IDataSourcePropertyNames {

    /** ⇱ FOR commons-dbcp, tomcat-jdbc-pool. */
    /* _____________________________ */section.part __COMMON__ = section.pseudo._void_;

    /**
     * (boolean) The default auto-commit state of connections created by this pool. If not set,
     * default is JDBC driver default (If not set then the setAutoCommit method will not be called.)
     */
    String defaultAutoCommit = "defaultAutoCommit";

    /**
     * (boolean) The default read-only state of connections created by this pool. If not set then
     * the setReadOnly method will not be called. (Some drivers don't support read only mode, ex:
     * Informix)
     */
    String defaultReadOnly = "defaultReadOnly";

    /**
     * (String) The default TransactionIsolation state of connections created by this pool. One of
     * the following: (see javadoc )
     * <ul>
     * <li>NONE
     * <li>READ_COMMITTED
     * <li>READ_UNCOMMITTED
     * <li>REPEATABLE_READ
     * <li>SERIALIZABLE
     * </ul>
     * If not set, the method will not be called and it defaults to the JDBC driver.
     */
    String defaultTransactionIsolation = "defaultTransactionIsolation";

    /**
     * (String) The default catalog of connections created by this pool.
     */
    String defaultCatalog = "defaultCatalog";

    /**
     * (String) The fully qualified Java class name of the JDBC driver to be used. The driver has to
     * be accessible from the same classloader as tomcat-jdbc.jar
     */
    String driverClassName = "driverClassName";

    /**
     * (String) The connection username to be passed to our JDBC driver to establish a connection.
     * Note that method DataSource.getConnection(username,password) by default will not use
     * credentials passed into the method, but will use the ones configured here. See
     * alternateUsernameAllowed property for more details.
     */
    String username = "username";

    /**
     * (String) The connection password to be passed to our JDBC driver to establish a connection.
     * Note that method DataSource.getConnection(username,password) by default will not use
     * credentials passed into the method, but will use the ones configured here. See
     * alternateUsernameAllowed property for more details.
     */
    String password = "password";

    /**
     * (int) The maximum number of active connections that can be allocated from this pool at the
     * same time. The default value is 100
     */
    String maxActive = "maxActive";

    /**
     * (int) The maximum number of connections that should be kept in the pool at all times. Default
     * value is maxActive:100 Idle connections are checked periodically (if enabled) and connections
     * that been idle for longer than minEvictableIdleTimeMillis will be released. (also see
     * testWhileIdle)
     */
    String maxIdle = "maxIdle";

    /**
     * (int) The minimum number of established connections that should be kept in the pool at all
     * times. The connection pool can shrink below this number if validation queries fail. Default
     * value is derived from initialSize:10 (also see testWhileIdle)
     */
    String minIdle = "minIdle";

    /**
     * (int)The initial number of connections that are created when the pool is started. Default
     * value is 10
     */
    String initialSize = "initialSize";

    /**
     * (int) The maximum number of milliseconds that the pool will wait (when there are no available
     * connections) for a connection to be returned before throwing an exception. Default value is
     * 30000 (30 seconds)
     */
    String maxWait = "maxWait";

    /**
     * (boolean) The indication of whether objects will be validated before being borrowed from the
     * pool. If the object fails to validate, it will be dropped from the pool, and we will attempt
     * to borrow another. NOTE - for a true value to have any effect, the validationQuery parameter
     * must be set to a non-null string. In order to have a more efficient validation, see
     * validationInterval. Default value is false
     */
    String testOnBorrow = "testOnBorrow";

    /**
     * (boolean) The indication of whether objects will be validated before being returned to the
     * pool. NOTE - for a true value to have any effect, the validationQuery parameter must be set
     * to a non-null string. The default value is false.
     */
    String testOnReturn = "testOnReturn";

    /**
     * (boolean) The indication of whether objects will be validated by the idle object evictor (if
     * any). If an object fails to validate, it will be dropped from the pool. NOTE - for a true
     * value to have any effect, the validationQuery parameter must be set to a non-null string. The
     * default value is false and this property has to be set in order for the pool cleaner/test
     * thread is to run (also see timeBetweenEvictionRunsMillis)
     */
    String testWhileIdle = "testWhileIdle";

    /**
     * (String) The SQL query that will be used to validate connections from this pool before
     * returning them to the caller. If specified, this query does not have to return any data, it
     * just can't throw a SQLException. The default value is null. Example values are SELECT
     * 1(mysql), select 1 from dual(oracle), SELECT 1(MS Sql Server)
     */
    String validationQuery = "validationQuery";

    /**
     * (int) The timeout in seconds before a connection validation queries fail. This works by
     * calling java.sql.Statement.setQueryTimeout(seconds) on the statement that executes the
     * validationQuery. The pool itself doesn't timeout the query, it is still up to the JDBC driver
     * to enforce query timeouts. A value less than or equal to zero will disable this feature. The
     * default value is -1.
     */
    String validationQueryTimeout = "validationQueryTimeout";

    /**
     * (String) The name of a class which implements the org.apache.tomcat.jdbc.pool.Validator
     * interface and provides a no-arg constructor (may be implicit). If specified, the class will
     * be used to create a Validator instance which is then used instead of any validation query to
     * validate connections. The default value is null. An example value is
     * com.mycompany.project.SimpleValidator.
     */
    String validatorClassName = "validatorClassName";

    /**
     * (int) The number of milliseconds to sleep between runs of the idle connection
     * validation/cleaner thread. This value should not be set under 1 second. It dictates how often
     * we check for idle, abandoned connections, and how often we validate idle connections. The
     * default value is 5000 (5 seconds).
     */
    String timeBetweenEvictionRunsMillis = "timeBetweenEvictionRunsMillis";

    /**
     * (int) Property not used in tomcat-jdbc-pool.
     */
    String numTestsPerEvictionRun = "numTestsPerEvictionRun";

    /**
     * (int) The minimum amount of time an object may sit idle in the pool before it is eligible for
     * eviction. The default value is 60000 (60 seconds).
     */
    String minEvictableIdleTimeMillis = "minEvictableIdleTimeMillis";

    /**
     * (boolean) Property not used. Access can be achieved by calling unwrap on the pooled
     * connection. see javax.sql.DataSource interface, or call getConnection through reflection or
     * cast the object as javax.sql.PooledConnection
     */
    String accessToUnderlyingConnectionAllowed = "accessToUnderlyingConnectionAllowed";

    /**
     * (boolean) Flag to remove abandoned connections if they exceed the removeAbandonedTimeout. If
     * set to true a connection is considered abandoned and eligible for removal if it has been in
     * use longer than the removeAbandonedTimeout Setting this to true can recover db connections
     * from applications that fail to close a connection. See also logAbandoned The default value is
     * false.
     */
    String removeAbandoned = "removeAbandoned";

    /**
     * (int) Timeout in seconds before an abandoned(in use) connection can be removed. The default
     * value is 60 (60 seconds). The value should be set to the longest running query your
     * applications might have.
     */
    String removeAbandonedTimeout = "removeAbandonedTimeout";

    /**
     * (boolean) Flag to log stack traces for application code which abandoned a Connection. Logging
     * of abandoned Connections adds overhead for every Connection borrow because a stack trace has
     * to be generated. The default value is false.
     */
    String logAbandoned = "logAbandoned";

    /**
     * (String) The connection properties that will be sent to our JDBC driver when establishing new
     * connections. Format of the string must be [propertyName=property;]* NOTE - The "user" and
     * "password" properties will be passed explicitly, so they do not need to be included here. The
     * default value is null.
     */
    String connectionProperties = "connectionProperties";

    /**
     * (boolean) Property not used.
     */
    String poolPreparedStatements = "poolPreparedStatements";

    /**
     * (int) Property not used.
     */
    String maxOpenPreparedStatements = "maxOpenPreparedStatements";

    /** ⇱ Tomcat JDBC Enhanced Attributes */
    /* _____________________________ */section.part __TOMCAT__ = pseudo._void_;

    /**
     * (String) A custom query to be run when a connection is first created. The default value is
     * null.
     */
    String initSQL = "initSQL";

    /**
     * (String) A semicolon separated list of classnames extending
     * org.apache.tomcat.jdbc.pool.JdbcInterceptor class. See Configuring JDBC interceptors below
     * for more detailed description of syntaz and examples.
     * <p>
     * These interceptors will be inserted as an interceptor into the chain of operations on a
     * java.sql.Connection object. The default value is null.
     * <p>
     * Predefined interceptors:
     * <dl>
     * <dt>org.apache.tomcat.jdbc.pool.interceptor.ConnectionState</dt>
     * <dd>keeps track of auto commit, read only, catalog and transaction isolation level.</dd>
     * <dt>org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer</dt>
     * <dd>keeps track of opened statements, and closes them when the connection is returned to the
     * pool.</dd>
     * </dl>
     * <p>
     * More predefined interceptors are described in detail in the JDBC Interceptors section.
     */
    String jdbcInterceptors = "jdbcInterceptors";

    /**
     * (long) avoid excess validation, only run validation at most at this frequency - time in
     * milliseconds. If a connection is due for validation, but has been validated previously within
     * this interval, it will not be validated again. The default value is 30000 (30 seconds).
     */
    String validationInterval = "validationInterval";

    /**
     * (boolean) Register the pool with JMX or not. The default value is true.
     */
    String jmxEnabled = "jmxEnabled";

    /**
     * (boolean) Set to true if you wish that calls to getConnection should be treated fairly in a
     * true FIFO fashion. This uses the org.apache.tomcat.jdbc.pool.FairBlockingQueue implementation
     * for the list of the idle connections. The default value is true. This flag is required when
     * you want to use asynchronous connection retrieval. Setting this flag ensures that threads
     * receive connections in the order they arrive. During performance tests, there is a very large
     * difference in how locks and lock waiting is implemented. When fairQueue=true there is a
     * decision making process based on what operating system the system is running. If the system
     * is running on Linux (property os.name=Linux. To disable this Linux specific behavior and
     * still use the fair queue, simply add the property
     * org.apache.tomcat.jdbc.pool.FairBlockingQueue.ignoreOS=true to your system properties before
     * the connection pool classes are loaded.
     */
    String fairQueue = "fairQueue";

    /**
     * (int) Connections that have been abandoned (timed out) wont get closed and reported up unless
     * the number of connections in use are above the percentage defined by
     * abandonWhenPercentageFull. The value should be between 0-100. The default value is 0, which
     * implies that connections are eligible for closure as soon as removeAbandonedTimeout has been
     * reached.
     */
    String abandonWhenPercentageFull = "abandonWhenPercentageFull";

    /**
     * (long) Time in milliseconds to keep this connection. When a connection is returned to the
     * pool, the pool will check to see if the now - time-when-connected > maxAge has been reached,
     * and if so, it closes the connection rather than returning it to the pool. The default value
     * is 0, which implies that connections will be left open and no age check will be done upon
     * returning the connection to the pool.
     */
    String maxAge = "maxAge";

    /**
     * (boolean) Set to true if you wish the ProxyConnection class to use String.equals and set to
     * false when you wish to use == when comparing method names. This property does not apply to
     * added interceptors as those are configured individually. The default value is true.
     */
    String useEquals = "useEquals";

    /**
     * (int) Timeout value in seconds. Default value is 0.
     * <p>
     * Similar to to the removeAbandonedTimeout value but instead of treating the connection as
     * abandoned, and potentially closing the connection, this simply logs the warning if
     * logAbandoned is set to true. If this value is equal or less than 0, no suspect checking will
     * be performed. Suspect checking only takes place if the timeout value is larger than 0 and the
     * connection was not abandoned or if abandon check is disabled. If a connection is suspect a
     * WARN message gets logged and a JMX notification gets sent once.
     */
    String suspectTimeout = "suspectTimeout";

    /**
     * (boolean) If autoCommit==false then the pool can terminate the transaction by calling
     * rollback on the connection as it is returned to the pool Default value is false.
     */

    String rollbackOnReturn = "rollbackOnReturn";
    /**
     * (boolean) If autoCommit==false then the pool can complete the transaction by calling commit
     * on the connection as it is returned to the pool If rollbackOnReturn==true then this attribute
     * is ignored. Default value is false.
     */

    String commitOnReturn = "commitOnReturn";
    /**
     * (boolean) By default, the jdbc-pool will ignore the
     * DataSource.getConnection(username,password) call, and simply return a previously pooled
     * connection under the globally configured properties username and password, for performance
     * reasons.
     * <p>
     * The pool can however be configured to allow use of different credentials each time a
     * connection is requested. To enable the functionality described in the
     * DataSource.getConnection(username,password) call, simply set the property
     * alternateUsernameAllowed to true.
     * <p>
     * Should you request a connection with the credentials user1/password1 and the connection was
     * previously connected using different user2/password2, the connection will be closed, and
     * reopened with the requested credentials. This way, the pool size is still managed on a global
     * level, and not on a per schema level.
     * <p>
     * The default value is false.
     * <p>
     * This property was added as an enhancement to bug 50025.
     */
    String alternateUsernameAllowed = "alternateUsernameAllowed";

    /**
     * (javax.sql.DataSource) Inject a data source to the connection pool, and the pool will use the
     * data source to retrieve connections instead of establishing them using the java.sql.Driver
     * interface. This is useful when you wish to pool XA connections or connections established
     * using a data source instead of a connection string. Default value is null
     */
    String dataSource = "dataSource";

    /**
     * (String) The JNDI name for a data source to be looked up in JNDI and then used to establish
     * connections to the database. See the dataSource attribute. Default value is null
     */
    String dataSourceJNDI = "dataSourceJNDI";

    /**
     * (boolean) Set this to true if you wish to put a facade on your connection so that it cannot
     * be reused after it has been closed. This prevents a thread holding on to a reference of a
     * connection it has already called closed on, to execute queries on it. Default value is true.
     */
    String useDisposableConnectionFacade = "useDisposableConnectionFacade";

    /**
     * (boolean) Set this to true to log errors during the validation phase to the log file. If set
     * to true, errors will be logged as SEVERE. Default value is false for backwards compatibility.
     */
    String logValidationErrors = "logValidationErrors";

    /**
     * (boolean) Set this to true to propagate the interrupt state for a thread that has been
     * interrupted (not clearing the interrupt state). Default value is false for backwards
     * compatibility.
     */
    String propagateInterruptState = "propagateInterruptState";

    /**
     * (boolean) Flag whether ignore error of connection creation while initializing the pool. Set
     * to true if you want to ignore error of connection creation while initializing the pool. Set
     * to false if you want to fail the initialization of the pool by throwing exception. The
     * default value is false.
     */
    String ignoreExceptionOnPreLoad = "ignoreExceptionOnPreLoad";

    /**
     * ⇱ BoneCP Specific
     * 
     * @see com.jolbox.bonecp.BoneCPConfig
     */
    /* _____________________________ */section.part __BONECP__ = pseudo._void_;

    /**
     * The JDBC connection string URL.
     * 
     * Default: None
     */
    String jdbcUrl = "jdbcUrl";

    /**
     * In order to reduce lock contention and thus improve performance, each incoming connection
     * request picks off a connection from a pool that has thread-affinity, i.e. pool[threadId %
     * partition_count]. The higher this number, the better your performance will be for the case
     * when you have plenty of short-lived threads. Beyond a certain threshold, maintenence of these
     * pools will start to have a negative effect on performance (and only for the case when
     * connections on a partition start running out).
     * <p>
     * Default: 1, minimum: 1, recommended: 3-4 (but very app specific)
     */
    String partitionCount = "partitionCount";

    /**
     * The number of connections to create per partition. Setting this to 5 with 3 partitions means
     * you will have 15 unique connections to the database. Note that BoneCP will not create all
     * these connections in one go but rather start off with minConnectionsPerPartition and
     * gradually increase connections as required.
     */
    String maxConnectionsPerPartition = "maxConnectionsPerPartition";

    /**
     * The number of connections to start off with per partition.
     */
    String minConnectionsPerPartition = "minConnectionsPerPartition";

    /**
     * When the available connections are about to run out, BoneCP will dynamically create new ones
     * in batches. This property controls how many new connections to create in one go (up to a
     * maximum of maxConnectionsPerPartition). Note: This is a per partition setting.
     * <p>
     * Default: 10
     */
    String acquireIncrement = "acquireIncrement";

    /** (long) Connections older than this are sent a keep-alive statement. */
    String idleConnectionTestPeriodInSeconds = "idleConnectionTestPeriodInSeconds";

    /** (long) Maximum age of an unused connection before it is closed off. */
    String idleMaxAgeInSeconds = "idleMaxAgeInSeconds";

    /** SQL statement to use for keep-alive/test of connection. */
    String connectionTestStatement = "connectionTestStatement";

    /** (int) Min no of prepared statements to cache. */
    String statementsCacheSize = "statementsCacheSize";

    /** (int) No of statements that can be cached per connection. Deprecated. */
    String statementsCachedPerConnection = "statementsCachedPerConnection";

    /** (int) Number of release-connection helper threads to create per partition. */
    String releaseHelperThreads = "releaseHelperThreads";

    /** (int) Number of statement release helper threads to create. */
    String statementReleaseHelperThreads = "statementReleaseHelperThreads";

    /** (com.jolbox.bonecp.hooks.ConnectionHook) Hook class (external). */
    String connectionHook = "connectionHook";

    /**
     * (boolean) If set to true, create a new thread that monitors a connection and displays
     * warnings if application failed to close the connection. FOR DEBUG PURPOSES ONLY!
     */
    String closeConnectionWatch = "closeConnectionWatch";

    /** (boolean) If set to true, log SQL statements being executed. */
    String logStatementsEnabled = "logStatementsEnabled";

    /**
     * (long) After attempting to acquire a connection and failing, wait for this value before
     * attempting to acquire a new connection again.
     */
    String acquireRetryDelayInMs = "acquireRetryDelayInMs";

    /**
     * (int) After attempting to acquire a connection and failing, try to connect these many times
     * before giving up.
     */
    String acquireRetryAttempts = "acquireRetryAttempts";

    /**
     * (boolean) If set to true, the connection pool will remain empty until the first connection is
     * obtained.
     */
    String lazyInit = "lazyInit";

    /**
     * (boolean) If set to true, stores all activity on this connection to allow for replaying it
     * again.
     */
    String transactionRecoveryEnabled = "transactionRecoveryEnabled";

    /** (string) Connection hook class name. */
    String connectionHookClassName = "connectionHookClassName";

    /** (Classloader) to use when loading the JDBC driver. */
    String classLoader = "classLoader";

    /** (string) Name of the pool for JMX and thread names. */
    String poolName = "poolName";

    /** Set to true to disable JMX. */
    String disableJMX = "disableJMX";

    /** Queries taking longer than this limit to execute are logged. */
    String queryExecuteTimeLimitInMs = "queryExecuteTimeLimitInMs";

    /** (int) Create more connections when we hit x% of our possible number of connections. */
    String poolAvailabilityThreshold = "poolAvailabilityThreshold";

    /** (boolean) Disable connection tracking. */
    String disableConnectionTracking = "disableConnectionTracking";

    /** Used when the alternate way of obtaining a connection is required */
    /** (long) Time to wait before a call to getConnection() times out and returns an error. */
    String connectionTimeoutInMs = "connectionTimeoutInMs";

    /** (long) Time in ms to wait for close connection watch thread. */
    String closeConnectionWatchTimeoutInMs = "closeConnectionWatchTimeoutInMs";

    /** (long) A connection older than maxConnectionAge will be destroyed and purged from the pool. */
    String maxConnectionAgeInSeconds = "maxConnectionAgeInSeconds";

    /** (string) Config property. */
    String configFile = "configFile";

    /** (string) Queue mode. Values currently understood are FIFO (default) and LIFO. */
    String serviceOrder = "serviceOrder";

    /** (boolean) If true, keep track of some statistics. */
    String statisticsEnabled = "statisticsEnabled";

    /** (int) The parsed transaction isolation value. Default = driver value */
    String defaultTransactionIsolationValue = "defaultTransactionIsolationValue";

    /** (boolean) If true, stop caring about username/password when obtaining raw connections. */
    String externalAuth = "externalAuth";

    /** (boolean) If true, try to unregister the JDBC driver when pool is shutdown. */
    String deregisterDriverOnClose = "deregisterDriverOnClose";

    /** (boolean) If true, return null on connection timeout rather than throw an exception. */
    String nullOnConnectionTimeout = "nullOnConnectionTimeout";

    /** (boolean) If true, issue a reset (rollback) on connection close in case client forgot it. */
    String resetConnectionOnClose = "resetConnectionOnClose";

    /**
     * (boolean) Detect uncommitted transactions. If true, and resetConnectionOnClose is also true,
     * the pool will print out a stack trace of the location where you had a connection that
     * specified setAutoCommit(false) but then forgot to call commit/rollback before closing it off.
     * This feature is intended for debugging only.
     */
    String detectUnresolvedTransactions = "detectUnresolvedTransactions";

    /** (string) Determines pool operation Recognised strategies are: DEFAULT, CACHED. */
    String poolStrategy = "poolStrategy";

    /**
     * If true, track statements and close them if application forgot to do so. See also:
     * detectUnclosedStatements.
     */
    String closeOpenStatements = "closeOpenStatements";

    /**
     * (boolean) If true, print out a stack trace of where a statement was opened but not closed
     * before the connection was closed. See also: closeOpenStatements.
     */
    String detectUnclosedStatements = "detectUnclosedStatements";

    /** (properties) If set, pool will call this for every new connection that's created. */
    String clientInfo = "clientInfo";

}
