import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 * TaskManager provides the functionality listed in the options.
 * The object stores input prompts and an arraylist of tasks to manage.
 * Features include loading tasks from csv file, selecting the correct option
 * based on input, printing all tasks in list, adding/deleting task to list, marking
 * a task as complete, list upcoming/overdue tasks, saving to csv and exiting.
 * Note the main method is also in this class.
 */

public class TaskManager {
    public static final String NAME_PROMPT = "Enter the name of new task: ";
    public static final String NOTES_PROMPT = "Enter the notes of the new task: ";
    public static final String EMPTY_NAME_PROMPT = "Name can't be empty";
    public static final String NO_OVERDUE_TASKS = "No overdue incomplete tasks to show.";
    public static final String NO_INCOMPLETE_TASKS = "No incomplete tasks to show.";
    public static final String EMPTY_ARRAY_MSG = "No tasks to show.";
    public static final String REMOVE_PROMPT = "Enter the task number you want to remove (0 to cancel): ";
    public static final String INVALID_VALUE_PROMPT = "Invalid selection, value out of range";
    public static final String MARK_COMPLETE_PROMPT = "Enter the task number you want to mark as completed (0 to cancel): ";
    public static final String NO_UPCOMING_INCOMPLETE = "No upcoming incomplete tasks to show.";
    public static final String YEAR_PROMPT = "Enter the year of the due date: ";
    public static final String MONTH_PROMPT = "Enter the month of the due date (1-12): ";
    public static final String INVALID_MONTH_PROMPT = "Error: month must be between 1 and 12";
    public static final String DAY_PROMPT = "Enter the day of the due date (1-28/29/30/31): ";
    public static final String NON_EXISTENT_DATE = "Error: this date does not exist.";
    public static final String HOUR_PROMPT = "Enter the hour of the due date (0-23): ";
    public static final String INVALID_HOUR_PROMPT = "Error: hour must be between 0 and 23";
    public static final String MINUTE_PROMPT = "Enter the minute of the due date (0-59):";
    public static final String INVALID_MINUTE_PROMPT = "Error: minute must be between 0 and 59";
    private ArrayList<Task> tasks;
    private Scanner sc = new Scanner(System.in);

    public TaskManager(){
        tasks = new ArrayList<>();
        load();
    }

    private void load(){
        Gson gson = new Gson();
        try {
            //https://howtodoinjava.com/gson/gson-parse-json-array/
            JsonReader reader = new JsonReader(new FileReader("task.csv"));
            Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
            tasks = gson.fromJson(reader, taskListType);
            reader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeSelection(int choice){
        switch (choice) {
            case 1 -> printTasks();
            case 2 -> addTask();
            case 3 -> delete();
            case 4 -> markAsComplete();
            case 5 -> listOverDue();
            case 6 -> listUpcoming();
            case 7 -> exit();
        }
    }

    public void printTasks(){
        if(tasks.isEmpty()){
            System.out.println(EMPTY_ARRAY_MSG);
        }
        for(int i = 0; i < tasks.size(); i++){
            Collections.sort(tasks);
            System.out.println("Task #" + (i+1));
            System.out.println(tasks.get(i));
            System.out.print("Completed? ");
            if(tasks.get(i).isComplete()){
                System.out.println("Yes\n");
            } else{
                System.out.println("No\n");
            }
        }
    }

    public void addTask(){
        System.out.print(NAME_PROMPT);
        String name = sc.nextLine();
        while(name.trim().isEmpty()){
            System.out.println(EMPTY_NAME_PROMPT);
            System.out.print(NAME_PROMPT);
            name = sc.nextLine();
        }
        System.out.print(NOTES_PROMPT);
        String notes = sc.nextLine();

        Task temp = new Task(name, notes, getDateInput());
        tasks.add(temp);
        System.out.println("Task " + temp.getName() + " has been added to the list of tasks.");
    }

    public GregorianCalendar getDateInput(){
        System.out.print(YEAR_PROMPT);
        int year = sc.nextInt();
        System.out.print(MONTH_PROMPT);
        int month = sc.nextInt();
        while (month < 1 || month > 12) {
            System.out.println(INVALID_MONTH_PROMPT);
            System.out.print(MONTH_PROMPT);
            month = sc.nextInt();
        }
        boolean isDateValid = false;
        int day = 0;
        while (!isDateValid) {
            try {
                System.out.print(DAY_PROMPT);
                day = sc.nextInt();
                LocalDate.of(year, month, day);
                isDateValid = true;
            } catch (DateTimeException e) {
                System.out.println(NON_EXISTENT_DATE);
            }
        }
        System.out.print(HOUR_PROMPT);
        int hour = sc.nextInt();
        while(hour < 0 || hour > 23) {
            System.out.println(INVALID_HOUR_PROMPT);
            System.out.print(HOUR_PROMPT);
            hour = sc.nextInt();
        }
        System.out.print(MINUTE_PROMPT);
        int minute = sc.nextInt();
        while(minute < 0 || minute > 59) {
            System.out.println(INVALID_MINUTE_PROMPT);
            System.out.print(MINUTE_PROMPT);
            minute = sc.nextInt();
        }
        return new GregorianCalendar(year, month, day, hour, minute);
    }

    public void delete(){
        printTasks();
        System.out.print(REMOVE_PROMPT);
        int toDelete = sc.nextInt();
        while(toDelete < 0 || toDelete > tasks.size()){
            System.out.println(INVALID_VALUE_PROMPT);
            System.out.print(REMOVE_PROMPT);
            toDelete = sc.nextInt();
        }
        if(toDelete > 0) {
            toDelete--;     //Selected task is stored at index i-1
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
                System.out.println(tasks.get(i));
            }
        }
        if(!hasIncomplete){
            System.out.println(NO_INCOMPLETE_TASKS);
        }
        System.out.println(MARK_COMPLETE_PROMPT);
        int toMark = sc.nextInt();
        while (toMark < 0 || toMark >tasks.size()){
            System.out.println(INVALID_VALUE_PROMPT);
            System.out.print(MARK_COMPLETE_PROMPT);
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
        current.add(Calendar.MONTH, 1);     //Month is stored 1 less than actual value.
        Collections.sort(tasks);
        boolean hasOverDue = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(current.after(temp.getDueDate()) && !temp.isComplete()){
                hasOverDue = true;
                System.out.println("Task #" + (i+1));
                System.out.println(tasks.get(i));
            }
        }
        if (!hasOverDue){
            System.out.println(NO_OVERDUE_TASKS);
        }
    }

    public void listUpcoming(){
        Calendar current = Calendar.getInstance();
        current.add(Calendar.MONTH, 1);
        Collections.sort(tasks);
        boolean hasUpcoming = false;
        for(int i = 0; i < tasks.size(); i++){
            Task temp = tasks.get(i);
            if(current.before(temp.getDueDate()) && !temp.isComplete()){
                hasUpcoming = true;
                System.out.println(tasks.get(i));
            }
        }
        if (!hasUpcoming){
            System.out.println(NO_UPCOMING_INCOMPLETE);
        }
    }

    private void save(){
        try {
            //https://attacomsian.com/blog/gson-write-json-file
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("task.csv"));
            gson.toJson(tasks, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot save to file.");
        }
    }

    public void exit(){
        save();
        System.out.println("Thank you for using the system");
    }

    public static void main(String[] args){
        TextMenu menu = new TextMenu();
        TaskManager manager = new TaskManager();
        int selection = 0;
        while(selection != 7){
            menu.printMenu();
            selection = menu.getInput();
            manager.makeSelection(selection);
        }
    }
}
