import java.util.ArrayList;
import java.util.List;

enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType type;
    private double balance;
    private List<Activity> activities;

    public Passenger(String name, int passengerNumber, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.activities = new ArrayList<>();
    }

    public void signUpForActivity(Activity activity) {
        if (type == PassengerType.STANDARD) {
            if (balance >= activity.getCost()) {
                balance -= activity.getCost();
                activities.add(activity);
            } else {
                System.out.println("Insufficient balance for " + name + " to sign up for activity: " + activity.getName());
            }
        } else if (type == PassengerType.GOLD) {
            double discountedCost = activity.getCost() * 0.9; 
            if (balance >= discountedCost) {
                balance -= discountedCost;
                activities.add(activity);
            } else {
                System.out.println("Insufficient balance for " + name + " to sign up for activity: " + activity.getName());
            }
        } else { 
            activities.add(activity);
        }
    }

    
    public void printDetails() {
        System.out.println("Passenger Name: " + name);
        System.out.println("Passenger Number: " + passengerNumber);
        if (type == PassengerType.STANDARD || type == PassengerType.GOLD) {
            System.out.println("Balance: " + balance);
        }
        System.out.println("Activities:");
        for (Activity activity : activities) {
            System.out.println("- " + activity.getName() + " at " + activity.getDestination().getName() + ", Price: " + activity.getCost());
        }
    }
}


class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
    }

    
    public int getAvailability() {
        
        return capacity; 
    }

    public void printDetails() {
        System.out.println("Activity Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Cost: " + cost);
        System.out.println("Capacity: " + capacity);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public Destination getDestination() {
        return destination;
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void printDetails() {
        System.out.println("Destination: " + name);
        System.out.println("Activities:");
        for (Activity activity : activities) {
            activity.printDetails();
        }
    }
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
        } else {
            System.out.println("Travel package is full. Cannot add more passengers.");
        }
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        System.out.println("Itinerary:");
        for (Destination destination : itinerary) {
            destination.printDetails();
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers: " + passengers.size());
        System.out.println("Passengers:");
        for (Passenger passenger : passengers) {
            System.out.println("- Name: " + passenger.getName() + ", Number: " + passenger.passengerNumber);
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        passenger.printDetails();
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.activities) {
                if (activity.getAvailability() > 0) {
                    activity.printDetails();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        TravelPackage package1 = new TravelPackage("Package 1", 5);

        Destination destination1 = new Destination("Destination 1");
        Activity activity1 = new Activity("Activity 1", "Description 1", 100.0, 10, destination1);
        destination1.addActivity(activity1);
        package1.addPassenger(new Passenger("John", 1, PassengerType.STANDARD));
        package1.addPassenger(new Passenger("Alice", 2, PassengerType.GOLD));
        package1.addPassenger(new Passenger("Bob", 3, PassengerType.PREMIUM));

        package1.printItinerary();
        package1.printPassengerList();
        package1.printAvailableActivities();
    }
}
