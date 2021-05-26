import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task {
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

    public boolean getComplete(){
        return this.isComplete;
    }

    public String getDateFormatted(){
        return dueDate.get(Calendar.YEAR) + "-" + dueDate.get(Calendar.MONTH)
                + "-" + dueDate.get(Calendar.DAY_OF_MONTH) + " " + dueDate.get(Calendar.HOUR)
                + ":" + dueDate.get(Calendar.MINUTE);
    }

    public void setComplete(){
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.notes + "\n"
                + this.dueDate.getTime() + "\n" + this.isComplete + "\n";
    }
}
