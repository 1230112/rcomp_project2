package org.shared.board.app.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

class HttpRequest implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private String baseFolder;

    public HttpRequest(Socket var1, String var2) {
        this.s = var1;
        this.baseFolder = var2;
    }

    public void run() {
        try {
            this.sOut = new DataOutputStream(this.s.getOutputStream());
            this.sIn = new DataInputStream(this.s.getInputStream());
        } catch (IOException var4) {
            System.out.println("Data Stream IOException");
        }

        String var1 = HTTP.readLineCRLF(this.sIn);
        if (var1.startsWith("POST /upload")) {
            this.processPostUpload();
        } else if (var1.startsWith("POST /list")) {
            this.processPostList();
        } else {
            this.processGet(var1);
        }

        try {
            this.s.close();
        } catch (IOException var3) {
            System.out.println("CLOSE IOException");
        }

    }

    void processGet(String var1) {
        String var2;
        do {
            var2 = HTTP.readLineCRLF(this.sIn);
        } while(var2.length() > 0);

        String var3 = var1.split(" ")[1];
        if (var3.compareTo("/") == 0) {
            var3 = "/index.html";
        }

        String var4 = this.baseFolder + var3;
        HTTP.sendHttpFileResponse(this.sOut, (String)null, var4);
    }

    void processPostUpload() {
        String var8 = "Content-Disposition: form-data; name=\"filename\"; filename=\"";
        byte[] var11 = new byte[300];
        int var7 = 0;
        String var2 = null;

        String var1;
        do {
            var1 = HTTP.readLineCRLF(this.sIn);
            if (var1.startsWith("Content-Length: ")) {
                var7 = Integer.parseInt(var1.split(" ")[1]);
            } else if (var1.startsWith("Content-Type: multipart/form-data; boundary=")) {
                var2 = var1.split("=")[1];
            }
        } while(var1.length() > 0);

        if (var7 == 0) {
            this.replyPostError("Content-Length: expected and not found");
        } else if (var2 == null) {
            this.replyPostError("Content-Type: multipart/form-data; expected and not found");
        } else {
            var1 = HTTP.readLineCRLF(this.sIn);
            if (!var1.endsWith(var2)) {
                this.replyPostError("Multipart separator expected and not found");
            } else {
                var7 = var7 - var1.length() - 2;
                String var3 = "";

                do {
                    var1 = HTTP.readLineCRLF(this.sIn);
                    var7 = var7 - var1.length() - 2;
                    if (var1.startsWith(var8)) {
                        var3 = var1.split("=")[2];
                        var3 = var3.substring(1, var3.length() - 1);
                    }
                } while(var1.length() > 0);

                try {
                    int var5;
                    if (var3.length() == 0) {
                        do {
                            var5 = this.sIn.read(var11, 0, 300);
                            var7 -= var5;
                        } while(var7 > 0);

                        this.replyPostError("Content-Disposition: form-data; expected and not found (NO FILENAME)");
                        return;
                    }

                    String var4 = this.baseFolder + "/" + var3;
                    File var9 = new File(var4);
                    FileOutputStream var10 = new FileOutputStream(var9);
                    var7 = var7 - var2.length() - 6;

                    do {
                        int var6;
                        if (var7 > 300) {
                            var6 = 300;
                        } else {
                            var6 = var7;
                        }

                        var5 = this.sIn.read(var11, 0, var6);
                        var10.write(var11, 0, var5);
                        var7 -= var5;
                    } while(var7 > 0);

                    var10.close();
                    var1 = HTTP.readLineCRLF(this.sIn);
                } catch (IOException var13) {
                    System.out.println("IOException");
                }

                this.replyPostList();
            }
        }
    }

    void processPostList() {
        String var1;
        do {
            var1 = HTTP.readLineCRLF(this.sIn);
        } while(var1.length() > 0);

        this.replyPostList();
    }

    void replyPostList() {
        String var1 = "<html><head><title>File List</title></head><body><h1>File List:</h1><big><ul>";
        String var2 = "</ul></big><hr><p><a href=/>BACK</a></body></html>";
        File var4 = new File("www/");
        String var3 = var1;
        File[] var5 = var4.listFiles();
        File[] var6 = var5;
        int var7 = var5.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            File var9 = var6[var8];
            if (var9.isFile()) {
                var3 = var3 + "<li><a href=/" + var9.getName() + ">" + var9.getName() + "</a>";
            }
        }

        var3 = var3 + var2;
        HTTP.sendHttpStringResponse(this.sOut, "200 Ok", "text/html", var3);
    }

    void replyPostError(String var1) {
        HTTP.sendHttpStringResponse(this.sOut, "500 Internal Server Error", "text/html", "<html><head><title>Server Error</title></head><body><center><img src=500.png><br>(500.png)</center><h1>Server error on POST</h1><p>ERROR: " + var1 + "<hr><p><a href=/>BACK</a></body></html>");
    }
}
