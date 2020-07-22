import dao.TaskDao;
import models.Squad;
import models.Task;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.time.LocalDateTime;
import java.util.List;

public class Squads implements TaskDao {
    private Sql2o sql2o;
    public Squads(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public List<Squad> getSquad() {
        try(Connection con =sql2o.open()) {
            return con.createQuery("SELECT * FROM squad")
                    .executeAndFetch(Squad.class);
        }

        }

    @Override
    public void add(Task task) {

    }

    @Override
    public void addSquad(models.Squad squad) {

    }


    public void addSquads(String name,String cause) {
        String sql="INSERT INTO Squad (name,cause) VALUES (:name,:cause)";
        LocalDateTime date=LocalDateTime.now();
        try(Connection con=this.sql2o.open()){
            con.createQuery(sql,true)
                    .addParameter("name",name)
                    .addParameter("cause",cause)
                    .executeUpdate()
                    .getKey();
        }catch(Sql2oException e){
            System.out.println("failed");
            System.out.println(e);
        }
    }
}
