package com.darrinhowell.healthtrackerbackend;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@RestController
public class ExerciseController {

    @Autowired ExerciseRepository exerciseRepo;

    @PostMapping("/newExercise")
    public void createExercise() {
        String title = "Pull Ups";
        String quantity = "30";
        String description = "3 x 10";

        Date date = new Date();
        String dateFormatStringified = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStringified);
        String formattedDate = dateFormat.format(date);

        Exercise newExercise = new Exercise(title, quantity, description, formattedDate);

        exerciseRepo.save(newExercise);
    }

    @GetMapping("/exercises")
    public String getExercises() {

        Iterable<Exercise> exerciseLog = exerciseRepo.findAll();
        Iterator<Exercise> iter = exerciseLog.iterator();
        ArrayList<Exercise> exerciseList = new ArrayList<>();

        while(iter.hasNext()) {
            exerciseList.add(iter.next());
        }

        Gson gson = new Gson();
        String jsonExerciseList = gson.toJson(exerciseList);

        return jsonExerciseList;
    }

}
