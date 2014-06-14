package com.huawei.screenonreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SreenEventReceiver extends BroadcastReceiver {
	
	private static final String TAG = "Screen";
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		String action = arg1.getAction();
		Log.d(TAG, " --------- receive broadcats : " + action);
	}

}
