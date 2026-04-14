package com.test;

import java.sql.*;
import java.util.Scanner;

public class CustomerCRUD2 {
    static Connection con;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Shop",
                "root",
                "RACHU@123r"
            );

            int choice;
            do {
                System.out.println("\n------CUSTOMER MENU-------");
                System.out.println("1.INSERT");
                System.out.println("2.DISPLAY");
                System.out.println("3.UPDATE");
                System.out.println("4.DELETE");
                System.out.println("5.EXIT");
                System.out.print("Enter Choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        insertCustomer(sc);
                        break;

                    case 2:
                        displayCustomer();
                        break;

                    case 3:
                        updateCustomer(sc);
                        break;

                    case 4:
                        deleteCustomer(sc);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } while (choice != 5);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // INSERT
    static void insertCustomer(Scanner sc) throws Exception {
        System.out.print("Enter ID: ");
        int cid = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        String sql = "INSERT INTO customers VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, cid);
        ps.setString(2, name);
        ps.setInt(3, age);

        ps.executeUpdate();

        System.out.println("Record Inserted Successfully");
    }

    // DISPLAY
    static void displayCustomer() throws Exception {
        String sql = "SELECT * FROM customers";
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("cid"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Age: " + rs.getInt("age"));
            System.out.println("----------------------");
        }
    }

    // UPDATE
    static void updateCustomer(Scanner sc) throws Exception {
        System.out.print("Enter Customer ID to update: ");
        int cid = sc.nextInt();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        String sql = "UPDATE customers SET age=? WHERE cid=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, age);
        ps.setInt(2, cid);

        ps.executeUpdate();

        System.out.println("Record Updated Successfully");
    }

    // DELETE
    static void deleteCustomer(Scanner sc) throws Exception {
        System.out.print("Enter Customer ID to delete: ");
        int cid = sc.nextInt();

        String sql = "DELETE FROM customers WHERE cid=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, cid);

        ps.executeUpdate();

        System.out.println("Record Deleted Successfully");
    }
}
