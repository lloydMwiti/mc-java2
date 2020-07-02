import java.util.ArrayList;

public class Post {
    private String name;
    private String power;
    private static ArrayList<Post> instances = new ArrayList<>();

    public Post (String name,String power){
        this.name = name;
        this.power = power;
        instances.add(this);
    }
    public String getName() {
        return name;
    }
    public String getPower() {
        return power;
    }
    public static ArrayList<Post> getAll(){
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }
}
