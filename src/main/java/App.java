import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[]args){
        staticFileLocation("/public");

        get("/",(request, response) ->{
                Map<String,Object> model=new HashMap<String,Object>();
                ArrayList<Post> posts = Post.getAll();
                model.put("posts", posts);

                return new ModelAndView(model,"index.hbs");
                } , new HandlebarsTemplateEngine());

        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String power = request.queryParams("power");
            Post newPost = new Post(name,power);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
