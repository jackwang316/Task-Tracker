import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TaskManager {
    public static final String NAME_PROMPT = "Enter the name of new task ";
    public static final String NOTES_PROMPT = "Enter the notes of the new task: ";
    public static final String EMPTY_NAME_PROMPT = "Name can't be empty";
    private final String EMPTY_ARRAY_MSG = "No tasks to show.";
    private final String REMOVE_PROMPT = "Enter the task number you want to remove (0 to cancel): ";
    private final String INVALID_REMOVE_PROMPT = "Invalid selection, value out of range";
    private ArrayList<Task> tasks;
    private Scanner sc = new Scanner(System.in);

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void printTasks(){
        if(tasks.isEmpty()){
            System.out.println(EMPTY_ARRAY_MSG);
        }
        for(Task temp : tasks){
            System.out.println(temp.toString());
        }
    }

    public void addTask(){
        System.out.print(NAME_PROMPT);
        String name = sc.nextLine();
        while(name.isEmpty()){
            System.out.println(EMPTY_NAME_PROMPT);
            System.out.print(NAME_PROMPT);
            sc.nextLine();
        }
        System.out.print(NOTES_PROMPT);
        String task = sc.nextLine();
    }

    public void delete(int i){
        printTasks();
        System.out.print(REMOVE_PROMPT);
        int toDelete = sc.nextInt();
        while(toDelete < 0 || toDelete > tasks.size()){
            System.out.print(INVALID_REMOVE_PROMPT);
            System.out.print(REMOVE_PROMPT);
            toDelete = sc.nextInt();
        }
        if(toDelete > 0) {
            tasks.remove(i);
        }
    }
}
