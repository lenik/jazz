package net.bodz.bas.db.jdbc;

import net.bodz.bas.sugar.IConstants;

public interface IHibernatePropertyNames
        extends IConstants {

    /**
     * <strong>Hibernate JDBC Properties</strong>
     * 
     * JDBC driver class
     */
    String connectionDriverClassKey = "hibernate.connection.driver_class";

    /**
     * <strong>Hibernate JDBC Properties</strong>
     * 
     * JDBC URL
     */
    String connectionUrlKey = "hibernate.connection.url";

    /**
     * <strong>Hibernate JDBC Properties</strong>
     * 
     * database user
     */
    String connectionUsernameKey = "hibernate.connection.username";

    /**
     * <strong>Hibernate JDBC Properties</strong>
     * 
     * database user password
     */
    String connectionPasswordKey = "hibernate.connection.password";

    /**
     * <strong>Hibernate JDBC Properties</strong>
     * 
     * maximum number of pooled connections
     */
    String connectionPoolSizeKey = "hibernate.connection.pool_size";

    /**
     * <strong>Hibernate Datasource Properties</strong>
     * 
     * datasource JNDI name
     */
    String connectionDatasourceKey = "hibernate.connection.datasource";

    /**
     * <strong>Hibernate Datasource Properties</strong>
     * 
     * URL of the JNDI provider (optional)
     */
    String jndiUrlKey = "hibernate.jndi.url";

    /**
     * <strong>Hibernate Datasource Properties</strong>
     * 
     * class of the JNDI InitialContextFactory (optional)
     */
    String jndiClassKey = "hibernate.jndi.class";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * The classname of a Hibernate org.hibernate.dialect.Dialect which allows Hibernate to generate
     * SQL optimized for a particular relational database.
     * 
     * @example full.classname.of.Dialect
     */
    String dialectKey = "hibernate.dialect";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Write all SQL statements to console. This is an alternative to setting the log category
     * org.hibernate.SQL to debug.
     * 
     * @example true | false
     */
    String showSqlKey = "hibernate.show_sql";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Pretty print the SQL in the log and console. e.g. true | false
     */
    String formatSqlKey = "hibernate.format_sql";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Qualify unqualified table names with the given schema/tablespace in generated SQL. e.g.
     * SCHEMA_NAME
     */
    String defaultSchemaKey = "hibernate.default_schema";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Qualifies unqualified table names with the given catalog in generated SQL. e.g. CATALOG_NAME
     */
    String defaultCatalogKey = "hibernate.default_catalog";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * The org.hibernate.SessionFactory will be automatically bound to this name in JNDI after it
     * has been created.
     * 
     * @example jndi/composite/name
     */
    String sessionFactoryNameKey = "hibernate.session_factory_name";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Sets a maximum "depth" for the outer join fetch tree for single-ended associations
     * (one-to-one, many-to-one). A 0 disables default outer join fetching.
     * 
     * @example recommended values between 0 and 3
     */
    String maxFetchDepthKey = "hibernate.max_fetch_depth";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Sets a default size for Hibernate batch fetching of associations. e.g. recommended values 4,
     * 8, 16
     */
    String defaultBatchFetchSizeKey = "hibernate.default_batch_fetch_size";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Sets a default mode for entity representation for all sessions opened from this
     * SessionFactory
     */
    String defaultEntityModeKey = "hibernate.default_entity_mode";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * Forces Hibernate to order SQL updates by the primary key value of the items being updated.
     * This will result in fewer transaction deadlocks in highly concurrent systems. e.g. true |
     * false
     */
    String orderUpdatesKey = "hibernate.order_updates";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * If enabled, Hibernate will collect statistics useful for performance tuning. e.g. true |
     * false
     */
    String generateStatisticsKey = "hibernate.generate_statistics";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * If enabled, generated identifier properties will be reset to default values when objects are
     * deleted. e.g. true | false
     */
    String useIdentifierRollbackKey = "hibernate.use_identifier_rollback";

    /**
     * <strong>Hibernate Configuration Properties</strong>
     * 
     * If turned on, Hibernate will generate comments inside the SQL, for easier debugging, defaults
     * to false.
     * 
     * @example true | false
     */
    String useSqlCommentsKey = "hibernate.use_sql_comments";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * A non-zero value determines the JDBC fetch size (calls Statement.setFetchSize()).
     */
    String jdbcFetchSizeKey = "hibernate.jdbc.fetch_size";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * A non-zero value enables use of JDBC2 batch updates by Hibernate. e.g. recommended values
     * between 5 and 30
     */
    String jdbcBatchSizeKey = "hibernate.jdbc.batch_size";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Set this property to true if your JDBC driver returns correct row counts from executeBatch().
     * Iit is usually safe to turn this option on. Hibernate will then use batched DML for
     * automatically versioned data. Defaults to false.
     * 
     * @example true | false
     */
    String jdbcBatchVersionedDataKey = "hibernate.jdbc.batch_versioned_data";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Select a custom org.hibernate.jdbc.Batcher. Most applications will not need this
     * configuration property.
     * 
     * @example classname.of.BatcherFactory
     */
    String jdbcFactoryClassKey = "hibernate.jdbc.factory_class";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Enables use of JDBC2 scrollable resultsets by Hibernate. This property is only necessary when
     * using user-supplied JDBC connections. Hibernate uses connection metadata otherwise. e.g. true
     * | false
     */
    String jdbcUseScrollableResultsetKey = "hibernate.jdbc.use_scrollable_resultset";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Use streams when writing/reading binary or serializable types to/from JDBC. *system-level
     * property*
     * 
     * @example true | false
     */
    String jdbcUseStreamsForBinaryKey = "hibernate.jdbc.use_streams_for_binary";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Enables use of JDBC3 PreparedStatement.getGeneratedKeys() to retrieve natively generated keys
     * after insert. Requires JDBC3+ driver and JRE1.4+, set to false if your driver has problems
     * with the Hibernate identifier generators. By default, it tries to determine the driver
     * capabilities using connection metadata.
     * 
     * @example true|false
     */
    String jdbcUseGetGeneratedKeysKey = "hibernate.jdbc.use_get_generated_keys";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * The classname of a custom org.hibernate.connection.ConnectionProvider which provides JDBC
     * connections to Hibernate.
     * 
     * @example classname.of.ConnectionProvider
     */
    String connectionProviderClassKey = "hibernate.connection.provider_class";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Sets the JDBC transaction isolation level. Check java.sql.Connection for meaningful values,
     * but note that most databases do not support all isolation levels and some define additional,
     * non-standard isolations.
     * 
     * @example 1, 2, 4, 8
     */
    String connectionIsolationKey = "hibernate.connection.isolation";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Enables autocommit for JDBC pooled connections (it is not recommended). e.g. true | false
     */
    String connectionAutocommitKey = "hibernate.connection.autocommit";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Specifies when Hibernate should release JDBC connections. By default, a JDBC connection is
     * held until the session is explicitly closed or disconnected. For an application server JTA
     * datasource, use after_statement to aggressively release connections after every JDBC call.
     * For a non-JTA connection, it often makes sense to release the connection at the end of each
     * transaction, by using after_transaction. auto will choose after_statement for the JTA and CMT
     * transaction strategies and after_transaction for the JDBC transaction strategy.
     * 
     * @example auto (default) | on_close | after_transaction | after_statement
     */
    String connectionReleaseModeKey = "hibernate.connection.release_mode";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Pass the JDBC property <propertyName> to DriverManager.getConnection().
     */
    String connection_PREFIXKey = "hibernate.connection.<propertyName>";

    /**
     * <strong>Hibernate JDBC and Connection Properties</strong>
     * 
     * Pass the property <propertyName> to the JNDI InitialContextFactory.
     */
    String jndi_PREFIXKey = "hibernate.jndi.<propertyName>";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * The classname of a custom CacheProvider.
     * 
     * @example classname.of.CacheProvider
     */
    String cacheProviderClassKey = "hibernate.cache.provider_class";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * Optimizes second-level cache operation to minimize writes, at the cost of more frequent
     * reads. This setting is most useful for clustered caches and, in Hibernate3, is enabled by
     * default for clustered cache implementations. e.g. true|false
     */
    String cacheUseMinimalPutsKey = "hibernate.cache.use_minimal_puts";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * Enables the query cache. Individual queries still have to be set cachable. e.g. true|false
     */
    String cacheUseQueryCacheKey = "hibernate.cache.use_query_cache";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * Can be used to completely disable the second level cache, which is enabled by default for
     * classes which specify a <cache> mapping.
     * 
     * @example true|false
     */
    String cacheUseSecondLevelCacheKey = "hibernate.cache.use_second_level_cache";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * The classname of a custom QueryCache interface, defaults to the built-in StandardQueryCache.
     * 
     * @example classname.of.QueryCache
     */
    String cacheQueryCacheFactoryKey = "hibernate.cache.query_cache_factory";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * A prefix to use for second-level cache region names. e.g. prefix
     */
    String cacheRegionPrefixKey = "hibernate.cache.region_prefix";

    /**
     * <strong>Hibernate Cache Properties</strong>
     * 
     * Forces Hibernate to store data in the second-level cache in a more human-friendly format.
     * e.g. true|false
     */
    String cacheUseStructuredEntriesKey = "hibernate.cache.use_structured_entries";

    /**
     * <strong>Hibernate Transaction Properties</strong>
     * 
     * The classname of a TransactionFactory to use with Hibernate Transaction API (defaults to
     * JDBCTransactionFactory).
     * 
     * @example classname.of.TransactionFactory
     */
    String transactionFactoryClassKey = "hibernate.transaction.factory_class";

    /**
     * <strong>Hibernate Transaction Properties</strong>
     * 
     * The classname of a TransactionManagerLookup. It is required when JVM-level caching is enabled
     * or when using hilo generator in a JTA environment.
     * 
     * @example classname.of.TransactionManagerLookup
     */
    String transactionManagerLookupClassKey = "hibernate.transaction.manager_lookup_class";

    /**
     * <strong>Hibernate Transaction Properties</strong>
     * 
     * If enabled, the session will be automatically flushed during the before completion phase of
     * the transaction. Built-in and automatic session context management is preferred, see Section
     * 2.5, “Contextual sessions”.
     * 
     * @example true | false
     */
    String transactionFlushBeforeCompletionKey = "hibernate.transaction.flush_before_completion";

    /**
     * <strong>Hibernate Transaction Properties</strong>
     * 
     * If enabled, the session will be automatically closed during the after completion phase of the
     * transaction. Built-in and automatic session context management is preferred, see Section 2.5,
     * “Contextual sessions”.
     * 
     * @example true | false
     */
    String transactionAutoCloseSessionKey = "hibernate.transaction.auto_close_session";

    /**
     * <strong>Miscellaneous Properties</strong>
     * 
     * Supply a custom strategy for the scoping of the "current" Session. See Section 2.5,
     * “Contextual sessions” for more information about the built-in strategies.
     * 
     * @example jta | thread | managed | custom.Class
     */
    String currentSessionContextClassKey = "hibernate.current_session_context_class";

    /**
     * <strong>Miscellaneous Properties</strong>
     * 
     * Chooses the HQL parser implementation. e.g. org.hibernate.hql.ast.ASTQueryTranslatorFactory
     * or org.hibernate.hql.classic.ClassicQueryTranslatorFactory
     */
    String queryFactoryClassKey = "hibernate.query.factory_class";

    /**
     * <strong>Miscellaneous Properties</strong>
     * 
     * Is used to map from tokens in Hibernate queries to SQL tokens (tokens might be function or
     * literal names, for example). e.g. hqlLiteral=SQL_LITERAL, hqlFunction=SQLFUNC
     */
    String querySubstitutionsKey = "hibernate.query.substitutions";

    /**
     * <strong>Miscellaneous Properties</strong>
     * 
     * Automatically validates or exports schema DDL to the database when the SessionFactory is
     * created. With create-drop, the database schema will be dropped when the SessionFactory is
     * closed explicitly.
     * 
     * @example validate | update | create | create-drop
     */
    String hbm2ddlAutoKey = "hibernate.hbm2ddl.auto";

    /**
     * <strong>Miscellaneous Properties</strong>
     * 
     * Enables the use of CGLIB instead of runtime reflection (System-level property). Reflection
     * can sometimes be useful when troubleshooting. Hibernate always requires CGLIB even if you
     * turn off the optimizer. You cannot set this property in hibernate.cfg.xml.
     * 
     * @example true | false
     */
    String cglibUseReflectionOptimizerKey = "hibernate.cglib.use_reflection_optimizer";

}