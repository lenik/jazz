package net.bodz.bas.search.elasticsearch;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ISettingsConsts;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;

public class EsPlayer
        implements ISettingsConsts {

    static String index = "p160323";
    Client client;

    public EsPlayer() {
        client = EsClientRegistry.getInstance().getAnyConfig().buildClient();
    }

    public void execute(String... args)
            throws Exception {
        search2(args[0]);
    }

    private void close() {
        client.close();
    }

    void index1() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("q", "kimchy");

        IndexResponse resp = client.prepareIndex(index, "clog") //
                .setSource(json) //
                .get();
        resp.getIndex();
        resp.getType();
        resp.getId();
        resp.getVersion();
        resp.isCreated();
    }

    void delete1() {
        client.prepareDelete(index, "clog", "1") //
                .get();
    }

    void update1() {
        client.prepareUpdate(index, "clog", "1") //
                .get();
    }

    void get1() {
        GetRequestBuilder reqb = client.prepareGet(index, "clog", "1") //
                .setOperationThreaded(false);
        GetResponse got = reqb.get();
        got.isExists();
        got.getFields();
    }

    void search1(String q) {
        SearchResponse results = client.prepareSearch(index) //
                .setTypes("clog") //
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH) //
                .setQuery(QueryBuilders.termQuery("multi", q)) //
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) //
                .setFrom(0).setSize(100) //
                .setExplain(true) //
                .execute() //
                .actionGet();
    }

    void search2(String q) {
        System.out.println("Searching " + q);
        SearchResponse results = client.prepareSearch("mytext") //
                .setTypes("doc") //
                // .setSearchType(SearchType.DFS_QUERY_AND_FETCH) //
                .addField("file.name")//
                .setQuery(QueryBuilders.matchQuery("file.content", q))//
                .highlighter(new HighlightBuilder()//
                        .field("file.content"))//
                // .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) //
                .setFrom(0).setSize(100) //
                // .setExplain(true) //
                .execute() //
                .actionGet();

        System.out.println("Took: " + results.getTook() + ", Found " + results.getHits().getTotalHits());

        for (SearchHit hit : results.getHits()) {
            float score = hit.getScore();
            Map<String, SearchHitField> fields = hit.getFields();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            SearchHitField fileNameField = fields.get("file.name");
            Object fileName = fileNameField.getValue();
            HighlightField fileContentField = highlightFields.get("file.content");
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
        player.close();
    }

}
