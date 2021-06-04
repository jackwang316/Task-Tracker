import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * The TextMenu class represents the options menu. Data includes the title,
 * options, and various input prompts. Supported features include displaying the menu and
 * reading integer input from keyboard.
 */
public class TextMenu {
    public static final int NUM_PADDING = 4;
    public final String MENU_TITLE = "Personal Reminder";
    public final String[] OPTIONS = {"List all tasks", "Add a new task", "Remove a task",
                            "Mark a task as completed", "List overdue incomplete tasks",
                            "List upcoming incomplete tasks", "Exit"};
    public final String MENU_PROMPT = "Choose an option by entering 1-7: ";
    public final String INVALID_MENU_PROMPT = "Invalid selection. Enter a number between 1 and 7";
    public Scanner scanner = new Scanner(System.in);

    public TextMenu(){}

    public void printMenu(){
        printTitle();
        printOptions();
    }

    private void printTitle(){
        for(int i = 0; i < MENU_TITLE.length() + NUM_PADDING; i++){
            System.out.print("#");
        }
        System.out.println();
        System.out.print("# ");
        System.out.print(MENU_TITLE);
        System.out.println(" #");
        for(int i = 0; i < MENU_TITLE.length() + 4; i++){
            System.out.print("#");
        }
        System.out.println();
    }

    private void printOptions(){
        for(int i = 0; i < OPTIONS.length; i++){
            int j = i + 1;
            System.out.println(j + ": " + OPTIONS[i]);
        }
    }

    public int getInput(){
        System.out.print(MENU_PROMPT);
        int input = scanner.nextInt();
        while (input < 1 || input > 7){
            System.out.println(INVALID_MENU_PROMPT);
            System.out.print(MENU_PROMPT);
            input = scanner.nextInt();
        }
        return input;
    }
}
