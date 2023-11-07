package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.task.Epic;
import ru.netology.task.Meeting;
import ru.netology.task.SimpleTask;
import ru.netology.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    private Todos todos = new Todos();
    private SimpleTask simpleTask;
    private Epic epic;
    private Meeting meeting;

    @BeforeEach
    public void setUp() {
        simpleTask = new SimpleTask(5, "Купить хлеб");

        String[] subtasks = { "молоко", "яйца", "хлеб" };
        epic = new Epic(55, subtasks);

        meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
    }

    @Test
    public void shouldFindThreeTasksOfDifferentType() {
        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSimpleTask() {
        Task[] expected = { simpleTask };
        Task[] actual = todos.search("Купить хлеб");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindEpicTask() {
        Task[] expected = { epic };
        Task[] actual = todos.search("яйца");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMeetingTask() {
        Task[] expected = { meeting };
        Task[] actual = todos.search("Выкатка 3й версии");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSomeTasks() {
        Task[] expected = { simpleTask, epic };
        Task[] actual = todos.search("хлеб");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAnyTasks() {
        Task[] expected = {};
        Task[] actual = todos.search("Какая-то задача");
        assertArrayEquals(expected, actual);
    }
}