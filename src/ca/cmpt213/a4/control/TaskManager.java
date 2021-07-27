package ca.cmpt213.a4.control;

import ca.cmpt213.a4.model.Task;
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
    private ArrayList<Task> tasks;
    private Calendar current = Calendar.getInstance();

    public TaskManager(){
        tasks = new ArrayList<>();
        current.add(Calendar.MONTH, 1); //Month starts at 0 instead of 1.
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

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public ArrayList<Task> getOverdue() {
        ArrayList<Task> result = new ArrayList<>();
        for(Task t : tasks) {
            if (current.after(t.getDueDate())) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Task> getUpcoming() {
        ArrayList<Task> result = new ArrayList<>();
        for(Task t : tasks) {
            if(current.before(t.getDueDate())) {
                result.add(t);
            }
        }
        return result;
    }

    public void addTask(String name, String notes, GregorianCalendar dueDate){
        tasks.add(new Task(name, notes, dueDate));
    }

    public void delete(Task task){
        tasks.remove(task);
    }

    public void markAsComplete(Task task, boolean isComplete) {
        int location = tasks.indexOf(task);
        if(location != -1) {
            tasks.get(location).setComplete(isComplete);
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
}
