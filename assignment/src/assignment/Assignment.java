/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
public class Assignment {
     static int count = 5;
    static int dcount = 4;
     static int recordscount = 0;
    static double fuelprice = 310;
    static double profitprecentage = 0.25;

    static int MAX_CITIES = 30;
    static double[][] allcost = new double[50][6];
    static String ROUTE_FILE = "routes.txt";
    static String DELIVERY_FILE = "deliveries.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] cityname = new String[MAX_CITIES];
        double[][] distance = new double[MAX_CITIES][MAX_CITIES];
        int[][] vehicle = {{1000, 30, 60, 12}, {5000, 40, 50, 6}, {10000, 80, 45, 4}};
        double [][] delivaryrequest = new double [50][9];
        String[] vehicletype = {"Van", "Truck", "Lorry"};
        
        boolean result = true;
        System.out.println("======================================================");
        System.out.println("       Menu-driven logistics management system");
        System.out.println("======================================================");
        while (result) {
            System.out.println("1.City Management");
            System.out.println("2.Distance Management");
            System.out.println("3.Vehicle Management ");
            System.out.println("4.Delivery Request");
            System.out.println("5.Delivery Records");
            System.out.println("6.Performance Reports ");
            System.out.println("7.Save & Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println("---------------------------------------");
            System.out.println("");

            switch (choice) {
                case 1 -> CityManagement(cityname);
                case 2 -> DistanceManagement(distance, cityname);
                case 3 -> VehicleManagement(vehicle, vehicletype);
                case 4 -> Delivaryrequest(delivaryrequest, cityname, vehicle, distance, vehicletype);
                case 5 -> DeliveryRecords(delivaryrequest, allcost, cityname, vehicletype);
                case 6 -> reports(distance, delivaryrequest);
                case 7 -> {
                    saveRoutes(cityname, distance);
                    saveDeliveries(delivaryrequest, allcost);
                    System.out.println("Data saved successfully!");
                    System.out.println("Exiting program...");
                    result = false;
                }
                default -> System.out.println("Invalid number, try again...");
            }
        }
    }
    private static void loadRoutes(String[] cityname, double[][] distance) {
        try {
            File f = new File(ROUTE_FILE);
            if (!f.exists()) {
                System.out.println("No route file found, loading default data...");
                cityname[0] = "colombo";
                cityname[1] = "gampaha";
                cityname[2] = "galle";
                cityname[3] = "kandy";
                cityname[4] = "ja-ela";
                distance[0][1] = distance[1][0] = 36;
                distance[0][2] = distance[2][0] = 130;
                distance[0][3] = distance[3][0] = 122;
                distance[0][4] = distance[4][0] = 25;
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(f));
            count = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < count; i++) {
                cityname[i] = br.readLine().trim();
            }
            for (int i = 0; i < count; i++) {
                String[] line = br.readLine().trim().split(",");
                for (int j = 0; j < count; j++) {
                    distance[i][j] = Double.parseDouble(line[j]);
                }
            }
            br.close();
            System.out.println("Routes loaded successfully from " + ROUTE_FILE);
        } catch (Exception e) {
            System.out.println("Error loading routes: " + e.getMessage());
        }
    }

    private static void saveRoutes(String[] cityname, double[][] distance) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ROUTE_FILE));
            bw.write(count + "\n");
            for (int i = 0; i < count; i++) {
                bw.write(cityname[i] + "\n");
            }
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++) {
                    bw.write(distance[i][j] + (j == count - 1 ? "" : ","));
                }
                bw.newLine();
            }
            bw.close();
            System.out.println("Routes saved successfully to " + ROUTE_FILE);
        } catch (Exception e) {
            System.out.println("Error saving routes: " + e.getMessage());
        }
    }

    private static void loadDeliveries(double[][] delivaryrequest) {
        try {
            File f = new File(DELIVERY_FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            recordscount = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < recordscount; i++) {
                String[] parts = br.readLine().split(",");
                for (int j = 0; j < 8; j++) {
                    delivaryrequest[i][j] = Double.parseDouble(parts[j]);
                }
            }
            br.close();
            System.out.println("Deliveries loaded successfully from " + DELIVERY_FILE);
        } catch (Exception e) {
            System.out.println("Error loading deliveries: " + e.getMessage());
        }
    }

    private static void saveDeliveries(double[][] delivaryrequest, double[][] allcost) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DELIVERY_FILE));
            bw.write(recordscount + "\n");
            for (int i = 0; i < recordscount; i++) {
                for (int j = 0; j < 8; j++) {
                    bw.write(delivaryrequest[i][j] + (j == 7 ? "" : ","));
                }
                bw.newLine();
            }
            bw.close();
            System.out.println("Deliveries saved successfully to " + DELIVERY_FILE);
        } catch (Exception e) {
            System.out.println("Error saving deliveries: " + e.getMessage());
        }
    }

        public static void CityManagement(String[] cityname) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<count;i++){
            System.out.println((i+1)+":"+cityname[i]);
            }
        System.out.println();
        
        boolean result=true;
        System.out.println("---------------------------------------");  
        while(result){ 
         System.out.println("1:Add a new city");
         System.out.println("2:Remove city");
         System.out.println("3:Rename city");
         System.out.println("4:Exsit");
         System.out.print("Enter your choice: ");
         int choice=sc.nextInt();
         System.out.println("---------------------------------------");
         System.out.println("");
         switch(choice){
            case 1 -> {
                addcity(cityname);
                System.out.println("---------------------------------------");
                }
            case 2 -> {                
                removecity(cityname);
                System.out.println("---------------------------------------");
                }
            case 3 -> {
                renamecity(cityname);
                System.out.println("---------------------------------------");
                }
            case 4 -> {
                System.out.println("Exsit.....");
                result=false;
                System.out.println("---------------------------------------");
                }
             default -> {
                 System.out.println("invalid number ....try again...");
                 System.out.println("---------------------------------------");
                }       
         }
         
         
        }
        
    }
    public static void addcity(String[] cityname) {
        Scanner sc = new Scanner(System.in);
        if (count >= MAX_CITIES) { System.out.println("Max cities reached."); return; }
        
        System.out.print("Enter new City name:");                 
        String newcityname=sc.next();
        
        boolean itsnew=true;
        for(int i=0;i<cityname.length;i++){
                     
            if(newcityname.equals(cityname[i])){
                itsnew=false;                        
                }                    
        }
        
        if(itsnew){
            System.out.println("*******");
            cityname[count]=newcityname;
            count=1+count;         
            System.out.println("Success"); 
             System.out.println("");
            for(int i=0;i<count;i++){
            System.out.println((i+1)+":"+cityname[i]);
            }
        }
        else{                    
            System.out.println(newcityname+" is already there");                    
        }
          
        
    }
    public static void removecity(String[] cityname) {
        Scanner sc = new Scanner(System.in);
        
        for(int i=0;i<count;i++){
            System.out.println((i+1)+":"+cityname[i]);
        }
        
        System.out.print("which you need to remove a city number : ");
        int removenumber=sc.nextInt();
        System.out.println(cityname[removenumber-1]+" is removed");
        cityname[removenumber-1]=null;
        
        for(int i=removenumber;i<=count-1;i++){
            cityname[i-1]=cityname[i];    
                }
        
        cityname[count-1]=null;
        count=count-1;  
        
        
    }
    public static void renamecity(String[] cityname) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<count;i++){
            System.out.println((i+1)+":"+cityname[i]);
        }
        System.out.print("which you need to rename a city number : ");
        int renamenumber=sc.nextInt();
        System.out.println("old name : "+cityname[renamenumber-1]);
        System.out.print("new name : ");
        cityname[renamenumber-1]=sc.next();
        System.out.println("Success");
    }

    private static void DistanceManagement(double[][] distance, String[] cityname) {
        
        Scanner sc = new Scanner(System.in);
        boolean result=true;
        System.out.println("---------------------------------------");  
        while(result){ 
         System.out.println("1:Display the distance table");   
         System.out.println("2:Input or edit distances between two cities");
         System.out.println("3:Exsit");
         System.out.print("Enter your choice: ");
         int choice=sc.nextInt();
         System.out.println("---------------------------------------");
         
         
         
         
         switch(choice){
            case 1 -> {
                displaydistances(distance,cityname);
                 
                System.out.println("---------------------------------------");
                }
            case 2 -> {                
                Inputdistances(distance,cityname);
                System.out.println("---------------------------------------");
                }
            
            case 3 -> {
                System.out.println("Exsit.....");
                result=false;
                System.out.println("---------------------------------------");
                }
             default -> {
                 System.out.println("invalid number ....try again...");
                 System.out.println("---------------------------------------");
                }       
         }
         
         
        }
        
        
    }    
    private static void Inputdistances(double[][] distance,String[] cityname) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<count;i++){
            System.out.println((i+1)+":"+cityname[i]);
            }
        System.out.println();
        System.out.println("-------Input Distances-------");
        System.out.print("enter source city number : ");
        int Source =sc.nextInt();
        System.out.print("enter Destination city number : ");
        int Destination=sc.nextInt();        
        if(Source ==Destination){
            System.out.println("Dictance froma city to itself must be 0");
        }
        else{
        System.out.println();
        System.out.print("Enter Distances between two cities(Km) : ");
        distance[Source -1][Destination-1]=sc.nextInt();
        distance[Destination-1][Source -1]=distance[Source -1][Destination-1];
        }
        
    }
    private static void displaydistances(double[][] distance, String[] cityname) {
        System.out.print("\t");
        for(int i=0;i<=count;i++){
            System.out.print(cityname[i]+"\t");   
        }
        System.out.println("");        
        for(int i=0;i<=count;i++){
            System.out.print(cityname[i]+"\t");
            for(int j=0;j<=count;j++){
                System.out.print(distance[i][j]+"\t");
            }
            System.out.println("");  
        }
    }

    private static void VehicleManagement(int[][] vehicle,String []vehicletype) {
    String []tabelhead={"Type","Capacity(Km)","Rate per km (LKR)","Avg Speed (km/h)","Fuel Efficiency (km/l)"};
    
    for(int i=0;i<5;i++){
            System.out.print(tabelhead[i]+"\t");   
        }
    System.out.println("");
    for(int i=0;i<3;i++){
            System.out.print(vehicletype[i]+"\t"); 
            for(int j=0;j<4;j++){
              System.out.print(vehicle[i][j]+"               "+"\t");  
            }
            System.out.println("");
        }
    
      System.out.println("");System.out.println("");  
    }

 
  }
    

