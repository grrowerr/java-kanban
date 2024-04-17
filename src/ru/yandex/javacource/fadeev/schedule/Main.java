package ru.yandex.javacource.fadeev.schedule;
/*
*Михаил, ещё раз привет!
*Хотел узнать у тебя, зачем нам добавлять такое количество пакетов, ведь получается очень длинный путь до классов,
*Совсем не понимаю - почему так будет лучше.
*
* Также по какой-то причине мне idea предлагает сделать поля хранения тасок, сабтасок и епиков финальными, есть ли в
* этом смысл в данном случае?
*/

import ru.yandex.javacource.fadeev.schedule.task.Epic;
import ru.yandex.javacource.fadeev.schedule.task.Status;
import ru.yandex.javacource.fadeev.schedule.task.SubTask;
import ru.yandex.javacource.fadeev.schedule.task.Task;
import ru.yandex.javacource.fadeev.schedule.manager.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // checking the methods for working with class Task
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));
        Task thirdTask = taskManager.createTask(new Task("Третий Таск", "Описание третьего Таска",
                Status.NEW));

        System.out.println("Created task: " + firstTask);

        Task taskFromManagerById = taskManager.getTaskById(firstTask.getId());
        System.out.println("Get task by Id: " + taskFromManagerById);

        Task updatedTask = new Task("Обновленный первый таск", "Обновленное описание таска",
                taskFromManagerById.getId(), Status.IN_PROGRESS);
        taskManager.updateTask(updatedTask);
        System.out.println("Updated task: " + taskManager.getTaskById(updatedTask.getId()));
        System.out.println("Updated task: " + updatedTask);

        System.out.println(taskManager.getTasks());
//        taskManager.deleteTaskById(updatedTask.getId());
        System.out.println(taskManager.getTasks());

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        // checking the methods for working with class Epic and two subtasks
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", Status.NEW, epic.getId()));

        System.out.println("Created epics: " + taskManager.getEpics());
        System.out.println("Created subTasks: " + taskManager.getSubTasks());
        System.out.println("Print all subTasks from epic: " + taskManager.returnEpicSubTasks(epic));

        SubTask firstSubTaskById = taskManager.getSubTaskById(firstSubTask.getId());
        SubTask secondSubTaskById = taskManager.getSubTaskById(secondSubTask.getId());
        System.out.println("Get first subTask by ID: " + firstSubTaskById);
        System.out.println("Get second subTask by ID: " + secondSubTaskById);

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        System.out.println("Get epic by Id: " + taskManager.getEpicById(epic.getId()));
        SubTask updatedFirstSubTask = new SubTask("Обновленный первый СабТаск",
                "Обновленное описание первого СабТаска", firstSubTask.getId(), Status.DONE, firstSubTask.getEpicId());
        taskManager.updateSubTask(updatedFirstSubTask);
        System.out.println("Print all subTasks from epic: " + taskManager.returnEpicSubTasks(epic));
        System.out.println("Epic with updated first SubTask: " + epic);
        System.out.println("Updated first SubTask: " + updatedFirstSubTask);

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        SubTask updatedSecondSubTask = new SubTask("Обновленный второй СабТаск",
        "Обновленное описание второго СабТаска",secondSubTask.getId(), Status.DONE, secondSubTask.getEpicId());
        taskManager.updateSubTask(updatedSecondSubTask);
        System.out.println("Epic with both updated SubTasks: " + epic);
        System.out.println("Updated second SubTask: " + updatedSecondSubTask);
        System.out.println("Print all subTasks from epic: " + taskManager.returnEpicSubTasks(epic));

        System.out.println("Epic: " + taskManager.getEpicById(epic.getId()));
        Epic updatedEpic = new Epic("Новое название", "Обновленное описание", epic.getId(), epic.getStatus());
        taskManager.updateEpic(updatedEpic);
        System.out.println("Epics:" + taskManager.getEpics());
        System.out.println("SubTasks from updated epic: " + taskManager.returnEpicSubTasks(epic));

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

//        checking the methods for delete all tasks, subTasks and epics
        System.out.println("All epics: " + taskManager.getEpics());
        System.out.println("All tasks: " + taskManager.getTasks());
        System.out.println("All subTasks: " + taskManager.getSubTasks());

        taskManager.removeTasks();
        System.out.println("All tasks: " + taskManager.getTasks());

        taskManager.removeSubTasks();
        System.out.println("All subTasks: " + taskManager.getSubTasks());
        System.out.println("Print all subTasks from epic: " + taskManager.returnEpicSubTasks(epic));

        taskManager.removeEpics();
        System.out.println("All epics: " + taskManager.getEpics());
        System.out.println("All subTasks: " + taskManager.getSubTasks());
        System.out.println("Print all subTasks from epic: " + taskManager.returnEpicSubTasks(epic));

    }
}
