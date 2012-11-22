package com.mottimotti.vodonapor.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {

    private String filePath;

    private List<JSONObject> history;

    private int currentVersion;

    public DocumentManager(String filePath) {
        this.filePath = filePath;
        history = new ArrayList<JSONObject>();
        currentVersion = -1;
    }

    public JSONObject open() throws IOException, JSONException {
        history.clear();

        JSONObject json = null;

        File file = new File(filePath);

        if (file.exists() && file.isFile() && file.canRead()) {
            InputStream is = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) sb.append(line + "n");
            is.close();
            json = new JSONObject(sb.toString());
        }

        remember(json);

        return json;
    }

    public boolean save() throws IOException {
        InputStream is = getInputStream(history.get(currentVersion));

        File file = new File(filePath);

        if (!file.exists()) file.createNewFile();

        FileOutputStream os = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = is.read(buffer)) != -1) os.write(buffer, 0, numRead);
        os.close();
        is.close();

        return false;
    }

    public JSONObject undo() {
        if (currentVersion < 1) return null;

        return history.get(--currentVersion);
    }

    public JSONObject redo() {
        if (currentVersion == getLastIndex()) return null;

        return history.get(++currentVersion);
    }

    public void remember(JSONObject json) {
        while (getLastIndex() > currentVersion) history.remove(getLastIndex());

        history.add(json);

        currentVersion = history.indexOf(json);
    }

    private int getLastIndex() {
        return history.size() - 1;
    }

    private InputStream getInputStream(JSONObject json) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(json.toString().getBytes("UTF-8"));
    }
}
