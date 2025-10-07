package com.quickcapes.utils;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.nio.channels.*;

public class Downloader {

    private final String apiUrl;
    private final File localDir;

    public Downloader(String apiUrl, File localDir) {
        this.apiUrl = apiUrl;
        this.localDir = localDir;
    }

    public void downloadAll() {
        new Thread(() -> {
            try {
                System.out.println("[QuickCapes] Fetching folder structure...");
                downloadRecursive(apiUrl, localDir);
                System.out.println("[QuickCapes] Download complete!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void downloadRecursive(String apiUrl, File localDir) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "QuickCapes");
        connection.connect();

        if (connection.getResponseCode() != 200) {
            throw new IOException("Failed to fetch " + apiUrl + ": " + connection.getResponseMessage());
        }

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            JsonParser parser = new JsonParser();
            JsonArray contents = parser.parse(reader).getAsJsonArray();

            for (JsonElement element : contents) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();
                String name = obj.get("name").getAsString();

                if (type.equals("file")) {
                    String downloadUrl = obj.get("download_url").getAsString();
                    File destFile = new File(localDir, name);
                    destFile.getParentFile().mkdirs();
                    System.out.println("[QuickCapes] Downloading " + downloadUrl);
                    downloadFile(downloadUrl, destFile);
                } else if (type.equals("dir")) {
                    String subDirApiUrl = obj.get("url").getAsString();
                    File subLocalDir = new File(localDir, name);
                    subLocalDir.mkdirs();
                    downloadRecursive(subDirApiUrl, subLocalDir);
                }
            }
        }
    }

    private void downloadFile(String urlStr, File destination) throws IOException {
        URL url = new URL(urlStr);
        try (ReadableByteChannel rbc = Channels.newChannel(url.openStream());
             FileOutputStream fos = new FileOutputStream(destination)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
}
