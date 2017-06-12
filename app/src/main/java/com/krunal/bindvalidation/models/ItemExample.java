package com.krunal.bindvalidation.models;

import android.content.Intent;

public class ItemExample {
    public final String message;
    public final Intent intent;
    public final String title;

    public ItemExample(String title, String message, Intent intent) {
        this.title = title;
        this.message = message;
        this.intent = intent;
    }
}
