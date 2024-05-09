package ru.yandex.javacource.fadeev.schedule;

import ru.yandex.javacource.fadeev.schedule.model.Epic;
import ru.yandex.javacource.fadeev.schedule.model.Status;
import ru.yandex.javacource.fadeev.schedule.model.SubTask;
import ru.yandex.javacource.fadeev.schedule.model.Task;
import ru.yandex.javacource.fadeev.schedule.service.Managers;
import ru.yandex.javacource.fadeev.schedule.service.TaskManager;

public class Main {

    public static void main(String[] args) {
    TaskManager manager = Managers.getDefault();

        Task firstTask = manager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = manager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));
        Task thirdTask = manager.createTask(new Task("Третий Таск", "Описание третьего Таска",
                Status.NEW));

        Epic epic = manager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        SubTask firstSubTask = manager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", Status.NEW, epic.getId()));
        SubTask secondSubTask = manager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", Status.NEW, epic.getId()));
        manager.getEpicById(4);

    printAllTasks(manager);
    }
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.returnEpicSubTasks(epic)) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
