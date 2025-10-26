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

    static int MAX_CITIES = 30;
    static double[][] allcost = new double[50][6];
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

    }
    

