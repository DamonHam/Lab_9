import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FoodList {
    private Food head;

    public void addFood(Food food) {
        if (head == null) {
            head = food;
        } else {
            Food current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = food;
        }
    }

    public void listFoods() {
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Name", "Food Group", "Calories", "Daily Percentage");
        System.out.println("============================================================================");
        Food current = head;
        while (current != null) {
            System.out.printf("%-20s%-20s%-20d%-20.2f\n", current.name, current.foodGroup, current.calories, current.dailyPercentage);
            current = current.next;
        }
    }

    public boolean containsFood(String foodName) {
        Food current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(foodName)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Food getFood(String foodName) {
        Food current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(foodName)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void removeHighCalorieFoods(int calorieLimit) {
        while (head != null && head.calories > calorieLimit) {
            head = head.next;
        }

        Food current = head;
        while (current != null && current.next != null) {
            if (current.next.calories > calorieLimit) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public Food[] getRandomFoods(int count) {
        List<Food> foodList = new ArrayList<>();
        Food current = head;
        while (current != null) {
            foodList.add(current);
            current = current.next;
        }
        Collections.shuffle(foodList);
        return foodList.stream().limit(count).toArray(Food[]::new);
    }
}