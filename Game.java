/**
 * Created by tamja on 7/10/2019.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static ArrayList<String> UserShots;
    private static ArrayList<String> ComputerShots;
    private static Random r;
    private static User u;
    private static Computer c;
    private static Scanner s;
    private static String ShootFirst;
    private static boolean UserWin;
    private static boolean ComputerWin;
    private static String user_shoot_string;
    private static String computer_shoot_string;
    private static String user_dive_string;
    private static String computer_dive_string;
    private static int user_shots_taken;
    private static int computer_shots_taken;

    public Game() {
        u = new User();
        c = new Computer();
        s = new Scanner(System.in);
        UserShots = new ArrayList<>();
        ComputerShots = new ArrayList<>();
        r = new Random();
        ShootFirst = null;
        UserWin = false;
        ComputerWin = false;
        user_shoot_string = null;
        user_dive_string = null;
        computer_dive_string = null;
        computer_shoot_string = null;
        user_shots_taken = 0;
        computer_shots_taken = 0;

        for (int i = 0; i < 5; i++) {
            UserShots.add("TBD");
            ComputerShots.add("TBD");
        }
    }

    public void play() {

        System.out.println("Welcome to the penalty shootout!");

        determine_first_shooter();

        if (ShootFirst.equals("User")) {
            user_kicks_first();
        }

        else if (ShootFirst.equals("Computer")) {
            computer_kicks_first();
        }

        if (UserWin) {
            System.out.println("Looks like the user has the win in the bag! Congratulations!");
        }
        else if (ComputerWin) {
            System.out.println("Looks like the computer has taken the win. Tough luck!");
        }

        System.out.println("Thanks for playing!");

    }

    public static void enter_to_cont() {
        System.out.println("Press enter to continue.");
        s.nextLine();
    }

    public static void determine_first_shooter() {

        System.out.println("Time to flip a coin to choose who determines who will shoot first.");
        System.out.println("The user is heads, the computer is tails. Here's the flip!");
        enter_to_cont();

        int first = r.nextInt(2);
        if (first == 0) {
            System.out.println("The user won the toss!");
            System.out.println("Would you like to shoot first or second? (type 'first' or 'second')");
            String choice = s.nextLine();
            while (!(choice.equals("first") || choice.equals("second"))) {
                System.out.println("That is not a valid choice. Please type 'first' or 'second'.");
                choice = s.nextLine();
            }
            if (choice.equals("first")) {
                ShootFirst = "User";
            } else { // choice equals 'second'
                ShootFirst = "Computer";
            }
        }
        if (first == 1) {
            System.out.println("The computer won the toss!");
            int decision = r.nextInt(2);
            if (decision == 0) {
                ShootFirst = "User";
                System.out.println("The computer decided to shoot second.");
            }
            else {
                ShootFirst = "Computer";
                System.out.println("The computer decided to shoot first.");
            }
        }

        enter_to_cont();

    }

    public static void user_kicks_first() {
        while (!(UserWin || ComputerWin)) {
            user_shoots();
            if (UserWin || ComputerWin) {
                break;
            }
            computer_shoots();
        }
    }

    public static void computer_kicks_first() {
        while (!(UserWin || ComputerWin)) {
            computer_shoots();
            if (UserWin || ComputerWin) {
                break;
            }
            user_shoots();
        }
    }

    public static void user_shoots() {
        System.out.println("The user's team will now shoot.");
        user_shoot_string = u.shoot();
        computer_dive_string = c.dive();
        boolean is_on_target = check_on_target();
        boolean is_save = check_save(user_shoot_string, computer_dive_string, is_on_target);
        boolean is_goal = check_goal(is_save, is_on_target);
        String characterization = goal_event(is_goal, is_save);
        shoot_commentary(user_shoot_string);
        dive_commentary(computer_dive_string);
        System.out.println(characterization);
        enter_to_cont();

        update_score_user(UserShots, is_goal);
        display_score();
        check_win(UserShots, ComputerShots);
        enter_to_cont();
    }

    public static void computer_shoots() {
        System.out.println("The computer's team will now shoot.");
        computer_shoot_string = c.shoot();
        user_dive_string = u.dive();
        boolean is_on_target = check_on_target();
        boolean is_save = check_save(computer_shoot_string, user_dive_string, is_on_target);
        boolean is_goal = check_goal(is_save, is_on_target);
        String characterization = goal_event(is_goal, is_save);
        shoot_commentary(computer_shoot_string);
        dive_commentary(user_dive_string);
        System.out.println(characterization);
        enter_to_cont();

        update_score_computer(ComputerShots, is_goal);
        display_score();
        check_win(UserShots, ComputerShots);
        enter_to_cont();
    }

    public static boolean check_on_target() {
        boolean on_target = true;
        int probability = r.nextInt(5);
        if (probability > 3) {
            on_target = false;
        }
        return on_target;
    }

    public static boolean check_save(String shoot_choice, String dive_choice, boolean on_goal) {
        boolean is_save = false;
        Double dub;

        if (on_goal) {

            switch(dive_choice) {
                case "r":
                    if (shoot_choice.equals("tr") || shoot_choice.equals("br")) {
                        dub = r.nextDouble();
                        if (dub < 0.75) {
                            is_save = true;
                        }
                    }
                    break;
                case "c":
                    if (shoot_choice.equals("c")) {
                        dub = r.nextDouble();
                        if (dub < 0.95) {
                            is_save = true;
                        }
                    }
                    break;
                case "l":
                    if (shoot_choice.equals("tl") || shoot_choice.equals("bl")) {
                        dub = r.nextDouble();
                        if (dub < 0.75) {
                            is_save = true;
                        }
                    }
                    break;
            }
        }

        return is_save;
    }

    public static boolean check_goal(boolean save, boolean on_goal) {
        boolean is_goal = true;

        if (save) {
            is_goal = false;
        }
        else if (!on_goal) {
            is_goal = false;
        }

        return is_goal;
    }

    public static void shoot_commentary (String shoot_string) {
        switch (shoot_string) {
            case "tl":
                System.out.println("The kicker shot top left!");
                break;
            case "bl":
                System.out.println("The kicker shot bottom left!");
                break;
            case "c":
                System.out.println("The kicker shot at the center of the goal!");
                break;
            case "tr":
                System.out.println("The kicker shot top right!");
                break;
            case "br":
                System.out.println("The kicker shot bottom right!");
                break;
        }
    }

    public static void dive_commentary(String dive_string) {
        switch (dive_string) {
            case "r":
                System.out.println("The keeper dove right!");
                break;
            case "c":
                System.out.println("The keeper stayed central!");
                break;
            case "l":
                System.out.println("The keeper dove left!");
                break;
        }
    }

    public static String goal_event(boolean is_goal, boolean is_save) {
        String occurrence;
        if (is_goal) {
            occurrence = "The kicker scored!";
        }
        else if (is_save){
            occurrence = "The keeper blocked the shot!";
        }
        else {
            int number = r.nextInt(2);
            if (number == 0) {
                occurrence = "The kicker completely missed the goal!";
            }
            else {
                occurrence = "The kicker hit the post, and failed to score!";
            }
        }

        return occurrence;
    }

    public static void display_score() {
        System.out.println("User's scoreboard: ");
        System.out.println(UserShots);
        System.out.println("Computer's scoreboard: ");
        System.out.println(ComputerShots);
    }

    public static void update_score_user(ArrayList<String> records, boolean is_goal) {
        if (records.size() <= 5) {
            if (is_goal) {
                records.set(user_shots_taken, "goal");
                user_shots_taken ++;
            } else {
                records.set(user_shots_taken, "miss");
                user_shots_taken ++;
            }
        }
        else {
            if (is_goal) {
                records.add("goal");
                user_shots_taken ++;
            } else {
                records.add("miss");
                user_shots_taken ++;
            }
        }
    }

    public static void update_score_computer(ArrayList<String> records, boolean is_goal) {
        if (records.size() <= 5) {
            if (is_goal) {
                records.set(computer_shots_taken, "goal");
                computer_shots_taken ++;
            } else {
                records.set(computer_shots_taken, "miss");
                computer_shots_taken ++;
            }
        }
        else {
            if (is_goal) {
                records.add("goal");
                computer_shots_taken ++;
            } else {
                records.add("miss");
                computer_shots_taken ++;
            }
        }
    }

    public static void check_win(ArrayList<String> UserRecord, ArrayList<String> ComputerRecord) {

        int user_goal_count = 0;
        int computer_goal_count = 0;
        int user_shots_remaining = 0;
        int computer_shots_remaining = 0;
        for (String s : UserRecord) {
            if (s.equals("goal")) {
                user_goal_count ++;
            }
            else if (s.equals("TBD")) {
                user_shots_remaining ++;
            }
        }
        for (String s : ComputerRecord) {
            if (s.equals("goal")) {
                computer_goal_count ++;
            }
            else if (s.equals("TBD")) {
                computer_shots_remaining ++;
            }
        }

        if ((UserRecord.size() == 5) && (ComputerRecord.size() == 5)) {
            if (user_goal_count > (computer_goal_count + computer_shots_remaining)) {
                UserWin = true;
            }
            if (computer_goal_count > (user_goal_count + user_shots_remaining)) {
                ComputerWin = true;
            }
        }
        else {
            if (UserRecord.size() == ComputerRecord.size()) {
                if (user_goal_count > computer_goal_count) {
                    UserWin = true;
                } else if (user_goal_count < computer_goal_count) {
                    ComputerWin = true;
                }
            }
        }

    }

}
