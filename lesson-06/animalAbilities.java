/*      animalAbilities

1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/

// Способность объекта к какому-либо действию определяет интерфейс
interface Moveable {
    public boolean swim(int distance);
    public boolean jump(double distance);
    public boolean  run(int distance);
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

    @Override public boolean jump(double distance) { return distance <= maxJump; }
    @Override public boolean swim(int distance)    { return distance <= maxSwim; }
    @Override public boolean  run(int distance)    { return distance <= maxRun;  }
}

class Cat extends Animal {
    public Cat() { super(200, 2, 0); }
    // Можно задавать свои значения для каждого объекта, что вполне логично
    public Cat(int maxRun, double maxJump, int maxSwim) { super(maxRun, maxJump, maxSwim); }
}

class Dog extends Animal {
    public Dog() { super(500, 0.5, 10); }
    // А можно изменять ограничители, относительно исходных/базовых значений по умолчанию, но они не так удобны
    public Dog(int run, double jump, int swim) { this(); maxRun += run; maxJump += jump; maxSwim += swim; }
}

public class animalAbilities {
    public static void main(String[] args) {
        // Каждый конткретный экзепляр собаки определяется относительно базовых значений
        Dog haskey = new Dog(300, 0, 0); // например, хаски более вынослива, чем обычная собака (в остальном ничем не отличается)
        Dog mops = new Dog(-200, -0.5, 0); // а мопс бегает хуже, да и прыгает плохо

        // А для каждого экзепляра кота мы задаем уже свои значения т.к. кошки могут быть очень разными (вприниципе, что верно и для собак)
        Cat barsik = new Cat(); // допустим у нас есть обычный кот барсик
        Cat gepard = new Cat(600, 5, 5); // а есть гепард - спорткар кошачьего мира
    }
}