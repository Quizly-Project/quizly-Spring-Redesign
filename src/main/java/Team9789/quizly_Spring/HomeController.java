package Team9789.quizly_Spring;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;

@Controller
@Hidden
public class HomeController {

    public String home(){
        return "index";
    }
}
