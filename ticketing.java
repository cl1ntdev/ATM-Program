import java.util.*;

public class ticketing {
    public static void tradeMark() {
        System.out.println(
                "                                        ==========================================================================================");
        System.out.println(
                "                                        <>* <>*                W H I T E       S T E L L A R       B A N K               *<> *<>");
        System.out.println(
                "                                        ==========================================================================================");
    }

    public static void loginedTemp() {
        tradeMark();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(loginShow());
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        tradeMark();
    }

    public static void clrScreen() {
        for (int i = 0; i < 5; i++) {
            System.out.println(" ");
        }
    }

    public static String balNotif(int bal) {
        return "                                             > > > YOUR BALANCE IS NOW " + bal + "< < <";
    }

    public static String loginShow() {
        return "                                                                     > > > YOU ARE LOGGED IN < < <";
    }

    public static String invalidShow() {
        return "                                                                     > > > ACCOUNT INVALID < < <";
    }

    public static boolean isTransac() {
        Scanner scan = new Scanner(System.in);
        System.out.print("                                        DO YOU WANT ANOTHER TRANSACTION? Y/y || N/n : ");
        String choiceBase = scan.nextLine();
        if (choiceBase.equals("y") || choiceBase.equals("Y")) {
            // back to 1
            return true;
        } else if (choiceBase.equals("n") || choiceBase.equals("N")) {
            return false;
        } else {
            System.out.println("                                        PLEASE ENTER A VALID CHARACTER");
            return isTransac(); // return transact in case other input is done since we need to return false or
                                // true
        }
    }

    // "Inquire","Withraw","Deposit","Cancel"
    public static void newScene(int choice, String[] ops, int accNum, int bal) {
        String mainChoice = ops[choice];
        Scanner scan = new Scanner(System.in);
        String choiceBase = " ";
        boolean isValidWithraw = false;
        boolean isValidDep = false;
        int baseBalance = bal;

        clrScreen();
        clrScreen();
        clrScreen();
        switch (mainChoice) {
            case "Inquire":
                tradeMark();
                clrScreen();
                clrScreen();
                System.out.println("                                        YOUR ACCOUNT NUMBER IS " + accNum);
                System.out.println("                                        YOUR BALANCE IS " + "PHP " + baseBalance);
                clrScreen();
                clrScreen();
                tradeMark();

                if (isTransac()) {
                    clrScreen();
                    clrScreen();
                    loginedTemp();

                    menu(ops, accNum, baseBalance); // go back to menu with updated balance
                } else {
                    clrScreen();
                    clrScreen();
                    clrScreen();
                    clrScreen();
                    onStart(baseBalance); // restart the program with the updated balance to save the previous one
                }
                break;

            case "Withraw":
                tradeMark();
                do {
                    System.out.print("                                        ENTER AMOUNT TO WITHRAW: ");

                    int withrawAm = scan.nextInt();
                    if (withrawAm <= bal) {
                        baseBalance -= withrawAm;
                        clrScreen();
                        clrScreen();
                        System.out.println(balNotif(baseBalance)); // function to show balacne
                        clrScreen();
                        clrScreen();
                        isValidWithraw = true;
                        if (isTransac()) {
                            clrScreen();
                            clrScreen();
                            loginedTemp();

                            menu(ops, accNum, baseBalance);// go back to menu with updated balance
                        } else {
                            clrScreen();
                            clrScreen();
                            clrScreen();
                            clrScreen();
                            onStart(baseBalance);// if transac returns false meaning no transaction, go bac to onstart
                        }
                    } else {
                        System.out.println("                                         INVALID AMOUNT ");
                    }

                } while (!isValidWithraw);
                break;
            case "Deposit":
                do {
                    tradeMark();
                    clrScreen();
                    clrScreen();
                    System.out.print("                                        ENTER AMOUNT TO DEPOSIT: ");
                    int depAm = scan.nextInt();
                    if (depAm >= 0) {

                        baseBalance += depAm;
                        tradeMark();
                        clrScreen();
                        clrScreen();
                        System.out.println(balNotif(baseBalance));
                        clrScreen();
                        clrScreen();
                        tradeMark();
                        isValidDep = true;
                        if (isTransac()) {
                            clrScreen();
                            clrScreen();
                            clrScreen();
                            menu(ops, accNum, baseBalance); // go back to menu with updated balance
                        } else {
                            onStart(baseBalance); // if transac returns false meaning no transaction, go bac to onstart
                        }
                    } else {
                        System.out.println("                                         INVALID AMOUNT ");
                    }
                } while (!isValidDep);
                break;
            case "Cancel":
                onStart(baseBalance);
                break;
            default:
                break;
        }
    }

    public static void menu(String[] options, int accNum, int bal) {
        boolean isLogout = false;
        Scanner scan = new Scanner(System.in);
        int i = 0; // used in for each iteration base
        int choice = 0; // use in terminal feel aesthetic
        do {
            i = 0;
            for (String opt : options) {
                if (choice == i) { // compare if choice is the same as iteration and then add arrow
                    System.out.println("                                         > " + opt);
                } else {
                    System.out.println("                                         " + opt);
                }
                i++;
            }
            System.out.print(
                    "                                        W/w -> up || D/d -> down || C/c -> confirm            Choice: ");
            String chBase = scan.nextLine();
            clrScreen();
            clrScreen();
            switch (chBase) {
                case "d": // increment choice to go down 0,1,2,3 like indexing of the array which is in
                          // the ops
                    loginedTemp();

                    if (choice >= options.length - 1) {
                        choice = 0;
                    } else {
                        choice++;
                    }
                    break;
                case "w":
                    loginedTemp();

                    if (choice == 0) {
                        choice = 0;
                    } else {
                        choice--;
                    }
                    break;
                case "c":
                    newScene(choice, options, accNum, bal); // new section with updated balance withraw incuire etc.
                    isLogout = true;
                    break;

                default:
                    loginedTemp(); // login template
                    choice = 0;
                    break;
            }

        } while (!isLogout);
    }

    public static void onStart(int bal) {
        int accNumb = 12345;
        int balance = bal; // store bal here
        int accInp = 0;
        String[] options = { "Inquire", "Withraw", "Deposit", "Cancel" };
        Scanner scan = new Scanner(System.in);
        boolean isLogin = false;

        do {
            clrScreen(); // aesthetic purposes
            tradeMark();
            System.out.print("                                        ENTER ACCOUNT NUMBER: ");
            accInp = scan.nextInt();
            if (accInp == accNumb) { // acc comparison with acc numb and user input
                isLogin = true; // if correct stop the loop and go to next
            } else {
                System.out.println(invalidShow());
            }
        } while (!isLogin); // stop this condition as long as the acc numb and input is met
        clrScreen();
        clrScreen();
        tradeMark();
        clrScreen();
        System.out.println(loginShow());
        clrScreen();
        tradeMark();
        menu(options, accNumb, balance);// pass the option param,
    }

    public static void main(String[] args) {
        int bal = 5000;
        onStart(bal); // start the porgram with inital balance of 5000
    }
}
