package com.joshbgold.catroulette;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChoicesActivity extends MainActivity {

    private RadioGroup radioGroup;
    private String choice;
    private RadioButton allButton;
    private RadioButton dubstepButton;
    private RadioButton maruButton;
    private RadioButton noNoNoButton;
    private RadioButton nyanButton;
    private RadioButton roombaButton;
    private RadioButton simonButton;
    private RadioButton venusButton;
    private RadioButton yodelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_choices);

        final Button returnButton = (Button) findViewById(R.id.returnButton);

         /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //radioGroup.clearCheck();

        allButton = (RadioButton) findViewById(R.id.AllButton);
        dubstepButton = (RadioButton) findViewById(R.id.DubstepButton);
        maruButton = (RadioButton) findViewById(R.id.MaruButton);
        noNoNoButton = (RadioButton) findViewById(R.id.NoNoNoButton);
        nyanButton = (RadioButton) findViewById(R.id.NyanButton);
        roombaButton = (RadioButton) findViewById(R.id.RoombaButton);
        simonButton = (RadioButton) findViewById(R.id.SimonButton);
        venusButton = (RadioButton) findViewById(R.id.VenusButton);
        yodelButton = (RadioButton) findViewById(R.id.YodelButton);

        try {
            setRadioGroupCheckMark();
        }
        catch (Exception exception){
            Log.d("Prefs did not load", "choice variable may be null. Not necessarily an issue though.");
        }

         /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    String selectedChoice = Integer.toString(selectedId);

                    if (selectedChoice.equals("2131492929")){
                        choice = "All";
                    }
                    else if (selectedChoice.equals("2131492930")){
                        choice = "Dubstep";
                    }
                    else if (selectedChoice.equals("2131492931")){
                        choice = "Maru";
                    }
                    else if (selectedChoice.equals("2131492933")){
                        choice = "NoNoNo";
                    }
                    else if (selectedChoice.equals("2131492934")){
                        choice = "Nyan";
                    }
                    else if (selectedChoice.equals("2131492935")){
                        choice = "Roomba";
                    }
                    else if (selectedChoice.equals("2131492936")){
                        choice = "Simon";
                    }
                    else if (selectedChoice.equals("2131492937")){
                        choice = "Venus";
                    }
                    else if (selectedChoice.equals("2131492938")){
                        choice = "Yodel";
                    }
                    else {
                        choice = "All";
                    }

                   // Toast.makeText(ChoicesActivity.this, "You chose " + choice + " " + selectedChoice, Toast.LENGTH_SHORT).show();
                    savePrefs("Choice", choice);
                }

            }
        });

        View.OnClickListener leave = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefs("Choice", choice);
                finish();
            }
        };

        returnButton.setOnClickListener(leave);
    }

    private void setRadioGroupCheckMark() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        choice = loadPrefs("Choice", choice);

        if (choice.equals("All")){
            allButton.setChecked(true);
        }
        else if (choice.equals("Dubstep")){
            dubstepButton.setChecked(true);
        }
        else if (choice.equals("Maru")){
            maruButton.setChecked(true);
        }
        else if (choice.equals("NoNoNo")){
            noNoNoButton.setChecked(true);
        }
        else if (choice.equals("Nyan")){
            nyanButton.setChecked(true);
        }
        else if (choice.equals("Roomba")){
            roombaButton.setChecked(true);
        }
        else if (choice.equals("Simon")){
            simonButton.setChecked(true);
        }
        else if (choice.equals("Venus")){
            venusButton.setChecked(true);
        }
        else if (choice.equals("Yodel")){
            yodelButton.setChecked(true);
        }
    }

    //save prefs
    public void savePrefs(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //get prefs
    public String loadPrefs(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String data = sharedPreferences.getString(key, value);
        return data;
    }
}
