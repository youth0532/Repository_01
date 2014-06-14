package com.huawei.screenonreceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScreenCountActivity extends Activity implements OnClickListener{

	private static final String TAG = "Screen";
	
	private static final String ACTION_START_SERVICE = "intent.action.START_SERVICE";
	
	private TextView mTvScreenOn = null;
	private TextView mTvScreenOff = null;
	private TextView mTime = null;
	private Button mReset = null;
	
	SharedPreferences preference;
	SharedPreferences.Editor editor;
	
	int mScreenOn = 0;
	int mScreenOff = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_count);
		mTvScreenOn = (TextView)findViewById(R.id.count_poweron);
		mTvScreenOff = (TextView)findViewById(R.id.count_poweroff);
		mTime = (TextView)findViewById(R.id.tv_time);
		mReset = (Button)findViewById(R.id.btn_reset);
		mReset.setOnClickListener(this);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		Log.d(TAG, "width : " + width + ", height : " + height);
		
		preference = getSharedPreferences("record_file", MODE_PRIVATE);
		editor = preference.edit();
		
		Intent intent = new Intent(ACTION_START_SERVICE);
		startService(intent);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onResume");
		readPreference();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		Log.d(TAG, "" + format.format(cal.getTime()));
		mTime.setText(format.format(cal.getTime()));
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_count, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_reset:
			editor.putInt("count_screen_off", 0);
			editor.putInt("count_screen_on", 0);
			editor.commit();
			readPreference();
			break;
	    default:
				break;
		}
	}
	
	void readPreference() {
		mScreenOff = preference.getInt("count_screen_off", 0);
		mScreenOn = preference.getInt("count_screen_on", 0);
		mTvScreenOff.setText(String.valueOf(mScreenOff));
		mTvScreenOn.setText(String.valueOf(mScreenOn));	
	}

}
