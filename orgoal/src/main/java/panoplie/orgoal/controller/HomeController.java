package panoplie.orgoal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//홈화면을 응답해주는 컨트롤러
@Controller
public class HomeController {
    @GetMapping("/")
    public String homeController() {
        return "index.html";
    }
}
