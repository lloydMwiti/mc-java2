import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
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
        staticFileLocation("/public");
        staticFileLocation("/public");

        get("/",(request, response) ->{
                Map<String,Object> model=new HashMap<String,Object>();
                ArrayList<Post> posts = Post.getAll();
//                ArrayList<Task> tasks = Task.getAll();
//                model.put("tasks", tasks);
                model.put("posts", posts);
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
            Post newPost = new Post(name,power,weakness,age,fight);
//            Task newTask = new Task(name,power,age,weakness,fight);
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
