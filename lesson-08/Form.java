/*		Manty Hall Problem

Выполнение программы разбито на два шага. Мы имеем два набора листенеров и переключаемся между ними. Варианта проще не нашел. Свернул анонимные классы в лямбду, чтобы выглядело компактнее и не так зловеще. Так же введены еще флаги шагов, что также наводит на некоторую избыточность, но только в таком варианте это работает

Было бы неплохо сделать логику графического приложения более высокоуровневой, чтобы оперировать не объектами формы, а терминами самой игры. Сказывается отсутствие опыта с библиотекой, готовых шаблонов и концепций
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    private JButton[] jbs;
    private boolean firstStep;
    private boolean secondStep;

    public Form() throws HeadlessException {
        setTitle("Manty Hall Problem");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 300);
        setLayout(new GridLayout());

        jbs = new JButton[3];
        for (int i = 0; i < jbs.length; i++)
            jbs[i] = new JButton("Door " + (i + 1));
        setLayout(new GridLayout());
        // firstListeners();
        // secondListeners();

        add(jbs[0]);
        add(jbs[1]);
        add(jbs[2]);

        setVisible(true);
        Begin();
    }

    private void Begin() {
        MantyHall.prize = MantyHall.random.nextInt();
        MantyHall.prize = MantyHall.random.nextInt(3);

        for (int i = 0; i < jbs.length; i++)
            jbs[i].setText("Door " + (i + 1));

        // Механизм работы в том, чтобы назначить нужный набор слушателей (предыдущий набор при этом очищается)
        firstListeners();
        // secondListeners();

        // Флаги отвечают за последовательность запуска/выполнения
        firstStep = true;
        secondStep = false;
    }

    // Удаляет всех слушателей с кнопок
    private void cleanListeners() {
        // if (true) return;
        for (int b = 0; b < jbs.length; b++) {
            ActionListener[] al = jbs[b].getActionListeners();
            //jbs[b].removeActionListener(al[0]);
            for (ActionListener lsnr : al)
                jbs[b].removeActionListener(lsnr);
        }
    }

    // Слушатели первого шага
    private void firstListeners() {
        jbs[0].addActionListener(event -> firstChoose(0)); // в цикле лямба-выражение разваливается (требует final)
        jbs[1].addActionListener(event -> firstChoose(1));
        jbs[2].addActionListener(event -> firstChoose(2));
    }

    // Слушатели второго шага
    private void secondListeners() {
        jbs[0].addActionListener(event -> secondChoose(0));
        jbs[1].addActionListener(event -> secondChoose(1));
        jbs[2].addActionListener(event -> secondChoose(2));
    }

    // Первая выборка
    private void firstChoose(int x) {
        if (!firstStep) return;

        // Мы открываем любую пустую дверь и повторяем вопрос
        int reply1 = JOptionPane.showConfirmDialog(null, "Your choose is " + (x+1) + " door", "Leader", JOptionPane.DEFAULT_OPTION);
        int free = MantyHall.getAnyFreeDoor(x, 0);
        System.out.println();
        jbs[free].setText("EMPTY");
        int reply2 = JOptionPane.showConfirmDialog(null, "Other door is open. Try again", "Leader", JOptionPane.DEFAULT_OPTION);

        // Изменить слушателей
        cleanListeners();
        secondListeners();

        // Перейти ко второму шагу
        firstStep = false;
        secondStep = true;
    }

    // Вторая выборка
    private void secondChoose(int x) {
        if (!secondStep) return;

        // Здесь решается победили мы или выиграли
        if (x == MantyHall.prize) {
            jbs[x].setText("WIN");
            int reply = JOptionPane.showConfirmDialog(null, "Congratulations! You win", "Leader", JOptionPane.DEFAULT_OPTION);
        } else {
            jbs[x].setText("EMPTY");
            int reply = JOptionPane.showConfirmDialog(null, "Oh, no! You lose", "Leader", JOptionPane.DEFAULT_OPTION);
        }

        // Завершаем работу
        cleanListeners();
        secondStep = false;

        // Повторить?
        int reply = JOptionPane.showConfirmDialog(null, "Do you wants repeat?", "Leader", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
            Begin();
    }
}