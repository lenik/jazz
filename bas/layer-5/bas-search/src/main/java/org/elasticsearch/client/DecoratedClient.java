package org.elasticsearch.client;

import java.util.Map;

import org.elasticsearch.action.Action;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.explain.ExplainRequest;
import org.elasticsearch.action.explain.ExplainRequestBuilder;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.fieldstats.FieldStatsRequest;
import org.elasticsearch.action.fieldstats.FieldStatsRequestBuilder;
import org.elasticsearch.action.fieldstats.FieldStatsResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.percolate.MultiPercolateRequest;
import org.elasticsearch.action.percolate.MultiPercolateRequestBuilder;
import org.elasticsearch.action.percolate.MultiPercolateResponse;
import org.elasticsearch.action.percolate.PercolateRequest;
import org.elasticsearch.action.percolate.PercolateRequestBuilder;
import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.termvectors.MultiTermVectorsRequest;
import org.elasticsearch.action.termvectors.MultiTermVectorsRequestBuilder;
import org.elasticsearch.action.termvectors.MultiTermVectorsResponse;
import org.elasticsearch.action.termvectors.TermVectorsRequest;
import org.elasticsearch.action.termvectors.TermVectorsRequestBuilder;
import org.elasticsearch.action.termvectors.TermVectorsResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedClient
        extends AbstractDecorator<Client>
        implements Client {

    private static final long serialVersionUID = 1L;

    public DecoratedClient(Client _orig) {
        super(_orig);
    }

    public void close() {
        getWrapped().close();
    }

    public <Request extends ActionRequest<Request>, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> ActionFuture<Response> execute(
            Action<Request, Response, RequestBuilder> action, Request request) {
        return getWrapped().execute(action, request);
    }

    public <Request extends ActionRequest<Request>, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> void execute(
            Action<Request, Response, RequestBuilder> action, Request request, ActionListener<Response> listener) {
        getWrapped().execute(action, request, listener);
    }

    public <Request extends ActionRequest<Request>, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> RequestBuilder prepareExecute(
            Action<Request, Response, RequestBuilder> action) {
        return getWrapped().prepareExecute(action);
    }

    public ThreadPool threadPool() {
        return getWrapped().threadPool();
    }

    public AdminClient admin() {
        return getWrapped().admin();
    }

    public ActionFuture<IndexResponse> index(IndexRequest request) {
        return getWrapped().index(request);
    }

    public void index(IndexRequest request, ActionListener<IndexResponse> listener) {
        getWrapped().index(request, listener);
    }

    public IndexRequestBuilder prepareIndex() {
        return getWrapped().prepareIndex();
    }

    public ActionFuture<UpdateResponse> update(UpdateRequest request) {
        return getWrapped().update(request);
    }

    public void update(UpdateRequest request, ActionListener<UpdateResponse> listener) {
        getWrapped().update(request, listener);
    }

    public UpdateRequestBuilder prepareUpdate() {
        return getWrapped().prepareUpdate();
    }

    public UpdateRequestBuilder prepareUpdate(String index, String type, String id) {
        return getWrapped().prepareUpdate(index, type, id);
    }

    public IndexRequestBuilder prepareIndex(String index, String type) {
        return getWrapped().prepareIndex(index, type);
    }

    public IndexRequestBuilder prepareIndex(String index, String type, String id) {
        return getWrapped().prepareIndex(index, type, id);
    }

    public ActionFuture<DeleteResponse> delete(DeleteRequest request) {
        return getWrapped().delete(request);
    }

    public void delete(DeleteRequest request, ActionListener<DeleteResponse> listener) {
        getWrapped().delete(request, listener);
    }

    public DeleteRequestBuilder prepareDelete() {
        return getWrapped().prepareDelete();
    }

    public DeleteRequestBuilder prepareDelete(String index, String type, String id) {
        return getWrapped().prepareDelete(index, type, id);
    }

    public ActionFuture<BulkResponse> bulk(BulkRequest request) {
        return getWrapped().bulk(request);
    }

    public void bulk(BulkRequest request, ActionListener<BulkResponse> listener) {
        getWrapped().bulk(request, listener);
    }

    public BulkRequestBuilder prepareBulk() {
        return getWrapped().prepareBulk();
    }

    public ActionFuture<GetResponse> get(GetRequest request) {
        return getWrapped().get(request);
    }

    public void get(GetRequest request, ActionListener<GetResponse> listener) {
        getWrapped().get(request, listener);
    }

    public GetRequestBuilder prepareGet() {
        return getWrapped().prepareGet();
    }

    public GetRequestBuilder prepareGet(String index, String type, String id) {
        return getWrapped().prepareGet(index, type, id);
    }

    public ActionFuture<MultiGetResponse> multiGet(MultiGetRequest request) {
        return getWrapped().multiGet(request);
    }

    public void multiGet(MultiGetRequest request, ActionListener<MultiGetResponse> listener) {
        getWrapped().multiGet(request, listener);
    }

    public MultiGetRequestBuilder prepareMultiGet() {
        return getWrapped().prepareMultiGet();
    }

    public ActionFuture<SearchResponse> search(SearchRequest request) {
        return getWrapped().search(request);
    }

    public void search(SearchRequest request, ActionListener<SearchResponse> listener) {
        getWrapped().search(request, listener);
    }

    public SearchRequestBuilder prepareSearch(String... indices) {
        return getWrapped().prepareSearch(indices);
    }

    public ActionFuture<SearchResponse> searchScroll(SearchScrollRequest request) {
        return getWrapped().searchScroll(request);
    }

    public void searchScroll(SearchScrollRequest request, ActionListener<SearchResponse> listener) {
        getWrapped().searchScroll(request, listener);
    }

    public SearchScrollRequestBuilder prepareSearchScroll(String scrollId) {
        return getWrapped().prepareSearchScroll(scrollId);
    }

    public ActionFuture<MultiSearchResponse> multiSearch(MultiSearchRequest request) {
        return getWrapped().multiSearch(request);
    }

    public void multiSearch(MultiSearchRequest request, ActionListener<MultiSearchResponse> listener) {
        getWrapped().multiSearch(request, listener);
    }

    public MultiSearchRequestBuilder prepareMultiSearch() {
        return getWrapped().prepareMultiSearch();
    }

    public ActionFuture<TermVectorsResponse> termVectors(TermVectorsRequest request) {
        return getWrapped().termVectors(request);
    }

    public void termVectors(TermVectorsRequest request, ActionListener<TermVectorsResponse> listener) {
        getWrapped().termVectors(request, listener);
    }

    public TermVectorsRequestBuilder prepareTermVectors() {
        return getWrapped().prepareTermVectors();
    }

    public TermVectorsRequestBuilder prepareTermVectors(String index, String type, String id) {
        return getWrapped().prepareTermVectors(index, type, id);
    }

    @Deprecated
    public ActionFuture<TermVectorsResponse> termVector(TermVectorsRequest request) {
        return getWrapped().termVector(request);
    }

    @Deprecated
    public void termVector(TermVectorsRequest request, ActionListener<TermVectorsResponse> listener) {
        getWrapped().termVector(request, listener);
    }

    @Deprecated
    public TermVectorsRequestBuilder prepareTermVector() {
        return getWrapped().prepareTermVector();
    }

    @Deprecated
    public TermVectorsRequestBuilder prepareTermVector(String index, String type, String id) {
        return getWrapped().prepareTermVector(index, type, id);
    }

    public ActionFuture<MultiTermVectorsResponse> multiTermVectors(MultiTermVectorsRequest request) {
        return getWrapped().multiTermVectors(request);
    }

    public void multiTermVectors(MultiTermVectorsRequest request, ActionListener<MultiTermVectorsResponse> listener) {
        getWrapped().multiTermVectors(request, listener);
    }

    public MultiTermVectorsRequestBuilder prepareMultiTermVectors() {
        return getWrapped().prepareMultiTermVectors();
    }

    public ActionFuture<PercolateResponse> percolate(PercolateRequest request) {
        return getWrapped().percolate(request);
    }

    public void percolate(PercolateRequest request, ActionListener<PercolateResponse> listener) {
        getWrapped().percolate(request, listener);
    }

    public PercolateRequestBuilder preparePercolate() {
        return getWrapped().preparePercolate();
    }

    public ActionFuture<MultiPercolateResponse> multiPercolate(MultiPercolateRequest request) {
        return getWrapped().multiPercolate(request);
    }

    public void multiPercolate(MultiPercolateRequest request, ActionListener<MultiPercolateResponse> listener) {
        getWrapped().multiPercolate(request, listener);
    }

    public MultiPercolateRequestBuilder prepareMultiPercolate() {
        return getWrapped().prepareMultiPercolate();
    }

    public ExplainRequestBuilder prepareExplain(String index, String type, String id) {
        return getWrapped().prepareExplain(index, type, id);
    }

    public ActionFuture<ExplainResponse> explain(ExplainRequest request) {
        return getWrapped().explain(request);
    }

    public void explain(ExplainRequest request, ActionListener<ExplainResponse> listener) {
        getWrapped().explain(request, listener);
    }

    public ClearScrollRequestBuilder prepareClearScroll() {
        return getWrapped().prepareClearScroll();
    }

    public ActionFuture<ClearScrollResponse> clearScroll(ClearScrollRequest request) {
        return getWrapped().clearScroll(request);
    }

    public void clearScroll(ClearScrollRequest request, ActionListener<ClearScrollResponse> listener) {
        getWrapped().clearScroll(request, listener);
    }

    public FieldStatsRequestBuilder prepareFieldStats() {
        return getWrapped().prepareFieldStats();
    }

    public ActionFuture<FieldStatsResponse> fieldStats(FieldStatsRequest request) {
        return getWrapped().fieldStats(request);
    }

    public void fieldStats(FieldStatsRequest request, ActionListener<FieldStatsResponse> listener) {
        getWrapped().fieldStats(request, listener);
    }

    public Settings settings() {
        return getWrapped().settings();
    }

    public Client filterWithHeader(Map<String, String> headers) {
        return getWrapped().filterWithHeader(headers);
    }

}
