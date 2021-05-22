import java.util.Scanner;

public class TextMenu {
    final String MENU_TITLE = "Personal Reminder";
    final String[] OPTIONS = {"List all tasks", "Add a new task", "Remove a task",
                            "Mark a task as completed", "List overdue incomplete tasks",
                            "List upcoming incomplete tasks", "Exit"};
    final String MENU_PROMPT = "Choose an option by entering 1-7: ";
    final String INVALID_MENU_PROMPT = "Invalid selection. Enter a number between 1 and 7";
    Scanner scanner = new Scanner(System.in);

    public TextMenu(){}

    public void printMenu(){
        printTitle();
        printOptions();
    }

    private void printTitle(){
        for(int i = 0; i < MENU_TITLE.length() + 4; i++){
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

    public void makeChoice(){
        int selection = getInput();
        switch (selection){
            case 1:
                //list all tasks
                break;
            case 2:
                //Add new task
                break;
            case 3:
                //Remove task
                break;
            case 4:
                //Mark task as completed
                break;
            case 5:
                //List overdue incomplete tasks
                break;
            case 6:
                //List upcoming incomplete tasks
                break;
            case 7:
                System.exit(0);
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

    public static void main(String[] args){
        TextMenu test = new TextMenu();
        test.printMenu();
    }
}
