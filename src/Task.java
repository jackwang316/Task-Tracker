import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Task models the information about a task that has or needs to be completed.
 * A task includes its name, notes regarding the task, the due date, and
 * whether or not the task has been completed. Features include retrieving the
 * attributes of the project, formatting the date to a proper string format and
 * formatting the entire object into one string.
 */
public class Task implements Comparable<Task> {
    private String name;
    private String notes;
    private GregorianCalendar dueDate;
    private boolean isComplete;

    public Task(String name, String notes, GregorianCalendar dueDate){
        this.name = name;
        this.notes = notes;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNotes() {
        return this.notes;
    }

    public boolean isComplete(){
        return this.isComplete;
    }

    public GregorianCalendar getDueDate(){
        return  this.dueDate;
    }

    public String getDateFormatted(){
        return dueDate.get(Calendar.YEAR) + "-" + dueDate.get(Calendar.MONTH)
                + "-" + dueDate.get(Calendar.DAY_OF_MONTH) + " " + dueDate.get(Calendar.HOUR_OF_DAY)
                + ":" + dueDate.get(Calendar.MINUTE);
    }

    public void setComplete(){
        this.isComplete = true;
    }

    @Override
    public String toString() {
        String result = "Task: " + this.getName() + "\n" + "Notes: " + getNotes() + "\n"
                        + "Due date: " + getDateFormatted();
        return result;
    }

    @Override
    public int compareTo(Task t) {
        return this.getDueDate().compareTo(t.getDueDate());
    }
}
