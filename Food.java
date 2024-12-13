class Food {
    String name;
    String foodGroup;
    int calories;
    double dailyPercentage;
    Food next;

    public Food(String name, String foodGroup, int calories, double dailyPercentage) {
        this.name = name;
        this.foodGroup = foodGroup;
        this.calories = calories;
        this.dailyPercentage = dailyPercentage;
        this.next = null;
    }
}