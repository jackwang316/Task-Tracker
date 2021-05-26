import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final String EMPTY_ARRAY_MSG = "No tasks to show.";

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

    public void delete(int i){
        tasks.remove(i);
    }

    public void makeComplete(int i){
        tasks.get(i).setComplete();
    }
}
