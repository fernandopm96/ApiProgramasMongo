package com.jose.apiprogramas.controller;
import com.jose.apiprogramas.entities.Program;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgramController {

    @Autowired
    MongoCollection<Program> programCollection;

    @GetMapping("/test")
    public String helloWorld(){
        return "works";
    }

    @GetMapping("/program/{name}")
    public Program findProgram(@PathVariable String name){
        Bson filter = Filters.eq("name",name);
        return  programCollection.find(filter).first();
    }

    @GetMapping("/program/{name}/price")
    public Double findProgramPrice(@PathVariable String name){
        Bson filter = Filters.eq("name",name);
        return programCollection.find(filter).first().getPrice();
    }

    @PostMapping("/program")
    public String insertProgram(@RequestBody Program program){
        programCollection.insertOne(program);
        return "ok";
    }

    @DeleteMapping("/program/{name}")
    public String deleteProgram(@PathVariable String name){
        Bson filter = Filters.eq("name",name);
        programCollection.findOneAndDelete(filter);
        return "deleted";
    }


}
