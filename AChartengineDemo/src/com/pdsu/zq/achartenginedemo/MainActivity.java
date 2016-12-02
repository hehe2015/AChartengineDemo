package com.pdsu.zq.achartenginedemo;

import java.util.LinkedHashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
		
		LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>();
		
		map.put("游戏爱好者1", 90.0);
		map.put("游戏爱好者2", 80.0);
		map.put("游戏爱好者3", 70.0);
		map.put("游戏爱好者4", 60.0);
		map.put("游戏爱好者5", 50.0);
		
		rl.addView(new AChartExamole(map).getView(MainActivity.this));

	}

}
