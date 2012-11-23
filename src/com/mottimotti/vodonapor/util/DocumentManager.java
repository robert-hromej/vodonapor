package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.controllers.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class DocumentManager {

    private final String filePath;

    public DocumentManager(String filePath) {
        this.filePath = filePath;
    }

    protected JSONObject open() throws IOException, JSONException {
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

        return json;
    }

    public void save(Document document) {
        try {
            InputStream is = getInputStream(document.toJson());

            File file = new File(filePath);

            if (!file.exists()) file.createNewFile();

            FileOutputStream os = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = is.read(buffer)) != -1) os.write(buffer, 0, numRead);
            os.close();
            is.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
//            TODO alert dialog
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            TODO alert dialog
        } catch (IOException e) {
            e.printStackTrace();
//            TODO alert dialog
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private InputStream getInputStream(JSONObject json) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(json.toString().getBytes("UTF-8"));
    }

    public void openDocument(Document document) {
        try {
            JSONObject json = open();

            if (json == null) return;

            document.load(json);
        } catch (IOException e) {
            e.printStackTrace();
//            TODO alert dialog
        } catch (JSONException e) {
            e.printStackTrace();
//            TODO alert dialog
        }
    }
}
