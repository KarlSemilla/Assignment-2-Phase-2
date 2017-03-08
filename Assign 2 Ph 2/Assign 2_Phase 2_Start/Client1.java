import java.util.Scanner;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory for Phase 2
 * 
 * @author  Your name here
 * @version 
 */
/*
 * Instructor: 
 * Assumptions: 
 * Known errors: None (or, a SPECIFIC explanation of what you know doesn't work)
 *                e.g. not "sometime bombs when reading file" bit "bombs when reading if > 20 lines")
 *
 * DELETE extraneous info from this comment
 */
public class Client1 
{
    /**
     *  Based on the user's choice, transactions are processed
     */
    public static void main(String args[]) throws IOException
    {
        // complete the main by adding the necessary variables and statements
        Scanner in = new Scanner(System.in);
        int choice = 0;
        int count = 0;
        String num = "";
        Warehouse ware = new Warehouse();
        ware.loadData();

        while (choice != 5)
        {
            invMenu();
            System.out.println("Please enter your coice or 5 to exit");
            choice = in.nextInt();
            switch(choice) 
            {
                case 1:
                System.out.println("Enter item number");
                num = in.next();
                ware.addItem(num);
                break;
                
                case 2:
                
                break;
                
                case 3:
                
                break;
                
                case 4:
                
                break;
            }
        }
        System.out.println ("Thank you for using the Inventory Processing System");
    }

    /**
     *  The Main menu
     */
    public static void mainMenu()
    {
        System.out.println("\nMAIN MENU:");
        System.out.println("1) Inventory item inquiry");
        System.out.println("2) Warehouse and Inventory Maintenance");
        System.out.println("3) Process transactions from the file");
        System.out.println("4) End of Day Processing");
        System.out.println();
        System.out.println("5) Exit");
    }

    /**
     *  The Inventory Maintenance menu
     */
    public static void invMenu()
    {

        System.out.println("\nINVENTORY PROCESSING MENU:");
        System.out.println("1) Adding an Item to the Warehoue");
        System.out.println("2) Removing an Item from the Warehouse");
        System.out.println("3) Changing the price of an Item in the Warehouse"); 
        System.out.println();
        System.out.println("4) Exit");
    } 
}
