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
    static int MAX_CITIES = 30;
    static int recordscount = 0;
    static double fuelprice = 310;
    static double profitprecentage = 0.25;

    static double totaltime = 0;
    static double totalprofit = 0;
    static double totalditance = 0;

    static double[][] allcost = new double[50][6];

    static String ROUTE_FILE = "routes.txt";
    static String DELIVERY_FILE = "deliveries.txt";

    public static void main(String[] args) {
        String[] cityname = new String[MAX_CITIES];
        double[][] distance = new double[MAX_CITIES][MAX_CITIES];
        int[][] vehicle = {{1000, 30, 60, 12}, {5000, 40, 50, 6}, {10000, 80, 45, 4}};
        double [][] delivaryrequest = new double [50][9];
        String[] vehicletype = {"Van", "Truck", "Lorry"};
        boolean result = true;
        Scanner sc = new Scanner(System.in);

        // Load data from files if they exist
        loadRoutes(cityname, distance);
        loadDeliveries(delivaryrequest);

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

    

    private static void Delivaryrequest(double[][] delivaryrequest,String []cityname,int[][] vehicle,double [][]distance,String []vehicletype) {
        boolean result=true;
        if(recordscount<50){
            
            Scanner sc = new Scanner(System.in);
        
            for(int i=0;i<count;i++){
                System.out.println((i+1)+":"+cityname[i]);
            }
        
            System.out.print("Enter source city index :\t");
            delivaryrequest[recordscount][0]=sc.nextInt();
            System.out.println("");
        
            while(result){
                System.out.print("Enter Destination city index :\t ");
                delivaryrequest[recordscount][1]=sc.nextInt();
                if ( delivaryrequest[recordscount][1]>count){
                    System.out.println("it is not valid");  
                }
                else{                   
                     if (distance[(int)delivaryrequest[recordscount][0]-1][(int)delivaryrequest[recordscount][1]-1] ==0 ){
                        System.out.println("Distance is null");
                       
                    }
                    else{
                        if (delivaryrequest[recordscount][0]==delivaryrequest[recordscount][1]){
                        System.out.println("it is not valid");
                       
                    }
                    else{
                        result=false;
                    } 
                    } 
                }
                
            }
            result=true;
            while(result){
                System.out.println("");System.out.println("");        
                System.out.println("1.Van");
                System.out.println("2.Truck");
                System.out.println("3.Lory");        
                System.out.print("Enter Vehicle type :\t");
                delivaryrequest[recordscount][2]=sc.nextInt();
            
                if(delivaryrequest[recordscount][2]<4 && delivaryrequest[recordscount][2]>0){
                    System.out.println("capacity: "+vehicle[(int)delivaryrequest[recordscount][2]-1][0]+"kg");
                    result=false;   
                }
                else{
                    System.out.println("it is not valid"); 
                }
            }
            result=true;
            while(result){
                System.out.print("Enter Weight (in kg) :\t");
                delivaryrequest[recordscount][3]=sc.nextInt();
                if(vehicle[(int)delivaryrequest[recordscount][2]-1][0]>delivaryrequest[recordscount][3]){
                    result=false;
                }
                else{
                    System.out.println("maxsimum weight is "+vehicle[(int)delivaryrequest[recordscount][2]-1][0]+"kg"); 
                    System.out.println("it is not valid");
                }
            }
        
        
        
        
        
         System.out.println("====================================================== ");
         System.out.println("DELIVERY COST ESTIMATION "); 
         System.out.println("------------------------------------------------------");
         System.out.println("From: "+cityname[(int)delivaryrequest[recordscount][0]-1]);
         System.out.println("TO: "+cityname[(int)delivaryrequest[recordscount][1]-1]);
         System.out.print("Minimum Distance: ");
         minimumparth(distance,cityname,delivaryrequest);
         
         System.out.println("Vehicle: "+vehicletype[(int)delivaryrequest[recordscount][2]-1]);
         System.out.println("Weight: "+delivaryrequest[recordscount][3]+"kg");
         System.out.println("------------------------------------------------------ ");
         System.out.println("");
         
         Basecost(vehicle,delivaryrequest,distance);
         Fuelused(delivaryrequest,distance,vehicle);
         
         /*total cost*/
         allcost[recordscount][3]=allcost[recordscount][0]+allcost[recordscount][2];
         
         
         System.out.println("Operational Cost: "+allcost[recordscount][3]+"LKR");
         
         
         /*profit*/
         allcost[recordscount][4]=allcost[recordscount][0]*profitprecentage;
         delivaryrequest[recordscount][6]= (allcost[recordscount][4])+(allcost[recordscount][3]);
         
         delivaryrequest[recordscount][7]=allcost[recordscount][4];
         System.out.println("Profit: "+allcost[recordscount][4]+"LKR");
         
         System.out.println("Customer Charge: "+delivaryrequest[recordscount][6]+"LKR");
         Time(delivaryrequest,distance,vehicle);
         System.out.println("====================================================== ");
         recordscount=recordscount+1; 
         System.out.println("");
         
         System.out.println("recordscount : "+recordscount);
         
        }
        else{
             System.out.println("not capacity");
        }
        
           
       
    }
    private static void Basecost(int[][] vehicle, double[][] delivaryrequest, double[][] distance) {
        double cost=(distance[(int)delivaryrequest[recordscount][0]-1][(int)delivaryrequest[recordscount][1]-1])*(vehicle[(int)delivaryrequest[recordscount][2]-1][1])*(1+(delivaryrequest[recordscount][3]/10000));
        allcost[recordscount][0]=cost;
         System.out.println("Base Cost: "+(distance[(int)delivaryrequest[recordscount][0]-1][(int)delivaryrequest[recordscount][1]-1])+" X "+(vehicle[(int)delivaryrequest[recordscount][2]-1][1])+" X (1+"+(delivaryrequest[recordscount][3])+"/10000) = "+cost+" LKR");
    }
    private static void Fuelused(double[][] delivaryrequest,double[][] distance,int[][] vehicle){
        double fuelused=(distance[(int)delivaryrequest[recordscount][0]-1][(int)delivaryrequest[recordscount][1]-1])/vehicle[(int)delivaryrequest[recordscount][2]-1][3];
        allcost[recordscount][1]=fuelused;
        System.out.println("Fuelused: "+fuelused+" L");
        double fuelcost=fuelused*fuelprice;
        allcost[recordscount][2]=fuelcost;
        System.out.println("Fuelcost: "+fuelcost+" LKR");
    } 
    private static void Time(double[][] delivaryrequest, double[][] distance, int[][] vehicle) {
        allcost[recordscount][5]=(distance[(int)delivaryrequest[recordscount][0]-1][(int)delivaryrequest[recordscount][1]-1])/vehicle[(int)delivaryrequest[recordscount][2]-1][2];
        delivaryrequest[recordscount][5]=allcost[recordscount][5];
        System.out.println("Estimated Time: "+allcost[recordscount][5]+" hours");
    }

    private static void reports(double[][] distance, double[][] delivaryrequest) {
         System.out.println("************************");
         double LongestRoutes=0;
         double ShortestRoutes=0;
        for(int i=0;i<=recordscount;i++){
            totaltime=totaltime+delivaryrequest[i][5];
            totalprofit=totalprofit+delivaryrequest[i][7];
             totalditance=totalditance+delivaryrequest[i][4];
           
        }
        System.out.println("Total Deliveries Completed :"+recordscount);
        System.out.println("Total Distance Covered : "+totalditance+" KM");
        
        System.out.println(" Average Delivery Time : "+totaltime/(double)recordscount);
        System.out.println("Total Revenue and Profit : "+totalprofit+" LKR");
        
        for(int i=0;i<recordscount;i++){
             
            if(LongestRoutes<=delivaryrequest[i][4]){             
                LongestRoutes=delivaryrequest[i][4];
            }
        }
        ShortestRoutes=LongestRoutes;
         for(int j=0;j<recordscount;j++){
          
            if(ShortestRoutes>delivaryrequest[j][4]){
                ShortestRoutes=delivaryrequest[j][4];
            }
            
            
            
        }
        
        
        System.out.println("Longest Routes: "+LongestRoutes+" Km");
        System.out.println("Shortest Routes: "+ShortestRoutes+"Km");
        System.out.println("");
    }

    private static void minimumparth(double[][] distance, String[] cityname,double[][] delivaryrequest) {
   
    int source = (int) (delivaryrequest[recordscount][0]-1);

    
    int destination = (int) (delivaryrequest[recordscount][1]-1);

    int n = count;
    double[] dist = new double[n];
    boolean[] visited = new boolean[n];
    int[] prev = new int[n];

    // Initialize
    for (int i = 0; i < n; i++) {
        dist[i] = Double.MAX_VALUE / 2;
        prev[i] = -1;
        visited[i] = false;
    }
    dist[source] = 0;

    // Dijkstra algorithm
    for (int i = 0; i < n - 1; i++) {
        // Find the unvisited node with the smallest distance
        double min = Double.MAX_VALUE;
        int u = -1;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && dist[j] < min) {
                min = dist[j];
                u = j;
            }
        }

        if (u == -1) break;
        visited[u] = true;

        // Update distances
        for (int v = 0; v < n; v++) {
            if (!visited[v] && distance[u][v] > 0 && dist[u] + distance[u][v] < dist[v]) {
                dist[v] = dist[u] + distance[u][v];
                prev[v] = u;
            }
        }
    }

    // Reconstruct the path
    if (dist[destination] == Double.MAX_VALUE / 2) {
        System.out.println("No path exists between these two cities.");
        return;
    }

  
    delivaryrequest[recordscount][4]=(int) dist[destination];
    System.out.println("Minimum Distance: " + dist[destination] + " km");

   
    System.out.println("---------------------------------------");
}

    private static void DeliveryRecords(double[][] delivaryrequest, double[][] allcost, String[] cityname, String[] vehicletype) {
            String []tabelhead={"count","From","   To","Distance(km)","vehicle type","Weight(KG)","Customer Charge(LKR)"};
            for(int i=0;i<7;i++){
                System.out.print(tabelhead[i]+"\t");   
            }
            System.out.println("");
        for(int i=0;i<recordscount;i++){
              System.out.print((i+1)+"\t");
              for(int j=0;j<2;j++){
                 System.out.print(cityname[(int)delivaryrequest[i][j]-1]+""+"\t");
              }
            System.out.print("  "+delivaryrequest[i][4]+"\t\t"); 
            System.out.print(vehicletype[(int)delivaryrequest[i][2]-1]+"\t\t");
            System.out.print(delivaryrequest[i][3]+"\t\t");
            System.out.print(delivaryrequest[i][6]+"\t");
            System.out.println("");
        }
        System.out.println("");
        System.out.println("---------------------------------------");
    }
    
    
    
   

}