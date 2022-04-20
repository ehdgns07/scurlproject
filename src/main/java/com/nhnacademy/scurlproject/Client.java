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
    public void printGetBody(String request, boolean isHeader,
                             List<String> headerAppending) throws IOException {
        String line;
        Args args = new Args();
        Socket socket = new Socket(MainClass.getUrl(), MainClass.getPort());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        if(request==null){
            request="GET";
        }

        if (!headerAppending.isEmpty()) {
            out.writeBytes(request +" /" + MainClass.getPath() + " HTTP/1.1\nHost:" + MainClass.getUrl() +
                "\nUser-Agent: curl/7.68.0\n");
            for (String header : headerAppending) {
                out.writeBytes(header + "\n");
            }
            out.writeBytes("\n");

        } else {
            out.writeBytes(
                request + " /" + MainClass.getPath() + " HTTP/1.1\nHost:" + MainClass.getUrl() + "\nUser-Agent: curl/7.68.0\n\n");
        }
        out.flush();

        try (BufferedReader read = new BufferedReader(new InputStreamReader(in))) {
            while ((line = read.readLine()) != null) {
                if (Objects.equals(line, "{")) {
                    isHeader = true;
                }
                if (isHeader) {
                    log.info(line);
                }

            }
        }
    }


}
