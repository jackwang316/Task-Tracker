import java.util.*;

public class TaskManager {
    public static final String NAME_PROMPT = "Enter the name of new task ";
    public static final String NOTES_PROMPT = "Enter the notes of the new task: ";
    public static final String EMPTY_NAME_PROMPT = "Name can't be empty";
    public static final String NO_OVERDUE_TASKS = "No overdue incomplete tasks to show.";
    public static final String NO_INCOMPLETE_TASKS = "No incomplete tasks to show.";
    private static final String EMPTY_ARRAY_MSG = "No tasks to show.";
    private static final String REMOVE_PROMPT = "Enter the task number you want to remove (0 to cancel): ";
    private static final String INVALID_VALUE_PROMPT = "Invalid selection, value out of range";
    public static final String MARK_COMPLETE_PROMPT = "Enter the task number you want to mark as completed (0 to cancel): ";
    public static final String NO_UPCOMING_INCOMPLETE = "No upcoming incomplete tasks to show.";
    public ArrayList<Task> tasks;
    private Scanner sc = new Scanner(System.in);

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void makeSelection(int choice){
        switch (choice){
            case 1:
                printTasks();
                break;
            case 2:
                addTask();
                break;
            case 3:
                delete();
                break;
            case 4:
                markAsComplete();
                break;
            case 5:
                listOverDue();
                break;
            case 6:
                listUpcoming();
                break;
            case 7:
                //Exit
        }
    }

    public void printTasks(){
        if(tasks.isEmpty()){
            System.out.println(EMPTY_ARRAY_MSG);
        }
        for(int i = 0; i < tasks.size(); i++){
            Collections.sort(tasks);
            System.out.println("Task #" + (i+1));
            System.out.println("Task: " + tasks.get(i).getName());
            System.out.println("Notes: " + tasks.get(i).getNotes());
            System.out.println("Due date: " + tasks.get(i).getDateFormatted());
            System.out.print("Completed? ");
            if(tasks.get(i).isComplete()){
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
            System.out.print(INVALID_VALUE_PROMPT);
            System.out.print(REMOVE_PROMPT);
            toDelete = sc.nextInt();
        }
        if(toDelete > 0) {
            toDelete--;
            String deletedName = tasks.get(toDelete).getName();
            tasks.remove(toDelete);
            System.out.println("Task " + deletedName + " has been removed from the list of tasks");
        }
    }

    public void markAsComplete(){
        Collections.sort(tasks);
        boolean hasIncomplete = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(!temp.isComplete()){
                hasIncomplete = true;
                System.out.println("Task #" + (i+1));
                System.out.println("Task: " + tasks.get(i).getName());
                System.out.println("Notes: " + tasks.get(i).getNotes());
                System.out.println("Due date: " + tasks.get(i).getDateFormatted() + "\n");
            }
        }
        if(!hasIncomplete){
            System.out.println(NO_INCOMPLETE_TASKS);
        }

        System.out.println(MARK_COMPLETE_PROMPT);
        int toMark = sc.nextInt();
        while (toMark < 0 || toMark >tasks.size()){
            System.out.println(INVALID_VALUE_PROMPT);
            System.out.println(MARK_COMPLETE_PROMPT);
            toMark = sc.nextInt();
        }
        if(toMark > 0) {
            toMark--;
            tasks.get(toMark).setComplete();
            System.out.println("Task " + tasks.get(toMark).getName() + " is now completed.");
        }
    }

    public void listOverDue(){
        Calendar current = Calendar.getInstance();
        Collections.sort(tasks);
        boolean hasOverDue = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(current.compareTo(temp.getDueDate()) > 0 && !temp.isComplete()){
                hasOverDue = true;
                System.out.println("Task #" + (i+1));
                System.out.println("Task: " + tasks.get(i).getName());
                System.out.println("Notes: " + tasks.get(i).getNotes());
                System.out.println("Due date: " + tasks.get(i).getDateFormatted() + "\n");
            }
        }
        if (!hasOverDue){
            System.out.println(NO_OVERDUE_TASKS);
        }
    }

    public void listUpcoming(){
        Calendar current = Calendar.getInstance();
        Collections.sort(tasks);
        boolean hasUpcoming = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(current.compareTo(temp.getDueDate()) < 0 && !temp.isComplete()){
                hasUpcoming = true;
                System.out.println("Task #" + (i+1));
                System.out.println("Task: " + tasks.get(i).getName());
                System.out.println("Notes: " + tasks.get(i).getNotes());
                System.out.println("Due date: " + tasks.get(i).getDateFormatted() + "\n");
            }
        }
        if (!hasUpcoming){
            System.out.println(NO_UPCOMING_INCOMPLETE);
        }
    }

    public static void main(String[] args){
        GregorianCalendar test1 = new GregorianCalendar(2029, 04, 21);
        GregorianCalendar test2 = new GregorianCalendar(2023, 04, 21);

        Task t1 = new Task("Test1", "", test1);
        Task t2 = new Task("Test2", "", test2);

        TaskManager taskManager = new TaskManager();
        taskManager.tasks.add(t2);
        taskManager.tasks.add(t1);
        taskManager.listUpcoming();

    }
}
