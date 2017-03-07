package com.tab28.nafila.koor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSparseArrays")
public class RamadanActivity extends Activity {

	private ListView listView1;
	private String nafila = "";
	private String quickNafila = "";
	private String defaultDate = "2014/01/01";
	private String apparitionLuneDate;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ramadan_list);
		showUserSettings();
		AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();
		alertDialog1.setTitle("ASSALAMOU HALEYKOUM!");
		alertDialog1
				.setMessage(Html
						.fromHtml("NOTRE OBJECTIF: Oeuvrer pour Cheikh Ahmadou Bamba Khadimou Rassoul. <br/>Nous demandons à toute personne utilisant ce logiciel de prier pour SERIGNE SALIOU MBACKE"));
		alertDialog1.setIcon(R.drawable.ss);
		alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if ("2014/01/01".equals(defaultDate)) {
					AlertDialog alertDialog2 = new AlertDialog.Builder(
							RamadanActivity.this).create();
					alertDialog2.setTitle("RAPPEL!");
					alertDialog2.setMessage(Html
							.fromHtml("Merci de paramètrer la date d\'apparition du croissant lunaire via le menu Paramètrage"));
					alertDialog2.setIcon(R.drawable.ic_launcher);
					alertDialog2.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

									Toast.makeText(
											getApplicationContext(),
											"TAB28 vous souhaite un bon Ramadan",
											Toast.LENGTH_SHORT).show();
								}
							});
					alertDialog2.show();
				}
			}
		});
		alertDialog1.show();

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			View title = getWindow().findViewById(android.R.id.title);
			View titleBar = (View) title.getParent();
			titleBar.setBackgroundColor(Color.BLACK);
			setTitleColor(Color.WHITE);
			TextView titre = (TextView) title;
			titre.setGravity(Gravity.CENTER);

		}

		TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);
		apparitionLuneDate = settingsTextView.getText().toString();
		initAdapter(apparitionLuneDate);

		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(
						RamadanActivity.this);
				adb.setTitle("Oeuvre selectionnée");
				Intent i = new Intent(getApplicationContext(),
						NafiladujourActivity.class);

				switch (position) {
				case 0:
					quickNafila = "<b>10 raakas</b> dont chacun la sourate <b>«Fatiha»</b> suivie des sourates: <br><b>«Ina Anzalnahou» (2 fois),</b><br> <b>«Al Käfirouna» (2 fois),</b><br><b>«Iklass» (2 fois)</b>";
					nafila = "Celui qui effectue dans la nuit qui précède le premier jour de Ramadan <b>10 raakas</b> avec pour chacun la sourate <b>«Fatiha»</b> suivie des sourates: <br><b>«Ina Anzalnahou» (2 fois),</b><br> <b>«Al Käfirouna» (2 fois),</b><br><b>«Iklass» (2 fois)</b>: <br><br>Dieu le sauvera à jamais de l’enfer et exaucera tous ses voeux formulés à la suite de cette prière.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;

				case 1:
					quickNafila = "<b>6 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Ahtaïnakal» (10 fois)</b>";
					nafila = "Dans la nuit du 1er au 2, celui qui effectue <b>6 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Ahtaïnakal» (10 fois):</b><br><br>sera absous de tous ses péchés avant qu’il ne quitte les lieux de prières, il bénéficiera des avantages liés à mille grands pèlerinages et à mille petits pèlerinages (Ouma) effectues, ainsi qu’aux avantages dus à quelqu’un qui a donné en offrande 1000 ‘‘dereumes’’ et qui a rendu visite à 1000 malades il aura également les avantages dus à quelqu’un qui a accompagné 1000 morts jusqu’à leur dernière demeure ; ainsi qu’à ceux dus à quelqu’un qui a lu tout le coran. Il bénéficiera enfin de bénédictions plus importantes que celles dues à des dévotions d’une durée de 70 ans.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 2:
					quickNafila = "<b>6 raakas</b> avec pour chaque raaka la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Ina Anzahnahou» (4 fois),</b><br> <b>«Khoul Ya Ayouhal Kâfirouna» (4 fois)</b>";
					nafila = "Dans la nuit du 2 au 3 : si on effectue <b>6 raakas</b> avec pour chaque raaka la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Ina Anzahnahou» (4 fois),</b><br> <b>«Khoul Ya Ayouhal Kâfirouna» (4 fois):</b><br><br>on aura bénéficié des avantages dus à quelqu’un qui aura affranchi 1000 esclaves et de ceux de quelqu’un qui aura donné à manger à 1000 personnes au terme d’une journée de jeûne, de ceux dus à quelqu’un qui a habillé 1000 personnes se trouvant dans un dénuement complet, donné à manger à 1000 personnes qui ont faim, on bénéficiera de bénédictions aussi importantes en quantités que le nombre des espèces volantes de la planète ; on bénéficiera en outre des avantages dus aux plus grands saints et ceux dus à ceux qui sont tués au cours d’une guerre sainte, enfin on ne rencontrera aucune épreuve dans la tombe.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 3:
					quickNafila = "<b>4 raakas</b> avec pour chaque raaka la <b>«Fatiha»</b> suivie de la sourate:<br><br><b>«Khoul Ya Ayouhal Kafirouna» (3 fois)</b>";
					nafila = "Dans la nuit du 3 au 4 : <b>4 raakas</b> avec pour chaque raaka la <b>«Fatiha»</b> suivie de la sourate:<br><br><b>«Khoul Ya Ayouhal Kafirouna» (3 fois):</b><br><br>on bénéficiera des avantages dus à 1000 personnes qui se sont acquittées de leurs dévotions, on sera en plus absous de tous ses péchés avant qu’on quitte le lieu de prière.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 4:
					quickNafila = "<b>4 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie des versets: <br><br><b>«Alam Nasraa» (1 fois),</b><br> <b>«Khoul Houwa Allahou» (3 fois)</b>";
					nafila = "Dans la nuit du 4 au 5 : <b>4 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie des versets: <br><br><b>«Alam Nasraa» (1 fois),</b><br> <b>«Khoul Houwa Allahou» (3 fois): </b><br><br>on sera considéré comme quelqu’un qui a fait le grand et le petits pèlerinages 1000 fois. On sera préservé de la pauvreté et verra nos chances d’acquisition de biens multipliées. De chaque lettre naîtront 2 anges qui se chargeront d’effacer nos péchés et d’enregistrer des bienfaits à notre intention; de nous octroyer des grades et ce, durant une année entière. A notre mort, on bénéficiera des faveurs dues à un combattant tué au cours d’une guerre sainte. On sera absous (de tous ses péchés), on ne connaîtra pas les affres de la mort et on aura absolument les agréments du Tout-Puisant, à sa mort, on lui ouvrira de sa tombe des portes qui donnent sur le paradis et qu’il contemplera jusqu’au jour du jugement.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 5:
					quickNafila = "<b>2 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (12 fois)</b>";
					nafila = "Dans la nuit du 5 au 6 : <b>2 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (12 fois): </b><br><br>il sera considéré comme quelqu’un qui a effectué 1000 rakas sur le «makhama» Ibrahima (à quelque métre de la Kaaba), 1000 rakas dans la Mosquée de la Mecque, donné en aumône (5000f) donné à mangé1000 personnes qui ont faim, ses voeux seront exaucés. Il sera considéré comme quelqu’un qui a donné à manger après une journée de jeûne à tous les musulmans. A sa mort il sera compté parmi les ceux qui sont tués au cours d’une guerre sainte, il sera préservé des affres de la mort, sa tombe sera aménagée comme des jardins d’Eden. Il ressuscitera auréolé de la lumière céleste, sera d’une beauté comparable à celle du soleil avec le plus beau parfum du monde, il ira au Paradis sans subir aucune épreuve, sans jugement et il sera parmi les Prophètes dans la vie éternelle.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 6:
					quickNafila = "<b>6 raakas</b> avec pour chacun d’eux la <b>«Fatiha»<b> suivie de la sourate: <br><br><b>«Khoul Ya Ayouhal Kâfirouna» (7 fois): </b><br><br>Et la sourate <b>«Khoul Houwa Allahou» (7 fois)</b>";
					nafila = "Nuit du 06 au 07 : <b>6 raakas</b> avec pour chacun d’eux la <b>«Fatiha»<b> suivie de la sourate: <br><br><b>«Khoul Ya Ayouhal Kâfirouna» (7 fois): </b><br><br>Et la sourate <b>«Khoul Houwa Allahou» (7 fois): </b><br><br>il bénéficiera de bienfaits pour chaque verset, il sera considéré comme quelqu’un qui aura donné en aumône 1000 dinars ; tous ses voeux seront exaucés, il bénéficiera en outre de bénédictions aussi importantes en qualité que le nombre de jours de l’existence terrestre; pour chaque lettre on lui bâtira une grande maison au paradis.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 7:
					quickNafila = "<b>2 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (12 fois)</b>";
					nafila = "Nuit du 07 au 08: <b>2 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (12 fois): </b><br><br>Pour chaque lettre des sourates employées, il bénéficiera des bénédictions dues à 1000 ans de dévotions. Il sera considéré comme quelqu’un qui a donné à manger à tous les musulmans pendant 70 ans révolus tout en leur donnant à boire et les habillant. Il aura en outre les bénédictions liées à l’affranchissement de 1000 esclaves; ses voeux seront exaucés, il ressuscitera avec la lumière céleste. Il traversera «siraat» comme un éclair, il lui sera demandé de sauver de l’enfer (70) personnes qui y était destinées.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 8:
					quickNafila = "<b>4 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Tabatt Yada» (3 fois),</b><br> et de la sourate <b>«Khoul Houwa Allahou» (1 fois)</b>";
					nafila = "Nuit du 08 au 09: <b>4 raakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Tabatt Yada» (3 fois),</b><br> et de la sourate <b>«Khoul Houwa Allahou» (1 fois): </b><br><br>il lui sera accordé les bénédictions dues à tous ceux qui acceptent stoïquement les décrets divins (mounkat yi) et à tous ceux qui manifestent à Dieu leur reconnaissance ; il lui sera accordé les avantages liés à la lecture du coran tout entier ; il bénéficiera des bénédictions qui procurent 70 ans de dévotions pendant lesquels il passe les journées à jeûner et les nuits à prier. Ordre sera donné à 2 anges qui se chargeront d’effacer ses péchés et d’enregistrer des bienfaits à son intention et ce jusqu’au jour du jugement dernier. Dieu agréera ses prières et son jeûne et le mettra à l’abri des mauvaises intentions des hommes, il ouvrira à son intention les portes des 8 paradis et le priera d’entrer dans celui de son choix et ce sans le juger.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 9:
					quickNafila = "<b>4 raakas</b> avec chacun d’eux la <b>«Fatiha»</b> suivie du verset: <br><br><b>«Ayatoul Koursiyou»(1 fois),</b><br> et de la sourate <b>«Ina Anzalnahou» (12 fois)</b>";
					nafila = "Nuit du 09 au 10 : <b>4 raakas</b> avec chacun d’eux la <b>«Fatiha»</b> suivie du verset: <br><br><b>«Ayatoul Koursiyou»(1 fois),</b><br> et de la sourate <b>«Ina Anzalnahou» (12 fois): </b><br><br>Avant de quitter le lieu de prière, il se verra absous de tous ses péchés sans exclusive ; on lui bénéficiera de bénédictions égales à celles de 50 (homme de Dieu) véridique, celles de 70 personnes tuées dans une guerre sainte. Avant sa mort, il goûtera de fruits provenant du paradis, on lui demandera de sauver de l’enfer 70 personnes qui y étaient destinées.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 10:
					quickNafila = "<b>4 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Anzalnahou» (7 fois), </b><br>des sourates: <br><b>«Khoul Ya Ayouhal Kâfirouna» (7 fois),</b><br> <b>«Khoul Houwal Allahou» (7 fois)</b>";
					nafila = "Nuit du 10 au 11: <b>4 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Anzalnahou» (7 fois), </b><br>des sourates: <br><b>«Khoul Ya Ayouhal Kâfirouna» (7 fois),</b><br> <b>«Khoul Houwal Allahou» (7 fois): </b><br><br>après le salut final dire «la hawla wala khouwata ila billah hahilyil azimi»(70 fois) dire ensuite «salatou ala nabi» (70 fois) il sera absous de tous ses péchés sans exclusive ; pour chaque verset il lui sera accordé des faveurs dues aux dévotions d’une année ainsi que celles dues à quelqu’un qui a rendu leur liberté à 1000 esclaves, donnée en aumône 1000 dinars, donnée à manger à 1000 personnes qui n’ont rein à manger, habillé 1000 personnes se trouvant dans le dénouement le plus complet. Tout ce qui existe sur terre vivant ou non intercédera auprès de Dieu pour qu’il lui pardonne tous ses péchés, il sera préservé contre toutes les calamités, toutes les peines, la faim, l’indigence (sous ses formes: raag) les djinns contre les affres de la mort il ira au paradis sans jugement aucun.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 11:
					quickNafila = "<b>10 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (6 fois)</b>";
					nafila = "Nuit du 11 au 12 : <b>10 raakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (6 fois): </b><br><br>il bénéficiera de 8000 épouses au Paradis et il vivra en compagnie du Prophète (PSL), il bénéficiera en outre des faveurs dues à celui qui a étudié le «tawrête», «l’indjile» , le «zabor» et le «coran». Par ailleurs ? il sera absous de tous ses péchés quelle que soit leur importance même s’ils égalaient en nombre les vagues de la mer. Il lui sera accordé des bénédictions dues à 70 ans de dévotions à Dieu ; il sera considéré comme quelqu’un qui a rendu leur liberté à 1000 esclaves ; le jour du jugement dernier on lui donnera l’ordre de sauver de l’enfer autant de personnes qu’il le désirera et qui y étaient destinées. Il ira au Paradis sans être jugé.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 12:
					quickNafila = "<b>2 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (5 fois)</b>";
					nafila = "Nuit du 12 au 13 : <b>2 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (5 fois): </b><br><br>on lui bâtira au paradis une grande maison composée de 7 chambres tout en or. Dans chacune de ces chambres il y aura 1000 lits avec dans chacun d’eux une épouse, en outre il bénéficiera de bénédictions dues à 20 ans passés à jeûner et des bénédictions dues à 1000 rakas effectués.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 13:
					quickNafila = "<b>8 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Izaa Djaha» (7 fois)</b>";
					nafila = "Nuit du 13 au 14 : <b>8 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Izaa Djaha» (7 fois): </b><br><br>il sera considéré comme quelqu’un qui a donné 1000 dinars, affranchi 1000 esclaves, donné à manger à 1000 personnes n’ayant rien à manger. Dieu agréera toutes ses prières et son jeûne, il sera préserver contre les maux d’ici et de l’au-delà. Il ne connaîtra pas les inquiétudes et angoisses du jour du jugement dernier, il lui sera donné l’ordre de sauver de l’enfer autant de personnes qu’il voudra et qui y étaient destinées sans qu’elles soient jugées.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 14:
					quickNafila = "<b>6 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Izaa Djaha», </b><br>et <b>35 «Khoul Houwa Alahou»</b>";
					nafila = "Nuit du 14 au 15: <b>6 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Izaa Djaha», </b><br>et <b>35 «Khoul Houwa Alahou»: </b><br><br>Dieu agréera ses prières et son jeûne, tous ses péchés seront reconvertis en bienfaits. Pour chacune des lettres composant les sourates employées, il lui sera bâti en grande cité au paradis, il sera considéré comme quelqu’un qui a donné à manger aux musulmanes du monde entier.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 15:
					quickNafila = "<b>2 rakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Izaa Zoulzilati» (10 fois)</b>";
					nafila = "Nuit du 15 au 16 : <b>2 rakas</b> avec pour chacun la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Izaa Zoulzilati» (10 fois): </b><br><br>il lui accordera beaucoup de bénédictions en outre il sera absous de tous ses péchés par ailleurs, s’il se trouvait dans l’angoisses, Dieu l’en délivrera, Dieu le préservera des calamités de ce monde et de l’autre ; il sera préservé contre la pauvreté et verra ses chances d’acquisition de biens multipliées. Dieu se montrera miséricordieux à son égard. Il ira au paradis en compagnie des plus illustres créatures.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 16:
					quickNafila = "<b>12 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de sourates: <br><br><b>«Ina Anzalnahou» (2 fois) </b><br>et <b>«Khoul Houwa Allahou» (2 fois)</b>";
					nafila = "Nuit du 16 au 17 : <b>12 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de sourates: <br><br><b>«ina anzalnahou» (2 fois) </b><br>et <b>«Khoul Houwa Allahou» (2 fois): </b><br><br>il bénéficiera de bénédictions d’un nombre aussi important que le nombre de tous les croyants, du premier au dernier s’il se trouvait malade, il guérira, s’il se trouve endetté. Dieu l’acquittera de ses dettes, s’il se trouvait angoissé, Dieu l’en guérirait et Dieu le préservera de l’enfer.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 17:
					quickNafila = "<b>10 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Sabi Hisma» (1 fois), </b><br> <b>«Khoul Ya Ayouhal Kafirouna» (1fois), </b><br> <b>«Khoul Houwa Allahou» (1 fois)</b>";
					nafila = "Nuit du 17 au 18 : <b>10 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Sabi Hisma» (1 fois), </b><br> <b>«Khoul Ya Ayouhal Kafirouna» (1fois), </b><br> <b>«Khoul Houwa Allahou» (1 fois): </b><br><br>il sera considéré comme quelqu’un qui détiendrait toutes les richesses terrestres et les emploierait au service du Tout-Puissant Dieu. Pour chaque lettre des sourates employées, on lui bâtira une grande cité au paradis, il sera considéré comme quelqu’un qui s ‘est acquitté de ses devoirs envers le Tout-Puissant pendant 70 ans au cours duquel, il aura passé les journées à jeûner et les nuits à prier ; il sera considéré comme quelqu’un qui a lu le coran 100 fois, fait 100 fois le grand pèlerinage et 100 fois le petit pèlerinage, il bénéficiera des bénédictions dont Dieu seul connaît l’importance avant de mourir, il lui sera montré sa demeure au paradis, le jour du jugement dernier, il ne verra rien qui lui fasse peur ou qui l’inquiète.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 18:
					quickNafila = "<b>6 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (7 fois)</b>";
					nafila = "Nuit du 18 au 19: <b>6 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (7 fois): </b><br><br>Le paradis est la récompense due à cette prière, Dieu préservera l’auteur de cette prière de tout ce dont il a peur, Dieu exaucera tous ses voeux. Satan évitera de le rencontrer, il conservera la foi jusqu’à la fin de ses jours, il fera partie des premières personnes à entrer au Paradis.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 19:
					quickNafila = "<b>8 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Ina Anzalnahou» (1 fois), </b><br> et <b>«Khoul Houwa Allahou» (3 fois)</b>";
					nafila = "Nuit du 19 au 20 : <b>8 rakas</b> avec pour chacun d’eux la <b>«Fatiha»</b> suivie des sourates: <br><br><b>«ina anzalnahou» (1 fois), </b><br> et <b>«Khoul Houwa Allahou» (3 fois): </b><br><br>il bénéficiera des bénédictions aussi importantes que celles dues à tout les hommes qui ont fait quelque chose de bien. Il sera absous de tous ses péchés sans exclusive. On le préservera de l’obscurité de la tombe, il ira au Paradis en compagnie du Prophète Muhammad (PSL).";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 20:
					quickNafila = "<b>4 Rakas</b> dont chacun <b>«Fatiha»</b> suivi de la sourate: <br><br><b>«Khoul Houwa Allahou» (20fois)</b>";
					nafila = "Nuit du 20 au 21 : <b>4 Rakas</b> dont chacun <b>«Fatiha»</b> suivi de la sourate: <br><br><b>«Khoul Houwa Allahou» (20fois): </b><br><br>il sera considéré comme quelqu’un qui détiendrait tous les biens de la planète et les dépenserait au nom de Dieu, comme quelqu’un qui avait lu le «Tawrète», le «Injiil» le «Zabaur» et le «Coran».A sa mort il sera aussi propre (en péché) que le jour de sa naissance.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 21:
					quickNafila = "<b>2 rakas</b> dont chacun <b>«Fatiha»</b> suivi des sourates: <br><br><b>«Sabi Hisma» (3 fois), </b><br><b>«Inna Anzalnabou» (3 fois), </b><br> <b>«Khoul Houwa Allahou» (3 fois), </b><br> <b>«Falakhi» (3 fois), </b><br> <b>«Naassi» (3 fois)</b>";
					nafila = "Nuit du 21 au 22 : <b>2 rakas</b> dont chacun <b>«Fatiha»</b> suivi des sourates: <br><br><b>«Sabi Hisma» (3 fois), </b><br><b>«Inna Anzalnabou» (3 fois), </b><br> <b>«Khoul Houwa Allahou» (3 fois), </b><br> <b>«Falakhi» (3 fois), </b><br> <b>«Naassi» (3 fois): </b><br><br>Dieu bâtira à son intention 70 cités avec dans chaque cité 1000 maisons et dans chaque concession 1000 chambres en or et en argent, dans chaque chambre 1000 lits et dans chaque lit 1000 épouses.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 22:
					quickNafila = "<b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Izaa Djaha Nassourou Laahi» (5 fois), </b><br><b>«Khoul Houwa Allahou» (5 fois)</b>";
					nafila = "Nuit du 22 au 23 : <b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Izaa Djaha Nassourou Laahi» (5 fois), </b><br><b>«Khoul Houwa Allahou» (5 fois): </b><br><br>il sera absous de tous ses péchés. Ordre sera donné à 2 anges qui se chargeront d’effacer ses péchés et d’enregistrer à son nom des bénédictions pendant une année entière. S’il meurt entre temps, il bénéficiera des bénédictions dues à quelqu’un qui s’est tué au cours d’une guerre sainte. Il ressuscitera avec une beauté aussi éclatante que le soleil, il traversera «siraat» comme un éclair.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 23:
					quickNafila = "<b>6 rakas</b> dont chacun <b>«Fatiha»</b> suivi de la sourate: <br><br><b>«Khoul Houwa Allahou» (3 fois)</b>";
					nafila = "Nuit du 23 au 24 : <b>6 rakas</b> dont chacun <b>«Fatiha»</b> suivi de la sourate: <br><br><b>«Khoul Houwa Allahou» (3 fois): </b><br><br>il sera au Paradis sans être jugé.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 24:
					quickNafila = "<b>8 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (4 fois)</b>";
					nafila = "Nuit du 24 au 25 : <b>8 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (4 fois): </b><br><br>il sera absous de tous ses péchés avant de quitter le lieu de prière, en outre, il ne connaîtra jamais les épreuves (de Dieu).";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 25:
					quickNafila = "<b>10 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Al Qârihatou» (1 fois), </b><br> <b>«Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il lui est possible";
					nafila = "Nuit du 25 au 26 : <b>10 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Al Qârihatou» (1 fois), </b><br> <b>«Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il lui est possible, il sera préservé des feux de l’enfer, il sera considéré comme quelqu’un qui aura jeûné pendant toute la durée de l’existence, il bénéficiera des bénédictions aussi importantes en quantité que le nombre de toutes les étoiles et de toutes les feuilles des arbres ; il bénéficiera également de bénédictions capable de couvrir tout l’espace compris entre le ciel et la terre. Le jour de la résurrection il ira au Paradis escorté des anges Djibril, Mikaël, Izrafil, Izrahil.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 26:
					quickNafila = "<b>12 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Anzalnahou» (10 fois)</b>";
					nafila = "Nuit du 26 au 27 : <b>12 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Ina Anzalnahou» (10 fois): </b><br><br>il bénéficiera des bénédictions aussi importantes en nombre que le nombre des jours de l’existence terrestre. A la résurrection, il sera parmi les prophètes.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 27:
					quickNafila = "<b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Wa Tiini» (5 fois), </b><br>«Khoul Ya Ayouhal Kafirouna» (5 fois) «Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il peut alors";
					nafila = "Nuit du 27 au 28 : <b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Wa Tiini» (5 fois), </b><br><b>«Khoul Ya Ayouhal Kafirouna» (5 fois), </b><br>«Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il peut alors, il sera absous de tous ses péchés.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 28:
					quickNafila = "<b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Wa Tiini» (5 fois), </b><br>«Khoul Ya Ayouhal Kafirouna» (5 fois) «Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il peut alors";
					nafila = "Nuit du 28 au 29 : <b>4 rakas</b> dont chacun <b>«Fatiha»</b> suivie des sourates: <br><br><b>«Wa Tiini» (5 fois), </b><br>«Khoul Ya Ayouhal Kafirouna» (5 fois) «Khoul Houwa Allahou» (5 fois): </b><br><br>Après la prière il se repend aussi longtemps qu’il peut alors, il sera absous de tous ses péchés.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 29:
					quickNafila = "<b>6 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (11fois)</b>";
					nafila = "Nuit du 29 au 30 : <b>6 rakas</b> dont chacun <b>«Fatiha»</b> suivie de la sourate: <br><br><b>«Khoul Houwa Allahou» (11fois): </b><br><br>il sera bâti à son intention une maison au Paradis. De même si on effectue 4 rakas dont chacun «Fatiha» suivie des sourates «khoul ya ayouhal kâfirouna» (25 fois) «khoul houwa allahou» (25 fois), on sera préservé des feux de l’enfer, on sera absous de tous ses péchés, de même ses proches parents on traversera «siraat» comme un éclair.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				default:
					break;
				}
			}
		});

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, R.string.app_about);
		menu.add(0, 1, 1, R.string.str_exit);
		menu.add(0, 2, 2, R.string.action_settings);
		menu.add(0, 3, 3, R.string.layla);
		return super.onCreateOptionsMenu(menu);
	}

	@SuppressWarnings("deprecation")
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
		case 3:
			TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);
			apparitionLuneDate = settingsTextView.getText().toString();
			AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();
			alertDialog1.setTitle("LAYLATOUL QADRE");

			try {
				alertDialog1
						.setMessage(Html
								.fromHtml("La nuit du LAYLATOUL QADR sera inch'ALLAH celle du "
										+ addDaysToDate(
												getLaylatoulKhadreDay(apparitionLuneDate),
												-1)
										+ " au "
										+ getLaylatoulKhadreDay(apparitionLuneDate)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			alertDialog1.setIcon(R.drawable.ic_launcher);
			alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {

				}
			});
			alertDialog1.show();
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

	public static String getLaylatoulKhadreDay(String date) {
		String dateLayla = null;
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedDate);
		int intJour = calendar.get(Calendar.DAY_OF_WEEK) + 1;
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		switch (intJour) {
		
		case 8/*Dimanche*/:
			try {
				dateLayla = addDaysToDate(date, 27);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2/*Lundi*/:
			try {
				dateLayla = addDaysToDate(date, 19);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 3/*Mardi*/:
			try {
				dateLayla = addDaysToDate(date, 25);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 4/*Mercredi*/:
			try {
				dateLayla = addDaysToDate(date, 17);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 5/*Jeudi*/:
			try {
				dateLayla = addDaysToDate(date, 23);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			break;
		case 6/*Vendredi*/:
			try {
				dateLayla = addDaysToDate(date, 29);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			break;
		case 7/*Samedi*/:
			try {
				dateLayla = addDaysToDate(date, 21);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
		return dateLayla;
	}

	private static String addDaysToDate(String date, int daysToAdd)
			throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date parsedDate = sdf.parse(date);
		Calendar now = Calendar.getInstance();
		now.setTime(parsedDate);
		now.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String dateFinale = sdf.format(now.getTime());
		return dateFinale;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			showUserSettings();
			break;

		}

	}

	private void showUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		StringBuilder builder = new StringBuilder();
		builder.append(sharedPrefs.getString("prefUsername", "2014/01/01"));
		TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);
		defaultDate = builder.toString().replace(".", "/");
		settingsTextView.setText(defaultDate);
		initAdapter(defaultDate);
	}

	public void initAdapter(String apparitionLuneDate) {
		Weather weather_data[];
		try {
			weather_data = new Weather[] {
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n0),
							addDaysToDate(apparitionLuneDate, 0)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n1),
							addDaysToDate(apparitionLuneDate, 1)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n2),
							addDaysToDate(apparitionLuneDate, 2)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n3),
							addDaysToDate(apparitionLuneDate, 3)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n4),
							addDaysToDate(apparitionLuneDate, 4)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n5),
							addDaysToDate(apparitionLuneDate, 5)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n6),
							addDaysToDate(apparitionLuneDate, 6)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n7),
							addDaysToDate(apparitionLuneDate, 7)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n8),
							addDaysToDate(apparitionLuneDate, 8)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n9),
							addDaysToDate(apparitionLuneDate, 9)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n10),
							addDaysToDate(apparitionLuneDate, 10)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n11),
							addDaysToDate(apparitionLuneDate, 11)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n12),
							addDaysToDate(apparitionLuneDate, 12)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n13),
							addDaysToDate(apparitionLuneDate, 13)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n14),
							addDaysToDate(apparitionLuneDate, 14)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n15),
							addDaysToDate(apparitionLuneDate, 15)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n16),
							addDaysToDate(apparitionLuneDate, 16)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n17),
							addDaysToDate(apparitionLuneDate, 17)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n18),
							addDaysToDate(apparitionLuneDate, 18)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n19),
							addDaysToDate(apparitionLuneDate, 19)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n20),
							addDaysToDate(apparitionLuneDate, 20)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n21),
							addDaysToDate(apparitionLuneDate, 21)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n22),
							addDaysToDate(apparitionLuneDate, 22)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n23),
							addDaysToDate(apparitionLuneDate, 23)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n24),
							addDaysToDate(apparitionLuneDate, 24)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n25),
							addDaysToDate(apparitionLuneDate, 25)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n26),
							addDaysToDate(apparitionLuneDate, 26)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n27),
							addDaysToDate(apparitionLuneDate, 27)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n28),
							addDaysToDate(apparitionLuneDate, 28)),
					new Weather(R.drawable.btn_tawhid, getApplicationContext()
							.getResources().getString(R.string.n29),
							addDaysToDate(apparitionLuneDate, 29)), };
			WeatherAdapter adapter = new WeatherAdapter(this,
					R.layout.listview_item_row, weather_data);

			listView1 = (ListView) findViewById(R.id.listView1);
			adapter.notifyDataSetChanged();
			listView1.setAdapter(adapter);
			setupActionBar();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
