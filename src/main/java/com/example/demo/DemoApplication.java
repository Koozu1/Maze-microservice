package com.example.demo;

import com.example.demo.MazeGenerator.Generator;
import com.example.demo.MazeGenerator.KoozuPair;
import com.example.demo.MazeGenerator.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;


@SpringBootApplication
@RestController
public class DemoApplication {

    Generator mazeGenerator = new Generator();

    @PostMapping("/posttest")
    public ArrayList<Location> postThing(@RequestBody KoozuPair<Location, Location> locations){
        return mazeGenerator.calculateMazeLocs(locations.getKey(), locations.getValue());

    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



}
