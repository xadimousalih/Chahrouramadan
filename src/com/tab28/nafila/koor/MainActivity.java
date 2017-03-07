package com.tab28.nafila.koor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends DashBoardActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			View title = getWindow().findViewById(android.R.id.title);
			View titleBar = (View) title.getParent();
			titleBar.setBackgroundColor(Color.BLACK);
			setTitleColor(Color.WHITE);
			TextView titre = (TextView) title;
			titre.setGravity(Gravity.CENTER);
		}

		/**
		 * Creating all buttons instances
		 * */
		Button btn_nafila = (Button) findViewById(R.id.btn_nafila);

		btn_nafila.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						RamadanActivity.class);
				startActivity(i);
			}
		});
		Button btn_lecture = (Button) findViewById(R.id.btn_lecture);

		btn_lecture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						MainActivity.this);

				// Setting Dialog Title
				alertDialog2.setTitle(R.string.app_name);

				// Setting Dialog Message
				alertDialog2.setMessage(R.string.chg);

				// Setting Icon to Dialog
				alertDialog2.setIcon(R.drawable.ic_launcher);
				// Setting Positive "Yes" Btn
				alertDialog2.setPositiveButton(R.string.oui,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (canDisplayPdf(MainActivity.this)) {
									CopyAssets("ramadan.pdf");
								} else {
									Toast.makeText(
											getApplicationContext(),
											"Merci de télécharger un utilitaire permettant de lire les fichiers PDF",
											Toast.LENGTH_LONG).show();
								}
							}
						});
				// Setting Negative "NO" Btn
				alertDialog2.setNegativeButton(R.string.non,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								finish();

							}
						});

				// Showing Alert Dialog
				alertDialog2.show();

			}
		});

	}

	public static final String MIME_TYPE_PDF = "application/pdf";

	/**
	 * Check if the supplied context can render PDF files via some installed
	 * application that reacts to a intent with the pdf mime type and viewing
	 * action.
	 * 
	 * @param context
	 * @return
	 */
	public static boolean canDisplayPdf(Context context) {
		PackageManager packageManager = context.getPackageManager();
		Intent testIntent = new Intent(Intent.ACTION_VIEW);
		testIntent.setType(MIME_TYPE_PDF);
		if (packageManager.queryIntentActivities(testIntent,
				PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void CopyAssets(final String path) {

		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), path);
		try {
			in = assetManager.open(path);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + path),
				"application/pdf");

		startActivity(intent);
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
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
