package com.example.yazlab2_2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

public class tsp {

    private static int numberOfNodes;
    private static Stack<Integer> stack;

    public tsp() {
        stack = new Stack<Integer>();
    }

    public static String[] tsp(int[][] adjacencyMatrix, String[] adresler) {
        String yeniAdres [] = new String[15];
        String yeniAdres2 [] = new String[15];
        String yeniAdresler="";
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        //1+ /t
        System.out.print(adresler[1] + "\t");
        yeniAdres[0]=adresler[1];
        System.out.print(yeniAdres[0]);
        yeniAdresler+=yeniAdres[0];

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }

            if (minFlag) {
                int k = 1;
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst + "\t");
                // swap (yerdegistirme)
                String temp = adresler[dst];// sakliyoruz
                adresler[dst] = adresler[k];
                adresler[k] = temp;
                System.out.print(adresler[k] + "\t");
                yeniAdres[k]=adresler[k];
                System.out.print(" "+yeniAdres[k]+" ");
                yeniAdresler+=" "+yeniAdres[k];
                System.out.println("Sonnn:" +yeniAdresler);
                try {
                    yeniAdres2 = yeniAdresler.split("\\s+");
                } catch (PatternSyntaxException ex) {
                    //
                }
                try (FileWriter writer = new FileWriter("adres.txt")) {
                    writer.write(yeniAdresler);

                } catch (IOException e) {
                    System.out.println("Hata:"+e);
                }

                k++;
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        return yeniAdres2;

    }
    public static String[] tersceviri(int[] eleman){
        int i;
        String yenisehirler[] = new String[eleman.length];
        yenisehirler[0]="aa";
        for(i=1;i<eleman.length;i++){
            if(eleman[i]==0){
               yenisehirler[i]="aa";
            }else if(eleman[i]==1){
                yenisehirler[i]="Basiskele";
            }else if(eleman[i]==2){
                yenisehirler[i]="Cayirova";
            }else if(eleman[i]==3){
                yenisehirler[i]="Darica";
            }else if(eleman[i]==4){
                yenisehirler[i]="Derince";
            }else if(eleman[i]==5){
                yenisehirler[i]="Dilovasi";
            }else if(eleman[i]==6){
                yenisehirler[i]="Gebze";
            }else if(eleman[i]==7){
                yenisehirler[i]="Golcuk";
            }else if(eleman[i]==8){
                yenisehirler[i]="Kandira";
            }else if(eleman[i]==9){
                yenisehirler[i]="Karamursel";
            }else if(eleman[i]==10){
                yenisehirler[i]="Kartepe";
            }else if(eleman[i]==11){
                yenisehirler[i]="Korfez";
            } else if(eleman[i]==12){
                yenisehirler[i]="Izmit"; }


        }
        return yenisehirler;
    }
    public static int[] ceviri(String[] ilceler){
        int i;
        int elemanmatris[] = new int[ilceler.length];
       elemanmatris[0]=0;
        for(i=1;i<ilceler.length;i++){
            if(ilceler[i].equals("Basiskele")){
                elemanmatris[i]=1;
            }else if(ilceler[i].equals("Cayirova")){
                elemanmatris[i]=2;
            }else if(ilceler[i].equals("Darica")){
                elemanmatris[i]=3;
            }else if(ilceler[i].equals("Derince")){
                elemanmatris[i]=4;
            }else if(ilceler[i].equals("Dilovasi")){
                elemanmatris[i]=5;
            }else if(ilceler[i].equals("Gebze")){
                elemanmatris[i]=6;
            }else if(ilceler[i].equals("Golcuk")){
                elemanmatris[i]=7;
            }else if(ilceler[i].equals("Kandira")){
                elemanmatris[i]=8;
            }else if(ilceler[i].equals("Karamursel")){
                elemanmatris[i]=9;
            }else if(ilceler[i].equals("Kartepe")){
                elemanmatris[i]=10;
            }else if(ilceler[i].equals("Korfez")){
                elemanmatris[i]=11;
            }else if(ilceler[i].equals("Izmit")){
                elemanmatris[i]=12;

            }


        }
        return elemanmatris;
    }
    public float maliyethesapla(int katsayi, int[] ceviri, int[][] adjacencyMatrix){
        float toplam=0;
        for(int i=0;i<ceviri.length-1;i++){
        toplam=toplam+adjacencyMatrix[ceviri[i]][ceviri[i+1]];}
       return toplam*katsayi;

    }
    public double[][] koordinatceviri(String[] ilceler){
        int i;
        double elemanmatris[][] = new double[ilceler.length][2];

        for(i=0;i<ilceler.length;i++){
            if(ilceler[i].equals("Basiskele")){
                elemanmatris[i][0]=40.6298;
                elemanmatris[i][1]=29.9509;
            }if(ilceler[i].equals("Cayirova")){
                elemanmatris[i][0]=40.8242;
                elemanmatris[i][1]=29.3722;
            }if(ilceler[i].equals("Darica")){
                elemanmatris[i][0]=40.7739;
                elemanmatris[i][1]=29.4003;
            }if(ilceler[i].equals("Derince")){
                elemanmatris[i][0]=40.7562;
                elemanmatris[i][1]=29.8309;
            }if(ilceler[i].equals("Dilovasi")){
                elemanmatris[i][0]=40.7876;
                elemanmatris[i][1]=29.5442;
            }if(ilceler[i].equals("Gebze")){
                elemanmatris[i][0]=40.8025;
                elemanmatris[i][1]=29.4398;
            }if(ilceler[i].equals("Golcuk")){
                elemanmatris[i][0]=40.7168;
                elemanmatris[i][1]=29.8195;
            }if(ilceler[i].equals("Kandira")){
                elemanmatris[i][0]=41.0704;
                elemanmatris[i][1]=30.1524;
            }if(ilceler[i].equals("Karamursel")){
                elemanmatris[i][0]=40.6913;
                elemanmatris[i][1]=29.6164;
            }if(ilceler[i].equals("Kartepe")){
                elemanmatris[i][0]=40.7534;
                elemanmatris[i][1]=30.0232;
            }if(ilceler[i].equals("Korfez")){
                elemanmatris[i][0]=40.7764;
                elemanmatris[i][1]=29.7377;
            }if(ilceler[i].equals("Izmit")){
                elemanmatris[i][0]=40.7654;
                elemanmatris[i][1]=29.9408;
                System.out.println("izmitttt");
            }
        }
       // elemanmatris[i+1][0]=40.8222;
       // elemanmatris[i+1][1]=29.9217;
        return elemanmatris;
    }


    public static int[][] yenimatris(int[] matriseleman, int[][] mesafematrisi){
        int [][]yenimesafematrisi=new int[matriseleman.length][matriseleman.length];

        for(int i=0;i<matriseleman.length;i++){
            for(int j=0;j<matriseleman.length;j++){
                if(i==0){
                    yenimesafematrisi[i][j]=0;
                } else if(j==0){
                    yenimesafematrisi[i][j]=0;
                } else
                    yenimesafematrisi[i][j]=mesafematrisi[matriseleman[i]][matriseleman[j]];
               // System.out.println("yeni matris="+yenimesafematrisi[i][j]);
            }
        }
        return yenimesafematrisi;
    }
    public int[] ilkeleman(int[][] adjacencyMatrix, int[] ceviri){
        int eskiilk=ceviri[1];
        System.out.println("eskiilk="+ceviri[0]);
        int nvalue=0;
        int nindex=0;
        int n=0;
        for(int i=0;i<ceviri.length;i++){
            if(adjacencyMatrix[13][ceviri[i]]>nvalue){
           nvalue=adjacencyMatrix[13][ceviri[i]];
           n=ceviri[i];
           nindex=i;
            }
        }
     ceviri[1]=n;
        ceviri[nindex]= eskiilk;
        return ceviri;
    }
    public static void main(String[] args) {
        String[]   ilceler = new String[]{
                "aa",
                "1Basiskele",
                "2Cayirova",
                "3Darica",
                "4Derince",
                "5Dilovasi",
                "6Gebze",
                "7Golcuk",
                "8Kandira",
                "9Karamursel",
                "10Kartepe",
                "11Korfez",
                "12Izmit"};
        String[]   ilceler2 = new String[]{"aa","Izmit","Korfez","Cayirova"};

        int [] eleman=new int[20];
        int[][]   mesafeMatrixi = new int[][]{
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
        int[][]   mesafeMatrixi2 = new int[20][20];
        tsp a=new tsp();

        eleman= ceviri(ilceler2);
        System.out.println("matrix2="+eleman[0]+"x"+eleman[1]+"x"+eleman[2]);
        mesafeMatrixi2=yenimatris(eleman,mesafeMatrixi);
        String yeniAdres3 [] = new String[15];
        double elemanmatris2[][] = new double[100][2];
        yeniAdres3=tsp(mesafeMatrixi2,ilceler2);
        //System.out.println("yeni adresler="+ilceler[0]+"x"+ilceler[1]+"x"+ilceler[2]+ilceler[3]);
        elemanmatris2= a.koordinatceviri(yeniAdres3);
        for(int i=0;i<elemanmatris2.length;i++){
            for(int j=0;j<2;j++){
               System.out.println("\nkoordinatlar="+elemanmatris2[i][j]);
            }

        }
        double maliyet;
        maliyet=a.maliyethesapla(2,eleman,mesafeMatrixi);
        System.out.println("maliyet="+maliyet);

        int firsteleman[]= a.ilkeleman(mesafeMatrixi,eleman);
        System.out.println("yeni elemanlar="+firsteleman[0]+"x"+firsteleman[1]+"x"+firsteleman[2]);

    }
}