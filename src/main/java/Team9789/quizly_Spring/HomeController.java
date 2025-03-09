package Team9789.quizly_Spring;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    public String home(){
        return "index";
    }
}
