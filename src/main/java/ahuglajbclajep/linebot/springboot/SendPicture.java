package ahuglajbclajep.linebot.springboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SendPicture {

    @RequestMapping(method = RequestMethod.GET)
    public void hello() {
        System.out.println("hello");
    }
}
