// Ivan Yu
// orderedArray.java
// demonstrates ordered array class
// to run this program: C>java OrderedApp
////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.Random;

class OrdArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   private Scanner input = new Scanner(System.in);
   private Random rand = new Random();
   //-----------------------------------------------------------
   public OrdArray(int max)          // constructor
      {
      a = new long[max];             // create array
      nElems = 0;
      }
   //-----------------------------------------------------------
   public int size()
      { return nElems; }
   //-----------------------------------------------------------
   public int find(long searchKey)
      {
      int lowerBound = 0;
      int upperBound = nElems-1;
      int curIn;

      while(true)
         {
         curIn = (lowerBound + upperBound ) / 2;
         if(a[curIn]==searchKey)
            return curIn;              // found it
         else if(lowerBound > upperBound)
            return nElems;             // can't find it
         else                          // divide range
            {
            if(a[curIn] < searchKey)
               lowerBound = curIn + 1; // it's in upper half
            else
               upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
         }  // end while
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      int j;
      for(j=0; j<nElems; j++)        // find where it goes
         if(a[j] > value)            // (linear search)
            break;
      for(int k=nElems; k>j; k--)    // move bigger ones up
         a[k] = a[k-1];
      a[j] = value;                  // insert it
      nElems++;                      // increment size
      }  // end insert()
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j = find(value);
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems - 1; k++) // move bigger ones down
            a[k] = a[k+1];
         nElems--;                   // decrement size
         return true;
         }
      }  // end delete()
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public void displayMenu() {
      System.out.println("1. Fill array");
      System.out.println("2. Add a number");
      System.out.println("3. Find the index of a number");
      System.out.println("4. Remove number at index");
      System.out.println("5. Remove number ");
      System.out.println("6. Quit ");
      choiceHandling();
      }
   //-----------------------------------------------------------
   public void choiceHandling() {
      String choice;
      System.out.print("Enter your choice 1-6: ");
      while (true) {
         choice = input.nextLine();
         try {
            if (Long.parseLong(choice) == 1) {
               if (nElems == a.length)
                  System.out.println("Array is filled");
               else {
                  System.out.print("Enter number of elements 1-10: ");
                  choice = input.nextLine();
                  boolean tmp = true;
                  while (tmp) {
                     try {
                        for (int i = 0; i < Long.parseLong(choice); i++) {
                           insert(rand.nextInt(99) + 1);
                        }
                        tmp = false;
                     } catch (NumberFormatException e) {
                        System.out.print("Please re-enter 1-10: ");
                     }
                  }
               }
            } else if (Long.parseLong(choice) == 2) {
               if (nElems == a.length) {
                  System.out.println("Array is filled");
               } else {
                  insert(rand.nextInt(99) + 1);
               }
            } else if (Long.parseLong(choice) == 3) {
               System.out.print("Enter number to find: ");
               choice = input.nextLine();
               boolean tmp = true;
               while (tmp) {
                  try {
                     if (find(Long.parseLong(choice)) == nElems)
                        System.out.println(Long.parseLong(choice) + " is not found");
                     else
                        System.out.println(Long.parseLong(choice) + " at index " + find(Long.parseLong(choice)));
                     
                        tmp = false;
                  } catch (NumberFormatException e) {
                     System.out.print("Please re-enter a number to find: ");
                  }
               }
            } else if (Long.parseLong(choice) == 4) {
               System.out.print("Enter index 0-9: ");
               choice = input.nextLine();
               boolean tmp = true;
               while(tmp) {
                  try {
                     if ((Long.parseLong(choice) >= 0) && (Long.parseLong(choice) <= (nElems - 1))) {
                        delete(a[(int)Long.parseLong(choice)]);
                        tmp = false;
                     } else {
                        System.out.print("Please re-enter 0-9: ");
                        choice = input.nextLine();
                     }
                  } catch (NumberFormatException e) {
                     System.out.print("Please re-enter 0-9: ");
                  }
               }
            } else if (Long.parseLong(choice) == 5) {
               System.out.print("Enter number to remove: ");
               choice = input.nextLine();
               boolean tmp = true;
               while (tmp) {
                  try {
                     if (!delete(Long.parseLong(choice))) {
                        System.out.println(Long.parseLong(choice) + " is not found.");
                        tmp = false;
                     } else {
                        delete(Long.parseLong(choice));
                        tmp = false;
                     }
                  } catch (NumberFormatException e) {
                     System.out.print("Please re-enter a number to remove: ");
                  }
               }
            } else if (Long.parseLong(choice) == 6) {
               System.exit(0);
            } else if (Long.parseLong(choice) < 1 || Long.parseLong(choice) > 6) {
               System.out.print("Please re-enter 1-6: ");
            }
         } catch (NumberFormatException e) {
            System.out.print("Please re-enter 1-6: ");
         }
         System.out.println("");
         display();
         displayMenu();
      }
      }
   }  // end class OrdArray
////////////////////////////////////////////////////////////////
class OrderedApp
   {
   public static void main(String[] args)
      {
         OrdArray a = new OrdArray(10);
         a.displayMenu();
      }  // end main()
   }  // end class OrderedApp
