import java.util.GregorianCalendar;

public class Task {
    String name;
    String notes;
    GregorianCalendar dueDate;
    boolean isComplete;

    public Task(String name, String notes, GregorianCalendar dueDate){
        this.name = name;
        this.notes = notes;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public void setComplete(){
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.notes + "\n"
                + this.dueDate + "\n" + this.isComplete + "\n";
    }
}
