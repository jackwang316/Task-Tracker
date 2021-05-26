import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void printTasks(){
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
