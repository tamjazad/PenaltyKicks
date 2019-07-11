/**
 * Created by tamja on 7/10/2019.
 */

import java.util.Random;

public class Computer {

    private Random r;
    private String shoot_choice;
    private String dive_choice;

    public Computer() {
        r = new Random();
        shoot_choice = null;
        dive_choice = null;
    }

    public String shoot() {
        int decision = r.nextInt(5);

        if (decision == 0) {
            shoot_choice = "tr";
        }
        else if (decision == 1) {
            shoot_choice = "tl";
        }
        else if (decision == 2) {
            shoot_choice = "br";
        }
        else if (decision == 3) {
            shoot_choice = "bl";
        }
        else if (decision == 4) {
            shoot_choice = "c";
        }

        return shoot_choice;
    }

    public String dive() {
        int decision = r.nextInt(3);

        if (decision == 0) {
            dive_choice = "r";
        }
        else if (decision == 1) {
            dive_choice = "l";
        }
        else if (decision == 2) {
            dive_choice = "c";
        }

        return dive_choice;
    }
}
