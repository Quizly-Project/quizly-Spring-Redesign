package Team9789.quizly_Spring.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        return "Admin Controller";
    }
}
