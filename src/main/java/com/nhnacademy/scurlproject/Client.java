package com.nhnacademy.scurlproject;

import com.nhnacademy.scurlproject.args.Args;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {
    private static final Log log = LogFactory.getLog(Client.class);

    public void printBody(String url, int port, String path, boolean isHeader,
                          String headerAppending) throws IOException {
        String line;
        Args args = new Args();
        Socket socket = new Socket(url, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        if (headerAppending != null) {
            out.writeBytes("GET /" + path + " HTTP/1.1\nHost:" + url +
                "\nUser-Agent: curl/7.68.0\n"+ headerAppending +"\n\n");
        } else {
            out.writeBytes(
                "GET /" + path + " HTTP/1.1\nHost:" + url + "\nUser-Agent: curl/7.68.0\n\n");
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
