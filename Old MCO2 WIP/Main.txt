import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* START OF GAME */
        //UserView userview = new UserView();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Factory Name: ");
        String name = sc.nextLine();
        Factory factory = new Factory(name);
        System.out.println("Welcome to " + factory.getName());
        
        /* CREATE REGULAR VENDING MACHINE */
        

        /* CREATE SPECIAL VENDING MACHINE */
        
        sc.close();
    }
}
