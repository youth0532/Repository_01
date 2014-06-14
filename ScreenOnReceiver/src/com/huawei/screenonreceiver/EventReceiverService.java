package com.huawei.screenonreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class EventReceiverService extends Service {
	
	private static final String TAG = "Screen";
	
	SharedPreferences preference;
	SharedPreferences.Editor editor;
	
	
	
	BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			String action = arg1.getAction();
			Log.d(TAG, "@@@@@ receive : " + arg1.getAction());
			
			if (action.equals("android.intent.action.SCREEN_OFF")) {
				Log.d(TAG, "receive screen off");
				int oldValue = preference.getInt("count_screen_off", 0);
				editor.putInt("count_screen_off", oldValue+1);
			} else if (action.equals("android.intent.action.SCREEN_ON")) {
				Log.d(TAG, "receive screen on");
				int oldValue = preference.getInt("count_screen_on", 0);
				editor.putInt("count_screen_on", oldValue+1);
			}
			
			editor.commit();
		}
	};
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate");
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction("android.intent.action.SCREEN_OFF");
		mFilter.addAction("android.intent.action.SCREEN_ON");
		registerReceiver(mReceiver, mFilter);
		
		preference = getSharedPreferences("record_file", MODE_PRIVATE);
		editor = preference.edit();
		
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

}
