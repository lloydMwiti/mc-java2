package dao;

import models.Squad;
import models.Task;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oTaskDao implements TaskDao {
    private final Sql2o sql2o;
    private Connection conn;
    public Sql2oTaskDao(Sql2o sql2o){
        this.sql2o = sql2o;

    }

    @Override
    public List<Task> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tasks")
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public List<Squad> getSquad() {
        return null;
    }

    @Override
    public void add(Task task) {
        String sql = "INSERT INTO tasks (name,power,age,weakness,fight) VALUES (:name,:power,:age,:weakness,:fight)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(task)
                    .executeUpdate()
                    .getKey();
            task.setId(id);
        } catch (Sql2oException ex) {

            System.out.println("failed");
            System.out.println(ex);
        }
    }

    @Override
    public void addSquad(Squad squad) {

    }


}

