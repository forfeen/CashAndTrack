package cashandtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashAndTrack {

    private static String menuName;
    private static double menuPrice;
    private  static List<Menu> allMenu = new ArrayList<Menu>();

    public static void main(String[] args) {

        while (true) {
            System.out.print("(C)ustomer , (M)enu , (P)ayment : ");

            Scanner input = new Scanner(System.in);
            String choose =  input.next();

            if (choose.equals("M".toLowerCase())) {
                Scanner menuInput = new Scanner(System.in);

                while (true) {

                    System.out.print("(A)dd , (D)elete , (S)how menu : ");
                    String chooseMenu = menuInput.next();

                    if (chooseMenu.equals("A".toLowerCase())) {
                        Scanner addMenu = new Scanner(System.in);
                        System.out.print("Menu : ");
                        menuName = addMenu.next();


                        Scanner addPrice = new Scanner(System.in);
                        System.out.print("Price : ");
                        menuPrice = addPrice.nextDouble();
                        Menu newMenu = new Menu(menuName, menuPrice);
                        allMenu.add(newMenu);
                        newMenu.showAllMenu(allMenu);

                    }

                    if (chooseMenu.equals("S".toLowerCase())) {
                        Menu nM = new Menu(menuName, menuPrice);
                        nM.showAllMenu(allMenu);
                    }
                    if (chooseMenu.equals("D".toLowerCase())) {
                        Menu newMenu = new Menu(menuName, menuPrice);
                        if (allMenu.size() == 0 ) {
                            System.out.println("No Menu");
                        } else {
                            newMenu.showAllMenu(allMenu);
                            Scanner numDelete = new Scanner(System.in);
                            System.out.print("Input the number to delete : ");
                            int index = numDelete.nextInt();
                            newMenu.deleteMenu(index, allMenu);
                        }

                    }
                }

            }
        }



    }




}
