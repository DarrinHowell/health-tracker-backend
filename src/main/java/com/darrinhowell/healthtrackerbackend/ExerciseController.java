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

        String title2 = "Power Cleans";
        String quantity2 = "21";
        String description2 = "3 x 7";

        Date date2 = new Date();
        String dateFormatStringified2 = "hh:mm:ss a";
        DateFormat dateFormat2 = new SimpleDateFormat(dateFormatStringified2);
        String formattedDate2 = dateFormat2.format(date2);

        Exercise newExercise2 = new Exercise(title2, quantity2, description2, formattedDate2);

        exerciseRepo.save(newExercise2);

        String title3 = "Power Cleans";
        String quantity3 = "21";
        String description3 = "3 x 7";

        Date date3 = new Date();
        String dateFormatStringified3 = "hh:mm:ss a";
        DateFormat dateFormat3 = new SimpleDateFormat(dateFormatStringified3);
        String formattedDate3 = dateFormat3.format(date3);

        Exercise newExercise3 = new Exercise(title3, quantity3, description3, formattedDate3);
        exerciseRepo.save(newExercise3);
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
