/*      animalAbilities

1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/

// Способность объекта к какому-либо действию определяет интерфейс
interface Moveable {
    public boolean swim(double distance);
    public boolean jump(double distance);
    public boolean  run(double distance);
}

// Абстрактный класс животного - его экземпляры мы не создаем, однако он предоставляет общую структуру для всех животных
abstract class Animal implements Moveable {
    protected int maxSwim;
    protected double maxJump;
    protected int maxRun;

    public Animal(int maxRun, double maxJump, int maxSwim) {
        this.maxSwim = maxSwim;
        this.maxJump = maxJump;
        this.maxRun  = maxRun;
    }

    private String name; // вводим имя для явного различия объектов между собой (для удобства в конструктор его не включаем)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override public boolean jump(double distance) { return distance <= maxJump; }
    @Override public boolean swim(double distance) { return distance <= maxSwim; }
    @Override public boolean  run(double distance) { return distance <= maxRun;  }
}

// Мы либо используем значения по умолчанию, либо создаем свой пользовательский экземляр с заданными характеристиками
class Cat extends Animal {
    public Cat() { super(200, 2, 0); }
    public Cat(int maxRun, double maxJump, int maxSwim) { super(maxRun, maxJump, maxSwim); }
}

class Dog extends Animal {
    public Dog() { super(500, 0.5, 10); }
    public Dog(int maxRun, double maxJump, int maxSwim) { super(maxRun, maxJump, maxSwim); }
}

enum movingType {elevation, water, ground};

class Obstacle {
    double size; // размер препятствия
    movingType moving; // тип препятствия (вода, трасса, возвышенность)
    public Obstacle(movingType moving, double size) {
        this.moving = moving;
        this.size = size;
    }
}

public class animalAbilities {
    public static void main(String[] args) {
        // Мы создаем несложную полосу препятствий и даем возможность пройти ее каждому участнику
        Animal[] members = getMembers();
        Obstacle[] track = getSimpleTrack();

        for (Animal animal : members) {
            System.out.println("participant " + animal.getName() + " (" + animal.getClass().getName() + ")");
            boolean success = false;
            for (Obstacle obstacle : track) {
                success = moving(animal, obstacle);
                System.out.println("obstacle: " + obstacle.moving.name() + " (" + obstacle.size + " m) - " + success);
                if (!success) break; // если участник не преодолевает препятствие, двигаться дальше он не может
            }
            System.out.println(success ? "track complete!": "obstacle fail");
            System.out.println();
        }
    }

    // Создаем набор препятствий и объединяем в массив
    public static Obstacle[] getSimpleTrack() {
        Obstacle[] track = new Obstacle[5];
        track[0] = new Obstacle(movingType.elevation, 0.3);
        track[1] = new Obstacle(movingType.ground, 100.0);
        track[2] = new Obstacle(movingType.water, 3.0);
        track[3] = new Obstacle(movingType.elevation, 1.0);
        track[4] = new Obstacle(movingType.ground, 400.0);
        return track;
    }

    // Также мы задаем набор спортсменов дикого мира
    public static Animal[] getMembers() {
        // Хаски имеет увеличенный запас хода, но с прыжками у него не так хорошо, как у котов, а плавает средне
        Dog haskey = new Dog(800, 1.5, 15);
        haskey.setName("Fang");

        // Мопс бегает плохо, может преодолевать незначительные возвышенности и водные переправы
        Dog mops = new Dog(100, 0.3, 3);
        mops.setName("Chappie");

        // Бенгальский кот - самый обычный кот, не умеет только плавать
        Cat bengal = new Cat();
        bengal.setName("Barsik");

        // Гепард может преодолевать большие расстояния и запрыгнуть куда угодно, может немного плавать, но остается котом
        Cat gepard = new Cat(600, 3.5, 5);
        gepard.setName("Hercules");

        Animal[] members = {haskey, mops, bengal, gepard};
        return members;
    }

    // Для каждого препятствия используется свой метод (способ преодоления)
    public static boolean moving(Animal animal, Obstacle obstacle) {
        switch (obstacle.moving) {
            case water: return animal.swim(obstacle.size);
            case ground: return animal.run(obstacle.size);
            case elevation: return animal.jump(obstacle.size);
            default: return false;
        }
    }
}