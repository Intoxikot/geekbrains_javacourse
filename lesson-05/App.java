
public class App {
    public static void main(String[] args) {
        Employee[] workstate = new Employee[6];

        workstate[0] = new Employee("Nik Koteev", 25, "Director", 5000, "netoxikot@gmail.com", 9525155735L);
        workstate[1] = new Employee("Dmitriy Fedorov", 22, "Manager", 1500, "fedorov@mail.ru", 9528002020L);
        workstate[2] = new Employee("Alexey Marchenko", 24, "System Administrator", 1200, "marchenko@mail.ru", 9991002030L);
        workstate[3] = new Employee("Karl Fridman", 20, "Fractal Engineer", 3400, "fridman@gmail.com", 9909009090L);
        workstate[4] = new Employee("Pavel Evdokimov", 27, "Designer", 2200, "evdokimov@yandex.ru", 9009008080L);
        workstate[5] = new Employee("Sergey Slovakin", 21, "Coffee Department Leader", 4400, "slovakin@yandex.ru", 8006005040L);
		
		// В моем штате молодые сотрудники, так что выводим тех, кто от 22 и старше :D
        for (Employee employee: workstate)
            if (employee.getAge() >= 22) employee.showInfo();
    }
}