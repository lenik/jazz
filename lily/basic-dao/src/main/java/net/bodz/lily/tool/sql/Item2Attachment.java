package net.bodz.lily.tool.sql;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.util.PGobject;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.io.res.AbstractStreamResource;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.t.catalog.*;
import net.bodz.bas.t.tuple.Split;
import net.bodz.lily.entity.attachment.AttachmentGroup;
import net.bodz.lily.entity.attachment.DefaultAttachment;
import net.bodz.lily.storage.IVolume;

/**
 * Refactor props field to attachments field. (but don't rename the column.)
 */
@ProgramName("item2att")
public class Item2Attachment
        extends BasicCLI {

    static Logger logger = LoggerFactory.getLogger(Item2Attachment.class);

    /**
     * Use specified codegen config.
     *
     * @option -c =FILE
     */
    File configFile;

    /**
     * Specify the data context
     *
     * @option -d --data-context =NAME
     */
    DataContext dataContext;

    Connection connection;

    CatalogSubset catalogSubset = new CatalogSubset(null);

    public Item2Attachment(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    boolean processTableOrView(ITableMetadata tableView) {
        switch (tableView.getTableType()) {
        case TABLE:
        case SYSTEM_TABLE:
            logger.info("process table " + tableView.getId());
            try {
                processTable(tableView);
            } catch (Exception e) {
                logger.error("Error make table: " + e.getMessage(), e);
                return false;
            } finally {
            }
            return true;

        default:
            return false;
        }
    }

    JsonFormOptions inOpts = JsonFormOptions.DEFAULT;
    JsonFormOptions outOpts = JsonFormOptions.DEFAULT;

    AttachmentGroup anyGroup = new AttachmentGroup(new File("."));

    public void processTable(ITableMetadata table)
            throws IOException, SQLException, ParseException, FormatException {
        IVolume volume = anyGroup.getVolume(table.getEntityTypeName());

        Statement st = connection.createStatement(//
                ResultSet.TYPE_FORWARD_ONLY, //
                ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = st.executeQuery("select * from " + table.getId());
        while (rs.next()) {
            String json = rs.getString("props");
            if (json == null || json.isEmpty())
                continue;

            JsonObject o = JsonFn.parseObject(json);
            if (o.isEmpty())
                continue;

            System.out.println(json);

            JsonObject newO = new JsonObject();
            boolean dirty = false;
            for (String key : o.keySet()) {
                Object oldVal = o.get(key);
                Object newVal = oldVal;
                if (oldVal instanceof JsonArray) {
                    JsonArray oldValArray = (JsonArray) oldVal;
                    JsonArray newValArray = new JsonArray();
                    int n = oldValArray.length();
                    boolean arrayDirty = false;

                    for (int i = 0; i < n; i++) {
                        if (oldValArray.isNull(i))
                            continue;
                        JsonObject oldValArrayItem = oldValArray.getJsonObject(i);
                        String itemFileMagic = oldValArrayItem.getString("dir");
                        if (itemFileMagic != null) {
                            ItemFile item = new ItemFile();
                            item.jsonIn(oldValArrayItem, null);

                            DefaultAttachment attachment = new DefaultAttachment();
                            attachment.setLabel(item.getLabel());
                            // default dir is Foo/ID.
                            // attachment.setDirName(item.getDir());

                            String oldFileName = item.getName();
                            Split split = Split.nameExtension(oldFileName);
                            String oldSha1 = item.getSha1();
                            if (split.a.equalsIgnoreCase(oldSha1)) {
                                attachment.setExtension(split.b);
                            } else {
                                attachment.setFileName(oldFileName);
                            }

                            attachment.setFileSize(item.getSize());
                            attachment.setFileSHA1(item.getSha1());

                            JsonObject newValArrayItem = JsonFn.toJsonObject(attachment);
                            newValArray.put(newValArrayItem);
                            arrayDirty = true;
                            continue;
                        } // if ItemFile
                        newValArray.put(oldValArrayItem);
                    } // for array
                    if (arrayDirty)
                        newVal = newValArray;
                } // if array
                if (newVal != oldVal)
                    dirty = true;
                newO.put(key, newVal);
            } // for key

            if (dirty) { // update
                String newJson = newO.toString();
                System.out.println("  => " + newJson);
                System.out.println();

                PGobject pgo = new PGobject();
                pgo.setType("json"); // jsonb .. similar.
                pgo.setValue(newJson);
                rs.updateObject("props", pgo);

                rs.updateRow();
            }
        }
        rs.close();
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        for (String arg : args) {
            if (arg.startsWith("@")) {
                String path = arg.substring(1);
                AbstractStreamResource resource;

                if (new File(path).exists()) {
                    resource = ResFn.file(new File(path));
                } else {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    URL classRes = classLoader.getResource(path);
                    if (classRes == null)
                        throw new IllegalArgumentException("Bad resource path: " + path);
                    resource = ResFn.url(classRes);
                }

                for (String line : resource.read().lines()) {
                    String name = line.trim();
                    if (name.isEmpty() || name.startsWith("#"))
                        continue;
                    TableOid oid = TableOid.parse(name);
                    catalogSubset.addTable(oid);
                }

            } else if (arg.endsWith(".*.*")) {
                catalogSubset.addAllSchemas();
            } else if (arg.endsWith(".*")) {
                String schemaName = StringPart.before(arg, ".*");
                // schemaName = schemaName.toLowerCase();
                catalogSubset.addFullSchema(schemaName);
            } else {
                TableOid oid = TableOid.parse(arg);
                catalogSubset.addTable(oid);
            }
        }

        DefaultCatalogMetadata catalog = new DefaultCatalogMetadata();
        catalog.setJDBCLoadSelector(new IJDBCLoadSelector() {
            @Override
            public SelectMode selectSchema(SchemaOid id) {
                ContainingType type = catalogSubset.contains(id.getSchemaName());
                if (type != ContainingType.NONE)
                    return SelectMode.INCLUDE;
                else
                    return SelectMode.SKIP;
            }

            @Override
            public SelectMode selectTable(TableOid oid, TableType type) {
                if (!type.isTable())
                    return SelectMode.SKIP;
                if (catalogSubset.contains(oid))
                    return SelectMode.INCLUDE;
                else
                    return SelectMode.SKIP;
            }

        });

        try {
            connection = dataContext.getConnection();
            catalog.loadFromJDBC(connection, "TABLE", "VIEW");

            catalog.accept(new ICatalogVisitor() {
                @Override
                public boolean beginTableOrView(ITableMetadata table) {
                    IColumnMetadata propsCol = table.getColumn("props");
                    if (propsCol != null)
                        processTableOrView(table);
                    return false;
                }
            });
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    public static void main(String[] args)
            throws Exception {
        DataContext dataContext = DataHub.getPreferredHub().getMain();
        Item2Attachment app = new Item2Attachment(dataContext);
        app.execute(args);
    }

}
