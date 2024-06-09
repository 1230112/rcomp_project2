package org.shared.board.app.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class HttpServerFormFileUpload {
    private static final String BASE_FOLDER = "www";
    private static ServerSocket sock;

    HttpServerFormFileUpload() {
    }

    public static void main(String[] var0) throws Exception {
        if (var0.length != 1) {
            System.out.println("Local port number required at the command line.");
            System.exit(1);
        }

        try {
            sock = new ServerSocket(Integer.parseInt(var0[0]));
        } catch (IOException var3) {
            System.out.println("Failed to open local port " + var0[0]);
            System.exit(1);
        }

        while(true) {
            Socket var1 = sock.accept();
            (new Thread(new HttpRequest(var1, "www"))).start();
        }
    }
}
