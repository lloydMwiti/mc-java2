import dao.Sql2oTaskDao;
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
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[]args){
        port(getHerokuAssignedPort());
        String connectionString = "jdbc:h2:~/squads.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTaskDao taskDao = new Sql2oTaskDao(sql2o);
        staticFileLocation("/public");

        get("/",(request, response) ->{
                Map<String,Object> model=new HashMap<String,Object>();
                List<Task> tasks = taskDao.getAll();
                model.put("tasks", tasks);
                return new ModelAndView(model,"index.hbs");
                } , new HandlebarsTemplateEngine());

        post("/posts/new", (request, response) -> { //URL to make new post on POST route
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

        get("/session",(request, response) ->{
            request.session(true);
            Map<String,Object> model=new HashMap<String,Object>();

            String uname=request.session().attribute("usrname");
            model.put("a",uname);
            return null;
        } , new HandlebarsTemplateEngine());
    }
}
