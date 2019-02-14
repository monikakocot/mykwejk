package pl.akademiakodu.kwejk.model;

public class Category {

    private int id;
    private String name;
    // KOD 16 - for automation of the Category number
    static private int nextId =1;

    public Category(int id, String name) {
        //this.id = id;
        //KOD 16
        this.name = name;
        this.id = nextId;
        setId(id);
    }
    public Category(String name) {
        //this.id = id;
        this.name = name;
        this.id = nextId;
        setId(id);
    }

    public Category() {}


    //GETTERS, SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
