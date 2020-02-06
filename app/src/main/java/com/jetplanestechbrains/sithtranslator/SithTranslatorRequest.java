package com.jetplanestechbrains.sithtranslator;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SithTranslatorRequest extends AsyncTask<String, Void, String> {

    private OkHttpClient client = new OkHttpClient();

    private AsyncResponse asyncResponse;

    private String englishToSithText;

    SithTranslatorRequest(String translation, AsyncResponse output) {
        englishToSithText = translation;
        asyncResponse = output;
    }

    /**
     * Using AsyncTask allows us to make network calls off the main UI thread
     *
     * @return the Sith translated text
     */
    @Override
    protected String doInBackground(String... strings) {
        Request request = new Request.Builder()
                .url(buildURL())
                .get()
                .addHeader("x-rapidapi-host", "sith.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "api-key-here")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject translationJSON = jsonObject.optJSONObject("contents");

            Translation translation = new Translation();
            translation.setFields(translationJSON);

            return translation.getTranslated();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * When API call is complete, update the asyncResponse with the translated text
     *
     * @param string Sith Translation
     */
    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        try {
            if (string != null) {
                asyncResponse.onRequestComplete(string);
            }
        } catch (Exception e) {
            Log.d("SithTranslator", "Sorry, something went wrong!");
        }
    }

    private String buildURL() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("sith.p.rapidapi.com")
                .appendPath("sith.json")
                .appendQueryParameter("text", englishToSithText);
        return builder.build().toString();
    }
}
