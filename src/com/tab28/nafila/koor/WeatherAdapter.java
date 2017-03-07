package com.tab28.nafila.koor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends ArrayAdapter<Weather> {

	Context context;
	int layoutResourceId;
	Weather data[] = null;

	public WeatherAdapter(Context context, int layoutResourceId, Weather[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeatherHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new WeatherHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
			holder.txtDate = (TextView) row.findViewById(R.id.txtDate);

			row.setTag(holder);

		} else {
			holder = (WeatherHolder) row.getTag();
		}

		Weather weather = data[position];
		holder.txtTitle.setText(weather.title);
		holder.imgIcon.setImageResource(weather.icon);
		Date now = new Date();
		String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(now);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// String firstNightRam = "20130709";
		String firstNightRam = "09/07/2013";
		String firstDayRam = "10/07/2013";
		Date dateDuJour = null;
		Date dateList = null;
		Date layla = null;
		try {
			firstDayRam = premierJourRamadan(firstNightRam);
			dateDuJour = sdf.parse(nowAsString);
			dateList = sdf.parse(weather.date);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "
					+ addDaysToDate(firstNightRam,
							getLaylatoulKhadreDay(firstNightRam)));
			layla = format.parse(addDaysToDate(firstDayRam,
					getLaylatoulKhadreDay(firstDayRam)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
//		if (layla.compareTo(dateList) == 0) {
//			holder.txtDate.setText("LAYLATOUL QADR LE " + weather.date);
//			holder.txtTitle.setText("");
//			holder.txtDate.setTextSize(25);
//			holder.txtDate.setTextColor(Color.WHITE);
//			holder.txtDate.setBackgroundColor(Color.parseColor("#006633"));
//		} else if (dateDuJour.compareTo(dateList) == 0) {
//			holder.txtDate.setText("Cette Nuit " + weather.date);
//			holder.txtTitle.setText("");
//			holder.txtDate.setTextSize(25);
//			holder.txtDate.setTextColor(Color.WHITE);
//			holder.txtDate.setBackgroundColor(Color.parseColor("#006633"));
//		} else
			
			holder.txtDate.setText(weather.date);
		return row;
	}

	static class WeatherHolder {
		ImageView imgIcon;
		TextView txtTitle;
		TextView txtDate;
	}

	public int getLaylatoulKhadreDay(String date) throws Exception {
		int dateLayla = 0;
		Date parsedDate = null;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		parsedDate = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedDate);
		int intJour = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		switch (intJour) {
		case 1:
			dateLayla = 27 - 2;
			break;
		case 2:
			dateLayla = 19 - 2;

			break;
		case 3:
			dateLayla = 25 - 2;

			break;
		case 4:
			dateLayla = 17 - 2;

			break;
		case 5:
			dateLayla = 23 - 2;

			break;
		case 6:
			dateLayla = 29 - 2;

			break;
		case 7:
			dateLayla = 21 - 2;

			break;

		default:
			break;
		}
		return dateLayla;
	}

	private String addDaysToDate(String date, int daysToAdd) throws Exception {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate = sdf.parse(date);
		Calendar now = Calendar.getInstance();
		now.setTime(parsedDate);
		now.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String dateFinale = sdf.format(now.getTime());
		return dateFinale;
	}

	public String premierJourRamadan(String premiereNuit) throws Exception {
		System.out
				.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Premier Jour Ramadan "
						+ addDaysToDate(premiereNuit, 1));
		return addDaysToDate(premiereNuit, 1);
	}

}