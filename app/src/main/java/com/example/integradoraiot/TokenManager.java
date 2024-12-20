package com.example.integradoraiot;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String PREF_NAME = "AuthPreferences";
    private static final String TOKEN_KEY = "AuthToken";
    private final SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    // Método para eliminar el token
    public void removeToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply();
    }
}


