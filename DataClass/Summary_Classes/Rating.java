package Summary_Classes;

import java.util.HashMap;
import java.util.Map;

public class Rating {
    private String materialId;
    private Map<String, Float> ratings;
    private float averagePoint;

    public Rating(String materialId) {
        this.materialId = materialId;
        this.ratings = new HashMap<>();
    }

    public void addRating(String userId, float newPoint) {
        ratings.put(userId, newPoint);
        updatePoints();
    }

    public void deleteRating(String userId) {
        if (!ratings.containsKey(userId)) {
            System.out.println("User ID not found: " + userId);
            return;
        }
        ratings.remove(userId);
        updatePoints();
    }

    private void updatePoints() {
        float sum = 0;
        for (float value : ratings.values()) {
            sum += value;
        }
        this.averagePoint = ratings.isEmpty() ? 0 : sum / ratings.size();
    }

    public String getMaterialId() {
        return materialId;
    }

    public Map<String, Float> getRatings() {
        return ratings;
    }

    public float getAveragePoint() {
        return averagePoint;
    }

}