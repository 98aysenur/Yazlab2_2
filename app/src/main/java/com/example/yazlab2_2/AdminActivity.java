package com.example.yazlab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class AdminActivity extends AppCompatActivity {
    Spinner dropdown;
    Button btnrota,btnrota2;
    Button btnsave;
    String selected;
    EditText number3;
    static String [] allSelected2;
    static int[][]   mesafeMatrixi ;
    static int[] eleman;
    static int[] yenieleman;
    static int[][]   mesafeMatrixi2 ;
    static String[] yeniAdres3;
    static double[][] elemanmatris2;

    static DBHelper2 DB2;
    static DBHelper3 DB3;
    static ArrayList <String> allSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dropdown = findViewById(R.id.spinner2);
        number3 = (EditText) findViewById(R.id.number);

        String[] items = new String[]{"seciniz...", "Basiskele", "Cayirova", "Darica",
                "Derince", "Dilovasi", "Gebze", "Golcuk", "Kandira", "Karamursel",
                "Kartepe", "Korfez", "Izmit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.
                simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        DB2 = new DBHelper2(this);
        DB3 = new DBHelper3(this);
        allSelected = new ArrayList<>();
        mesafeMatrixi = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0, 86626, 80608, 33847, 66072, 74913, 28520, 64336, 46530, 26342, 43439,
                        22670},
                {0,80540, 0, 10154, 49368, 24881, 9580, 64052, 103786, 45573, 65295, 46480,
                        59372},
                {0,74503, 10573, 0, 43331, 18683, 6105, 58015, 97749, 39537, 59258, 35678,
                        53336},
                {0,31991, 49893, 43875, 0, 27558, 38180, 25733, 55039, 43743, 16746, 9591,
                        10823},
                {0,60221, 24112, 18094, 28027, 0, 12399, 53964, 83467, 32883, 44976, 19875,
                        39054},
                {0,69497, 12685, 6011, 38325, 13677, 0, 53009, 92743, 34530, 54252, 30672,
                        48330},
                {0,27152, 64494, 58476, 28881, 61105, 52781, 0, 59370, 19150, 21375, 38473,
                        16624},
                {0,66007, 104615, 98598, 54519, 84061, 92903, 59505, 0, 77515, 47983, 64671,
                        45121},
                {0,45314, 45345, 39327, 47042, 33895, 33632, 18499, 77531, 0, 39537, 54401,
                        34785},
                {0,26487, 72099, 66081, 23171, 51545, 60386, 29585, 51979, 47594, 0, 32154,
                        13773},
                {0,41108, 40964, 34946, 9236, 19437, 29251, 34851, 64673, 49496, 25863, 0,
                        19941},
                {0, 23585, 59897, 53879, 10979, 39343, 48184, 17327, 44770, 35337, 7706, 20571,
                        0}
        };
        tsp tspobj = new tsp();
        eleman = new int [20];
        mesafeMatrixi2 = new int[20][20];
        yeniAdres3 = new String [15];


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selected = (String) dropdown.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              //  choosen = null;
            }

        });

        btnsave = (Button) findViewById(R.id.kaydet);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTextFromET = number3.getText().toString();
                int number2 = new Integer(sTextFromET).intValue();
                System.out.println("sayı==="+number2);
                if(!DB2.CheckIsInDB(selected)) {   // veri tabanında yoksa kaydet
                    DB2.insertData(selected);
                    DB2.getCount(selected,number2);

                   // all = DB2.getTableAsString("durations");
                   // System.out.println(all);

                } else{
                    DB2.getCount(selected,number2);
                }


            }
        });

        allSelected = DB2.getRowsAsArrayList("durations");
        allSelected2 = new String[allSelected.size()];
         DB3.insertData();
        for (int i = 0; i < allSelected.size(); i++) {
            System.out.println(allSelected.get(i) + "ALLSELECTED");
            allSelected2[i] = allSelected.get(i);
        }



        btnrota = (Button) findViewById(R.id. btnadminrota);
        btnrota.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);



            }});
        btnrota2 = (Button) findViewById(R.id.rota);
        btnrota2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {



               /* allSelected = DB2.getRowsAsArrayList("durations");
                allSelected2 = new String[allSelected.size()];
                for (int i = 0; i < allSelected.size(); i++) {
                    System.out.println(allSelected.get(i) + " ");
                    allSelected2[i] = allSelected.get(i);
                }
                elemanmatris2 = new double[allSelected2.length][2];

                eleman = tspobj.ceviri(allSelected2);

                mesafeMatrixi2 = tspobj.yenimatris(eleman, mesafeMatrixi);
                yeniAdres3 = tspobj.tsp(mesafeMatrixi2, (allSelected2));
                elemanmatris2 = tspobj.koordinatceviri(yeniAdres3);
                for(int i=0;i<elemanmatris2.length;i++){
                    for(int j=0;j<2;j++){
                        System.out.println("\nkoordinatlar="+elemanmatris2[i][j]);}}*/
            }});


    }
public static double[][] matrisx(){
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
        System.out.println(allSelected.get(i) + " ");
        allSelected2[i] = allSelected.get(i);
    }
    elemanmatris2 = new double[allSelected2.length][2];

    eleman = tspobj.ceviri(allSelected2);
    yenieleman=tspobj.ilkeleman(mesafeMatrixi,eleman);

    mesafeMatrixi2 = tspobj.yenimatris(yenieleman, mesafeMatrixi);
    yeniAdres3 = tspobj.tsp(mesafeMatrixi2, (allSelected2));
    elemanmatris2 = tspobj.koordinatceviri(yeniAdres3);
    for(int i=0;i<elemanmatris2.length;i++){
        for(int j=0;j<2;j++){
            System.out.println("\nkoordinatlar="+elemanmatris2[i][j]);}}


return elemanmatris2;
}

}
