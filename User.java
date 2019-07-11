/**
 * Created by tamja on 7/10/2019.
 */

import java.util.Scanner;

public class User {

    private Scanner s;
    private String shoot_choice;
    private String dive_choice;

    public User() {
        s = new Scanner(System.in);
        shoot_choice = null;
        dive_choice = null;
    }

    public String shoot() {
        System.out.println("Where would you like to shoot the ball?");
        System.out.println("Your choices are tl, tr, bl, br, c");
        shoot_choice = s.nextLine();
        while (!(shoot_choice.equals("tl") || shoot_choice.equals("tr") || shoot_choice.equals("bl")
            || shoot_choice.equals("br") || shoot_choice.equals("c"))) {
            System.out.println("That is not a valid shooting direction choice.");
            System.out.println("Please enter a valid choice.");
            System.out.println("Your choices are tl, tr, bl, br, c");
            shoot_choice = s.nextLine();
        }

        return shoot_choice;
    }

    public String dive() {
        System.out.println("Where would you like your keeper to dive?");
        System.out.println("Your choices are r, l, c");
        dive_choice = s.nextLine();
        while (!(dive_choice.equals("r") || dive_choice.equals("l") || dive_choice.equals("c"))) {
            System.out.println("Taht is not a valid diving direction choice.");
            System.out.println("Please enter a valid choice.");
            System.out.println("Your choices are r, l, c");
            dive_choice = s.nextLine();
        }
        return dive_choice;
    }
}
