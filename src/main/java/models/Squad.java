package models;

public class Squad {
    private String name,cause,size;
    private int id;

    public Squad(String name,String cause){
        this.name=name;
        this.cause=cause;

    }


    // getter methods
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getCause() {
        return cause;
    }

    public String getSize() {
        return size;
    }

}
