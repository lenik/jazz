package net.bodz.bas.search.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.common.settings.ISettingsConsts;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.SearchType;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;

public class EsPlayer
        implements
            ISettingsConsts {

    static String index = "p160323";
    ElasticsearchClient client;

    public EsPlayer() {
        client = EsClientRegistry.getInstance().getAnyConfig().buildClient();
    }

    public void execute(String... args)
            throws Exception {
        search1(args[0]);
    }

    private void shutdown() {
        client.shutdown();
    }

    void index1()
            throws ElasticsearchException, IOException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("q", "kimchy");

        SearchResponse<Object> resp = client.search(s -> s.index("clog"), Object.class);
        resp.hits();
    }

    void delete1()
            throws ElasticsearchException, IOException {
        DeleteResponse resp;
        resp = client.delete(s -> s.index(index).index("clog"));
    }

    void update1()
            throws ElasticsearchException, IOException {
        client.update(s -> s, Object.class);
    }

    void get1()
            throws ElasticsearchException, IOException {
        GetResponse<Object> resp = client.get(s -> s, Object.class);
        // .setOperationThreaded(false);
        resp.id();
    }

    void search1(String q)
            throws ElasticsearchException, IOException {
        SearchResponse<Object> resp = client.search(s -> s//
                .index("clog") //
                .searchType(SearchType.DfsQueryThenFetch) //
                .q(q) //
                // .postFilter(f -> f.range()) //
                .from(0).size(100) //
                .explain(true) //
                , Object.class);

        for (Hit<?> hit : resp.hits().hits()) {
            double score = hit.score();
            Map<String, JsonData> fields = hit.fields();
            Map<String, List<String>> highlightFields = hit.highlight();
            JsonData fileNameField = fields.get("file.name");
            // Object fileName = fileNameField.deserialize(null);
            List<String> fileContentField = highlightFields.get("file.content");
            String fileContent = fileContentField.toString();
            // System.out.println("" + score + fileName);
            // System.out.println(fileContent);
        }
    }

    public static void main(String[] args)
            throws Exception {
        EsPlayer player = new EsPlayer();
        int i = 2;
        while (i-- > 0) {
            player.execute("却将金香玉塞进了胸前衣服里");
            player.execute("夸讲着这里是商州最能出美女的地方");
        }
        player.shutdown();
    }

}
