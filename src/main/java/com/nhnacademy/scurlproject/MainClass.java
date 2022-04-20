package com.nhnacademy.scurlproject;

import com.beust.jcommander.JCommander;
import com.nhnacademy.scurlproject.args.Args;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainClass {
    private static final Log log = LogFactory.getLog(MainClass.class);
    private static String url;
    private static int port;
    private static String path;

    public static void main(String[] args) throws IOException {
        MainClass mainClass = new MainClass();
        Args jargs = new Args();

        //        if(args.length<1){
//            log.info("please write host");
//        }

        JCommander.newBuilder()
            .addObject(jargs)
            .build()
            .parse(Arrays.copyOf(args,args.length-1));

        mainClass.parseUrl(args[args.length-1]);
//        client.printBody(url, port, path, false);

        Client client = new Client();
            client.printBody(jargs.getRequest() ,jargs.isHeader(), jargs.getAppendHeader(),jargs.getPostContents());
    }

    private void parseUrl(String arg){

        String[] temp = arg.split("/");

        String[] portParsing = temp[2].split(":");
        url = portParsing[0];
        if(portParsing.length > 1){
            port = Integer.parseInt(portParsing[1]);
        }else if(temp[0].equals("http:")){
            port = 80;
        }else if(temp[0].equals("https:")){
            port = 443;
        }
        path = temp[3];
        if(temp.length>4){
            int length = temp.length;
            for (int i = 4; i <= length-1; i++) {
               path = path.concat("/"+temp[i]);
            }
        }
    }

    public static String getUrl() {
        return url;
    }

    public static int getPort() {
        return port;
    }

    public static String getPath() {
        return path;
    }
}
