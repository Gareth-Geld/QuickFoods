import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    // I was going to make a 3d array to store the orders but this simply doesn't
    // work as its different variable types so thats far too complex
    // Instead I just made 6 arrayLists to store the information

    private static ArrayList<Integer> Amounts = new ArrayList<>();
    private static ArrayList<String> foods = new ArrayList<>();
    private static ArrayList<Double> prices = new ArrayList<>();

    private static ArrayList<String> drivers = new ArrayList<>();
    private static ArrayList<String> locations = new ArrayList<>();
    private static ArrayList<Integer> loads = new ArrayList<>();

    private static int ValidationCount;

    public static void main(String[] args) {

        int TheIndex = -1;

        // Getting user information
        // Variables
        // Restaurant
        int orderNumber = 0;
        String orderNumberSTR = "";
        String RestaurantName = "";
        String RestaurantNumber = "";
        String RestaurantAddress = "";
        String RestaurantLocation = "";
        String RestaurantEmail = "";

        // Customer
        String CustomerName = "";
        String CustomerNumber = "";
        String CustomerAddress = "";
        String CustomerLocation = "";
        String CustomerEmail = "";
        String SpecialInstructions = "";

        // Getting input
        // Restaurant info
        // With Validation
        while (ValidationCount != 6) {
            ValidationCount = 0;
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter Restaurant Name : ");
            RestaurantName = input.nextLine();
            if (!isWord(RestaurantName)) {
                continue;
            }
            System.out.print("Please enter in the Restaurant Number : ");
            RestaurantNumber = input.nextLine();
            if (!isCellNumber(RestaurantNumber)) {
                continue;
            }
            System.out.print("Please enter in the Restaurant Address : ");
            RestaurantAddress = input.nextLine();
            if (!isAddress(RestaurantAddress)) {
                continue;
            }
            System.out.print("Please enter in the Restaurant Location : ");
            RestaurantLocation = input.nextLine();
            if (!isWord(RestaurantLocation)) {
                continue;
            }
            RestaurantLocation = capitalizeLocation(RestaurantLocation);
            System.out.print("Please enter in the Restaurant Email : ");
            RestaurantEmail = input.nextLine();
            if (!isValidEmail(RestaurantEmail)) {
                continue;
            }
            // The system overlapped the questions in the terminal if I put this input first
            // for some reason - Thats why its at the bottom :)
            System.out.print("Please enter in the order Number : ");
            orderNumberSTR = input.nextLine();
            if (!isOnlyDigits(orderNumberSTR)) {
                continue;
            }
            System.out.println(ValidationCount);
            orderNumber = Integer.parseInt(orderNumberSTR);
        }

        // Customer info
        // With Validation
        while (ValidationCount != 12) {
            ValidationCount = 6;
            Scanner input2 = new Scanner(System.in);
            System.out.print("Please enter Customer Name : ");
            CustomerName = input2.nextLine();
            if (!isWord(CustomerName)) {
                continue;
            }
            System.out.print("Please enter in the Customer Number : ");
            CustomerNumber = input2.nextLine();
            if (!isCellNumber(CustomerNumber)) {
                continue;
            }
            System.out.print("Please enter in the Customer Address : ");
            CustomerAddress = input2.nextLine();
            if (!isAddress(CustomerAddress)) {
                continue;
            }
            System.out.print("Please enter in the Customer Location : ");
            CustomerLocation = input2.nextLine();
            if (!isWord(CustomerLocation)) {
                continue;
            }
            CustomerLocation = capitalizeLocation(CustomerLocation);
            System.out.print("Please enter in the Customer Email : ");
            CustomerEmail = input2.nextLine();
            if (!isValidEmail(CustomerEmail)) {
                continue;
            }
            System.out.print("Please enter in the Special Instructions if any : ");
            SpecialInstructions = input2.nextLine();
            if (!isWord(SpecialInstructions)) {
                continue;
            }

        }

        // Item Ordering
        // With Validation
        Boolean MoreFood = true;
        while (MoreFood) {
            Scanner input3 = new Scanner(System.in);
            System.out.print("Please enter the name of food Item : ");
            String ItemOfFood = input3.nextLine();
            if (!isWord(ItemOfFood)) {
                continue;
            }
            System.out.print("Please enter amount of food Item : ");
            String AmountOfFoodStr = input3.nextLine();
            if (!isOnlyDigits(AmountOfFoodStr)) {
                continue;
            }
            int AmountOfFood = Integer.parseInt(AmountOfFoodStr);
            System.out.print("Please enter the price of food Item: ");
            String priceStr = input3.nextLine();
            if (!isDouble(priceStr)) {
                continue;
            }
            double PriceOfFood = Double.parseDouble(priceStr);
            System.out.print("Would you like to enter more food options ? true or false : ");
            String MoreFoodStr = input3.nextLine();
            if (!isBoolean(MoreFoodStr)) {
                continue;
            } else {
                if (MoreFoodStr.equalsIgnoreCase("true")) {
                    MoreFood = true;
                } else {
                    MoreFood = false;
                }
            }

            // Putting this information into the Array Lists- Food Items
            AddAmount(AmountOfFood);
            AddFood(ItemOfFood);
            AddPrice(PriceOfFood);
        }

        // Putting information into objects
        Restaurant MyRest = new Restaurant(orderNumber, RestaurantName, RestaurantNumber, RestaurantAddress,
                RestaurantLocation, RestaurantEmail);
        Customer MyCust = new Customer(orderNumber, CustomerName, CustomerNumber, CustomerAddress, CustomerLocation,
                CustomerEmail, SpecialInstructions);

        // Opening of the drivers.txt file
        try {
            // File path for the program to find the existing path
            String filePath = "drivers.txt";

            // FileReader and BufferedReader
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // This is where the Manipulation Happens
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Separate(line);
            }

            // Closing files
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        // This loops through the locations to ensure that the Restaurant location
        // matches the Drivers Location
        if (locations.contains(MyRest.RestLocation)) {
            int lowest = 100;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).equals(MyRest.RestLocation)) { // Used equals() to compare strings
                    if (loads.get(i) <= lowest) {
                        lowest = loads.get(i);
                        TheIndex = i;
                    }
                }
            }
        }

        // This is the Output - I display it in the console as well as in the
        // invoice.txt
        if (TheIndex != -1) {
            double TotalCost = 0;
            String output = "\nOrder Number : " + MyRest.getorderNum();
            output += "\nCustomer: " + MyCust.getCustomerName();
            output += "\nEmail: " + MyCust.GetCustEmail();
            output += "\nphone Number: " + MyCust.getCustContactNum();
            output += "\nLocation : " + MyCust.GetCustLocation();
            output += "\n";
            output += "\nYou have ordered the following from " + MyRest.getRestaurantName() + " in "
                    + MyRest.GetRestLocation();
            output += "\n";
            for (int i = 0; i < foods.size(); i++) {
                output += "\n" + Amounts.get(i) + " x " + foods.get(i) + " (R" + prices.get(i) + ")";
            }
            output += "\n";
            output += "\nSpecial Instructions: " + MyCust.GetSpecialInst();
            output += "\n";
            for (int j = 0; j < prices.size(); j++) {
                TotalCost += prices.get(j) * Amounts.get(j);
            }
            output += "\nTotal: " + TotalCost;
            output += "\n";
            output += "\n" + drivers.get(TheIndex)
                    + " is the nearest to the Restaurant and so he will be delivering your order to you at:";
            output += "\n";
            output += "\n" + MyCust.getCustAddress();
            output += "\n";
            output += "\nIf you need to contact the Restaurant, their number is " + MyRest.getRestContactNum() + ".";
            output += "\n";
            System.out.println("Your Driver is: " + drivers.get(TheIndex) + " The location is: "
                    + locations.get(TheIndex) + " The current load is: " + loads.get(TheIndex));

            // Creation of the invoice.txt file and write output to it
            try {
                // file path for newly created file
                String filePathNEW = "invoice.txt";

                // Create Object with the file path
                FileWriter fileWriter = new FileWriter(filePathNEW);

                // Create object buffered writer
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                System.out.println("Line written to the file successfully.");

                bufferedWriter.write(output);

                // Closing files
                bufferedWriter.close();

            } catch (IOException e) {
                System.out.println("File not found ERROR");
                e.printStackTrace();
            }

        } else {
            System.out.println("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
        }

    }

    // This method Separates the drivers into different Arrays so that I can use the
    // information more Efficiently
    public static void Separate(String line) {
        String name;
        String location;
        String load;

        int pos1 = (line.indexOf(","));
        name = line.substring(0, pos1);
        line = line.substring(pos1 + 2, line.length());
        int pos2 = (line.indexOf(","));
        location = line.substring(0, pos2);
        line = line.substring(pos2 + 2, line.length());
        load = line.substring(0, line.length());

        if (load == "") {
            load = "0";
        }

        AddDriver(name);
        AddLocation(location);
        AddLoad(Integer.parseInt(load));
    }

    // These are my methods that put the information into the ArrayLists- I like
    // these :)
    public static ArrayList<String> AddDriver(String driver) {
        drivers.add(driver);
        return drivers;
    }

    public static ArrayList<String> AddLocation(String location) {
        locations.add(location);
        return locations;
    }

    public static ArrayList<Integer> AddLoad(int load) {
        loads.add(load);
        return loads;
    }

    public static ArrayList<Integer> AddAmount(int amount) {
        Amounts.add(amount);
        return Amounts;
    }

    public static ArrayList<String> AddFood(String food) {
        foods.add(food);
        return foods;
    }

    public static ArrayList<Double> AddPrice(double price) {
        prices.add(price);
        return prices;
    }

    // Validation Methods
    // Used a lot of Regular expressions
    // Source : https://www.jrebel.com/blog/java-regular-expressions-cheat-sheet
    // Checks if there are any digits in the code
    public static boolean isWord(String word) {
        // Convert the word to lowercase for case-insensitive comparison
        String lowercaseWord = word.toLowerCase();

        // Check if the word contains any numbers
        if (lowercaseWord.matches(".*\\d.*")) {
            System.out.println("This word is invalid as it contains digits.");
            System.out.println("Please ensure that this field is only letters.");
            return false;
        }

        // Check if the word is a valid word (you can add additional checks here if
        // needed)
        ValidationCount++;
        return true;
    }

    // Checks Valid Cellphone number
    public static boolean isCellNumber(String number) {
        // Regular expression pattern to match a valid cellphone number format
        // Checks both formats with or without spaces
        String regex = "^(\\d{3}[\\s-]?\\d{3}[\\s-]?\\d{4})$";
        if (!number.matches(regex)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Please ensure that all characters are numbers and that there are 10 Numbers.");
            return number.matches(regex);
        } else {
            ValidationCount++;
            return number.matches(regex);
        }

    }

    // Check Valid Address
    public static boolean isAddress(String address) {
        // Regular expression pattern to match address format
        String regex = "^\\d+[a-zA-Z]?\\s+.*$";

        if (!address.matches(regex)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Please follow the right address format - 123 Street, City");
            return address.matches(regex);
        } else {
            ValidationCount++;
            return address.matches(regex);
        }
    }

    // Checks valid email
    public static boolean isValidEmail(String email) {
        // Regular expression pattern to match a valid email address format
        String regex = "^[^@]+@[^@]+\\.[^.]+$";
        if (!email.matches(regex)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("please follow the right format - name@email.com");
            return email.matches(regex);
        } else {
            ValidationCount++;
            return email.matches(regex);
        }
    }

    // Checks if a string is only digits
    public static boolean isOnlyDigits(String OrderNum) {
        // Regular expression pattern to match only digits
        String regex = "\\d+";
        if (!OrderNum.matches(regex)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Please only enter numbers for this field !");
            return OrderNum.matches(regex);
        } else {
            ValidationCount++;
            return OrderNum.matches(regex);
        }
    }

    // Checks if string is a double
    public static boolean isDouble(String str) {
        try {
            double value = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid price.");
            System.out.println("Please only enter Numbers as this is a price for an item");
            return false;
        }
    }

    // checks if the string is a boolean
    public static boolean isBoolean(String str) {
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
            return true;
        } else {
            System.out.println("Please only enter true or false");
            return false;
        }
    }

    // Checks the Start of every word is capitalized like it is in driver.txt for
    // the Location
    public static String capitalizeLocation(String location) {
        if (location.isEmpty()) {
            System.out.println("No string");
            return location;
        }

        location = location.toLowerCase();
        String[] words = location.split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                char firstLetter = Character.toUpperCase(word.charAt(0));
                String capitalizedWord = firstLetter + word.substring(1);

                result.append(capitalizedWord);

                if (i != words.length - 1) {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }
}
