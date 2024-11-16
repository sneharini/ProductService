package com.sneha.productservice.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// This class will be hosting a set of HTTP apis
@RequestMapping("/say")
public class ServiceController {

    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("times") int times){
        String output = "";
        for(int i = 0; i < times; i++){
            output += "Hello " + name ;
        }

        return output;
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye World";
    }
}


/*

http://localhost:8080/say/hello
 */
