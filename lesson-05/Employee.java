
public class Employee {
    private String name;
    private int age;
    private String workpost;
    private int salary;
    private String email;
    private long phone;
	
    public Employee(String name, int age, String workpost, int salary, String email, long phone) {
        this.name = name;
        this.age = age;
        this.workpost = workpost;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
    }

    public String getName() { return this.name; }
    public String getWorkpost() { return this.workpost; }
    public String getEmail() { return this.email; }
    public int getAge() { return this.age; }
    public int getSalary() { return this.salary; }
    public String getPhone() { return "+7" + Long.toString(this.phone); }

    public void showInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("\t" + "Employee info:" + "\n");
        buf.append("employee: " + getName() + ", " + getAge() + " years" + "\n");
        buf.append("workpost: " + getWorkpost() + ", " + getSalary() + "$" + "\n");
        buf.append("contacts: " + getPhone() + ", " + getEmail() + "\n");
        return buf.toString();
    }	
}