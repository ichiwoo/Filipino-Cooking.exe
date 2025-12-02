
<h1 align="center" style="color:hotpink;">👨‍🍳 Filipino Cooking Assistant 👩‍🍳</h1>


## 💡Description/Overview💡
Deciding what to cook can be difficult, especially when ingredients are available but ideas are not. This Java command-line application helps users reduce stress and avoid food waste by suggesting Filipino recipes based on the ingredients they have. The system includes 30+ recipes across categories such as Meat and Poultry, Fish and Seafood, Vegetables, Soup, and Snack/Dessert. Users can view suggested dishes, browse the full recipe catalog, and manage their ingredient inventory. The program also indicates which recipes can’t be cooked due to missing ingredients. With simple inputs, cooking becomes easier, faster, and more efficient.

🔘 Main Features:
  - Ingredient-based recipe matching: Input available ingredients to find recipes you can make
  - Comprehensive Filipino recipe database: 30+ authentic recipes with detailed instructions
  - Recipe categorization: Browse recipes by food categories
  - Specialized recipe types: Quick recipes (under 20 mins) and traditional regional recipes
  - Interactive command-line interface: User-friendly menu system
  - Inventory management: Track and reuse your ingredient list


## 💻OOP Concepts Applied💻

### 💊Encapsulation
- All class fields are declared as private with controlled access through public getter and setter methods
- Internal data structures (like the recipe list in Cookbook) are hidden from external classes
- Data validation and normalization are handled internally (e.g., ingredient name trimming in Ingredient class)

### 👨‍👩‍👧Inheritance
- FoodItem abstract class serves as the base class for all food-related entities
- Recipe extends FoodItem, inheriting common properties like name
- QuickRecipe and TraditionalRecipe extend Recipe, inheriting recipe properties while adding specialized functionality
- Ingredient extends FoodItem, creating a hierarchy of food-related objects

### 🐛Polymorphism
- Method Overriding: displayInfo() and displayRecipe() methods are overridden in subclasses to provide specialized behavior
- Abstract Methods: The abstract displayInfo() method in FoodItem is implemented differently in Recipe, QuickRecipe, and TraditionalRecipe
- Runtime Binding: The correct display method is called based on the actual object type at runtime

### 🖼️Abstraction
- FoodItem abstract class defines the common interface without implementation details
- Complex recipe matching algorithms are abstracted behind simple public methods in Cookbook
- Users interact with high-level operations without needing to understand the internal data structures

## 💡Program Structure💡
### 🔘Main Classes and Their Roles

**🏠Main** - The primary entry point and user interface controller
- Manages the main program loop and menu navigation
- Handles all user input through the Scanner object
- Coordinates interactions between the user, Cookbook, and inventory
- Implements the console-based user interface with clear menu options

**📔Cookbook** - The central database and recipe manager
- Stores and organizes all recipe objects in an ArrayList
- Implements recipe search and matching algorithms using Stream API
- Groups recipes by category using HashMap for efficient organization
- Provides methods to find recipes based on available ingredients
- Contains helper methods for ingredient matching and normalization

**🍲FoodItem** (abstract) - The foundation of the food hierarchy
- Defines the common name property for all food-related items
- Declares the abstract displayInfo() method that all subclasses must implement
- Establishes the base structure for the inheritance hierarchy

**📖Recipe** - Represents a complete cooking recipe
- Extends FoodItem and serves as the base for specialized recipes
- Stores recipe details: name, required ingredients list, instructions, and category
- Implements the displayInfo() method to show recipe details
- Contains the core recipe display logic with formatted output

**📑QuickRecipe** - Specialized recipe for fast meals
- Extends Recipe class
- Adds preparation time property (in minutes)
- Overrides displayInfo() to include quick meal details
- Represents recipes that can be prepared in under 20 minutes

**🧾TraditionalRecipe** - Specialized recipe for regional dishes
- Extends Recipe class
- Adds region of origin property
- Overrides displayInfo() to include regional information
- Represents traditional Filipino dishes from specific regions

**🍅Ingredient** - Represents individual food ingredients
- Extends FoodItem
- Normalizes ingredient names (trimming and converting to lowercase)
- Implements simple display functionality
- Used in the user's ingredient inventory

**‼️RecipeNotFoundException** - Custom exception class
- Extends Exception class
- Used when a requested recipe cannot be found
- Provides meaningful error messages for better user experience

### 💡Class Hierarchy and Relationships💡

```bash
FoodItem (abstract)
|
├── Recipe (extends FoodItem)
│   ├── QuickRecipe (extends Recipe)
│   └── TraditionalRecipe (extends Recipe)
|
└── Ingredient (extends FoodItem)

Main (Program Entry Point)
|
├── Cookbook (Recipe Manager)
└── RecipeNotFoundException (Custom Exception)
```

### Key Relationships
- Composition: Cookbook contains a List of Recipe objects
- Aggregation: Main contains Cookbook and List<Ingredient> objects  
- Inheritance: QuickRecipe and TraditionalRecipe inherit from Recipe
- Dependency: Main depends on Cookbook for recipe operations
  

## How to Run the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line/terminal access

### Steps to Compile and Run

1. Save all Java files in the same directory:
   - Main.java
   - Cookbook.java
   - FoodItem.java
   - Recipe.java
   - QuickRecipe.java
   - TraditionalRecipe.java
   - Ingredient.java
   - RecipeNotFoundException.java

2. Open a terminal/command prompt and navigate to the directory containing the files

3. Compile all Java files:
   ```
   javac *.java
   ```

4. Run the program:
   ```
   java Main
   ```
## 📸 Sample Output

| Screenshot | Description |
|------------|-------------|
| <img width="300" alt="Main Menu" src="https://github.com/user-attachments/assets/7c86277e-8bc8-4135-adc9-a21015323973" /> | **Main Menu** - The starting interface with all available options |
| <img width="300" alt="Ingredient Input" src="https://github.com/user-attachments/assets/fd81d20e-73f9-440e-b06d-7d4e599cbde9" /> | **Ingredient Input & Recipe Suggestions** - Adding ingredients to your inventory and showing what you can cook |
| <img width="400" alt="Recipe Suggestions" src="https://github.com/user-attachments/assets/50ab2b1d-e717-4f5e-8d99-104de47fa9d2" /> | **Recipe** - Recipe of your selected dish from the suggestions |
| <img width="300" alt="Recipe Categories" src="https://github.com/user-attachments/assets/e3ce337a-2ab7-4a5d-9703-6603d1dcf880" /> | **Inventory** - List of ingredients added |
| <img width="300" alt="Quick Recipes" src="https://github.com/user-attachments/assets/f0ec5824-de32-4372-8eb9-afefedc4c825" /> | **Append Ingredients** - Add ingredients to inventory |
| <img width="300" alt="Traditional Recipes" src="https://github.com/user-attachments/assets/d4c300fd-5a75-42ad-84cf-da057cd8dc58" /> | **Recipe Catalog** - Browse recipes by catgeory |
| <img width="300" alt="Recipe Details" src="https://github.com/user-attachments/assets/e1994789-4d76-4674-882a-32970aea9258" /> | **Ingredient Index** - Browse ingredients that can be added |
| <img width="300" alt="Missing Ingredients" src="https://github.com/user-attachments/assets/84fbf771-bf76-442e-b636-01f3ea4fc403" /> | **Help & About** - Shows instructions on how to use the program |
| <img width="300" alt="Inventory Management" src="https://github.com/user-attachments/assets/e8cdace2-ecfe-4f52-8977-9ba786e806ba" /> | **Thank You Message** - Shows when you exit the program |
















---

## 👩‍🍳 Authors and Acknowledgements

### 🧑‍💻 **Authors**
| Name | Role | Contact |
|------|------|---------|
| **Jhunethertynn V. Dejayco** | Developer | 📧 24-04752@g.batstate-u.edu.ph<br>🐱 GitHub: jhunethertynn |
| **Rein Ainakelle P. Peralta** | Developer | 📧 24-08568@g.batstate-u.edu.ph<br>🐱 GitHub: strawberreinn |
| **Ichiro A. Plata** | Leader & Developer | 📧 24-05401@g.batstate-u.edu.ph<br>🐱 GitHub: ichiwoo |

### 🙏 **Acknowledgement**
We would like to express our gratitude to everyone who contributed to the completion of this project:

- Our professor, **Sir Emmanuel Charlie Enriquez**, for his guidance and support throughout the development of this work
- Our family, blockmates, and friends for their never-ending support and encouragement
- God for granting us the wisdom, strength, and patience to complete this project
- The online resources that helped us bring our ideas to life and provided inspiration and guidance throughout the making of this project

---

## Future Enhancements
1. **UI/UX Design Integration**: Improve the aesthetic appeal by incorporating graphical user interface elements and better user experience design
2. **Expanded Recipe Database**: Include more Filipino recipes beyond the current collection
3. **Broader Category Range**: Offer additional categories not limited to ulam and meryenda
4. **Advanced Programming Features**: Implement more sophisticated functionality to make the program more efficient and useful
5. **Maintain User-Friendliness**: Ensure all enhancements preserve the program's ease of use and accessibility

## Technical Details
- Language: Java (Standard Edition)
- Paradigm: Object-Oriented Programming
- Data Structures: ArrayList, HashMap, HashSet, Stream API
- Design Patterns: Template Method (via abstract class), Strategy (via recipe types)
- Error Handling: Custom exception (RecipeNotFoundException) for robust error management

## References
- Java Documentation: https://docs.oracle.com/javase/
- Object-Oriented Programming Principles
- Filipino culinary resources and traditional recipes
- Java Stream API documentation for functional programming patterns

---
Enjoy cooking authentic Filipino dishes withFilipinoCooking.exe! Kain tayo!```



