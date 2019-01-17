package com.darrinhowell.healthtrackerbackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String title;
    public String quantity;
    public String description;
    public String timeStamp;

    public Exercise() {}

    public Exercise(String title, String quantity, String description, String timeStamp) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public String toString() {
        return "Title: " + this.title + ". Quantity: " + this.quantity + ". Id: " + this.id;
    }
}
