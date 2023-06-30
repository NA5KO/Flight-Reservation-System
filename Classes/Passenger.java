public class Passenger {
    private String first_name;
    private String last_name;
    private String nationality;
    private int age;
    private int phone_number;
    private String email;
    private float bank_account;
    private String password;
    private int passport_id;

    // Constructors
    //default constructor

    // Getters and Setters
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name= first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name= last_name;
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password= password;
    }
    public String getnationality(){
        return nationality;
    }
    public void setnationality(String nationality){
        this.nationality= nationality;
    }
    public int getage(){
        return age;
    }
    public void setage(int age){
        this.age= age;
    }
    public int getphone_number(){
        return phone_number;
    }
    public void setphone_number(int phone_number){
        this.phone_number= phone_number;
    }
    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email= email;
    }
    public float getbank_account(){
        return bank_account;
    }
    public void setbank_account(float bank_account){
        this.bank_account= bank_account;
    }
    public int getpassport_id(){
        return passport_id;
    }
    public void setpassport_id(int passport_id){
        this.passport_id= passport_id;
    }
    public void setpassword(char[] password) {
        this.password = String.valueOf(password);
    }
    public String getpassword(char[] password) {
        return String.valueOf(password);
    }
    // Methods


}
