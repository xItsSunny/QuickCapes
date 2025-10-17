package com.quickcapes.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.quickcapes.QuickCapes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AutoUpdater {
    public static void init() {
        QuickCapes.getExecutor().execute(() -> {
            if (QuickCapes.config.isShowupdate()) {
                @NotNull Result result = checkVersion();
                switch (result.getType()) {
                    case FAIL:
                        Messenger.sendMessageAnyWay(ChatFormatting.RED + "Fail to check latest version.");
                        break;
                    case OLD:
                        Messenger.sendMessageAnyWay(ChatFormatting.RED + "You are not at latest version." +
                                ChatFormatting.RESET + " current: " + QuickCapes.VERSION + "  latest: " + result.getLatestVersion());
                        Messenger.sendMessageAnyWay("Run command /qcupdate to download latest version.");
                        break;
                }
            }
        });
    }

    public static void update() {
        Messenger.sendMessage("Fetching download link...");
        Result result = checkVersion();

        switch (result.getType()) {
            case FAIL:
                Messenger.sendMessage(ChatFormatting.RED + "Fail to check latest version.");
                return;
            case LATEST:
                Messenger.sendMessage(ChatFormatting.GREEN + "You are at latest version! " + result.getLatestVersion());
                return;
        }

        Messenger.sendMessage("Downloading...");
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .build()
                ).build()) {
            HttpGet httpGet = new HttpGet(result.getLatestURL());
            CloseableHttpResponse response = httpClient.execute(httpGet);

            InputStream input = response.getEntity().getContent();
            File file = new File(QuickCapes.mc.mcDataDir + File.separator + "mods", "quickcapes.jar");
            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);

            byte[] buffer = new byte[10240];
            int ch;
            while ((ch = input.read(buffer)) != -1) {
                output.write(buffer, 0, ch);
            }

            input.close();
            output.flush();
            output.close();
            Messenger.sendMessage(ChatFormatting.GREEN + "Download success! Restart client to finish update.");
        } catch (Exception e) {
            Messenger.sendMessage(ChatFormatting.RED + "Fail to download latest version.");
        }
    }

    @Contract(" -> new")
    private static @NotNull Result checkVersion() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet("https://api.github.com/repos/xItsSunny/QuickCapes/releases/latest");
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {
                Messenger.sendMessage(ChatFormatting.RED + "Fail to check latest version.");
                return new Result(Result.Type.FAIL, null, null);
            }

            String jsonResult = EntityUtils.toString(response.getEntity());
            JsonObject jsonObject = new Gson().fromJson(jsonResult, JsonObject.class);

            String ver = jsonObject.get("tag_name").getAsString().substring(1);
            String url = jsonObject.getAsJsonArray("assets").get(0).getAsJsonObject().get("browser_download_url").getAsString();

            if (ver.equals(QuickCapes.VERSION)) {
                return new Result(Result.Type.LATEST, url, ver);
            } else {
                return new Result(Result.Type.OLD, url, ver);
            }
        } catch (Exception ignored) {
        }

        return new Result(Result.Type.FAIL, null, null);
    }

    @Getter
    @AllArgsConstructor
    private static class Result {
        private final Type type;
        private final String latestURL;
        private final String latestVersion;

        private enum Type {
            LATEST,
            FAIL,
            OLD
        }
    }
}
