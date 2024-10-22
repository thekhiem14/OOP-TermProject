package javaapplication1;

import java.util.Map;

public class Rating {
    private String userId, materialId;
    private Map<String, Float> rating;
    private float point;

    public Rating(String userId, String materialId, float newPoint) {
        this.userId = userId;
        this.materialId = materialId;
        rating.put(userId, newPoint);
        float sum = 0;
        for (Map.Entry<String, Float> entry : rating.entrySet()) {
            sum += entry.getValue();
        }
        point = sum / rating.size();
    }

    public void deleteRating(String userId) {
        if (!rating.containsKey(userId)) {
            System.out.println("User ID not found: " + userId);
            return;
        }
        float deletePoint = rating.get(userId);
        if (rating.size() == 1)
            this.point = 0;
        else
            this.point = ((point * rating.size()) - deletePoint) / (rating.size() - 1);
        this.rating.remove(userId);
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Float> getRating() {
        return rating;
    }

    public float getPoint() {
        return point;
    }

    public String getMaterialId() {
        return materialId;
    }
}
