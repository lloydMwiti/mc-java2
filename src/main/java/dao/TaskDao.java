package dao;

import models.Squad;
import models.Task;

import java.util.List;

public  interface TaskDao {

        List<Task> getAll();
        List<Squad> getSquad();
        void add(Task task);
        void addSquad(Squad squad);
}
