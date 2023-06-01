package main;

import main.model.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public String index() {
        return (new Date()).toString();
    }

    @PostMapping(value = "api/test", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> test(@RequestBody Task request) {
        System.out.println(request.getTitle());
        return ResponseEntity.ok("YES");
    }
}
