package com.joshbgold.catroulette;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;
import java.util.Random;

/**
 * Plays random cat video from a list of pre-selected best interwebs Cat videos.  Youtube app is required to be installed.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final int REQ_START_STANDALONE_PLAYER = 1;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 2;

    private static String VIDEO_ID = ""; //stores the appropriate cat video list
    private String choice;
    private static final String[] MainVideoList = {

            "xdhLQCYQ-nQ",  //1.  Big box and Maru
            "hPzNl6NKAG0",  //2.  Maru slide
            "6lC2lbeY_rU",  //3.  Maru and hanna
            "gqY-t0la2q4",  //4.  Maru rice cake box
            "el-HyrsjyFY",  //5.  Maru pacifier

            "kxRX6LXDpWs",  //1.  No no no cat

            "aQeIDhz-_eg", //1.  Dubstep cat (AKA Skifcha)
            "P3rWnOyHUAY", //2.  Dubstep cat dancing - funny as

            "2yJgwwDcgV8",  //1. Nyan cat (AKA pop tart cat)
            "PuW23sMVWEs",  //2. Real life Nyan / Pop tart cat
            "m6WMksck87M",  //3. Angel Nyan cat
            "Cu6ZP2Y06-I",  //4. Pop Tart Heart [The Nyan Cat Love Song] - Kaleb Nation
            "lhwx5vInhvk?t=14", //5. Nyan Cat falls in love
            "HdEN2JinZVE",  //6. Nyan Cat Short Version

            "ctarXVlDtXQ",  //1.  Lynx and cat
            "cPtEdCM4On8", //2.  cat drinking water
            "9-Fke4eAdbc", //3.  cat drinking water
            "Of2HU3LGdbo", //4.  cat roomba baby duck
            "tFz1jK6sGIU", //5.  cat with baby
            "0Bmhjf0rKe8", //6.  surprise kitten
            "8-1F-CokXNU", //7.  kitten vs apples
            "MtGog4Cb7RY", //8.  cat through snow
            "-xYeTjeGZ3Q", //9.  nervous cat
            "FlObis7DkIw", //10. cat in bag
            "d7HfWd-TgOs", //11. cat caught stealing
            "Sh-zzb5jLLs", //12. kitty hug
            "OM_soUJ9In0", //13. yodeling
            "DDdU_iIy6XE", //14. Venus
            "INscMGmhmX4", //15. Grumpy cat
            "z3U0udLH974", //16. Convo
            "gIXw-3H48yU", //17. Pallas cat
            "kkwiQmGWK4c", //18. oh long johnson
            "N_uVsmSbgCE", //19. lion hug
            "as59IRSoM9M", //20. P-Funk tail chase
            "zr39jPapmBU", //21. kittens in a line
            "drq_ww7Ytzw", //22. purring cheetah
            "D12k-4cOs0w", //23. cat swiffer ride
            "YPKzhr1mR1Q", //24. catwoman meow
            "z5rXLZfsJT0", //25. cat walking human
            "Q34z5dCmC4M", //26. henri chat noir
            "ovWqEtVVUFs", //27. stealing dog beds
            "wqkW8ebVfAM", //28. attack printer
            "hJwReSTj0IE", //29. asks for pets
            "w9lc_da9IXc", //30. takes glasses off
            "msmC1MwS-LY", //31. wake up human
            "W8XtxpenGdc", //32. sleepy
            "V5Z6jQrjrLg", //33. lil bub
            "mEi0p6m3LYs", //34. Simon's Cat - Wake Up
            "B8TvkyjBGPQ", //35. Cat & Horse
            "rNnG1IfvtUE", //36. Cat in the hat

    };

    private static final String[] DubstepCatVideoList = {
            "aQeIDhz-_eg", //1.  Dubstep cat (AKA Skifcha)
            "P3rWnOyHUAY", //2.  Dubstep cat dancing - funny as
    };

    private static final String[] MaruVideoList = {
            "xdhLQCYQ-nQ",  //1.  Big box and Maru
            "hPzNl6NKAG0",  //2.  Maru slide
            "6lC2lbeY_rU",  //3.  Maru and hanna
            "gqY-t0la2q4",  //4.  Maru rice cake box
            "el-HyrsjyFY",  //5.  Maru pacifier
    };


    private static final String[] NoNoNoVideoList = {
            "kxRX6LXDpWs"  //1.  No no no cat
    };

    private static final String[] NyanVideoList = {
            "2yJgwwDcgV8",  //1. Nyan cat (AKA pop tart cat)
            "PuW23sMVWEs",  //2. Real life Nyan / Pop tart cat
            "m6WMksck87M",  //3. Angel Nyan cat
            "Cu6ZP2Y06-I",  //4. Pop Tart Heart [The Nyan Cat Love Song] - Kaleb Nation
            "lhwx5vInhvk?t=14", //5 Nyan Cat falls in love
            "HdEN2JinZVE",  //6. nyan cat
    };

    private static final String[] RoombaVideoList = {
            "Of2HU3LGdbo" //4.  cat roomba baby duck
    };


    private static final String[] SimonVideoList= {
            "mEi0p6m3LYs", //34. Simon's Cat - Wake Up
    };

    private static final String[] VenusVideoList = {
            "DDdU_iIy6XE", //14. Venus
    };

    private static final String[] YodelVideoList = {
            "OM_soUJ9In0", //1. yodeling
    };


    private Button playVideoButton;
    private EditText startTimeEditText;
    private CheckBox autoplayCheckBox;
    private CheckBox lightboxModeCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standalone_player);

        final Button chooseCatsButton = (Button) findViewById(R.id.Choose_Cats_button);
        final Button exitButton = (Button) findViewById(R.id.exit_button);

        playVideoButton = (Button) findViewById(R.id.start_video_button);
        startTimeEditText = (EditText) findViewById(R.id.start_time_text);
        autoplayCheckBox = (CheckBox) findViewById(R.id.autoplay_checkbox);
        lightboxModeCheckBox = (CheckBox) findViewById(R.id.lightbox_checkbox);

        playVideoButton.setOnClickListener(this);

        View.OnClickListener chooseCats = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCatChoices();
            }
        };

        View.OnClickListener exit = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        chooseCatsButton.setOnClickListener(chooseCats);
        exitButton.setOnClickListener(exit);
    }

    private void startCatChoices(){
        Intent intent = new Intent(MainActivity.this, ChoicesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        int startTimeMillis = parseInt(startTimeEditText.getText().toString()) * 1000;
        boolean autoplay = autoplayCheckBox.isChecked();
        boolean lightboxMode = lightboxModeCheckBox.isChecked();
        boolean connected;

        connected = isConnected();

        if (connected) {

            Intent intent = null;

            //gets a random Youtube cat video from the list
            VIDEO_ID = getCatVideo();

            //play the video with the proper settings included
            if (v == playVideoButton) {
                intent = YouTubeStandalonePlayer.createVideoIntent(
                        this, DeveloperKey.DEVELOPER_KEY, VIDEO_ID, startTimeMillis, autoplay, lightboxMode);
            }

            if (intent != null) {
                if (canResolveIntent(intent)) {
                    startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
                } else {
                    // Could not resolve the intent - must need to install or update the YouTube API service.
                    YouTubeInitializationResult.SERVICE_MISSING
                            .getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
                }
            }
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cat Roulette is sorry...")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("You do not have a mobile or wifi connection with internet service at this time.")
                    .setCancelable(false)
                    .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    //Checks for mobile or wifi connectivity, returns true for connected, false otherwise
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_START_STANDALONE_PLAYER && resultCode != RESULT_OK) {
            YouTubeInitializationResult errorReason =
                    YouTubeStandalonePlayer.getReturnedInitializationResult(data);
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, 0).show();
            } else {
                String errorMessage =
                        String.format(getString(R.string.error_player), errorReason.toString());
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    private int parseInt(String text) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                // fall through
            }
        }
        return 0;
    }

    //gets a random Youtube cat video from the list
    private String getCatVideo() {

        choice = loadPrefs("Choice", choice);  //gets the user's selection from saved preferences
        //Toast.makeText(MainActivity.this, "You chose " + choice, Toast.LENGTH_SHORT).show();

        //choose the correct video list
        if (choice == null){
            int i = new Random().nextInt(MainVideoList.length);
            return MainVideoList[i];
        }
        else if (choice.equals("All")){
            int i = new Random().nextInt(MainVideoList.length);
            return MainVideoList[i];
        }
        else if (choice.equals("Dubstep")){
            int i = new Random().nextInt(DubstepCatVideoList.length);
            return DubstepCatVideoList[i];
        }
        else if (choice.equals("Maru")){
            int i = new Random().nextInt(MaruVideoList.length);
            return MaruVideoList[i];
        }
        else if (choice.equals("NoNoNo")) {
            int i = new Random().nextInt(NoNoNoVideoList.length);
            return NoNoNoVideoList[i];
        }
        else if (choice.equals("Nyan")) {
            int i = new Random().nextInt(NyanVideoList.length);
            return NyanVideoList[i];
        }
        else if (choice.equals("Roomba")) {
            int i = new Random().nextInt(RoombaVideoList.length);
            return RoombaVideoList[i];
        }
        else if (choice.equals("Simon")) {
            int i = new Random().nextInt(SimonVideoList.length);
            return SimonVideoList[i];
        }
        else if (choice.equals("Venus")) {
            int i = new Random().nextInt(VenusVideoList.length);
            return VenusVideoList[i];
        }
        else if (choice.equals("Yodel")) {
            int i = new Random().nextInt(YodelVideoList.length);
            return YodelVideoList[i];
        }
        else {
            int i = new Random().nextInt(MainVideoList.length);
            return MainVideoList[i];
        }
    }

    //save prefs
    public void savePrefs(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //get prefs
    public String loadPrefs(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(key, value);
    }

}