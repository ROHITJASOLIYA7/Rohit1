import java.util.*;

class Directory {

    private Map<String, Person> directory;

    public Directory() {
        directory = new HashMap<>();
    }

    public void createEntry(Person person) {
        if (directory.containsKey(person.getUniqueId())) {
            System.out.println("Error: Unique ID already exists.");
            return;
        }
        directory.put(person.getUniqueId(), person);
        System.out.println("Entry created successfully.");
    }

    public void editEntry(String uniqueId) {
        if (!directory.containsKey(uniqueId)) {
            System.out.println("Error: Unique ID not found.");
            return;
        }
        Person person = directory.get(uniqueId);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new name (or leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            person.setName(name);
        }

        System.out.print("Enter new address (or leave blank to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            person.setAddress(address);
        }

        System.out.print("Enter new telephone number (or leave blank to keep current): ");
        String telephoneNumber = scanner.nextLine();
        if (!telephoneNumber.isEmpty()) {
            person.setTelephoneNumber(telephoneNumber);
        }

        System.out.print("Enter new mobile number (or leave blank to keep current): ");
        String mobileNumber = scanner.nextLine();
        if (!mobileNumber.isEmpty()) {
            person.setMobileNumber(mobileNumber);
        }

        System.out.print("Enter new head of family (Y/N, or leave blank to keep current): ");
        String headOfFamily = scanner.nextLine();
        if (!headOfFamily.isEmpty() && headOfFamily.equalsIgnoreCase("Y")) {
            person.setHeadOfFamily(true);
        } else if (!headOfFamily.isEmpty() && headOfFamily.equalsIgnoreCase("N")) {
            person.setHeadOfFamily(false);
        }

        System.out.println("Entry updated successfully.");
    }

    public void searchEntry(String keyword) {
        boolean found = false;
        for (Person person : directory.values()) {
            if (person.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                person.getAddress().toLowerCase().contains(keyword.toLowerCase()) ||
                (person.getTelephoneNumber() != null && person.getTelephoneNumber().contains(keyword)) ||
                (person.getMobileNumber() != null && person.getMobileNumber().contains(keyword)) ||
                person.getUniqueId().contains(keyword)) {
                System.out.println(person);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching entries found.");
        }
    }

    public void displayDirectory() {
        if (directory.isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }
        for (Person person : directory.values()) {
            System.out.println(person);
        }
    }

    public static void main(String[] args) {
        Directory directory = new Directory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new entry");
            System.out.println("2. Edit an existing entry");
            System.out.println("3. Search by keyword");
            System.out.println("4. Display the directory");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter telephone number (or leave blank if unavailable): ");
                    String telephoneNumber = scanner.nextLine();
                    System.out.print("Enter mobile number (or leave blank if unavailable): ");
                    String mobileNumber = scanner.nextLine();
                    System.out.print("Is this person the head of the family (Y/N): ");
                    String headOfFamily = scanner.nextLine();
                    boolean isHeadOfFamily = headOfFamily.equalsIgnoreCase("Y");
                    System.out.print("Enter unique ID: ");
                    String uniqueId = scanner.nextLine();
                    Person person = new Person(name, address, telephoneNumber, mobileNumber, uniqueId, isHeadOfFamily);
                    directory.createEntry(person);
                    break;
                case 2:
                    System.out.print("Enter unique ID of the entry to edit: ");
                    String uniqueIdToEdit = scanner.nextLine();
                    directory.editEntry(uniqueIdToEdit);
                    break;
                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    directory.searchEntry(keyword);
                    break;
                case 4:
                    directory.displayDirectory();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Person {
    private String name;
    private String address;
    private String telephoneNumber;
    private String mobileNumber;
    private String uniqueId;
    private boolean headOfFamily;

    public Person(String name, String address, String telephoneNumber, String mobileNumber, String uniqueId, boolean headOfFamily) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.uniqueId = uniqueId;
        this.headOfFamily = headOfFamily;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean isHeadOfFamily() {
        return headOfFamily;
    }

    public void setHeadOfFamily(boolean headOfFamily) {
        this.headOfFamily = headOfFamily;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nAddress: " + address +
                "\nTelephone Number: " + (telephoneNumber != null ? telephoneNumber : "N/A") +
                "\nMobile Number: " + (mobileNumber != null ? mobileNumber : "N/A") +
                "\nUnique ID: " + uniqueId +
                "\nHead of Family: " + (headOfFamily ? "Yes" : "No");
    }
}