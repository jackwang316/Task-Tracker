import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TaskManager {
    public static final String NAME_PROMPT = "Enter the name of new task ";
    public static final String NOTES_PROMPT = "Enter the notes of the new task: ";
    public static final String EMPTY_NAME_PROMPT = "Name can't be empty";
    public static final String NO_OVERDUE_TASKS = "No overdue incomplete tasks to show.";
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
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("Task #" + (i+1));
            System.out.println("Task: " + tasks.get(i).getName());
            System.out.println("Notes: " + tasks.get(i).getNotes());
            System.out.println("Due date: " + tasks.get(i).getDateFormatted());
            System.out.print("Completed? ");
            if(tasks.get(i).getComplete()){
                System.out.println("Yes");
            } else{
                System.out.println("No");
            }
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

    public void delete(){
        printTasks();
        System.out.print(REMOVE_PROMPT);
        int toDelete = sc.nextInt();
        while(toDelete < 0 || toDelete > tasks.size()){
            System.out.print(INVALID_REMOVE_PROMPT);
            System.out.print(REMOVE_PROMPT);
            toDelete = sc.nextInt();
        }
        if(toDelete > 0) {
            String deletedName = tasks.get(toDelete).getName();
            tasks.remove(toDelete);
            System.out.println("Task " + deletedName + " has been removed from the list of tasks");
        }
    }

    public void listOverDue(){
        Calendar current = Calendar.getInstance();
        boolean hasOverDue = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(current.compareTo(temp.getDueDate()) > 0 && !temp.getComplete()){
                hasOverDue = true;
                System.out.println("Task #" + (i+1));
                System.out.println("Task: " + tasks.get(i).getName());
                System.out.println("Notes: " + tasks.get(i).getNotes());
                System.out.println("Due date: " + tasks.get(i).getDateFormatted());
            }
        }
        if (!hasOverDue){
            System.out.println(NO_OVERDUE_TASKS);
        }
    }

    public static void main(String[] args){

    }
}
