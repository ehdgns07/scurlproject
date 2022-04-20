package com.nhnacademy.scurlproject.args;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Args {
    @Parameter(names = "-X", description = "signiture of request")
    private String request;
    @Parameter(names = "-v", description = "all of response logging")
    private boolean isHeader = false;
    @Parameter(names = "-H", description = "custom header append")
    private List<String> appendHeader = new ArrayList<>();
    @Parameter(names = "-d", description = "POST")
    private String postContents;


    public String getRequest() {
        return request;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public List<String> getAppendHeader() {
        return appendHeader;
    }

    public String getPostContents() {
        return postContents;
    }
}
