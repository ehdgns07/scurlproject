package com.nhnacademy.scurlproject;

import com.beust.jcommander.JCommander;
import com.nhnacademy.scurlproject.args.Args;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainClass {
    private static final Log log = LogFactory.getLog(MainClass.class);
    private static String url;
    private static int port;
    private static String path;

    public static void main(String[] args) throws IOException {
        MainClass mainClass = new MainClass();
        Client client = new Client();
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

        if(Objects.equals(jargs.getRequest(),"GET")){
            client.printBody(url, port, path, false);
        }
        if(jargs.isHeader()){
            client.printBody(url, port, path, true);
        }



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

    }
}
