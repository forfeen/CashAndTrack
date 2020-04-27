package cashandtrack;

import java.util.Scanner;

public class CashAndTrack {



    public static void main(String[] args) {
        while (true) {
            System.out.print("(C)ustomer , (M)enu , (P)ayment : ");

            Scanner input = new Scanner(System.in);
            String choose =  input.next();

            if (choose.equals("M".toLowerCase())) {
                Menu menu = new Menu();
                Scanner menuInput = new Scanner(System.in);

                while (true) {

                    System.out.print("(A)dd , (D)elete , (S)how menu : ");
                    String chooseMenu = menuInput.next();

                    if (chooseMenu.equals("A".toLowerCase())) {
                        Scanner addMenu = new Scanner(System.in);
                        System.out.print("Menu : ");
                        String newMenu = addMenu.next();


                        Scanner addPrice = new Scanner(System.in);
                        System.out.print("Price : ");
                        double newPrice = addPrice.nextDouble();
                        menu.newMenu(newMenu, newPrice);

                    }

                    if (chooseMenu.equals("S".toLowerCase())) {
                        menu.allMenu();
                    }
                    if (chooseMenu.equals("D".toLowerCase())) {
                        menu.deleteMenu();
                        menu.allMenu();
                    }
                }

            }
        }



    }




}
