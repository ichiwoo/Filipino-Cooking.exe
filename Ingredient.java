public class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getName() {
        return name;
    }

    // No setter needed, as ingredients shouldn't change once created
}