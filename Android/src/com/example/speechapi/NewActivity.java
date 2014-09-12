package com.example.speechapi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


import com.example.http.HttpOperations;
import com.sogou.speech.listener.RecognizerListener;
import com.sogou.speech.ui.RecognizerDialog;

public class NewActivity extends Activity {

	private Button btn;
	private EditText mResultsText;
	private RadioGroup mContinuousGroup;
	private RadioGroup mAutoStopGroup;
	private RecognizerDialog dialog = null;

	// applied APP ID
	private String appId = "DF465202";
	// applied Access Key
	private String accessKey = "EA382D6A";
	// set isAutoStop true in order to stop recording automatically,
	// default value is true
	private boolean isAutoStop = true;
	// set isContinuous true in order to get real-time partial recognized
	// results, default value is false
	private boolean isContinuous = false;

	// Save the results of recognizing
	private List<List<String>> wholeResult;

	private RecognizerListener listener = new RecognizerListener() {

		@Override
		// call it when the last package has received valid results
		public void onResults(List<List<String>> results) {
			addToWholeResult(results);
			showResults(mResultsText, results);
			String s = mResultsText.getText().toString();
			System.out.println(wholeResult.toString());
			try {
//				HttpOperations.sendGet(s);
				HttpOperations.sendPost(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		// call it when some error occurs
		public void onError(int err) {
			// TODO: show error code for users when necessary
		}

		@Override
		// call it when the former packages have received valid results
		public void onPartResults(List<List<String>> results) {
			addToWholeResult(results);
			showResults(mResultsText, results);
		}

		@Override
		// call it when the last package has no valid result but the former
		// packages have some valid results
		public void onQuitQuietly(int err) {
			// TODO: this is not an error, no need to show it for users
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dialog = new RecognizerDialog(NewActivity.this, appId, accessKey,
				isAutoStop, isContinuous);
		dialog.setlistener(listener);
		setContentView(R.layout.activity_new);
		bindViews();
		setListeners();
		start();
		
	}
	

	@Override
	protected void onDestroy() {
		if (dialog != null)
			dialog.dismiss();
		super.onDestroy();
	}

	private void bindViews() {
		mResultsText = (EditText) findViewById(R.id.text);
		btn = (Button) findViewById(R.id.button1);
		mAutoStopGroup = (RadioGroup) findViewById(R.id.group_isautostop);
		mContinuousGroup = (RadioGroup) findViewById(R.id.group_iscontinuous);
	}
	public void start() {
		wholeResult = new ArrayList<List<String>>();
		mResultsText.setText("");
		dialog.setAutoStop(isAutoStop);
		dialog.setContinuous(isContinuous);
		dialog.show();
	}

	private void setListeners() {
		// Click Listener
		btn.setOnClickListener(new OnClickListener() {
			
			//鍚姩褰曢煶澶�			@Override
			public void onClick(View v) {
				wholeResult = new ArrayList<List<String>>();
				mResultsText.setText("");
				dialog.setAutoStop(isAutoStop);
				dialog.setContinuous(isContinuous);
				dialog.show();
			}
		});

		mAutoStopGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.auto_stop_true:
							isAutoStop = true;
							break;
						case R.id.auto_stop_false:
							isAutoStop = false;
							break;
						}
					}
				});

		mContinuousGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.continuous_true:
							isContinuous = true;
							break;
						case R.id.continuous_false:
							isContinuous = false;
							break;
						}
					}
				});
	}

	// add recognized results to the wholeResult
	private void addToWholeResult(List<List<String>> ds) {
		if (ds != null) {
			int tmpAmount = ds.size();
			for (int i = 0; i < tmpAmount; i++) {
				List<String> tmpS = new ArrayList<String>();

				for (int j = 0; j < ds.get(i).size(); j++) {
					tmpS.add(ds.get(i).get(j));
				}
				wholeResult.add(tmpS);
			}
		}
	}

	// show wholeResult for users when necessary
	private void showResults(EditText textView, List<List<String>> results) {
		String result = "";
		for (List<String> list : results) {
			result += list.get(0);
		}
		textView.append(result);
	}

	// the functions below is useful only for UI design and it is never called
	// in the API
	/**
	 * Show candidates for the clicked sentence, the candidates can be
	 * List<String> defined in Activity
	 */
	public void onClientClick(int sentenceSquenceNo) {
		// TODO: when use continuous recognition and allow user modify displayed
		// sentences with candidates, realize it
	}

	/**
	 * replace the displayed sentence with the chosen candidate, the replaced
	 * parameter is defined in Activity
	 */
	public void onClientChoose(int sentenceSequenceNo, int resultOrderId) {
		// TODO: when use continuous recognition and allow user modify displayed
		// sentences with candidates, realize it
	}

	/**
	 * clear its candidates when the displayed sentence is modified, the
	 * candidates is defined in Activity
	 */
	public void onClientUpdate(int sentenceSequenceNo, int resultOrderId) {
		// TODO: when use continuous recognition and allow user modify displayed
		// sentences with candidates, realize it
	}
}
