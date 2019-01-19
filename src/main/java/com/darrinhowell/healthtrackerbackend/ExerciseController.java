package com.darrinhowell.healthtrackerbackend;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@RestController
public class ExerciseController {

    ///////////////////////////// -- class variables

    @Autowired ExerciseRepository exerciseRepo;

    Gson gson = new Gson();

    ///////////////////////////// -- get and post routes


    // attribution for RequestMapping Pattern inspired by: https://stackoverflow.com/questions/29313687/trying-to-use-spring-boot-rest-to-read-json-string-from-post
   // takes in post.body from health-tracker front end and stores data into server db
    @RequestMapping(value = "/newExercise", method = RequestMethod.POST)
    public void createExercise(@RequestBody String payload) {

        System.out.println(payload);

        Exercise incomingExercise = gson.fromJson(payload, Exercise.class);

        Exercise incomingExercise_StrippedID = new Exercise(incomingExercise.title, incomingExercise.quantity,
                incomingExercise.description, incomingExercise.timeStamp);

        exerciseRepo.save(incomingExercise_StrippedID);

    }

    // retrieves all exercise entities from server db, turns them into JSON, returns JSON to client
    @GetMapping("/exercises")
    public String getExercises() {

        Iterable<Exercise> exerciseLog = exerciseRepo.findAll();
        Iterator<Exercise> iter = exerciseLog.iterator();
        ArrayList<Exercise> exerciseList = new ArrayList<>();

        while(iter.hasNext()) {
            exerciseList.add(iter.next());
        }

        String jsonExerciseList = gson.toJson(exerciseList);

        return jsonExerciseList;
    }

}
