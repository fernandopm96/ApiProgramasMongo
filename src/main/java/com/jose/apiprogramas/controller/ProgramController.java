package com.jose.apiprogramas.controller;
import com.jose.apiprogramas.entities.Program;
import com.jose.apiprogramas.exceptions.NameRepeatedException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import static com.mongodb.client.model.Aggregates.sort;

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
    public void insertProgram(@RequestBody Program program){
            try{
                programCollection.insertOne(program);
            }catch (MongoWriteException e){
                throw new NameRepeatedException();
            }
    }

        //Bson query = Filters.and(Filters.gte("price", 20), Filters.lte("price", 15000));
    @GetMapping("/program/test")
    public List<Program> testProgram(){

        return programCollection.find().sort(Filters.eq("price", 1))
                .into(new ArrayList<Program>());
    }

    @GetMapping("/program")
    public List<Program> getPrograms(
            @RequestParam(required = false) String orderField,
            @RequestParam(required = false) Integer orderDirection,
            @RequestParam(required = false) String matchingName,
            @RequestParam(required = false) Integer matchingYear,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
            ){
        List<Bson> filterChain = new ArrayList<>();
        Bson sortFilter = Filters.eq("name", -1);
        if(orderField != null && orderDirection != null){
            sortFilter = Filters.eq(orderField, orderDirection);
        }
        if(matchingName != null){
            filterChain.add(Filters.eq("name", matchingName));
        }
        if(minPrice != null){
            filterChain.add(Filters.gte("price", minPrice));
        }
        if(maxPrice != null){
            filterChain.add(Filters.lte("price", maxPrice));
        }

        if(matchingYear != null){
            LocalDate dateMin = LocalDate.of(matchingYear, 1, 1);
            LocalDate dateMax = LocalDate.of(matchingYear+1, 1, 1);
            filterChain.add(Filters.and(Filters.gte("publicationDate", dateMin), Filters.lte("publicationDate", dateMax)));
        }
        if(filterChain.isEmpty()){
            return programCollection.find().sort(sortFilter).into(new ArrayList<>());
        }

        return programCollection.find(Filters.and(filterChain)).sort(sortFilter).into(new ArrayList<>());
    }

    @PutMapping("/program")
    public void updateProgram(@RequestParam String name, @RequestBody Program program){
        Bson query = Filters.eq("name", name);
        UpdateOptions updateOptions = new UpdateOptions().upsert(true);
        programCollection.replaceOne(query, program, updateOptions);
    }

    @DeleteMapping("/program")
    public void deleteProgram(@RequestParam String name){
        Bson query = Filters.eq("name", name);
        programCollection.deleteOne(query);
    }




}
