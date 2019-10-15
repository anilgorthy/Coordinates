package com.empower.challenge.util;

import android.content.Context;
import android.util.Log;

import com.empower.challenge.model.CoordinatesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CacheUtil {

    private static final String TAG = CacheUtil.class.getSimpleName();
    private static final String CACHED_FILE = "response.json";

    public static void writeJson(Object object, Context context) {
        Type listType = new TypeToken<List<CoordinatesResponse>>() {}.getType();
        File file = new File(context.getCacheDir(), CACHED_FILE);
        OutputStream outputStream = null;
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        try {
            outputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            Log.i(TAG, "JSON is: " + gson.toJson(object, listType));
            gson.toJson(object, bufferedWriter);
            bufferedWriter.close();

        } catch (IOException ioe) {
            Log.e(TAG, "Exception is: " + ioe.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception is: " + e.getMessage());
                }
            }
        }
    }

    static List<CoordinatesResponse> readJson(Context context) {
        Type listType = new TypeToken<List<CoordinatesResponse>>() {}.getType();
        List<CoordinatesResponse> jsonData = null;

        File file = new File(context.getCacheDir(), CACHED_FILE);
        InputStream inputStream = null;
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        try {
            inputStream = new FileInputStream(file);
            InputStreamReader streamReader;
            streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            jsonData = gson.fromJson(streamReader, listType);
            streamReader.close();

        } catch (FileNotFoundException fnfe) {
            Log.e(TAG, "FileNotFoundException e: '" + fnfe);
        } catch (IOException ioe) {
            Log.e(TAG, "IOException e: '" + ioe);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception in finally is: " + e.getMessage());
                }
            }
        }
        return jsonData;
    }

}
