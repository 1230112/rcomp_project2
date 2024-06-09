package org.shared.board.app.http;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class HTTP {
    private static final String HTTP_VERSION = "HTTP/1.0";
    private static final String HTTP_CONNECTION_CLOSE = "Connection: close";
    private static final String HTTP_CRLF = "\r\n";

    HTTP() {
    }

    static String readLineCRLF(DataInputStream var0) {
        byte[] var1 = new byte[300];
        int var2 = 0;

        try {
            while(true) {
                var0.read(var1, var2, 1);
                if (var1[var2] == 10) {
                    return new String(var1, 0, var2);
                }

                if (var1[var2] != 13) {
                    ++var2;
                }
            }
        } catch (IOException var4) {
            System.out.println("READ IOException");
            return null;
        }
    }

    static void writeLineCRLF(DataOutputStream var0, String var1) {
        try {
            var0.write(var1.getBytes(), 0, (byte)var1.length());
            var0.write("\r\n".getBytes(), 0, 2);
        } catch (IOException var3) {
            System.out.println("WRITE IOException");
        }

    }

    static void sendHttpResponseHeader(DataOutputStream var0, String var1, String var2, int var3) {
        writeLineCRLF(var0, "HTTP/1.0 " + var1);
        writeLineCRLF(var0, "Content-Type: " + var2);
        writeLineCRLF(var0, "Content-Length: " + var3);
        writeLineCRLF(var0, "Connection: close");
        writeLineCRLF(var0, "");
    }

    static void sendHttpResponse(DataOutputStream var0, String var1, String var2, byte[] var3, int var4) {
        sendHttpResponseHeader(var0, var1, var2, var4);

        try {
            var0.write(var3, 0, var4);
        } catch (IOException var6) {
            System.out.println("IOException");
        }

    }

    static void sendHttpStringResponse(DataOutputStream var0, String var1, String var2, String var3) {
        sendHttpResponse(var0, var1, var2, var3.getBytes(), var3.length());
    }

    static void sendHttpFileResponse(DataOutputStream var0, String var1, String var2) {
        String var3 = "200 Ok";
        String var4 = "text/html";
        File var5 = new File(var2);
        if (!var5.exists()) {
            sendHttpStringResponse(var0, "404 Not Found", var4, "<html><body><h1>404 File not found</h1></body></html>");
        } else {
            if (var2.endsWith(".pdf")) {
                var4 = "application/pdf";
            } else if (var2.endsWith(".js")) {
                var4 = "application/javascript";
            } else if (var2.endsWith(".txt")) {
                var4 = "text/plain";
            } else if (var2.endsWith(".gif")) {
                var4 = "image/gif";
            } else if (var2.endsWith(".png")) {
                var4 = "image/png";
            }

            if (var1 != null) {
                var3 = var1;
            }

            int var6 = (int)var5.length();
            sendHttpResponseHeader(var0, var3, var4, var6);
            byte[] var7 = new byte[300];

            try {
                BufferedInputStream var10 = new BufferedInputStream(new FileInputStream(var5));

                do {
                    int var9;
                    if (var6 > 300) {
                        var9 = 300;
                    } else {
                        var9 = var6;
                    }

                    try {
                        int var8 = var10.read(var7, 0, var9);
                        var6 -= var8;
                        var0.write(var7, 0, var8);
                    } catch (IOException var12) {
                        System.out.println("IOException");
                    }
                } while(var6 > 0);
            } catch (FileNotFoundException var13) {
                System.out.println("FILE OPEN FileNotFoundException");
            }

        }
    }
}
