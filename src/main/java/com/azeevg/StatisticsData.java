package com.azeevg;

public class StatisticsData {
    private final String host;
    private final String pathQuery;
    private final String method;
    private final int code;
    private final long start;
    private final long end;

    public StatisticsData(String host, String pathQuery, String method, int code, long start, long end) {
        this.host = host;
        this.pathQuery = pathQuery;
        this.method = method;
        this.code = code;
        this.start = start;
        this.end = end;
    }

    public String getHost() {
        return host;
    }

    public String getPathQuery() {
        return pathQuery;
    }

    public String getMethod() {
        return method;
    }

    public int getCode() {
        return code;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
