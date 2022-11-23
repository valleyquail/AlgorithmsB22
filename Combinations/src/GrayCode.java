import java.util.*;

public class GrayCode {
    private ArrayList<String> codes = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> currentChildren = new ArrayList<>();
    private String direction = "In";

    public GrayCode() {
        names.add("Alice");
        names.add("Bob");
        names.add("Chris");
        names.add("Dylan");
        names.add("Eve");
        names.add("Felix");
        names.add("Gerry");
        names.add("Helen");
        names.add("Ian");
        names.add("Jane");
        names.add("Karl");
        //etc. more names can be added
    }


    public void printGrayCodes(int number) {
        codes = grayHelperRecur(number);
        System.out.println(codes);
    }

    private ArrayList<String> grayHelperRecur(int order) {
        ArrayList<String> list = new ArrayList<>();
        if (order == 1) {
            list.add("0");
            list.add("1");
        } else {
            ArrayList<String> listOne;
            ArrayList<String> listTwo;
            listOne = grayHelperRecur(order - 1);
            listTwo = grayHelperRecur(order - 1);
            Collections.reverse(listTwo);
            for (int i = 0; i < listOne.size(); i++) {
                listOne.set(i, "0" + listOne.get(i));
                listTwo.set(i, "1" + listTwo.get(i));
            }
            listOne.addAll(listTwo);
            return listOne;
        }

        return list;
    }

    public String getName(int number) {
        String current = codes.get(number);
        String last = codes.get(number - 1);
        int j = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != last.charAt(i)) {
                j = i;
                break;
            }
        }
        j = current.length() - 1 - j;
        updateChildren(j);
        return names.get(j);
    }

    private void updateChildren(int child) {
        if (!currentChildren.contains(names.get(child))) {
            currentChildren.add(names.get(child));
            direction = "In";
        } else {
            currentChildren.remove(names.get(child));
            direction = "Out";
        }
    }

    public void printTable() {
        System.out.printf("%-8s%-13s%-30s%-10s\n", "Index", "Gray Codes", "Children in Photo", "Action");
        System.out.println("______________________________________________________________________________");
        for (int i = 1; i < codes.size(); i++) {
            String child = getName(i);
            System.out.printf("%-8d", i);
            System.out.printf("%-13s", codes.get(i));
            String children = String.join(", ", currentChildren);
            System.out.printf("%-30s", children);
            System.out.printf("%-1s %s\n", child, direction);
        }
    }
}
