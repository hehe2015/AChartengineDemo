package com.pdsu.zq.achartenginedemo;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AChartExamole {

	// private double[] values = { 0, 0, 0 };

	private LinkedHashMap<String, Double> map;

	/**
	 * 
	 * @param value
	 */
	// public AChartExamole(double value[]) {
	// for (int i = 0; i < value.length; i++) {
	// values[i] = value[i];
	// }
	// }

	public AChartExamole(LinkedHashMap<String, Double> map) {
		this.map = map;
	}

	public GraphicalView getView(final Context context) {

		int[] colors = new int[] { Color.LTGRAY,Color.GREEN,Color.RED, Color.YELLOW, Color.BLUE };
		final DefaultRenderer renderer = buildCategoryRenderer(colors);
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		final CategorySeries mSeries = new CategorySeries("Vehicles Chart");
		// mSeries.add("cars", values[0]);
		// mSeries.add("trucks", values[1]);
		// mSeries.add("bikes", values[2]);

		for (Entry<String, Double> entry : map.entrySet()) {
			mSeries.add(entry.getKey(),entry.getValue());
			System.out.println();
		}

		final GraphicalView mChartView = ChartFactory.getPieChartView(context,
				mSeries, renderer);

		/**********************************************************************/
		mChartView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SeriesSelection seriesSelection = mChartView
						.getCurrentSeriesAndPoint();// 获取当前的类别和指针
				if (seriesSelection == null) {
					Toast.makeText(context, "您未选择数据", Toast.LENGTH_SHORT)
							.show();
				} else {
					for (int i = 0; i < mSeries.getItemCount(); i++) {
						renderer.getSeriesRendererAt(i).setHighlighted(
								i == seriesSelection.getPointIndex());
					}
					mChartView.repaint();
					Toast.makeText(
							context,
							"您选择的是第"
									+ (seriesSelection.getPointIndex() + 1)
									+ " 项 "
									+ " 百分比为  "
									+ NumberFormat.getPercentInstance().format(
											seriesSelection.getValue()),
							Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		return mChartView;
	}

	private DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setApplyBackgroundColor(true);
		renderer.setChartTitle("Pie chart");
		renderer.setChartTitleTextSize(10);			//设置图表标题的文字大小
		renderer.setLegendTextSize(20);				//设置左下角表注的文字大小
		renderer.setLegendHeight(150);				//设置左下角表注的高度
		renderer.setLabelsTextSize(30);				//饼图上标记文字的字体大小
		renderer.setLabelsColor(Color.BLUE);		//饼图上标记文字的字体颜色
		
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);

		/***************************************************************/
		renderer.setPanEnabled(false);				//设置是否可以平移
		renderer.setClickEnabled(true);				//设置是否可以被点击
		renderer.setDisplayValues(true);			//显示数据

		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}

		return renderer;
	}

}
