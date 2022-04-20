package com.nhnacademy.scurlproject.args;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = "-X", description = "signiture of request")
    private String request;
    @Parameter(names = "-v", description = "all of response logging")
    private boolean isHeader = false;
    @Parameter(names = "-H", description = "custom header append")
    private String appendHeader;

    public String getRequest() {
        return request;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public String getAppendHeader() {
        return appendHeader;
    }
}
