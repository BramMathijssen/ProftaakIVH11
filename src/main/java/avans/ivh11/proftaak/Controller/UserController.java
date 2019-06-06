package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value = "/u")
public class UserController {

//    private final UserRepository userRepository;
//
//    public UserController(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @PostConstruct
//    public void init() {
//        CreateUsers();
//    }
//
//
//    public void CreateUsers(){
//
//
//
//        User user3 = new User();
//        user3.setUsername("Henk");
//        user3.setPassword(encoder.encode("aap"));
//        userRepository.save(user3);
//
//        User user4 = new User();
//        user4.setUsername("Aap");
//        user4.setPassword(encoder.encode("aap"));
//        userRepository.save(user4);



//    }
}
