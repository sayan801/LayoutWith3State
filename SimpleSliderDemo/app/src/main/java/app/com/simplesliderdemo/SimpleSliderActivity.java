package app.com.simplesliderdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.simplesliderdemo.AllCommonClass.AndroidPlotActivity;
import app.com.simplesliderdemo.AllCommonClass.UIslider;

public class SimpleSliderActivity extends FragmentActivity implements View.OnClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{

 String jsonData = "{\"npidata\":[{\"id\":\"1003197856\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS COTHRAN\",\"latitude\":\"41.815649\",\"longitude\":\"-87.665145\",\"fulladdress\":\"4313 S ASHLAND AVE,CHICAGO,IL,US,60609\",\"rating\":1,\"reviewCount\":10,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":1.1705719233072},{\"id\":\"1316931157\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ANTHONY BUIVIDAS, DPM\",\"latitude\":\"41.810535\",\"longitude\":\"-87.710032\",\"fulladdress\":\"4554 S ARCHER AVE,CHICAGO,IL,US,60632\",\"rating\":1,\"reviewCount\":40,\"primaryspecialty\":\"Podiatrist\",\"allspecialty\":\"Podiatrist\",\"distance\":1.1736627009442},{\"id\":\"1043264823\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"CYNTHIA THOMAS, MD\",\"latitude\":\"41.854675\",\"longitude\":\"-87.695541\",\"fulladdress\":\"2001 S CALIFORNIA AVE,CHICAGO,IL,US,60608\",\"rating\":3,\"reviewCount\":31,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine,Pediatrics\",\"distance\":2.9838163802167},{\"id\":\"1780698712\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"DR. SONYA L THOMAS, MD\",\"latitude\":\"41.844168\",\"longitude\":\"-87.730642\",\"fulladdress\":\"4230 W 26TH ST,CHICAGO,IL,US,60623\",\"rating\":5,\"reviewCount\":2,\"primaryspecialty\":\"Obstetrics & Gynecology\",\"allspecialty\":\"Obstetrics & Gynecology\",\"distance\":3.1524253690226},{\"id\":\"1174517411\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS D HUGGETT, MD MPH\",\"latitude\":\"41.852283\",\"longitude\":\"-87.721554\",\"fulladdress\":\"3860 W OGDEN AVE,CHICAGO,IL,US,60623\",\"rating\":2,\"reviewCount\":45,\"primaryspecialty\":\"Family Medicine\",\"allspecialty\":\"Family Medicine\",\"distance\":3.2987094989091},{\"id\":\"1003825662\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS W BOCKLE, M.D.\",\"latitude\":\"41.852283\",\"longitude\":\"-87.721554\",\"fulladdress\":\"3860 W OGDEN AVE,CHICAGO,IL,US,60623\",\"rating\":2,\"reviewCount\":7,\"primaryspecialty\":\"Pediatrics\",\"allspecialty\":\"Pediatrics\",\"distance\":3.2987094989091},{\"id\":\"1083698252\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS W SNICKENBERGER, M.D.\",\"latitude\":\"41.860556\",\"longitude\":\"-87.695731\",\"fulladdress\":\"1501 S CALIFORNIA AVE,CHICAGO,IL,US,60608\",\"rating\":4,\"reviewCount\":50,\"primaryspecialty\":\"Orthopaedic Surgery\",\"allspecialty\":\"Orthopaedic Surgery\",\"distance\":3.3877947984662},{\"id\":\"1932109188\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS A WIDELL, M.D.\",\"latitude\":\"41.860556\",\"longitude\":\"-87.695731\",\"fulladdress\":\"1501 S CALIFORNIA AVE,CHICAGO,IL,US,60608\",\"rating\":5,\"reviewCount\":25,\"primaryspecialty\":\"Emergency Medicine\",\"allspecialty\":\"Emergency Medicine\",\"distance\":3.3877947984662},{\"id\":\"1427044791\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS VARGISH, M.D\",\"latitude\":\"41.860556\",\"longitude\":\"-87.695731\",\"fulladdress\":\"1501 S CALIFORNIA AVE,CHICAGO,IL,US,60608\",\"rating\":2,\"reviewCount\":22,\"primaryspecialty\":\"Physician\",\"allspecialty\":\"Surgery,Surgical Critical Care\",\"distance\":3.3877947984662},{\"id\":\"1922448018\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS GEORGE, M.D.\",\"latitude\":\"41.860571\",\"longitude\":\"-87.695732\",\"fulladdress\":\"1500 SOUTH CALIFORNIA AVENUE,DEPARTMENT OF MEDICINE,CHICAGO,IL,US,60608\",\"rating\":5,\"reviewCount\":19,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":3.388829335798},{\"id\":\"1891101887\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. GEORGE EAPEN THOMAS, MD\",\"latitude\":\"41.861711\",\"longitude\":\"-87.694476\",\"fulladdress\":\"1500 S FAIRFIELD AVE,DEPT OF OBGYN, ROOM F208,CHICAGO,IL,US,60608\",\"rating\":2,\"reviewCount\":49,\"primaryspecialty\":\"Obstetrics & Gynecology\",\"allspecialty\":\"Obstetrics & Gynecology\",\"distance\":3.4594870978501},{\"id\":\"1104176734\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS LEFEBVRE\",\"latitude\":\"41.862573\",\"longitude\":\"-87.695793\",\"fulladdress\":\"1401 S CALIFORNIA BLVD,CHICAGO,IL,US,60608\",\"rating\":3,\"reviewCount\":2,\"primaryspecialty\":\"Physical Therapist\",\"allspecialty\":\"Physical Therapist\",\"distance\":3.526459059575},{\"id\":\"1336383777\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"THOMASINA CANTY, P.T.\",\"latitude\":\"41.831082\",\"longitude\":\"-87.619334\",\"fulladdress\":\"3500 S GILES AVE,CHICAGO,IL,US,60653\",\"rating\":2,\"reviewCount\":36,\"primaryspecialty\":\"Physical Therapist\",\"allspecialty\":\"Physical Therapist\",\"distance\":3.7426222224074},{\"id\":\"1093815458\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS MICHAEL KOMAR, M.D.\",\"latitude\":\"41.802126\",\"longitude\":\"-87.614192\",\"fulladdress\":\"500 E 51ST ST,CHICAGO,IL,US,60615\",\"rating\":4,\"reviewCount\":39,\"primaryspecialty\":\"Surgery\",\"allspecialty\":\"Surgery\",\"distance\":3.8268775444638},{\"id\":\"1548524317\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"DR. KELLY DIANNE THOMAS, PSY.D.\",\"latitude\":\"41.867506\",\"longitude\":\"-87.680297\",\"fulladdress\":\"1100 S HAMILTON AVE,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":15,\"primaryspecialty\":\"Clinical Psychologist\",\"allspecialty\":\"Clinical Psychologist,Addiction (Substance Use Disorder) Counselor\",\"distance\":3.8572170652928},{\"id\":\"1346252608\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS B. OWLEY\",\"latitude\":\"41.866837\",\"longitude\":\"-87.670356\",\"fulladdress\":\"1747 W ROOSEVELT RD,CHICAGO,IL,US,60608\",\"rating\":4,\"reviewCount\":11,\"primaryspecialty\":\"Child & Adolescent Psychiatry Psychiatry & Neurology\",\"allspecialty\":\"Child & Adolescent Psychiatry Psychiatry & Neurology\",\"distance\":3.8932179365181},{\"id\":\"1770522062\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS D STAMOS, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":4,\"reviewCount\":30,\"primaryspecialty\":\"Cardiovascular Disease Internal Medicine\",\"allspecialty\":\"Cardiovascular Disease Internal Medicine\",\"distance\":4.052545056744},{\"id\":\"1346539525\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS JAMES HARTMAN, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":30,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1417375320\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"DR. SANY MARY THOMAS, MBBCH\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":21,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1780001081\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS JAMES FEEHAN, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":43,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1508108739\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DANIEL JAMES THOMAS, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":15,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1184631087\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. KURIAN THOMAS, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,DEPT 3444,CHICAGO,IL,US,60612\",\"rating\":4,\"reviewCount\":6,\"primaryspecialty\":\"Neurology Psychiatry & Neurology\",\"allspecialty\":\"Neurology Psychiatry & Neurology\",\"distance\":4.052545056744},{\"id\":\"1871701201\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"CHINU THOMAS\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":47,\"primaryspecialty\":\"Family Nurse Practitioner\",\"allspecialty\":\"Family Nurse Practitioner\",\"distance\":4.052545056744},{\"id\":\"1336467968\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS RYAN ALCORN, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":42,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1316365869\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ALEXANDER MORRISON, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":46,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1760595813\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"JAMES REGAN THOMAS, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":34,\"primaryspecialty\":\"Otolaryngology\",\"allspecialty\":\"Otolaryngology\",\"distance\":4.052545056744},{\"id\":\"1699193409\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"DR. KENYA THOMAS\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":25,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.052545056744},{\"id\":\"1811251820\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"MERINA THOMAS, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":43,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program,Surgery\",\"distance\":4.052545056744},{\"id\":\"1093835274\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS LAYDEN, M.D.\",\"latitude\":\"41.869204\",\"longitude\":\"-87.670376\",\"fulladdress\":\"1740 W TAYLOR ST,DEPT 3462,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":39,\"primaryspecialty\":\"Gastroenterology Internal Medicine\",\"allspecialty\":\"Gastroenterology Internal Medicine\",\"distance\":4.052545056744},{\"id\":\"1831176338\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS M TAMLYN, MD\",\"latitude\":\"41.847033\",\"longitude\":\"-87.623706\",\"fulladdress\":\"2525 S MICHIGAN AVE,12TH FLR,CHICAGO,IL,US,60616\",\"rating\":2,\"reviewCount\":42,\"primaryspecialty\":\"Cardiovascular Disease Internal Medicine\",\"allspecialty\":\"Cardiovascular Disease Internal Medicine\",\"distance\":4.0756650265475},{\"id\":\"1639182868\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS E. WRIGHT\",\"latitude\":\"41.870216\",\"longitude\":\"-87.671450\",\"fulladdress\":\"912 S WOOD ST,CHICAGO,IL,US,60612\",\"rating\":4,\"reviewCount\":28,\"primaryspecialty\":\"Child & Adolescent Psychiatry Psychiatry & Neurology\",\"allspecialty\":\"Child & Adolescent Psychiatry Psychiatry & Neurology\",\"distance\":4.1095119703819},{\"id\":\"1578787354\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS JOBE, MD\",\"latitude\":\"41.870216\",\"longitude\":\"-87.671450\",\"fulladdress\":\"912 S WOOD ST,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":18,\"primaryspecialty\":\"Psychiatry Psychiatry & Neurology\",\"allspecialty\":\"Psychiatry Psychiatry & Neurology\",\"distance\":4.1095119703819},{\"id\":\"1952329161\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS R STELMACK, O.D.\",\"latitude\":\"41.871237\",\"longitude\":\"-87.676376\",\"fulladdress\":\"820 S DAMEN AVE,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":38,\"primaryspecialty\":\"Optometrist\",\"allspecialty\":\"Optometrist\",\"distance\":4.1365818583717},{\"id\":\"1700290863\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS W ENGEL, M.D.\",\"latitude\":\"41.871516\",\"longitude\":\"-87.673764\",\"fulladdress\":\"1900 W POLK ST,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":3,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.1760342831924},{\"id\":\"1831459411\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS JOHN OGRADY, D.O.\",\"latitude\":\"41.871516\",\"longitude\":\"-87.673764\",\"fulladdress\":\"1900 W POLK ST,SUITE 465,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":18,\"primaryspecialty\":\"Urology\",\"allspecialty\":\"Urology\",\"distance\":4.1760342831924},{\"id\":\"1366504326\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS TRIANTAFILLOU, M.D.\",\"latitude\":\"41.846716\",\"longitude\":\"-87.617322\",\"fulladdress\":\"2545 S KING DR,CHICAGO,IL,US,60616\",\"rating\":4,\"reviewCount\":14,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.3320307334393},{\"id\":\"1205085255\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ADAM MESSER, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,DIVISION OF BURNS, ROOM 3229,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":8,\"primaryspecialty\":\"Surgical Critical Care\",\"allspecialty\":\"Surgical Critical Care\",\"distance\":4.3414412308036},{\"id\":\"1972511632\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS EDWARD LAD, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":13,\"primaryspecialty\":\"Hematology & Oncology Internal Medicine\",\"allspecialty\":\"Hematology & Oncology Internal Medicine\",\"distance\":4.3414412308036},{\"id\":\"1760408090\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"MS. SONIA SENU THOMAS, NP\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":22,\"primaryspecialty\":\"Primary Care Nurse Practitioner\",\"allspecialty\":\"Primary Care Nurse Practitioner\",\"distance\":4.3414412308036},{\"id\":\"1841529021\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"CHRISTINA GRACE THOMAS, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":17,\"primaryspecialty\":\"Pediatrics\",\"allspecialty\":\"Pediatrics\",\"distance\":4.3414412308036},{\"id\":\"1073951463\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ADAM CRISWELL, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":22,\"primaryspecialty\":\"Emergency Medicine\",\"allspecialty\":\"Emergency Medicine\",\"distance\":4.3414412308036},{\"id\":\"1013001064\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"TIN TIN THOMAS, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":48,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.3414412308036},{\"id\":\"1851321467\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"BONNIE WILLIAM THOMAS, MD\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":13,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.3414412308036},{\"id\":\"1568894772\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS DOBOSZ, M.D.\",\"latitude\":\"41.874067\",\"longitude\":\"-87.674832\",\"fulladdress\":\"1901 W HARRISON ST,CHICAGO,IL,US,60612\",\"rating\":4,\"reviewCount\":29,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.3414412308036},{\"id\":\"1730593096\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"NATASHA THOMAS, M.D.\",\"latitude\":\"41.873707\",\"longitude\":\"-87.671548\",\"fulladdress\":\"627 S WOOD ST # 835,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":49,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.3452321466107},{\"id\":\"1215089982\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS A DEUTSCH, M.D.\",\"latitude\":\"41.874145\",\"longitude\":\"-87.669913\",\"fulladdress\":\"1725 W HARRISON ST,SUITE 918,CHICAGO,IL,US,60612\",\"rating\":2,\"reviewCount\":22,\"primaryspecialty\":\"Ophthalmology\",\"allspecialty\":\"Ophthalmology\",\"distance\":4.3913643500046},{\"id\":\"1669560314\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ROY WITT, MD\",\"latitude\":\"41.874145\",\"longitude\":\"-87.669913\",\"fulladdress\":\"1725 W HARRISON,SUITE 409,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":20,\"primaryspecialty\":\"Physician\",\"allspecialty\":\"Surgery,Surgical Oncology\",\"distance\":4.3913643500046},{\"id\":\"1326363409\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS RICHARD O'TOOLE, M.D.\",\"latitude\":\"41.874160\",\"longitude\":\"-87.669111\",\"fulladdress\":\"600 S PAULINA ST,527 ACFAC,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":29,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":4.4009864201124},{\"id\":\"1235138546\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS J NIELSEN, M.D.\",\"latitude\":\"41.874177\",\"longitude\":\"-87.667954\",\"fulladdress\":\"1611 W HARRISON ST,SUITE 550,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":19,\"primaryspecialty\":\"Otolaryngology\",\"allspecialty\":\"Otolaryngology,Plastic Surgery within the Head & Neck Otolaryngology\",\"distance\":4.4151989466301},{\"id\":\"1003016585\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS MICHAEL DONAHUE, MD\",\"latitude\":\"41.875150\",\"longitude\":\"-87.668325\",\"fulladdress\":\"1653 W CONGRESS PKWY,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":46,\"primaryspecialty\":\"Surgery\",\"allspecialty\":\"Surgery\",\"distance\":4.476508709585},{\"id\":\"1962486480\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS BETLEJ, MD\",\"latitude\":\"41.875150\",\"longitude\":\"-87.668325\",\"fulladdress\":\"1653 W CONGRESS PKWY,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":27,\"primaryspecialty\":\"Anatomic Pathology & Clinical Pathology\",\"allspecialty\":\"Anatomic Pathology & Clinical Pathology,Specialist\",\"distance\":4.476508709585},{\"id\":\"1811377757\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS VIKTORAS QUINN, M.D.\",\"latitude\":\"41.875150\",\"longitude\":\"-87.668325\",\"fulladdress\":\"1653 W CONGRESS PKWY,CHICAGO,IL,US,60612\",\"rating\":4,\"reviewCount\":18,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.476508709585},{\"id\":\"1033524608\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS JOHN KAMINSKY, M.D.\",\"latitude\":\"41.875150\",\"longitude\":\"-87.668325\",\"fulladdress\":\"1653 W CONGRESS PKWY,CHICAGO,IL,US,60612\",\"rating\":3,\"reviewCount\":11,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.476508709585},{\"id\":\"1548288525\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS P BLECK, MD\",\"latitude\":\"41.875283\",\"longitude\":\"-87.669112\",\"fulladdress\":\"600 S PAULINA ST,544 AF,CHICAGO,IL,US,60612\",\"rating\":1,\"reviewCount\":48,\"primaryspecialty\":\"Neurology Psychiatry & Neurology\",\"allspecialty\":\"Neurology Psychiatry & Neurology,Critical Care Medicine Internal Medicine\",\"distance\":4.4768206649243},{\"id\":\"1922203496\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"STEPHEN THOMAS, M.D.\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S MARYLAND AVE,MC 2026,CHICAGO,IL,US,60637\",\"rating\":1,\"reviewCount\":43,\"primaryspecialty\":\"Diagnostic Radiology\",\"allspecialty\":\"Diagnostic Radiology\",\"distance\":4.5340105876703},{\"id\":\"1447403449\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS CANTEY, M.D.\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S MARYLAND AVE,MC3077,CHICAGO,IL,US,60637\",\"rating\":5,\"reviewCount\":26,\"primaryspecialty\":\"Psychiatry Psychiatry & Neurology\",\"allspecialty\":\"Psychiatry Psychiatry & Neurology\",\"distance\":4.5340105876703},{\"id\":\"1083052880\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS XUEFENG LU, MD, PHD\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S MARYLAND AVE,MC 7082,CHICAGO,IL,US,60637\",\"rating\":1,\"reviewCount\":44,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":4.5340105876703},{\"id\":\"1275528499\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS L FISHER, MD., M.P.H\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S MARYLAND AVE,CHICAGO,IL,US,60637\",\"rating\":4,\"reviewCount\":19,\"primaryspecialty\":\"Emergency Medicine\",\"allspecialty\":\"Emergency Medicine\",\"distance\":4.5340105876703},{\"id\":\"1962841684\",\"type\":\"physician\",\"gender\":\"F\",\"fullname\":\"ASHLEY LYNN THOMAS, M.D.\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S MARYLAND AVE,MC 7082,CHICAGO,IL,US,60637\",\"rating\":2,\"reviewCount\":22,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine,Pediatrics\",\"distance\":4.5340105876703},{\"id\":\"1619357639\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS PETER COURI, M.D.\",\"latitude\":\"41.788784\",\"longitude\":\"-87.604956\",\"fulladdress\":\"5841 S. MARYLAND AVE. MC 7082,CHICAGO,IL,US,60637\",\"rating\":5,\"reviewCount\":38,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine,Pediatrics\",\"distance\":4.5340105876703},{\"id\":\"1639280530\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS R MIZEN, M.D.\",\"latitude\":\"41.793937\",\"longitude\":\"-87.773935\",\"fulladdress\":\"6187 S ARCHER AVE,SUITE 101,CHICAGO,IL,US,60638\",\"rating\":1,\"reviewCount\":39,\"primaryspecialty\":\"Ophthalmology\",\"allspecialty\":\"Ophthalmology\",\"distance\":4.6313621427883},{\"id\":\"1700222072\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS W MADER\",\"latitude\":\"41.870524\",\"longitude\":\"-87.627288\",\"fulladdress\":\"8 E 9TH ST,APT 1701,CHICAGO,IL,US,60605\",\"rating\":1,\"reviewCount\":4,\"primaryspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"allspecialty\":\"Student in an Organized Health Care Education\\/Training Program\",\"distance\":5.0933755258563},{\"id\":\"1427073543\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS LEE FISHER, M.D.\",\"latitude\":\"41.872276\",\"longitude\":\"-87.629604\",\"fulladdress\":\"47 W POLK ST LBBY G1,CHICAGO,IL,US,60605\",\"rating\":3,\"reviewCount\":33,\"primaryspecialty\":\"Dermatology\",\"allspecialty\":\"Dermatology,Dermatopathology Dermatology,Procedural Dermatology Dermatology\",\"distance\":5.1200119062476},{\"id\":\"1730114505\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS ANDRE MASON, M.D.\",\"latitude\":\"41.779335\",\"longitude\":\"-87.596174\",\"fulladdress\":\"6337 S WOODLAWN AVE,CHICAGO,IL,US,60637\",\"rating\":3,\"reviewCount\":50,\"primaryspecialty\":\"Internal Medicine\",\"allspecialty\":\"Internal Medicine\",\"distance\":5.2075074456864},{\"id\":\"1588780993\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS DZIELAWSKI, D.C.\",\"latitude\":\"41.748955\",\"longitude\":\"-87.752354\",\"fulladdress\":\"5225 W 79TH ST,BURBANK,IL,US,60459\",\"rating\":4,\"reviewCount\":36,\"primaryspecialty\":\"Chiropractor\",\"allspecialty\":\"Chiropractor\",\"distance\":5.4921034570825},{\"id\":\"1437129178\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"MR. THOMAS KLUSS, OD\",\"latitude\":\"41.880509\",\"longitude\":\"-87.744279\",\"fulladdress\":\"4758 W MADISON,CHICAGO,IL,US,60644\",\"rating\":5,\"reviewCount\":31,\"primaryspecialty\":\"Optometrist\",\"allspecialty\":\"Optometrist\",\"distance\":5.5723675737128},{\"id\":\"1437427127\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS R BENTHIEN, O.D.\",\"latitude\":\"41.881337\",\"longitude\":\"-87.630865\",\"fulladdress\":\"50 S CLARK ST,CHICAGO,IL,US,60603\",\"rating\":1,\"reviewCount\":25,\"primaryspecialty\":\"Optometrist\",\"allspecialty\":\"Optometrist\",\"distance\":5.6076043441269},{\"id\":\"1508903782\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS BRITT, MD\",\"latitude\":\"41.747295\",\"longitude\":\"-87.618051\",\"fulladdress\":\"8106 S. PRAIRIE AVE.,CHICAGO,IL,US,60619\",\"rating\":3,\"reviewCount\":28,\"primaryspecialty\":\"Diagnostic Radiology\",\"allspecialty\":\"Diagnostic Radiology\",\"distance\":5.717077361229},{\"id\":\"1356656128\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS FARMER, PSY.D.\",\"latitude\":\"41.881898\",\"longitude\":\"-87.624292\",\"fulladdress\":\"18 S MICHIGAN AVE,SUITE 400,CHICAGO,IL,US,60603\",\"rating\":5,\"reviewCount\":5,\"primaryspecialty\":\"Clinical Psychologist\",\"allspecialty\":\"Clinical Psychologist\",\"distance\":5.8223063467358},{\"id\":\"1720100928\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"DR. THOMAS PATRICK COGAN, PHD\",\"latitude\":\"41.883491\",\"longitude\":\"-87.624331\",\"fulladdress\":\"30 N MICHIGAN AVE,SUITE 1114,CHICAGO,IL,US,60602\",\"rating\":3,\"reviewCount\":4,\"primaryspecialty\":\"Psychologist\",\"allspecialty\":\"Psychologist\",\"distance\":5.9129025150675},{\"id\":\"1295765485\",\"type\":\"physician\",\"gender\":\"M\",\"fullname\":\"THOMAS E. CULLINS, M.D.\",\"latitude\":\"41.748598\",\"longitude\":\"-87.605163\",\"fulladdress\":\"8046 S COTTAGE GROVE AVE,CHICAGO,IL,US,60619\",\"rating\":3,\"reviewCount\":47,\"primaryspecialty\":\"Gastroenterology Internal Medicine\",\"allspecialty\":\"Gastroenterology Internal Medicine\",\"distance\":6.0885151038845}],\"status\":1}";

    public String[] firstName, providerNpiID, lat, longg, address;
    GoogleMap map;
    TextView middle;
    ListView listView;// = new ListView(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);
        middle = (TextView) findViewById(R.id.TV_middle);

        ArrayList<String> stringArray = new ArrayList<String>();

        // ListView Item
        for (int i=0; i<50 ; i++)
            stringArray.add(i + " no Item");
        listView = new ListView(this);

        //find TOP Linear Layout to add container
        ViewGroup inclusionViewGroup_top = (ViewGroup)findViewById(R.id.LL_top);
        //Load child/includable XML

        //  View child1 = LayoutInflater.from(this).inflate(R.layout.map_view, null);
        //Load child/includable XML -> sub controler
        View childLayout_map = LayoutInflater.from(this).inflate(R.layout.map_view,
                (ViewGroup) findViewById(R.id.map));

        //add map child/includable view
        inclusionViewGroup_top.addView(childLayout_map);

        //BOTTOM view
        //find Bottom Linear Layout container
        ViewGroup inclusionViewGroup_bottom = (ViewGroup)findViewById(R.id.LL_bottom);

        listView.setId(R.id.layout1);
        listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        //add map child/includable view
        inclusionViewGroup_bottom.addView(listView);

        Double providerLatitude = 0.0, providerLongitude = 0.0;
        //map
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if(map != null)
        {
            map.setOnMarkerClickListener(this);
            map.setOnInfoWindowClickListener(this);
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("npidata");

            //set Array Size;
            firstName = new String[jsonArray.length()];
            providerNpiID = new String[jsonArray.length()];
            lat = new String[jsonArray.length()];
            longg = new String[jsonArray.length()];
            address = new String[jsonArray.length()];

            //set counted search result
            middle.setText("  " + jsonArray.length() + " Data found");

            JSONObject jsonObject1;
            for (int i = 0; i < jsonArray.length(); i++)
            {
                try
                {
                    jsonObject1 = jsonArray.getJSONObject(i);

                    // getting  provider Name
                    if (jsonObject1.has("fullname"))
                    {
                        firstName[i] = jsonObject1.getString("fullname");
                    }
                    else
                    {
                        firstName[i] = "Unknown Name";
                    }
                    if (jsonObject1.has("fulladdress"))
                    {
                        address[i] = jsonObject1.getString("fulladdress");
                    }
                    else
                    {
                        address[i] = "Unknown Address";
                    }

                    // for Lat Common code for all like NAME & ALSO HOSPITAL
                    if (jsonObject1.has("latitude"))
                    {
                        if(String.valueOf(jsonObject1.getDouble("latitude")).equals("Unknown") ||
                                String.valueOf(jsonObject1.getDouble("latitude")).equals("OVER_QUERY_LIMIT"))
                        {
                            lat[i] = String.valueOf(0.0);
                        }
                        else
                        {
                            lat[i] = String.valueOf(jsonObject1.getDouble("latitude"));
                        }
                    }
                    else
                    {
                        lat[i] = String.valueOf(0.0);
                    }
                    // for Long
                    if (jsonObject1.has("longitude"))
                    {
                        if(String.valueOf(jsonObject1.getDouble("longitude")).equals("Unknown") ||
                                String.valueOf(jsonObject1.getDouble("longitude")).equals("OVER_QUERY_LIMIT"))
                        {
                            longg[i] = String.valueOf(0.0);
                        }
                        else
                        {
                            longg[i] = String.valueOf(jsonObject1.getDouble("longitude"));
                        }
                    }
                    else
                    {
                        longg[i] = String.valueOf(0.0);
                    }
                }
                catch (Exception e)
                {
                    Log.d("CRASH 141 ", e.toString());
                }

                providerLatitude = Double.parseDouble(lat[i]);
                providerLongitude = Double.parseDouble(longg[i]);
                if(map != null)
                {
                    map.addMarker(new MarkerOptions()
                                    .position(new LatLng(providerLatitude, providerLongitude))
                                    .title(firstName[i])
                                    .snippet(address[i])
                        /*.icon(IconMarkerplot)*/);
                }


            }

            if(map != null)
            {
                //1st marker as Center position
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.parseDouble(lat[0]), Double.parseDouble(longg[0])), 9));
            }

        }
        catch (Exception e)
        {
            Log.d("CRASH 161 ", e.toString());
        }



        //Listview Code
        listView = (ListView) findViewById(R.id.layout1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
        @Override
        public void onItemClick(AdapterView<?> argo, View view, int i, long l)
        {
            try
            {
                Toast.makeText(getApplicationContext(),"Click on " + i,Toast.LENGTH_SHORT).show();
            }
            catch (Exception excp)
            {
                //
            }
        }
    });
        listView.setAdapter(new ProviderSearchResultAdapter(getApplicationContext()));
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {

    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        try
        {
            marker.showInfoWindow();
            String m = marker.getId();
            m = m.replace("m","");
            final int alfaValue =  Integer.valueOf(m);
//            alfaValue = i ; //assign value

            //auto-Scroll the ListView position
            listView.smoothScrollToPositionFromTop(alfaValue, alfaValue);
            //programatically click on ListView row Item for corresponding marker Click
            listView.performItemClick(listView.getAdapter().getView(alfaValue, null, null), alfaValue, listView.getItemIdAtPosition(alfaValue));

        }
        catch (Exception error)
        {
            Log.d("CRASH","CRASH 485 > "+error.toString());
            error.printStackTrace();
        }
        return false;
    }

    /* ****** Custom BaseAdapter ****** */
    public class ProviderSearchResultAdapter extends BaseAdapter
    {
        Context context;

        public ProviderSearchResultAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getCount()
        {
            return firstName.length;
        }

        @Override
        public Object getItem(int position)
        {
            return position;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getViewTypeCount()
        {
            return getCount();
        }

        @Override
        public int getItemViewType(int position)
        {
            return position;
        }
        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.list_item_adapter, null);

            TextView FirstName = (TextView) convertView.findViewById(R.id.provider);
            TextView BusinessAddress = (TextView) convertView.findViewById(R.id.provider_address);

            FirstName.setText(firstName[position]+"");
            BusinessAddress.setText(address[position]+"");
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android_plot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void goto_android_plot(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AndroidPlotActivity.class);
        startActivity(intent);
    }
}
