
class Main {
    public static void main(String args[]) {

        if (args.length > 0) {
            System.out.println("The command line arguements are:");

            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }
        } else {
            System.out.println("No command line arguements found.");
        }

    }
}