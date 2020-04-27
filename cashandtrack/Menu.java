package cashandtrack;
import java.util.*;
import java.lang.String;

public class Menu {


    Map<String, Double> newMenu = new TreeMap<>();
    List<String> name = new ArrayList<>();

    public void newMenu(String menu, double price) {
        name.add(menu);
        newMenu.put(menu, price);
    }


    public void allMenu() {
        if (newMenu.size() <= 0) {
            System.out.println("No Menu.");
        } else {
            int num = 0;
            for (String i : newMenu.keySet()) {
                num++;
                System.out.println("#" + num + " Menu : " + i + " Price " + newMenu.get(i));
            }
        }
    }


    public void deleteMenu() {

        if (newMenu.size() <= 0) {
            System.out.println("No Menu.");
        } else {
            allMenu();
            System.out.print("Input the number to delete : ");
            Scanner index = new Scanner(System.in);
            int indexMenu = index.nextInt();
            if (indexMenu > newMenu.size()) {
                System.out.println("Out of index.");
            } else {
                String deleteName = name.get(indexMenu - 1);
                name.remove(indexMenu - 1);
                newMenu.remove(deleteName);
            }
        }
    }
}
