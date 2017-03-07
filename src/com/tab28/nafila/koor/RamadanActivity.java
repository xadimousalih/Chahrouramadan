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
						.fromHtml("NOTRE OBJECTIF: Oeuvrer pour Cheikh Ahmadou Bamba Khadimou Rassoul. <br/>Nous demandons � toute personne utilisant ce logiciel de prier pour SERIGNE SALIOU MBACKE"));
		alertDialog1.setIcon(R.drawable.ss);
		alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if ("2014/01/01".equals(defaultDate)) {
					AlertDialog alertDialog2 = new AlertDialog.Builder(
							RamadanActivity.this).create();
					alertDialog2.setTitle("RAPPEL!");
					alertDialog2.setMessage(Html
							.fromHtml("Merci de param�trer la date d\'apparition du croissant lunaire via le menu Param�trage"));
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
				adb.setTitle("Oeuvre selectionn�e");
				Intent i = new Intent(getApplicationContext(),
						NafiladujourActivity.class);

				switch (position) {
				case 0:
					quickNafila = "<b>10 raakas</b> dont chacun la sourate <b>�Fatiha�</b> suivie des sourates: <br><b>�Ina Anzalnahou� (2 fois),</b><br> <b>�Al K�firouna� (2 fois),</b><br><b>�Iklass� (2 fois)</b>";
					nafila = "Celui qui effectue dans la nuit qui pr�c�de le premier jour de Ramadan <b>10 raakas</b> avec pour chacun la sourate <b>�Fatiha�</b> suivie des sourates: <br><b>�Ina Anzalnahou� (2 fois),</b><br> <b>�Al K�firouna� (2 fois),</b><br><b>�Iklass� (2 fois)</b>: <br><br>Dieu le sauvera � jamais de l�enfer et exaucera tous ses voeux formul�s � la suite de cette pri�re.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;

				case 1:
					quickNafila = "<b>6 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Ahta�nakal� (10 fois)</b>";
					nafila = "Dans la nuit du 1er au 2, celui qui effectue <b>6 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Ahta�nakal� (10 fois):</b><br><br>sera absous de tous ses p�ch�s avant qu�il ne quitte les lieux de pri�res, il b�n�ficiera des avantages li�s � mille grands p�lerinages et � mille petits p�lerinages (Ouma) effectues, ainsi qu�aux avantages dus � quelqu�un qui a donn� en offrande 1000 ��dereumes�� et qui a rendu visite � 1000 malades il aura �galement les avantages dus � quelqu�un qui a accompagn� 1000 morts jusqu�� leur derni�re demeure ; ainsi qu�� ceux dus � quelqu�un qui a lu tout le coran. Il b�n�ficiera enfin de b�n�dictions plus importantes que celles dues � des d�votions d�une dur�e de 70 ans.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 2:
					quickNafila = "<b>6 raakas</b> avec pour chaque raaka la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Ina Anzahnahou� (4 fois),</b><br> <b>�Khoul Ya Ayouhal K�firouna� (4 fois)</b>";
					nafila = "Dans la nuit du 2 au 3 : si on effectue <b>6 raakas</b> avec pour chaque raaka la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Ina Anzahnahou� (4 fois),</b><br> <b>�Khoul Ya Ayouhal K�firouna� (4 fois):</b><br><br>on aura b�n�fici� des avantages dus � quelqu�un qui aura affranchi 1000 esclaves et de ceux de quelqu�un qui aura donn� � manger � 1000 personnes au terme d�une journ�e de je�ne, de ceux dus � quelqu�un qui a habill� 1000 personnes se trouvant dans un d�nuement complet, donn� � manger � 1000 personnes qui ont faim, on b�n�ficiera de b�n�dictions aussi importantes en quantit�s que le nombre des esp�ces volantes de la plan�te ; on b�n�ficiera en outre des avantages dus aux plus grands saints et ceux dus � ceux qui sont tu�s au cours d�une guerre sainte, enfin on ne rencontrera aucune �preuve dans la tombe.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 3:
					quickNafila = "<b>4 raakas</b> avec pour chaque raaka la <b>�Fatiha�</b> suivie de la sourate:<br><br><b>�Khoul Ya Ayouhal Kafirouna� (3 fois)</b>";
					nafila = "Dans la nuit du 3 au 4 : <b>4 raakas</b> avec pour chaque raaka la <b>�Fatiha�</b> suivie de la sourate:<br><br><b>�Khoul Ya Ayouhal Kafirouna� (3 fois):</b><br><br>on b�n�ficiera des avantages dus � 1000 personnes qui se sont acquitt�es de leurs d�votions, on sera en plus absous de tous ses p�ch�s avant qu�on quitte le lieu de pri�re.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 4:
					quickNafila = "<b>4 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie des versets: <br><br><b>�Alam Nasraa� (1 fois),</b><br> <b>�Khoul Houwa Allahou� (3 fois)</b>";
					nafila = "Dans la nuit du 4 au 5 : <b>4 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie des versets: <br><br><b>�Alam Nasraa� (1 fois),</b><br> <b>�Khoul Houwa Allahou� (3 fois): </b><br><br>on sera consid�r� comme quelqu�un qui a fait le grand et le petits p�lerinages 1000 fois. On sera pr�serv� de la pauvret� et verra nos chances d�acquisition de biens multipli�es. De chaque lettre na�tront 2 anges qui se chargeront d�effacer nos p�ch�s et d�enregistrer des bienfaits � notre intention; de nous octroyer des grades et ce, durant une ann�e enti�re. A notre mort, on b�n�ficiera des faveurs dues � un combattant tu� au cours d�une guerre sainte. On sera absous (de tous ses p�ch�s), on ne conna�tra pas les affres de la mort et on aura absolument les agr�ments du Tout-Puisant, � sa mort, on lui ouvrira de sa tombe des portes qui donnent sur le paradis et qu�il contemplera jusqu�au jour du jugement.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 5:
					quickNafila = "<b>2 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (12 fois)</b>";
					nafila = "Dans la nuit du 5 au 6 : <b>2 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (12 fois): </b><br><br>il sera consid�r� comme quelqu�un qui a effectu� 1000 rakas sur le �makhama� Ibrahima (� quelque m�tre de la Kaaba), 1000 rakas dans la Mosqu�e de la Mecque, donn� en aum�ne (5000f) donn� � mang�1000 personnes qui ont faim, ses voeux seront exauc�s. Il sera consid�r� comme quelqu�un qui a donn� � manger apr�s une journ�e de je�ne � tous les musulmans. A sa mort il sera compt� parmi les ceux qui sont tu�s au cours d�une guerre sainte, il sera pr�serv� des affres de la mort, sa tombe sera am�nag�e comme des jardins d�Eden. Il ressuscitera aur�ol� de la lumi�re c�leste, sera d�une beaut� comparable � celle du soleil avec le plus beau parfum du monde, il ira au Paradis sans subir aucune �preuve, sans jugement et il sera parmi les Proph�tes dans la vie �ternelle.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 6:
					quickNafila = "<b>6 raakas</b> avec pour chacun d�eux la <b>�Fatiha�<b> suivie de la sourate: <br><br><b>�Khoul Ya Ayouhal K�firouna� (7 fois): </b><br><br>Et la sourate <b>�Khoul Houwa Allahou� (7 fois)</b>";
					nafila = "Nuit du 06 au 07 : <b>6 raakas</b> avec pour chacun d�eux la <b>�Fatiha�<b> suivie de la sourate: <br><br><b>�Khoul Ya Ayouhal K�firouna� (7 fois): </b><br><br>Et la sourate <b>�Khoul Houwa Allahou� (7 fois): </b><br><br>il b�n�ficiera de bienfaits pour chaque verset, il sera consid�r� comme quelqu�un qui aura donn� en aum�ne 1000 dinars ; tous ses voeux seront exauc�s, il b�n�ficiera en outre de b�n�dictions aussi importantes en qualit� que le nombre de jours de l�existence terrestre; pour chaque lettre on lui b�tira une grande maison au paradis.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 7:
					quickNafila = "<b>2 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (12 fois)</b>";
					nafila = "Nuit du 07 au 08: <b>2 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (12 fois): </b><br><br>Pour chaque lettre des sourates employ�es, il b�n�ficiera des b�n�dictions dues � 1000 ans de d�votions. Il sera consid�r� comme quelqu�un qui a donn� � manger � tous les musulmans pendant 70 ans r�volus tout en leur donnant � boire et les habillant. Il aura en outre les b�n�dictions li�es � l�affranchissement de 1000 esclaves; ses voeux seront exauc�s, il ressuscitera avec la lumi�re c�leste. Il traversera �siraat� comme un �clair, il lui sera demand� de sauver de l�enfer (70) personnes qui y �tait destin�es.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 8:
					quickNafila = "<b>4 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Tabatt Yada� (3 fois),</b><br> et de la sourate <b>�Khoul Houwa Allahou� (1 fois)</b>";
					nafila = "Nuit du 08 au 09: <b>4 raakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Tabatt Yada� (3 fois),</b><br> et de la sourate <b>�Khoul Houwa Allahou� (1 fois): </b><br><br>il lui sera accord� les b�n�dictions dues � tous ceux qui acceptent sto�quement les d�crets divins (mounkat yi) et � tous ceux qui manifestent � Dieu leur reconnaissance ; il lui sera accord� les avantages li�s � la lecture du coran tout entier ; il b�n�ficiera des b�n�dictions qui procurent 70 ans de d�votions pendant lesquels il passe les journ�es � je�ner et les nuits � prier. Ordre sera donn� � 2 anges qui se chargeront d�effacer ses p�ch�s et d�enregistrer des bienfaits � son intention et ce jusqu�au jour du jugement dernier. Dieu agr�era ses pri�res et son je�ne et le mettra � l�abri des mauvaises intentions des hommes, il ouvrira � son intention les portes des 8 paradis et le priera d�entrer dans celui de son choix et ce sans le juger.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 9:
					quickNafila = "<b>4 raakas</b> avec chacun d�eux la <b>�Fatiha�</b> suivie du verset: <br><br><b>�Ayatoul Koursiyou�(1 fois),</b><br> et de la sourate <b>�Ina Anzalnahou� (12 fois)</b>";
					nafila = "Nuit du 09 au 10 : <b>4 raakas</b> avec chacun d�eux la <b>�Fatiha�</b> suivie du verset: <br><br><b>�Ayatoul Koursiyou�(1 fois),</b><br> et de la sourate <b>�Ina Anzalnahou� (12 fois): </b><br><br>Avant de quitter le lieu de pri�re, il se verra absous de tous ses p�ch�s sans exclusive ; on lui b�n�ficiera de b�n�dictions �gales � celles de 50 (homme de Dieu) v�ridique, celles de 70 personnes tu�es dans une guerre sainte. Avant sa mort, il go�tera de fruits provenant du paradis, on lui demandera de sauver de l�enfer 70 personnes qui y �taient destin�es.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 10:
					quickNafila = "<b>4 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Anzalnahou� (7 fois), </b><br>des sourates: <br><b>�Khoul Ya Ayouhal K�firouna� (7 fois),</b><br> <b>�Khoul Houwal Allahou� (7 fois)</b>";
					nafila = "Nuit du 10 au 11: <b>4 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Anzalnahou� (7 fois), </b><br>des sourates: <br><b>�Khoul Ya Ayouhal K�firouna� (7 fois),</b><br> <b>�Khoul Houwal Allahou� (7 fois): </b><br><br>apr�s le salut final dire �la hawla wala khouwata ila billah hahilyil azimi�(70 fois) dire ensuite �salatou ala nabi� (70 fois) il sera absous de tous ses p�ch�s sans exclusive ; pour chaque verset il lui sera accord� des faveurs dues aux d�votions d�une ann�e ainsi que celles dues � quelqu�un qui a rendu leur libert� � 1000 esclaves, donn�e en aum�ne 1000 dinars, donn�e � manger � 1000 personnes qui n�ont rein � manger, habill� 1000 personnes se trouvant dans le d�nouement le plus complet. Tout ce qui existe sur terre vivant ou non interc�dera aupr�s de Dieu pour qu�il lui pardonne tous ses p�ch�s, il sera pr�serv� contre toutes les calamit�s, toutes les peines, la faim, l�indigence (sous ses formes: raag) les djinns contre les affres de la mort il ira au paradis sans jugement aucun.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 11:
					quickNafila = "<b>10 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (6 fois)</b>";
					nafila = "Nuit du 11 au 12 : <b>10 raakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (6 fois): </b><br><br>il b�n�ficiera de 8000 �pouses au Paradis et il vivra en compagnie du Proph�te (PSL), il b�n�ficiera en outre des faveurs dues � celui qui a �tudi� le �tawr�te�, �l�indjile� , le �zabor� et le �coran�. Par ailleurs ? il sera absous de tous ses p�ch�s quelle que soit leur importance m�me s�ils �galaient en nombre les vagues de la mer. Il lui sera accord� des b�n�dictions dues � 70 ans de d�votions � Dieu ; il sera consid�r� comme quelqu�un qui a rendu leur libert� � 1000 esclaves ; le jour du jugement dernier on lui donnera l�ordre de sauver de l�enfer autant de personnes qu�il le d�sirera et qui y �taient destin�es. Il ira au Paradis sans �tre jug�.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 12:
					quickNafila = "<b>2 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (5 fois)</b>";
					nafila = "Nuit du 12 au 13 : <b>2 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (5 fois): </b><br><br>on lui b�tira au paradis une grande maison compos�e de 7 chambres tout en or. Dans chacune de ces chambres il y aura 1000 lits avec dans chacun d�eux une �pouse, en outre il b�n�ficiera de b�n�dictions dues � 20 ans pass�s � je�ner et des b�n�dictions dues � 1000 rakas effectu�s.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 13:
					quickNafila = "<b>8 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Izaa Djaha� (7 fois)</b>";
					nafila = "Nuit du 13 au 14 : <b>8 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Izaa Djaha� (7 fois): </b><br><br>il sera consid�r� comme quelqu�un qui a donn� 1000 dinars, affranchi 1000 esclaves, donn� � manger � 1000 personnes n�ayant rien � manger. Dieu agr�era toutes ses pri�res et son je�ne, il sera pr�server contre les maux d�ici et de l�au-del�. Il ne conna�tra pas les inqui�tudes et angoisses du jour du jugement dernier, il lui sera donn� l�ordre de sauver de l�enfer autant de personnes qu�il voudra et qui y �taient destin�es sans qu�elles soient jug�es.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 14:
					quickNafila = "<b>6 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Izaa Djaha�, </b><br>et <b>35 �Khoul Houwa Alahou�</b>";
					nafila = "Nuit du 14 au 15: <b>6 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Izaa Djaha�, </b><br>et <b>35 �Khoul Houwa Alahou�: </b><br><br>Dieu agr�era ses pri�res et son je�ne, tous ses p�ch�s seront reconvertis en bienfaits. Pour chacune des lettres composant les sourates employ�es, il lui sera b�ti en grande cit� au paradis, il sera consid�r� comme quelqu�un qui a donn� � manger aux musulmanes du monde entier.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 15:
					quickNafila = "<b>2 rakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Izaa Zoulzilati� (10 fois)</b>";
					nafila = "Nuit du 15 au 16 : <b>2 rakas</b> avec pour chacun la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Izaa Zoulzilati� (10 fois): </b><br><br>il lui accordera beaucoup de b�n�dictions en outre il sera absous de tous ses p�ch�s par ailleurs, s�il se trouvait dans l�angoisses, Dieu l�en d�livrera, Dieu le pr�servera des calamit�s de ce monde et de l�autre ; il sera pr�serv� contre la pauvret� et verra ses chances d�acquisition de biens multipli�es. Dieu se montrera mis�ricordieux � son �gard. Il ira au paradis en compagnie des plus illustres cr�atures.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 16:
					quickNafila = "<b>12 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de sourates: <br><br><b>�Ina Anzalnahou� (2 fois) </b><br>et <b>�Khoul Houwa Allahou� (2 fois)</b>";
					nafila = "Nuit du 16 au 17 : <b>12 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de sourates: <br><br><b>�ina anzalnahou� (2 fois) </b><br>et <b>�Khoul Houwa Allahou� (2 fois): </b><br><br>il b�n�ficiera de b�n�dictions d�un nombre aussi important que le nombre de tous les croyants, du premier au dernier s�il se trouvait malade, il gu�rira, s�il se trouve endett�. Dieu l�acquittera de ses dettes, s�il se trouvait angoiss�, Dieu l�en gu�rirait et Dieu le pr�servera de l�enfer.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 17:
					quickNafila = "<b>10 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Sabi Hisma� (1 fois), </b><br> <b>�Khoul Ya Ayouhal Kafirouna� (1fois), </b><br> <b>�Khoul Houwa Allahou� (1 fois)</b>";
					nafila = "Nuit du 17 au 18 : <b>10 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Sabi Hisma� (1 fois), </b><br> <b>�Khoul Ya Ayouhal Kafirouna� (1fois), </b><br> <b>�Khoul Houwa Allahou� (1 fois): </b><br><br>il sera consid�r� comme quelqu�un qui d�tiendrait toutes les richesses terrestres et les emploierait au service du Tout-Puissant Dieu. Pour chaque lettre des sourates employ�es, on lui b�tira une grande cit� au paradis, il sera consid�r� comme quelqu�un qui s �est acquitt� de ses devoirs envers le Tout-Puissant pendant 70 ans au cours duquel, il aura pass� les journ�es � je�ner et les nuits � prier ; il sera consid�r� comme quelqu�un qui a lu le coran 100 fois, fait 100 fois le grand p�lerinage et 100 fois le petit p�lerinage, il b�n�ficiera des b�n�dictions dont Dieu seul conna�t l�importance avant de mourir, il lui sera montr� sa demeure au paradis, le jour du jugement dernier, il ne verra rien qui lui fasse peur ou qui l�inqui�te.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 18:
					quickNafila = "<b>6 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (7 fois)</b>";
					nafila = "Nuit du 18 au 19: <b>6 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (7 fois): </b><br><br>Le paradis est la r�compense due � cette pri�re, Dieu pr�servera l�auteur de cette pri�re de tout ce dont il a peur, Dieu exaucera tous ses voeux. Satan �vitera de le rencontrer, il conservera la foi jusqu�� la fin de ses jours, il fera partie des premi�res personnes � entrer au Paradis.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 19:
					quickNafila = "<b>8 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Ina Anzalnahou� (1 fois), </b><br> et <b>�Khoul Houwa Allahou� (3 fois)</b>";
					nafila = "Nuit du 19 au 20 : <b>8 rakas</b> avec pour chacun d�eux la <b>�Fatiha�</b> suivie des sourates: <br><br><b>�ina anzalnahou� (1 fois), </b><br> et <b>�Khoul Houwa Allahou� (3 fois): </b><br><br>il b�n�ficiera des b�n�dictions aussi importantes que celles dues � tout les hommes qui ont fait quelque chose de bien. Il sera absous de tous ses p�ch�s sans exclusive. On le pr�servera de l�obscurit� de la tombe, il ira au Paradis en compagnie du Proph�te Muhammad (PSL).";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 20:
					quickNafila = "<b>4 Rakas</b> dont chacun <b>�Fatiha�</b> suivi de la sourate: <br><br><b>�Khoul Houwa Allahou� (20fois)</b>";
					nafila = "Nuit du 20 au 21 : <b>4 Rakas</b> dont chacun <b>�Fatiha�</b> suivi de la sourate: <br><br><b>�Khoul Houwa Allahou� (20fois): </b><br><br>il sera consid�r� comme quelqu�un qui d�tiendrait tous les biens de la plan�te et les d�penserait au nom de Dieu, comme quelqu�un qui avait lu le �Tawr�te�, le �Injiil� le �Zabaur� et le �Coran�.A sa mort il sera aussi propre (en p�ch�) que le jour de sa naissance.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 21:
					quickNafila = "<b>2 rakas</b> dont chacun <b>�Fatiha�</b> suivi des sourates: <br><br><b>�Sabi Hisma� (3 fois), </b><br><b>�Inna Anzalnabou� (3 fois), </b><br> <b>�Khoul Houwa Allahou� (3 fois), </b><br> <b>�Falakhi� (3 fois), </b><br> <b>�Naassi� (3 fois)</b>";
					nafila = "Nuit du 21 au 22 : <b>2 rakas</b> dont chacun <b>�Fatiha�</b> suivi des sourates: <br><br><b>�Sabi Hisma� (3 fois), </b><br><b>�Inna Anzalnabou� (3 fois), </b><br> <b>�Khoul Houwa Allahou� (3 fois), </b><br> <b>�Falakhi� (3 fois), </b><br> <b>�Naassi� (3 fois): </b><br><br>Dieu b�tira � son intention 70 cit�s avec dans chaque cit� 1000 maisons et dans chaque concession 1000 chambres en or et en argent, dans chaque chambre 1000 lits et dans chaque lit 1000 �pouses.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 22:
					quickNafila = "<b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Izaa Djaha Nassourou Laahi� (5 fois), </b><br><b>�Khoul Houwa Allahou� (5 fois)</b>";
					nafila = "Nuit du 22 au 23 : <b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Izaa Djaha Nassourou Laahi� (5 fois), </b><br><b>�Khoul Houwa Allahou� (5 fois): </b><br><br>il sera absous de tous ses p�ch�s. Ordre sera donn� � 2 anges qui se chargeront d�effacer ses p�ch�s et d�enregistrer � son nom des b�n�dictions pendant une ann�e enti�re. S�il meurt entre temps, il b�n�ficiera des b�n�dictions dues � quelqu�un qui s�est tu� au cours d�une guerre sainte. Il ressuscitera avec une beaut� aussi �clatante que le soleil, il traversera �siraat� comme un �clair.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 23:
					quickNafila = "<b>6 rakas</b> dont chacun <b>�Fatiha�</b> suivi de la sourate: <br><br><b>�Khoul Houwa Allahou� (3 fois)</b>";
					nafila = "Nuit du 23 au 24 : <b>6 rakas</b> dont chacun <b>�Fatiha�</b> suivi de la sourate: <br><br><b>�Khoul Houwa Allahou� (3 fois): </b><br><br>il sera au Paradis sans �tre jug�.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 24:
					quickNafila = "<b>8 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (4 fois)</b>";
					nafila = "Nuit du 24 au 25 : <b>8 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (4 fois): </b><br><br>il sera absous de tous ses p�ch�s avant de quitter le lieu de pri�re, en outre, il ne conna�tra jamais les �preuves (de Dieu).";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 25:
					quickNafila = "<b>10 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Al Q�rihatou� (1 fois), </b><br> <b>�Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il lui est possible";
					nafila = "Nuit du 25 au 26 : <b>10 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Al Q�rihatou� (1 fois), </b><br> <b>�Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il lui est possible, il sera pr�serv� des feux de l�enfer, il sera consid�r� comme quelqu�un qui aura je�n� pendant toute la dur�e de l�existence, il b�n�ficiera des b�n�dictions aussi importantes en quantit� que le nombre de toutes les �toiles et de toutes les feuilles des arbres ; il b�n�ficiera �galement de b�n�dictions capable de couvrir tout l�espace compris entre le ciel et la terre. Le jour de la r�surrection il ira au Paradis escort� des anges Djibril, Mika�l, Izrafil, Izrahil.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 26:
					quickNafila = "<b>12 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Anzalnahou� (10 fois)</b>";
					nafila = "Nuit du 26 au 27 : <b>12 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Ina Anzalnahou� (10 fois): </b><br><br>il b�n�ficiera des b�n�dictions aussi importantes en nombre que le nombre des jours de l�existence terrestre. A la r�surrection, il sera parmi les proph�tes.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 27:
					quickNafila = "<b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Wa Tiini� (5 fois), </b><br>�Khoul Ya Ayouhal Kafirouna� (5 fois) �Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il peut alors";
					nafila = "Nuit du 27 au 28 : <b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Wa Tiini� (5 fois), </b><br><b>�Khoul Ya Ayouhal Kafirouna� (5 fois), </b><br>�Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il peut alors, il sera absous de tous ses p�ch�s.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 28:
					quickNafila = "<b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Wa Tiini� (5 fois), </b><br>�Khoul Ya Ayouhal Kafirouna� (5 fois) �Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il peut alors";
					nafila = "Nuit du 28 au 29 : <b>4 rakas</b> dont chacun <b>�Fatiha�</b> suivie des sourates: <br><br><b>�Wa Tiini� (5 fois), </b><br>�Khoul Ya Ayouhal Kafirouna� (5 fois) �Khoul Houwa Allahou� (5 fois): </b><br><br>Apr�s la pri�re il se repend aussi longtemps qu�il peut alors, il sera absous de tous ses p�ch�s.";
					i.putExtra("nafila", nafila);
					i.putExtra("quickNafila", quickNafila);
					startActivity(i);
					break;
				case 29:
					quickNafila = "<b>6 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (11fois)</b>";
					nafila = "Nuit du 29 au 30 : <b>6 rakas</b> dont chacun <b>�Fatiha�</b> suivie de la sourate: <br><br><b>�Khoul Houwa Allahou� (11fois): </b><br><br>il sera b�ti � son intention une maison au Paradis. De m�me si on effectue 4 rakas dont chacun �Fatiha� suivie des sourates �khoul ya ayouhal k�firouna� (25 fois) �khoul houwa allahou� (25 fois), on sera pr�serv� des feux de l�enfer, on sera absous de tous ses p�ch�s, de m�me ses proches parents on traversera �siraat� comme un �clair.";
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
