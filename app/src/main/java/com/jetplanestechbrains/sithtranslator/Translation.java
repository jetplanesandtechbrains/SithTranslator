package com.jetplanestechbrains.sithtranslator;

import org.json.JSONObject;

/**
        "contents": {
                "translated": "Nu went kia coruscant which meo ten parsekas salini!",
                "text": "I went to coruscant which was ten parsec away!",
                "translation": "sith"
                }
                }
*/

public class Translation {

    private String translated;

    private String text;

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFields(JSONObject jsonObject) {
        translated = jsonObject.optString("translated");
        text = jsonObject.optString("text");
    }
}
