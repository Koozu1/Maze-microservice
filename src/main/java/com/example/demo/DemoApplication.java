package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/ok/{text}")
    String ok(@PathVariable String text){

        StringBuilder values = new StringBuilder(text + "\n");
        for (String item : text.split("s")){
            values.append(parseVariable(item).toString());
        }

        return "to coordds :  " + values ;
    }

    public Coordinate parseVariable(String variable){
        try {
            String[] strItems = variable.split(",");
            return new Coordinate(
                    Integer.parseInt(strItems[0])
                    , Integer.parseInt(strItems[1])
                    , Integer.parseInt(strItems[2]));
        }catch (Exception e){

            return null;
        }

    }

    @PostMapping("/posttest")
    public String postThing(@RequestBody Coordinate coordinate){
        return coordinate.toString() + ". tush ceissi fam";

    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



}
