package communicatorproject.studio.com.communicatorproject.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class preferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String ARCHIEVE_NAME = "Communicator.preferences";
    private final int MODE = 0;
    private String NAME_KEY = "name";
    private String PHONE_KEY = "phone";
    private String TOKEN_KEY = "token";

    public preferences(Context contextParameters){

        context = contextParameters;
        preferences = context.getSharedPreferences(ARCHIEVE_NAME, MODE);
        editor = preferences.edit();

    }

    public void saveUserPreferences(String name, String phone, String token){

        editor.putString(NAME_KEY, name);
        editor.putString(PHONE_KEY, phone);
        editor.putString(TOKEN_KEY, token);

        editor.commit();

    }

    public HashMap<String, String> getUserData(){

        HashMap<String, String> userData = new HashMap<>();

        userData.put(NAME_KEY, preferences.getString(NAME_KEY, null));

        userData.put(PHONE_KEY, preferences.getString(PHONE_KEY, null));

        userData.put(TOKEN_KEY, preferences.getString(TOKEN_KEY, null));

        return userData;

    }
}
