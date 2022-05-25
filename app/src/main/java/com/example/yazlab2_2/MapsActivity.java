package com.example.yazlab2_2;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.yazlab2_2.AdminActivity.*;
import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static boolean arabano2=false;
    boolean arabano3=false;
    boolean arabano4=false;
    MarkerOptions origin, destination,golcuk,basiskele,cayirova,darica,derince,dilovasi,gebze,kandira,karamursel,kartepe,korfez,izmit;
    static String [] allSelected2;
    static int[][]   mesafeMatrixi ;
    static int[] eleman;
    static int[] yenieleman;
    static int[][]   mesafeMatrixi2 ;
    static String[] yeniAdres3;
    static double[][] elemanmatris2;

    static DBHelper2 DB2;
    static ArrayList <String> allSelected;
    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String url;
        //Setting marker to draw route between these two points
       //AdminActivity adminobj=new AdminActivity();

      double elemanmatris3[][]=matrisx();

        golcuk = new MarkerOptions().position(new LatLng(40.7168, 29.8195)).title("golcuk").snippet("golcuk");
        basiskele = new MarkerOptions().position(new LatLng(40.6298, 29.9509)).title("basiskele").snippet("basiskele");
        cayirova = new MarkerOptions().position(new LatLng(40.8242, 29.3722)).title("cayirova").snippet("cayirova");
        darica = new MarkerOptions().position(new LatLng(40.7739, 29.4003)).title("darica").snippet("darica");
        derince = new MarkerOptions().position(new LatLng(40.7562, 29.8309)).title("derince").snippet("derince");
        dilovasi = new MarkerOptions().position(new LatLng(40.7876, 29.5442)).title("dilovasi").snippet("dilovasi");
        gebze = new MarkerOptions().position(new LatLng(40.8025, 29.4398)).title("gebze").snippet("gebze");
        kandira = new MarkerOptions().position(new LatLng(41.0704, 30.1524)).title("kandira").snippet("kandira");
        karamursel = new MarkerOptions().position(new LatLng(40.6913, 29.6164)).title("karamursel").snippet("karamursel");
        kartepe = new MarkerOptions().position(new LatLng(40.7534, 30.0232)).title("kartepe").snippet("kartepe");
        korfez = new MarkerOptions().position(new LatLng(40.7764, 29.7377)).title("korfez").snippet("korfez");
        izmit = new MarkerOptions().position(new LatLng(40.7654, 29.9408)).title("izmit").snippet("izmit");


        for(int i=0;i<elemanmatris3.length-1;i++){
            for(int j=0;j<2;j++){
                System.out.println("\nkoordinatlar2="+elemanmatris3[i][j]);}

        origin = new MarkerOptions().position(new LatLng(elemanmatris3[i][0], elemanmatris3[i][1]));
        destination = new MarkerOptions().position(new LatLng(elemanmatris3[i+1][0], elemanmatris3[i+1][1]));
  ;    DownloadTask downloadTask = new DownloadTask();
       url = getDirectionsUrl(origin.getPosition(), destination.getPosition());
        downloadTask.execute(url);

        }
        origin = new MarkerOptions().position(new LatLng(elemanmatris3[elemanmatris3.length-1][0], elemanmatris3[elemanmatris3.length-1][1]));
        destination = new MarkerOptions().position(new LatLng(40.8222, 29.9217));
        ;    DownloadTask downloadTask = new DownloadTask();
        url = getDirectionsUrl(origin.getPosition(), destination.getPosition());
        downloadTask.execute(url);
        k=0;
        // Start downloading json data from Google Directions API

        arabano2=true;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(destination);
        mMap.addMarker(izmit);
        mMap.addMarker(golcuk);
        mMap.addMarker(derince);
        mMap.addMarker(gebze);
        mMap.addMarker(basiskele);
        mMap.addMarker(dilovasi);
        mMap.addMarker(korfez);
        mMap.addMarker(kandira);
        mMap.addMarker(karamursel);
        mMap.addMarker(kartepe);
        mMap.addMarker(cayirova);
        mMap.addMarker(darica);


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin.getPosition(), 10));
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DataParser parser = new DataParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            k++;
            ArrayList points = new ArrayList();
            PolylineOptions lineOptions = new PolylineOptions();

            for (int i = 0; i < result.size(); i++) {

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                    System.out.println("points is0"+points.get(j));
                }
                if(arabano2==false) {
                    lineOptions.addAll(points);
                    lineOptions.width(12);
                    lineOptions.color(Color.RED);
                    lineOptions.geodesic(true);

                }else
                {
                    lineOptions.addAll(points);
                    lineOptions.width(12);
                    lineOptions.color(Color.BLUE);
                    lineOptions.geodesic(true);
                }

            }

            // Drawing polyline in the Google Map
            if (points.size() != 0)
                mMap.addPolyline(lineOptions);
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        /*String waypoints = "";
        for(int i=0;i<wpoints.size();i++){
            LatLng point  = (LatLng) wpoints.get(i);
            if(i==0)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }*/

        // Setting mode
        String mode = "mode=driving";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode ;
        System.out.println("PARAMETRELERRRR"+parameters);
        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyBvQJk7oqiinKQ7hdT3esY3YlXNpQBHpl0";

        return url;
    }
    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    public double[][] matrisx(){
        DB2 = new DBHelper2(this);
        allSelected = new ArrayList<>();
        tsp tspobj = new tsp();
        mesafeMatrixi = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0, 86626, 80608, 33847, 66072, 74913, 28520, 64336, 46530, 26342, 43439,
                        22670,31100},
                {0,80540, 0, 10154, 49368, 24881, 9580, 64052, 103786, 45573, 65295, 46480,
                        59372,62000},
                {0,74503, 10573, 0, 43331, 18683, 6105, 58015, 97749, 39537, 59258, 35678,
                        53336,59300},
                {0,31991, 49893, 43875, 0, 27558, 38180, 25733, 55039, 43743, 16746, 9591,
                        10823,15600},
                {0,60221, 24112, 18094, 28027, 0, 12399, 53964, 83467, 32883, 44976, 19875,
                        39054,43800},
                {0,69497, 12685, 6011, 38325, 13677, 0, 53009, 92743, 34530, 54252, 30672,
                        48330,53100},
                {0,27152, 64494, 58476, 28881, 61105, 52781, 0, 59370, 19150, 21375, 38473,
                        16624,28600},
                {0,66007, 104615, 98598, 54519, 84061, 92903, 59505, 0, 77515, 47983, 64671,
                        45121,46100},
                {0,45314, 45345, 39327, 47042, 33895, 33632, 18499, 77531, 0, 39537, 54401,
                        34785,43200},
                {0,26487, 72099, 66081, 23171, 51545, 60386, 29585, 51979, 47594, 0, 32154,
                        13773,2100},
                {0,41108, 40964, 34946, 9236, 19437, 29251, 34851, 64673, 49496, 25863, 0,
                        19941,24700},
                {0, 23585, 59897, 53879, 10979, 39343, 48184, 17327, 44770, 35337, 7706, 20571,
                        0,8500},
                {0,31100,61400,59100,15400,43800,52600,28900,46400,42800,16800,25000,9500,0}
        };
        eleman = new int [20];
        yenieleman = new int [20];
        mesafeMatrixi2 = new int[20][20];
        yeniAdres3 = new String [15];
        allSelected = DB2.getRowsAsArrayList("durations");
        allSelected2 = new String[allSelected.size()];
        for (int i = 0; i < allSelected.size(); i++) {
            System.out.println(allSelected.get(i) + "allselected");
            allSelected2[i] = allSelected.get(i);
        }
        elemanmatris2 = new double[allSelected2.length][2];

        eleman = tspobj.ceviri(allSelected2);
        yenieleman=tspobj.ilkeleman(mesafeMatrixi,eleman);
        System.out.println("yeni elemanlar="+yenieleman[0]+"x"+yenieleman[1]+"x"+yenieleman[2]+"x"+yenieleman[3]);

        mesafeMatrixi2 = tspobj.yenimatris(yenieleman, mesafeMatrixi);
        allSelected2=tspobj.tersceviri(yenieleman);
        yeniAdres3 = tspobj.tsp(mesafeMatrixi2, (allSelected2));
        elemanmatris2 = tspobj.koordinatceviri(yeniAdres3);
        for(int i=0;i<elemanmatris2.length;i++){
            for(int j=0;j<2;j++){
                System.out.println("\nkoordinatlar="+elemanmatris2[i][j]);}}


        return elemanmatris2;
    }
}