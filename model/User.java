package model;

public class User {
    private int id;
    private String generatedId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;

    public User(){}

    public User(String generatedId, String name, String email, String passwordHash, String role){
        this.generatedId = generatedId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getGeneratedId(){
        return generatedId;
    }
    public void setGeneratedId(String generatedId){
        this.generatedId = generatedId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPasswordHash(){
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString(){
        return "User{"+
                "id=" + id +
                ", generatedId='" + generatedId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
