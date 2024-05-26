import java.util.*;
import java.time.*;

class ContactList {
    private Contacts[] contactArray;
    private int nextIndex;
    private int size;
    private int loadFactor;

    ContactList(int size, int loadFactor) {
        contactArray = new Contacts[size];
        this.size = size;
        this.loadFactor = loadFactor;
        nextIndex = 0;
    }

    private boolean isFull() {
        return nextIndex >= size;
    }
    public Contacts[] tempArray() {
        Contacts[] tempContactArray = new Contacts[size];
        for (int i = 0; i < nextIndex; i++) {
            tempContactArray[i] = contactArray[i];
        }
        contactArray = tempContactArray;
        return tempContactArray;
    }
    public void extendArrays() {
        Contacts[] tempContactArray = new Contacts[loadFactor];
        for (int i = 0; i < nextIndex; i++) {
            tempContactArray[i] = contactArray[i];
        }
        contactArray = tempContactArray;
    }
    public void add(Contacts contacts) {
        if (isFull()) {
            extendArrays();
        }
        contactArray[nextIndex++] = contacts;
    }
    public int getSize() {
        return nextIndex;
    }
    public int search(String NameOrPhoneNum) {
        for (int i = 0; i < nextIndex; i++) {
            if (contactArray[i].getPhoneNumber().equals(NameOrPhoneNum)
                    || contactArray[i].getName().equalsIgnoreCase(NameOrPhoneNum)) {
                return i;
            }
        }
        return -1;
    }
    public void updateName(int foundIndex, String name) {
        contactArray[foundIndex].setName(name);
    }
    public void updatePhoneNumber(int foundIndex, String phoneNumber) {
        contactArray[foundIndex].setPhoneNumber(phoneNumber);
    }
    public void updateCompanyName(int foundIndex, String company) {
        contactArray[foundIndex].setCompanyName(company);
    }
    public void updateSalary(int foundIndex, double salary) {
        contactArray[foundIndex].setSalary(salary);
    }
    public boolean DuplicateChecker(String phoneNumber) {
        for (int i = 0; i < nextIndex; i++) {
            if (contactArray[i].getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
    public void delete(int foundIndex) {
        for (int i = foundIndex; i < nextIndex; i++) {
            contactArray[i] = contactArray[i + 1];
        }
        nextIndex--;
    }
    public String getId() {
        return this.contactArray[nextIndex - 1].getId();
    }
}

class Contacts {
    private String name;
    private String id;
    private String phoneNumber;
    private String birthday;
    private String company;
    private double salary;

    Contacts(String id, String name, String phoneNumber, String company, double salary, String birthday) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.company = company;
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompanyName(String company) {
        this.company = company;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompanyName() {
        return company;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getSalary() {
        return salary;
    }
}

class iFRIEND {
    public static ContactList contactList = new ContactList(100, 50);

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // ======================================================geerateID====================================================
    public static String tempcontactId() {
        if (contactList.getSize() == 0) {
            return "C0001";
        }
        String lastId = contactList.getId();
        int lastNo = Integer.parseInt(lastId.substring(1));
        return String.format("C%04d", lastNo + 1);
    }

    // ==================================================ValidationOfPhoneNumber=====================================================
    public static boolean ValidationOfPhoneNumber(String phoneNumber) {
        char ch = phoneNumber.charAt(0);
        if (ch != '0' || (phoneNumber.length() != 10)) {
            return true;
        }
        return false;
    }

    // ==================================================validationOfBirthday========================================================
    public static boolean validationOfBirthday(int year, int month, int date) {
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        int currentMonthDate = currentDate.getDayOfMonth();
        boolean validate;
        if (year < currentYear) {
            validate = true;
        } else if (year == currentYear) {
            if (month < currentMonthValue) {
                validate = true;
            } else if (month == currentMonthValue) {
                if (date < currentMonthDate) {
                    validate = true;
                } else {
                    validate = false;
                }
            } else {
                validate = false;
            }
        } else {
            validate = false;
        }
        if (!validate) {
            System.out.println("\n\tInvalid Birthday...");
            return false;
        } else if (year <= 2024) {
            if (year % 4 != 0) {
                if (month > 0 && month <= 12) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
                            || month == 12) {
                        if (date < 0 || date > 31) {
                            System.out.println("\n\tInvalid Birthday...");
                            return false;
                        }
                    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (date < 0 || date > 30) {
                            System.out.println("\n\tInvalid Birthday...");
                            return false;
                        }
                    } else if (month == 2) {
                        if (date < 0 || date > 28) {
                            System.out.println("\n\tInvalid Birthday...");
                            return false;
                        }
                    }
                } else {
                    System.out.println("\n\tInvalid Birthday...");
                    return false;
                }
            } else {
                if ((month > 0 && month <= 12) && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                        || month == 10 || month == 12)) {
                    if (date < 0 || date > 31) {
                        System.out.println("\n\tInvalid Birthday...");
                        return false;
                    }
                } else if ((month > 0 && month <= 12) && (month == 4 || month == 6 || month == 9 || month == 11)) {
                    if (date < 0 || date > 30) {
                        System.out.println("\n\tInvalid Birthday...");
                        return false;
                    }
                } else if ((month > 0 && month <= 12) && (month == 2)) {
                    if (date < 0 || date > 29) {
                        System.out.println("\n\tInvalid Birthday...");
                        return false;
                    }
                } else {
                    System.out.println("\n\tInvalid Birthday...");
                    return false;
                }
            }
        }
        return validate;
    }

    // ======================================================isValidSalary===========================================================
    public static boolean isValidSalary(double salary) {
        return salary > 0;
    }

    // ======================================================displayContact==========================================================
    public static void displayContact(int foundIndex) {
        System.out.println("\n\tContactID      : " + contactList.tempArray()[foundIndex].getId());
        System.out.println("\tName           : " + contactList.tempArray()[foundIndex].getName());
        System.out.println("\tPhone Number   : " + contactList.tempArray()[foundIndex].getPhoneNumber());
        System.out.println("\tCompany Name   : " + contactList.tempArray()[foundIndex].getCompanyName());
        System.out.println("\tSalary         : " + contactList.tempArray()[foundIndex].getSalary());
        System.out.println("\tBirthday       : " + contactList.tempArray()[foundIndex].getBirthday());
    }

    // =====================================================addContacts=============================================================
    public static void addContacts() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                      Add Contact to the list                      |");
        System.out.println("+-------------------------------------------------------------------+\n\n");
        System.out.println(" " + tempcontactId());
        String id = tempcontactId();
        System.out.println("=======\n");
        System.out.print("Name              : ");
        String name = input.nextLine();
        String phoneNumber;
        while (true) {
            System.out.print("Phone Number      : ");
            phoneNumber = input.nextLine();
            if (contactList.DuplicateChecker(phoneNumber)) {
                System.out.println("\n       Duplicate phone number...\n");
                System.out.print("Do you want to add phone number again (Y/N) : ");
                char choice = input.next().charAt(0);
                input.nextLine();
                if (choice == 'y' || choice == 'Y') {
                    System.out.print("\033[5A");
                    System.out.print("\033[0J");
                    continue;
                } else if (choice == 'n' || choice == 'N') {
                    clearConsole();
                    main(null);
                }
            } else if (ValidationOfPhoneNumber(phoneNumber)) {
                System.out.println("\n       Invalid phone number...\n");
                System.out.print("Do you want to add phone number again (Y/N) : ");
                char choice = input.next().charAt(0);
                input.nextLine();
                if (choice == 'y' || choice == 'Y') {
                    System.out.print("\033[5A");
                    System.out.print("\033[0J");
                    continue;
                } else if (choice == 'n' || choice == 'N') {
                    clearConsole();
                    main(null);
                }
            }
            break;
        }
        System.out.print("Company Name      : ");
        String company = input.nextLine();
        double salary;
        do {
            System.out.print("Salary		  : ");
            salary = input.nextDouble();
            if (!isValidSalary(salary)) {
                System.out.println("Invalid salary...");
                System.out.print("\nDo you want to input salary again (Y/N)? ");
                char ch = input.next().charAt(0);
                if (ch == 'Y' | ch == 'y') {
                    System.out.print("\033[4A");
                    System.out.print("\033[0J");

                } else if (ch == 'N' | ch == 'n') {
                    main(null);
                    break;
                }
            }
        } while (!isValidSalary(salary));
        String birthday;
        while (true) {
            System.out.print("B'Day(YYYY-MM-DD) : ");
            birthday = input.next();
            int year = Integer.parseInt(birthday.substring(0, 4));
            int month = Integer.parseInt(birthday.substring(5, 7));
            int date = Integer.parseInt(birthday.substring(8, 10));
            if (!validationOfBirthday(year, month, date)) {
                System.out.print("\nDo you want to input Bithday again (Y/N)? ");
                char ch = input.next().charAt(0);
                if (ch == 'Y' | ch == 'y') {
                    System.out.print("\033[5A");
                    System.out.print("\033[0J");
                    continue;
                } else if (ch == 'N' | ch == 'n') {
                    main(null);
                    break;
                }
            }
            break;
        }
        System.out.println("\n\tContact has been added successfully...");
        Contacts contact = new Contacts(id, name, phoneNumber, company, salary, birthday);
        contactList.add(contact);
        System.out.print("\n\nDo you want to add another Contact (Y/N) : ");
        char Choice = input.next().charAt(0);
        if (Choice == 'Y' || Choice == 'y') {
            addContacts();
        } else {
            main(null);
        }
    }

    // ====================================================updateContacts===========================================================
    public static void updateContacts() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                   Update Contact in the list                      |");
        System.out.println("+-------------------------------------------------------------------+\n\n");
        System.out.print("Search Contact by Name or Phone Number - ");
        String NameOrPhoneNum = input.nextLine();
        int foundIndex = contactList.search(NameOrPhoneNum);
        if (foundIndex == -1) {
            System.out.println("\n\tCan't Found this Contact...");
            System.out.print("\n\nDo you want to Search Contact by Name or Phone Number (Y/N) : ");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                System.out.println("\n\n");
                updateContacts();
            } else {
                main(null);
            }
        }
        displayContact(foundIndex);
        System.out.println("\nWhat do you want to update...");
        System.out.println();
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Phone Number");
        System.out.println("\t[3] Company Name");
        System.out.println("\t[4] Salary");
        System.out.print("\nEnter an option to continue -> ");
        int updateOption = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.println();
        switch (updateOption) {
            case 1:
                System.out.print("Enter new name : ");
                String newName = input.nextLine();
                contactList.updateName(foundIndex, newName);
                break;
            case 2:
                System.out.print("Phone Number     : ");
                String newPhoneNumber = input.nextLine();
                contactList.updatePhoneNumber(foundIndex, newPhoneNumber);
                break;
            case 3:
                System.out.print("Enter new company name : ");
                String newCompanyName = input.nextLine();
                contactList.updateCompanyName(foundIndex, newCompanyName);
                break;
            case 4:
                System.out.print("Enter new salary: ");
                double newSalary = input.nextDouble();
                contactList.updateSalary(foundIndex, newSalary);
                break;
            default:
                System.out.print("Invalid option...\n\nDo you want to enter valid option again? (Y/N): ");
                char choice = input.next().charAt(0);
                if (choice == 'Y' || choice == 'y') {
                    updateContacts(); // Recursively call the updateContacts method
                } else {
                    main(null); // Return to the main menu
                }
                break;
        }
        System.out.println("\n\tContact updated successfully...");
        System.out.print("\nDo you want to update another contact? (Y/N): ");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            updateContacts();
        } else {
            main(null);
        }
    }

    // ====================================================deleteContacts===========================================================
    public static void deleteContacts() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                           DELETE Contact                          |");
        System.out.println("+-------------------------------------------------------------------+\n\n");
        System.out.print("Search Contact by Name or Phone Number - ");
        String NameOrPhoneNum = input.nextLine();
        int foundIndex = contactList.search(NameOrPhoneNum);
        if (foundIndex == -1) {
            System.out.println("\n\tCan't Found this Contact...");
            System.out.print("\n\nDo you want to Search Contact again (Y/N) : ");
            int choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                System.out.println("\n\n");
                deleteContacts();
            } else {
                main(null);
            }
        }
        displayContact(foundIndex);
        System.out.print("\nDo you want to delete this contact (Y/N): ");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            contactList.delete(foundIndex);
            System.out.println("\nContact has been deleted successfully...");
        } else {
            System.out.println("\nContact delete Unsuccessfull...");
        }
        System.out.print("\nDo you want to delete another contact? (Y/N): ");
        choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            deleteContacts();
        } else {
            main(null);
        }
    }

    // ====================================================SearchContacts===========================================================
    public static void SearchContacts() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                           SEARCH Contact                          |");
        System.out.println("+-------------------------------------------------------------------+\n\n");
        System.out.print("Search Contact by Name or Phone Number - ");
        String NameOrPhoneNum = input.nextLine();
        int foundIndex = contactList.search(NameOrPhoneNum);
        if (foundIndex > -1) {
            displayContact(foundIndex); // Only display if contact was found
        } else {
            System.out.println("\n\tCan't Found this Contact...");
        }
        System.out.print("\nDo you want to search another contact? (Y/N): ");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            SearchContacts();
        } else {
            main(null);
        }
    }

    // ====================================================sortContacts=============================================================
    public static void sortContacts() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                           SORT Contact                            |");
        System.out.println("+-------------------------------------------------------------------+\n\n");
        System.out.println("\t[01] Sorting by Name\n");
        System.out.println("\t[02] Sorting by Salary\n");
        System.out.println("\t[03] Sorting by Birthday\n");
        System.out.print("\nEnter an option to continue -> ");
        int sortOption = input.nextInt();
        switch (sortOption) {
            case 1:
                nameSort();
                break;
            case 2:
                salarySort();
                break;
            case 3:
                birthdaySort();
                break;
        }
    }

    // ======================================================nameSort===============================================================
    public static void nameSort() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("\t\t\t+-------------------------------------------------------------------+");
        System.out.println("\t\t\t|                       List Contact by Name                        |");
        System.out.println("\t\t\t+-------------------------------------------------------------------+\n\n");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", "ContactID", "Name", "Phone Number",
                "Company", "Salary", "BirthDay");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        Contacts[] tempContactArray = new Contacts[contactList.getSize()];
        for (int i = 0; i < tempContactArray.length; i++) {
            tempContactArray[i] = contactList.tempArray()[i];
        }
        for (int i = 0; i < tempContactArray.length - 1; i++) {
            for (int j = 0; j < tempContactArray.length - i - 1; j++) {
                if (tempContactArray[j].getName().compareToIgnoreCase(tempContactArray[j + 1].getName()) > 0) {
                    // Swap names
                    Contacts tempContacts = tempContactArray[j];
                    tempContactArray[j] = tempContactArray[j + 1];
                    tempContactArray[j + 1] = tempContacts;
                }
            }
        }
        for (int i = 0; i < tempContactArray.length; i++) {
            System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", tempContactArray[i].getId(),
                    tempContactArray[i].getName(), tempContactArray[i].getPhoneNumber(),
                    tempContactArray[i].getCompanyName(), tempContactArray[i].getSalary(),
                    tempContactArray[i].getBirthday());
        }
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.print("\nDo you want to go  Home page (Y/N) :  ");
        char ch = input.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            main(null);
        } else if (ch == 'N' || ch == 'n') {
            System.exit(0);
        }
    }

    // =====================================================salarySort==============================================================
    public static void salarySort() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("\t\t\t+-------------------------------------------------------------------+");
        System.out.println("\t\t\t|                      List Contact by Salary                       |");
        System.out.println("\t\t\t+-------------------------------------------------------------------+\n\n");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", "ContactID", "Name", "Phone Number",
                "Company", "Salary", "BirthDay");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        Contacts[] tempContactArray = new Contacts[contactList.getSize()];
        for (int i = 0; i < tempContactArray.length; i++) {
            tempContactArray[i] = contactList.tempArray()[i];
        }
        // Sort the copied salaryArray using bubble sort
        for (int i = 0; i < tempContactArray.length - 1; i++) {
            for (int j = 0; j < tempContactArray.length - i - 1; j++) {
                if (tempContactArray[j].getSalary() > tempContactArray[j + 1].getSalary()) {
                    // Swap salaries
                    Contacts newContacts = tempContactArray[j];
                    tempContactArray[j] = tempContactArray[j + 1];
                    tempContactArray[j + 1] = newContacts;
                }
            }
        }
        for (int i = 0; i < tempContactArray.length; i++) {
            System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", tempContactArray[i].getId(),
                    tempContactArray[i].getName(), tempContactArray[i].getPhoneNumber(),
                    tempContactArray[i].getCompanyName(), tempContactArray[i].getSalary(),
                    tempContactArray[i].getBirthday());
        }
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.print("\nDo you want to go to Home page (Y/N) :  ");
        char ch = input.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            main(null);
        } else if (ch == 'N' || ch == 'n') {
            System.exit(0);
        }
    }

    // =====================================================birthdaySort============================================================
    public static void birthdaySort() {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("\t\t\t+-------------------------------------------------------------------+");
        System.out.println("\t\t\t|                    List Contact by Birthday                       |");
        System.out.println("\t\t\t+-------------------------------------------------------------------+\n\n");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", "ContactID", "Name", "Phone Number",
                "Company", "Salary", "BirthDay");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        // Bubble sort algorithm to sort by birthdays
        Contacts[] tempBdayArray = new Contacts[contactList.getSize()];
        for (int i = 0; i < tempBdayArray.length; i++) {
            tempBdayArray[i] = contactList.tempArray()[i];
        }
        for (int i = 0; i < tempBdayArray.length - 1; i++) {
            for (int j = 0; j < tempBdayArray.length - i - 1; j++) {
                int year1 = Integer.parseInt(tempBdayArray[j].getBirthday().substring(0, 4));
                int month1 = Integer.parseInt(tempBdayArray[j].getBirthday().substring(5, 7));
                int day1 = Integer.parseInt(tempBdayArray[j].getBirthday().substring(8, 10));
                int year2 = Integer.parseInt(tempBdayArray[j + 1].getBirthday().substring(0, 4));
                int month2 = Integer.parseInt(tempBdayArray[j + 1].getBirthday().substring(5, 7));
                int day2 = Integer.parseInt(tempBdayArray[j + 1].getBirthday().substring(8, 10));
                // Compare birthdays
                if (year1 > year2 || (year1 == year2 && month1 > month2)
                        || (year1 == year2 && month1 == month2 && day1 > day2)) {
                    Contacts newtempContacts = tempBdayArray[j];
                    tempBdayArray[j] = tempBdayArray[j + 1];
                    tempBdayArray[j + 1] = newtempContacts;
                }
            }
        }
        // Display sorted contacts
        for (int i = 0; i < tempBdayArray.length; i++) {
            System.out.printf("| %-12s| %-20s| %-20s| %-17s| %-19s| %-18s|\n", tempBdayArray[i].getId(),
                    tempBdayArray[i].getName(), tempBdayArray[i].getPhoneNumber(), tempBdayArray[i].getCompanyName(),
                    tempBdayArray[i].getSalary(), tempBdayArray[i].getBirthday());
        }
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.print("\nDo you want to go to Home page (Y/N) :  ");
        char ch = input.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            main(null);
        } else if (ch == 'N' || ch == 'n') {
            System.exit(0);
        }
    }

    // ====================================================Main
    // Method(HomePage)====================================================
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        clearConsole();
        System.out.println("\t/$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$ ");
        System.out.println("\t|__/| $$_____/| $$__  $$|_  $$_/| $$_____/| $$$ | $$| $$__  $$");
        System.out.println("\t /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$");
        System.out.println("\t| $$| $$$$$   | $$$$$$$/  | $$  | $$$$$   | $$ $$ $$| $$  | $$");
        System.out.println("\t| $$| $$__/   | $$__  $$  | $$  | $$__/   | $$  $$$$| $$  | $$");
        System.out.println("\t| $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$");
        System.out.println("\t| $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$$$$$/");
        System.out.println("\t|__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/ ");
        System.out.println("\n  _____            _             _          ____                        _              ");
        System.out.println(" / ____|          | |           | |        / __ \\                      (_)             ");
        System.out.println("| |     ___  _ __ | |_ __ _  ___| |_ ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __ ");
        System.out
                .println("| |    / _ \\| '_ \\| __/ _` |/ __| __/ __| | |  | | '__/ _` |/ _` | '_ \\| |_  / _ \\ '__|");
        System.out.println("| |___| (_) | | | | || |_| | |__| |_\\__ \\ | |__| | | | |_| | |_| | | | | |/ /  __/ |   ");
        System.out.println(
                " \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_|   ");
        System.out.println("                                                       __/ |                           ");
        System.out.println("                                                      |___/                            ");
        System.out.println(
                "\n=======================================================================================\n\n");

        System.out.println("\t[01] ADD Contacts\n");
        System.out.println("\t[02] UPDATE Contacts\n");
        System.out.println("\t[03] DELETE Contacts\n");
        System.out.println("\t[04] SEARCH Contacts\n");
        System.out.println("\t[05] LIST Contacts\n");
        System.out.println("\t[06] Exit\n");
        System.out.print("\nEnter an option to continue -> ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                addContacts();
                break;
            case 2:
                updateContacts();
                break;
            case 3:
                deleteContacts();
                break;
            case 4:
                SearchContacts();
                break;
            case 5:
                sortContacts();
                break;
            case 6:
                clearConsole();
                System.exit(0);
                break;
        }
    }
}
