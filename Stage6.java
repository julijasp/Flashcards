package FlashCard;

import java.io.*;
import java.util.*;



public class Stage6 {
    public static Scanner scanner = new Scanner(System.in);
    public static LinkedHashMap<String, String> cards = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, Integer> hardestCard = new LinkedHashMap<String, Integer>();
    public static String definitionCardBack;
    public static String answer;
    public static String term;
    public static String fileName;
    public static String exportFileName;
    public static String action;
    public static ArrayList<String> input = new ArrayList<String>();
    public static System.Logger logger;
    public static  StringBuilder sb;

    public static void main(String[] args) {



        printMenu();

    }

    public static void createCards() {

        System.out.println("How many times to ask?");
        int numberOfCards = Integer.parseInt(scanner.nextLine());
        int i = 1;
        int wrongAnswer = 0;

        for (String key : cards.keySet()) {
            System.out.printf("Print the definition of " + "\"%s\":%n", key);
            answer = scanner.nextLine();
            if (answer.equals(cards.get(key))) {
                System.out.println("Correct!");
                System.out.println();

            } else if (cards.values().contains(answer)) {
                for (Map.Entry<String, String> entry : cards.entrySet()) {
                    if (entry.getValue().equals(answer)) {
                        System.out.println("Wrong. The right answer is " + "\"" + cards.get(key) + "\"" +
                                ", but your definition is correct for " + "\"" + entry.getKey() + "\"");
                        wrongAnswer++;
                       // hardestCard.put(cards.values(), wrongAnswer);
                         System.out.println();
                        System.out.println(hardestCard);
                    }
                }
            } else {
                System.out.printf("Wrong. The right answer is " + "\"%s\".%n", cards.get(key));
                wrongAnswer++;
                // hardestCard.put(cards., wrongAnswer);
                 System.out.println();
            }
            if (i > numberOfCards - 1) {
                break;
            }

            if (numberOfCards > cards.size()) {

            }
            i++;
        }
    }


    public static void printMenu() {

        do {

            System.out.println("input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");

            action = scanner.nextLine();

            switch (action) {
                case "add":
                    System.out.println("The card:");
                    term = scanner.nextLine();
                    if (cards.keySet().contains(term)) {
                        System.out.println("The card " + "\"" + term + "\"" + " already exists. Try again:");
                        System.out.println();

                    } else {
                        System.out.println("The definition of the card:");
                        definitionCardBack = scanner.nextLine();
                        if (cards.values().contains(definitionCardBack)) {
                            System.out.println("The definition " + "\"" + definitionCardBack + "\"" + " already exists.");
                            System.out.println();
                        } else {
                            System.out.println("The pair " + "(\"" + term + "\":\"" + definitionCardBack + "\")" + " has been added.");
                            cards.put(term, definitionCardBack);
                            System.out.println();
                        }
                    }
                    break;

                case "remove":
                    System.out.println("Which card?");
                    String removeCard = scanner.nextLine();
                    if (cards.containsKey(removeCard)) {
                        cards.remove(removeCard);
                        System.out.println("The card has been removed.");
                        System.out.println();
                    } else {
                        System.out.println("Can't remove " + "\"" + removeCard + "\"" + " : there is no such card.");
                        System.out.println();
                    }
                    break;

                case "import":
                    System.out.println("File name:");
                    importFile();
                    break;

                case "export":
                    System.out.println("File name:");
                    exportFileName = scanner.nextLine();
                    System.out.println(cards.size() + " cards have been saved.");
                    System.out.println();
                    Stage4.printMapToFile(cards);
                    break;

                case "ask":
                    createCards();
                    break;

                case "exit":
                    System.out.println("Bye bye!");
                    break;

                case "log":
                    printLog();
                    break;
            }

        } while (!action.equals("exit"));
    }


    public static void printMapToFile(LinkedHashMap<String, String> map) {

        try {
            File fileTwo = new File(exportFileName);
            FileOutputStream fos = new FileOutputStream(fileTwo);
            PrintWriter pw = new PrintWriter(fos);

            for (Map.Entry<String, String> m : map.entrySet()) {
                pw.println(m.getKey() + "=" + m.getValue());
            }

            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }

    public static void importFile() {
        fileName = scanner.nextLine();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            int size = 0;
            //System.out.println(line);
            while (line != null) {
                String[] capitals = line.split("=");
                line = reader.readLine();
                size++;
                cards.put(capitals[0], capitals[1]);
            }
            System.out.println(size + " cards have been loaded.");
            System.out.println();
            reader.close();

        } catch (IOException e) {
            System.out.println("File not found.");
            System.out.println();
        }
    }

    public static void printLog() {
        sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        logOut("File name: ");
        String fileName = sc.nextLine();
        logIn(fileName);
        logOut("The log has been saved.");
        System.out.println();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            sb.append("\n");
            String s = sb.toString();
            bw.write(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

        public static void logOut(String s) {
            sb.append(s);
            sb.append("\n");
            System.out.println(s);
        }

       public static void logIn(String s) {
            sb.append(s);
            sb.append("\n");
        }






}