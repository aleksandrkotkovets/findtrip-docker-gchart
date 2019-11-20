package by.sum_solutions.findtrip.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    //Мы могли бы расписать эти 2 маппинга отдельно, но смысла дублировать одинаковый код нет.
    // этот метод будет слушать запросы на "/" и "/index"
    @GetMapping(value = {"/", "/index"})
    public String index() {
        System.out.println("index");
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("addmin");

        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        System.out.println("user");

        return "user";
    }

    @GetMapping("/about")
    public String about() {
        System.out.println("about");

        return "about";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("login");
        return "login";
    }
    @PostMapping("/login")
    public String postLogin() {
        System.out.println("postlogin");
        return "admin";
    }

    @GetMapping("/403")
    public String error403() {
        System.out.println("403");

        return "403";
    }
}