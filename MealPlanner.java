import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MealPlanner {
    public static void main(String[] args) {
        FoodList foodList = new FoodList();

        try (BufferedReader br = new BufferedReader(new FileReader("FoodListText"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String foodGroup = parts[1];
                int calories = Integer.parseInt(parts[2]);
                double dailyPercentage = Double.parseDouble(parts[3]);
                foodList.addFood(new Food(name, foodGroup, calories, dailyPercentage));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("---------------------------------");
            System.out.println("Welcome to Parkland Meal Selector");
            System.out.println("---------------------------------");
            System.out.println("Please select from the following");
            System.out.println("1 - List food database");
            System.out.println("2 - Create meal by manual selection");
            System.out.println("3 - Create meal by random selection");
            System.out.println("4 - Remove foods high in calorie");
            System.out.println("5 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    foodList.listFoods();
                    break;

                case 2:
                    List<Food> manualMeal = new ArrayList<>();
                    while (manualMeal.size() < 3) {
                        System.out.print("Enter food name: ");
                        String foodName = scanner.nextLine();
                        if (foodList.containsFood(foodName)) {
                            manualMeal.add(foodList.getFood(foodName));
                        } else {
                            System.out.println("Food " + foodName + " not in database, try again");
                        }
                    }
                    printMeal(manualMeal);
                    break;

                case 3:
                    Food[] randomMeal = foodList.getRandomFoods(3);
                    printMeal(Arrays.asList(randomMeal));
                    break;

                case 4:
                    System.out.print("Enter calorie limit: ");
                    int calorieLimit = scanner.nextInt();
                    scanner.nextLine();
                    foodList.removeHighCalorieFoods(calorieLimit);
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    private static void printMeal(List<Food> meal) {
        int totalCalories = 0;
        double totalDailyPercentage = 0;
        System.out.println("===============================");
        System.out.println("Your selected meal");
        System.out.print("Foods: ");
        for (Food food : meal) {
            System.out.print(food.name + " ");
            totalCalories += food.calories;
            totalDailyPercentage += food.dailyPercentage;
        }
        System.out.println();
        System.out.println("Total calorie count: " + totalCalories);
        System.out.println("Total daily percentage: " + (int) (totalDailyPercentage * 100) + "%");
        System.out.println("===============================");
    }
}
