package uber.entities;

import java.util.UUID;

public class User {
    protected final String id;
    protected String name;
    protected String contact;
    protected int rating;
    protected int ratingCount;

    public User(String name, String contact) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contact = contact;
        this.rating = 0;
        this.ratingCount = 0;
    }

    public void updateRating(int newRating) {
        if(newRating >= 1 && newRating <= 5) {
            rating = (rating * ratingCount + newRating) / (ratingCount + 1);
            ratingCount++;
        }
    }

    public String getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    public int getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}