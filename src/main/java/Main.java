package main.java;

class Main {
    public static void main(String[] args) {
        ArgumentParser driver = new ArgumentParser();

        if (args.length > 0) {
            // Print out all CLI arguments
            System.out.println("The command line arguments are:");
            for (String arg : args) {
                System.out.println(arg);
            }

            // Parse the arguments
            driver.parseArguments(args);

        } else {
            System.out.println("No command line arguments found.");
        }

    }
}