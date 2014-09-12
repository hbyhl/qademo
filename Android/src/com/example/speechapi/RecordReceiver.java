package com.example.speechapi;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecordReceiver extends BroadcastReceiver {

	private Intent intentServ;
	private Context context;
	private boolean IsRecord;
    private String number;
	// 获得首选项

	@Override
	public void onReceive(Context context, Intent intent) {
		
 
		intentServ = new Intent("com.giaour.FORVANILLA");
		this.context = context;
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			
			 number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			// 如果是去电（拨出）
			System.out.println("拨出 " + number);
		} else {
             number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Service.TELEPHONY_SERVICE);
			tm.listen(new MyPhoneStateListener(),
					PhoneStateListener.LISTEN_CALL_STATE);
		}
	}

	class MyPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:

				System.out.println("挂断");
				if (IsRecord) {
					intentServ.putExtra("Audio", "OFF");
					context.startService((intentServ));
					IsRecord = false;
					
				}
				break;

			case TelephonyManager.CALL_STATE_OFFHOOK:

				System.out.println("接听");
				if (!IsRecord) {
					intentServ.putExtra("Audio", "ON");
					context.startService(intentServ);
					IsRecord = true;
				}
				break;

			case TelephonyManager.CALL_STATE_RINGING:
            
				System.out.println("响铃");

				break;

			}
		}
	}
}
