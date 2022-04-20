package com.nhnacademy.scurlproject.args;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

public class Args {
    @Parameter(names = "-X", description = "signiture of request")
    private String request;
    @Parameter(names = "-v", description = "all of response logging")
    private boolean isHeader = false;
    @Parameter(names = "-H", description = "custom header append")
    private List<String> appendHeader = new ArrayList<>();
    @Parameter(names = "-d", description = "POST")
    private String postContents;
    @Parameter(names = "-L", description = "Redirect")
    private boolean redirecting;

    @RequestMapping("redirect")


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
