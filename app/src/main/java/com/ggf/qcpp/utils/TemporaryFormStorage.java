package com.ggf.qcpp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class TemporaryFormStorage {

    private static final String PREF_NAME = "temporary_form_storage";

    /**
     * Simpan data sementara (draft) dalam bentuk JSON
     *
     * @param context
     * @param key identifier unik untuk tiap form, misalnya "draft_chopper"
     * @param data object model yang mau disimpan
     */
    public static void saveDraft(Context context, String key, Object data) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            String json = new Gson().toJson(data);
            editor.putString(key, json);
            editor.apply();
            Log.d("TemporaryFormStorage", "Draft saved: " + key);
        } catch (Exception e) {
            Log.e("TemporaryFormStorage", "Error saving draft", e);
        }
    }

    /**
     * Load data draft
     *
     * @param context
     * @param key identifier unik
     * @param clazz class model target
     * @param <T> type
     * @return object yang sudah diload atau null
     */
    public static <T> T loadDraft(Context context, String key, Class<T> clazz) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            String json = prefs.getString(key, null);
            if (json != null) {
                return new Gson().fromJson(json, clazz);
            }
        } catch (Exception e) {
            Log.e("TemporaryFormStorage", "Error loading draft", e);
        }
        return null;
    }

    /**
     * Hapus draft
     */
    public static void clearDraft(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
        Log.d("TemporaryFormStorage", "Draft cleared: " + key);
    }
}
