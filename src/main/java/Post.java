import java.util.ArrayList;
import java.util.Collections;

public class Post {
    private String name,weakness,age,fight,power;
    private int members;

    private static ArrayList<Post> instances = new ArrayList<>();

    public Post (String name,String power,String weakness,String age,String fight){
        this.name = name;
        this.power = power;
        this.weakness = weakness;
        this.age = age;
        this.fight = fight;

        this.members =Collections.frequency(instances,this.fight);

        instances.add(this);
    }
    public String getAge(){return age;}
    public String getName() {
        return name;
    }
    public String getPower() {
        return power;
    }
    public String getWeakness(){return weakness;}
    public String getFight(){return fight;}
    public int getMembers(){return members;}
    public static ArrayList<Post> getAll(){
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }
}
