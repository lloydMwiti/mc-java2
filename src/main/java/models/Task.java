package models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Task {

    private String name;
    private String age;
    private String power;
    private String fight;
    private String weakness;
    private boolean completed;
    private static ArrayList<Task> instances = new ArrayList<>();
    private LocalDateTime createdAt;



    private int id;

    public Task(String name,String power,String age,String weakness,String fight){
        this.name = name;
        this.power = power;
        this.age = age;
        this.weakness = weakness;
        this.fight = fight;
        this.id = instances.size();
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getCompleted() == task.getCompleted() &&
                getId() == task.getId() &&
                Objects.equals(getName(),task.getName()) &&
                Objects.equals(getPower(),task.getPower()) &&
                Objects.equals(getAge(),task.getAge()) &&
                Objects.equals(getWeakness(),task.getWeakness()) &&
                Objects.equals(getFight(),task.getFight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),getPower(),getAge(),getWeakness(),getFight(), getCompleted(), getId());
    }
    public static void setInstances(ArrayList<Task> instances) {
        Task.instances = instances;
    }
    public static void clearAllTasks(){
        instances.clear();
    }
    public String getName() {return name;}
    public String getPower() {return power;}
    public String getAge() {return age;}
    public String getWeakness() {return weakness;}
    public String getFight() {return fight;}
    public boolean getCompleted(){ return this.completed;}
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public static ArrayList<Task> getAll(){
        return instances;
    }
    public int getId() {
        return id;
    }
    public static Task findById(int id){return instances.get(id-1);}

    public void setName(String name) {this.name = name;}
    public void setPower(String power) {this.power = power;}
    public void setAge(String age) {this.age = age;}
    public void setWeakness(String weakness) {this.weakness = weakness;}
    public void setFight(String fight) {this.fight = fight;}
    public void setCompleted(boolean completed) {this.completed = completed; }
    public void setId(int id) { this.id = id;  }


}
