package com.nhnacademy.scurlproject;

import com.nhnacademy.scurlproject.args.Args;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {

    private static final Log log = LogFactory.getLog(Client.class);

    String path = MainClass.getPath();

    public void printBody(String request, boolean isHeader,
                          List<String> headerAppending, String postContents) throws IOException {
        String line;
        Args args = new Args();
        Socket socket = new Socket(MainClass.getUrl(), MainClass.getPort());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        if(request==null){
            request="GET";
        }

        writeHeader(request, out);
        if (!headerAppending.isEmpty()) {
            for (String header : headerAppending) {
                out.writeBytes(header + "\n");
            }
        }

        if(postContents != null){
            out.writeBytes("Content-Length: "+ postContents.length() + "\n");
            out.writeBytes("\n");
            out.writeBytes(postContents + "\n");
        }
        out.writeBytes("\n");
        out.flush();

        try (BufferedReader read = new BufferedReader(new InputStreamReader(in))) {
            while ((line = read.readLine()) != null) {
                if(line.startsWith("location")||line.startsWith("Location")){
                    String[] arr = line.split(": ");
                    path = arr[1];
                    printBody(request, isHeader, headerAppending, postContents);
                    break;
                }
                if (Objects.equals(line, "{")) {
                    isHeader = true;
                }
                if (isHeader) {
                    log.info(line);
                }
            }
        }
    }

    private void writeHeader(String request, DataOutputStream out) throws IOException {
        out.writeBytes(request +" /" + path + " HTTP/1.1\nHost:" + MainClass.getUrl() +
            "\nUser-Agent: curl/7.68.0\n");
    }


}