/*		TimeToEat

1. Расширить задачу про котов и тарелки с едой.
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
(например, в миске 1 0 еды, а кот пытается покушать 1 5-20).
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту
удалось покушать (хватило еды), сытость = true.
4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть
наполовину сыт (это сделано для упрощения логики программы).
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и
потом вывести информацию о сытости котов в консоль.
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
*/

class Plate {
    private int food;
    public Plate(int food) { this.food = food; }
    public void decreaseFood(int requested) { food -= (requested <= food) ? requested: food; } // мы не можем съесть еды больше, чем есть в наличии
    public int getFood() { return food; }
    public void addFood(int volume) { food += volume; }
    public void info() { System.out.println("plate: " + food); }
}

class Cat {
    private String name;
    private int appetite;
    private boolean fed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fed = false;
    }

    public boolean eat(Plate p) {
        if (fed)
            return false; // кот не будет есть, если он уже сыт
        else if (this.appetite <= p.getFood()) {
            p.decreaseFood(appetite);
            fed = true;
            return fed;
        }
        else
            return false; // если коту не хватает еды, он расстраивается и ничего не ест
    }
}

public class Main {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik", 5);
        Plate plate = new Plate(10);
        plate.info();
        barsik.eat(plate);
        plate.addFood(30); // добавляем еду
        plate.info();
        Cat mursik = new Cat("Mursik", 15); // крайне прожорливый кот
        mursik.eat(plate);
        plate.info();
    }
}