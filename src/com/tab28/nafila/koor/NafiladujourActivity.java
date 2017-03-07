package com.tab28.nafila.koor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class NafiladujourActivity extends Activity {
	String nafila = "";
	TextView nafila_du_jourTv = null;
	String quickNafila = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nafila_du_jour_layout);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// Verification des versions
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			View title = getWindow().findViewById(android.R.id.title);
			View titleBar = (View) title.getParent();
			titleBar.setBackgroundColor(Color.BLACK);
			setTitleColor(Color.WHITE);
			TextView titre = (TextView) title;
			titre.setGravity(Gravity.CENTER);
		}
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			nafila = extras.getString("nafila");
			nafila_du_jourTv = (TextView) findViewById(R.id.nafila_du_jour);
			quickNafila = extras.getString("quickNafila");
			AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

			// Setting Dialog Title
			alertDialog2.setTitle("Voulez vous tout lire?");

			// Setting Dialog Message
			alertDialog2.setMessage(Html.fromHtml(quickNafila));

			// Setting Icon to Dialog
			alertDialog2.setIcon(R.drawable.btn_tawhid);
			// Setting Positive "Yes" Btn
			alertDialog2.setPositiveButton("Oui",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							nafila_du_jourTv.setText(Html.fromHtml(nafila));
						}
					});
			// Setting Negative "NO" Btn
			alertDialog2.setNegativeButton("Non",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							finish();

						}
					});

			// Showing Alert Dialog
			alertDialog2.show();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, R.string.app_about);
		menu.add(0, 1, 1, R.string.str_exit);
		menu.add(0, 2, 2, R.string.action_settings);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			openOptionsDialog();
			return true;
		case 1:
			exitOptionsDialog();
			return true;
		case 2:
			Intent i = new Intent(this, UserSettingActivity.class);
			startActivityForResult(i, 1);
			return true;

		}
		onBackPressed();
		return super.onOptionsItemSelected(item);
	}

	private void exitOptionsDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.app_exit)
				.setMessage(R.string.app_exit_message)
				.setNegativeButton(R.string.str_no,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})
				.setPositiveButton(R.string.str_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								finish();
							}
						}).show();
	}

	private void openOptionsDialog() {
		AboutDialog about = new AboutDialog(this);
		about.setTitle(Html.fromHtml(this.getString(R.string.app_about)));
		about.show();
	}

}
