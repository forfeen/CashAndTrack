package cashandtrack;
import java.util.*;
import java.lang.String;

public class Menu {

    private String menuName;
    private List<String> allMenu = new ArrayList<String>();

    public Menu(String menu) {
        this.menuName = menu;
    }

    public int count() {
        return allMenu.size();
    }

    public boolean addMenu(String newMenu) {
        allMenu.add(newMenu);
        return true;
    }

    public boolean deleteMenu(int index) {
        if (index > allMenu.size()) {
            return false; }
        else {
            allMenu.remove(index-1);
        }return true;
    }

    public String toString() {
        String menu = " ";
        if (allMenu.size() == 0){
            menu = "No Menu";
        } else {
            for (int i = 0; i < allMenu.size()-1; i++){
                menu += allMenu.get(i).toString() + ",";
            } menu += allMenu.get(allMenu.size()-1).toString();
        } return menu;
    }

}