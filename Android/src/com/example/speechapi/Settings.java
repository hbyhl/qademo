package com.example.speechapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class Settings  extends PreferenceActivity implements OnPreferenceChangeListener{
	
	private static final String NAME = "setting";
	private static final String KEY_BOOL = "check";
	private CheckBoxPreference checkBoxPreference;
	boolean value;
	// �����ѡ��
	private SharedPreferences preferences;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	//���ز����ļ�
		addPreferencesFromResource(R.xml.settings);
		preferences = getSharedPreferences(NAME, MODE_APPEND);
		checkBoxPreference = (CheckBoxPreference) findPreference("my_check");
		checkBoxPreference.setOnPreferenceChangeListener(this);
    }
      
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		value = (Boolean) newValue;
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(KEY_BOOL, value);
		editor.commit();
		return true;
	}
}
