import dao.Sql2oTaskDao;
import models.Squad;
import models.Task;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[]args){

        String connectionString = "jdbc:postgresql://localhost:5432/squad";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "chowder");
        Sql2oTaskDao taskDao = new Sql2oTaskDao(sql2o);
        Squads squado=new Squads(sql2o);
        staticFileLocation("/public");

        get("/",(request, response) ->{
                Map<String,Object> model=new HashMap<String,Object>();
                List<Squad> sqd= squado.getSquad();
                model.put("squad",sqd);
                List<Task> tasks = taskDao.getAll();
                model.put("tasks", tasks);
                return new ModelAndView(model,"index.hbs");
                } , new HandlebarsTemplateEngine());
        get("/newfile",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"new.hbs");
        },new HandlebarsTemplateEngine());
        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            request.session(true);
            String name = request.queryParams("name");
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            String age = request.queryParams("age");
            String fight = request.queryParams("fight");
            request.session().attribute("usrname",name);
            Task newTask = new Task(name,power,age,weakness,fight);
            taskDao.add(newTask);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        post("/serial/new",(request, response) -> {
            Map<String,Object>model=new HashMap<String,Object>();
            String name=request.queryParams("name");
            String cause=request.queryParams("cause");
            squado.addSquads(name,cause);
            List<Squad> sqd= squado.getSquad();
            model.put("squad",sqd);
            List<Task> tasks = taskDao.getAll();
            model.put("tasks", tasks);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

    }
}
