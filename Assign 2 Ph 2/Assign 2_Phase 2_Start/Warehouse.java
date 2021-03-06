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
    private ArrayList<Item> inv = new ArrayList<Item>();

    // the constructor
    public Warehouse()
    {

    }

    // the method below loads the data from a text file
    public int loadData()throws IOException
    {
        Scanner fileInput = new Scanner(new File("Inventory.txt"));

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
    // validates item number and if valid, returns the found item.  Will be used only by this class and hence declared as private
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
        {
            System.out.println (foundItem.printDetails());
        }
        else
        {
            System.out.println ("The item number " + num + " does not exist.");
        }
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
        {
            System.out.println ("The item number " + num + " does not exist.");     
        }
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
        {
            System.out.println ("The item number " + num + " does not exist.");   
        }
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
        {
            System.out.println ("The item number " + num + " does not exist."); 
        }
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
        {
            System.out.println ("The item number " + num + " does not exist."); 
        }
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
        {
            System.out.println ("The item number " + num + " does not exist."); 
        }
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
        {
            System.out.println ("The item number " + num + " does not exist."); 
        }
    }

    //the method below allows the user to add a new item to the invetory
    public void addItem(String num)
    {
        int i = 0;
        Item check = null;
        check = validateNum(num);

        if(check != null)
        {
            System.out.println("The item cannot be created. Item already exists."); 
        }
        else
        {
            System.out.println("The item was not found. The item will be created.");
            scanInfo();
        }
    }

    //the method below is utilised by the method above to request and set values for a new item
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
        System.out.println("Enter the econ order quantity: ");
        int eoq = scan.nextInt();
        inv.add(new Item(itemNum,itemName,stock,price,reorder,eoq));
        return(new Item(itemNum,itemName,stock,price,reorder,eoq));
    }

    //the method below removes an item from the inventory if it matches the item number the user entered
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

    //the method below allows the user to change the price of an item in the ArrayList
    public void ChangeItemPrice(String num)
    {
        Scanner scan = new Scanner(System.in);
        Item check = null;
        double newPrice = 0.0;
        int ref = 0;
        check = validateNum(num);

        if(check != null)
        {
            System.out.println("Number is valid. Please enter the amount to set " + num + "'s  price to: ");
            newPrice = scan.nextDouble();
            ref = ItemPosition(num);
            inv.set(ref,check).setUnitPrice(newPrice);
        }
        else
        {
            System.out.println("Item is not valid.");
        }
    }

    //the method below return the position on an item in the ArrayList
    public int ItemPosition(String num)
    {
        int i = 0;
        int y = 0;

        while(i < inv.size())
        {
            if (inv.get(i).getItemNo().equals(num))
            {
                y = i;
            }  
            i++;
        }

        return y;
    }

    //the method below reads a text file with transactions
    public void TransacFile() throws IOException
    {
        Scanner fileInput = new Scanner(new File("TestTransactions.txt"));
        Item check = null;
        String num = "";
        int type = 0;
        int amount = 0;

        while(fileInput.hasNext())
        {
            num = fileInput.next();
            type = fileInput.nextInt();
            amount = fileInput.nextInt();
            check = validateNum(num);

            if (check != null)
            {
                System.out.println("Item is valid. Processing will now occur.");
                ProcessSelect(num, type, amount);
            }
            else
            {
                System.out.println("" + num + " is not a valid item.");
            }
        } 

        fileInput.close();
    }

    //based on the method above, the method below processes the transaction from the text file accordingly
    public void ProcessSelect(String num, int type, int amount)
    {
        if(type == 2)
        {
            order(num,amount);
        }
        else if(type == 3)
        {
            receive(num,amount);
        }
        else if(type == 4)
        {
            returns(num, amount);
        }
        else if(type == 5)
        {
            shipCust(num, amount);
        }
        else if(type == 6)
        {
            orderCust(num, amount);
        }
        else if(type == 7)
        {
            returnCust(num, amount);
        }
    }
    
    //the method below prints all items and their corresponding information to a new text file
    public void PrintToFile() throws IOException
    {
        PrintWriter write = new PrintWriter("NewInventory.txt");
        int i = 0;

        while(i < inv.size())
        {
            write.print(inv.get(i));
            if(i <= inv.size())
            {
                write.println();
            }
            i++;
        }

        System.out.println("File has finished writing!");
        write.close();
    }

    //End of Day processing
    public void endOfDay()
    {
        printInvReport();
        processAuto();
    }
    
    //the method below prints out an inventory report
    private void printInvReport()
    {
        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n", "Item", "Item", " ",
            " ", " ", "Unit", "Item");

        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n\n","Number", "Name", "On Hand",
            "Committed", "On Order", "Price", "Value");

        for (int i = 0; i < inv.size(); i++)
        {
            System.out.printf ("%-8s %-10s %-10d %-10d %-10d $ %-7.2f $ %-10.2f \n", 
                inv.get(i).getItemNo(), inv.get(i).getItemName(), inv.get(i).getOnHand(), 
                inv.get(i).getCommitted(), inv.get(i).getOnOrder(), inv.get(i).getUnitPrice(),
                inv.get(i).calcInvValue());           
        }   
    }
    
    //the method below automatically orders items
    private void processAuto()
    {
        boolean autoOrd;
        for (int i = 0; i < inv.size(); i++)
        {
            autoOrd = inv.get(i).autoOrder(); 
            if (autoOrd)
            {
                System.out.println ("Ordered " + inv.get(i).getEconOrderQty() + " of item " + inv.get(i).getItemNo() + "  " + inv.get(i).getItemName());
                System.out.println ("on order amount is " + inv.get(i).getOnOrder());
            }

        }   
    }

}
