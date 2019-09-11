package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.io.res.IStreamInputSource;

class InMemoryURLConnection
        extends URLConnection {

    private IStreamInputSource inputSource;
    private Long length;

    private List<Entry<String, String>> headerList;
    private Map<String, List<String>> headerMap;

    public InMemoryURLConnection(URL url, IStreamInputSource inputSource, Long length, Map<String, String> headers) {
        super(url);
        if (inputSource == null)
            throw new NullPointerException("inputSource");
        this.inputSource = inputSource;
        this.length = length;

        headerList = new ArrayList<Entry<String, String>>();
        headerMap = new HashMap<String, List<String>>();

        for (Entry<String, String> entry : headers.entrySet()) {
            headerList.add(entry);

            String name = entry.getKey();
            List<String> values = headerMap.get(name);
            if (values == null)
                headerMap.put(name, values = new ArrayList<String>());
            values.add(entry.getValue());
        }
    }

    @Override
    public String getHeaderField(String name) {
        List<String> values = headerMap.get(name);
        if (values == null)
            return null;
        else
            return values.get(values.size() - 1);
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        return headerMap;
    }

    @Override
    public String getHeaderFieldKey(int n) {
        if (n >= headerList.size())
            return null;
        else
            return headerList.get(n).getKey();
    }

    @Override
    public String getHeaderField(int n) {
        if (n >= headerList.size())
            return null;
        else
            return headerList.get(n).getValue();
    }

    @Override
    public long getContentLengthLong() {
        if (length == null)
            return -1;
        else
            return length.longValue();
    }

    @Override
    public void connect()
            throws IOException {
    }

    @Override
    public InputStream getInputStream()
            throws IOException {
        return inputSource.newInputStream();
    }

}