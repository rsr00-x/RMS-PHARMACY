//package models;

public abstract class Entity {
    protected int id;
    protected String name;

	public Entity(){}
    
    public Entity(int id, String name) {
        setId(id);
        setName(name);
    }
    
	public void setId(int id){
		this.id = id;
	}
    public int getId() { 
        return id;
    }

	public void setName(String name){
		this.name =name;
	}

	public String getName(){
		return name;
	}
    
    public void display(){
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
	}
}
