package main.java;

class Main {
    public static void main(String[] args) {

        if (args.length > 0) {
            System.out.println("The command line arguments are:");

            for (String arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("No command line arguments found.");
        }

    }
}