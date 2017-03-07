import java.util.*;
import java.io.*;
/**
 * Warehouse contains the different items in stock
 * 
 * @author {Place your name here}
 *
 */
public class Warehouse
{
    // instance variables (fields)  
    private final static int MAX = 60;
    private ArrayList<Item> inv = new ArrayList<Item>();
    private int numItems;

    // the constructor
    public Warehouse()
    {
        //stock = new Item[MAX];

        //numItems = loadData();

    }

    /**
     * This is the hardcoded data to be loaded into the instance variables.  
     */
    public int loadData()throws IOException
    {
        Scanner fileInput = new Scanner(new File("Inventory.txt"));
        //stock[0] = new Item("A11111", "Widgets", 30, 50, 70, 2.50, 20, 50);
        //stock[1] = new Item("B22222", "Gadgets", 10, 20, 0, 4.00, 50, 100);
        //stock[2] = new Item("C33333", "Trinkets", 100, 20, 35, 3.75, 80, 150);
        //stock[3] = new Item("D44444", "Pickets", 0, 100, 20, 8.35, 25, 75);
        //stock[4] = new Item("E55555", "Sockets", 200, 300, 150, 1.00, 200, 400);
        while(fileInput.hasNextLine()){
            inv.add(new Item(fileInput.next(), 
                    fileInput.next(), 
                    fileInput.nextInt(),
                    fileInput.nextInt(),
                    fileInput.nextInt(),
                    fileInput.nextDouble(),
                    fileInput.nextInt(),
                    fileInput.nextInt()));

                    
        }
        fileInput.close();
        int number = 5;

        return number; 
    }

    // processing methods
    // validate item number and if valid, returns the found item.  Will be used only by this class and hence declared as private
    public Item validateNum(String num)
    {        
        Item foundItem = null;

        for(int i = 0; i < inv.size(); i++)
        {
            if (inv.get(i).getItemNo().equals(num))
            {
                foundItem = inv.get(i);
            }        
        }

        return foundItem;
    }

    // Inventory Information Inquiry (Option 1)
    public void invQuery(String num)
    {
        Item foundItem = null;
        String text;

        foundItem = validateNum(num);
        if (foundItem != null)
            System.out.println (foundItem.printDetails());
        else
            System.out.println ("The item number " + num + " does not exist.");

    }

    // ordering items from supplier
    public void order (String num, int amt)
    {
        Item foundItem = null;
        int orderAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            orderAmt = foundItem.orderItems(amt);
            System.out.println ("Ordered " + orderAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on order amount is " + foundItem.getOnOrder());
        }
        else 
            System.out.println ("The item number " + num + " does not exist.");     

    }

    // Receiving shipment from supplier
    public void receive(String num, int amt)
    {
        Item foundItem = null;
        int recAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            recAmt = foundItem.receiveItems(amt);
            System.out.println ("Received " + recAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on order amount is " + foundItem.getOnOrder()); 
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist.");            
    }

    // returning items to supplier
    public void returns(String num, int amt)
    {
        Item foundItem = null;
        int retAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            retAmt = foundItem.returnItems(amt);
            System.out.println ("Returned " + retAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // shipping items to customers
    public void shipCust(String num, int amt)
    {
        Item foundItem = null;
        int shipAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            shipAmt = foundItem.shipItems(amt);
            System.out.println ("Shipped " + shipAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("committed is " + foundItem.getCommitted());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // processing customer orders
    public void orderCust(String num, int amt)
    {
        Item foundItem = null;
        int ordCustAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            ordCustAmt = foundItem.custOrder(amt); 
            System.out.println ("Customer ordered " + ordCustAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("committed is " + foundItem.getCommitted());
            System.out.println ("on hand amount is " + foundItem.getOnHand());  
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // processing customer returns
    public void returnCust(String num, int amt)
    {
        Item foundItem = null;
        int retCustAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            retCustAmt = foundItem.custReturn(amt);
            System.out.println ("Customer returned " + retCustAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 
    }

    public void addItem(String num)
    {
        int i = 0;
        Item check = null;

        check = validateNum(num);
        
        if(check != null)
            System.out.println("The item cannot be created. Item already exists.");            
        else
        {
            System.out.println("The item was not found. The item will be created.");
            scanInfo();
        }

    } 
    
    public Item scanInfo()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter item number: ");
        String itemNum = scan.nextLine();
        System.out.println("Enter item name: ");
        String itemName = scan.nextLine();
        System.out.println("Enter amount in stock: ");
        int stock = scan.nextInt();
        System.out.println("Enter unit price of the item: ");
        double price = scan.nextDouble();
        System.out.println("Enter the reorder amount: ");
        int reorder = scan.nextInt();
        System.out.println("Enter the price of ordering the item: ");
        int eoq = scan.nextInt();
        inv.add(new Item(itemNum,itemName,stock,price,reorder,eoq));
        return(new Item(itemNum,itemName,stock,price,reorder,eoq));
    }
    
    public void removeItem(String num)
    {
        Item check = null;
        
        check = validateNum(num);
        if(check != null){
            inv.remove(check);
        }
        else
            System.out.println("Item could not be found.");
    }

    // End of Day processing
    //    public void endOfDay()
    //    {
    //        printInvReport();
    //        processAuto();
    //    }
    //    
    //    private void printInvReport()
    //    {
    //        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n", "Item", "Item", " ",
    //                             " ", " ", "Unit", "Item");
    //                            
    //         System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n\n","Number", "Name", "On Hand",
    //                            "Committed", "On Order", "Price", "Value");
    //         
    //         for (int i = 0; i <numItems; i++)
    //          {
    //             System.out.printf ("%-8s %-10s %-10d %-10d %-10d $ %-7.2f $ %-10.2f \n", 
    //                     stock[i].getItemNo(), stock[i].getItemName(), stock[i].getOnHand(), 
    //                     stock[i].getCommitted(), stock[i].getOnOrder(), stock[i].getUnitPrice(),
    //                     stock[i].getUnitPrice() * (stock[i].getOnHand() + stock[i].getCommitted()));           
    //          }   
    //    }
    //    
    //    private void processAuto()
    //    {
    //        boolean autoOrd;
    //        for (int i = 0; i <numItems; i++)
    //          {
    //              autoOrd = stock[i].autoOrder(); 
    //              if (autoOrd)
    //              {
    //                  System.out.println ("Ordered " + stock[i].getEconOrderQty() + " of item " + stock[i].getItemNo() + "  " + stock[i].getItemName());
    //                  System.out.println ("on order amount is " + stock[i].getOnOrder());
    //              }
    //              
    //          }   
    //    }
    //    
}
