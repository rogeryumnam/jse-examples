package com.gilt;

import java.util.*;

/**
 * You run a paint shop, and there are a few different colors of paint you can prepare. Each color can be either
 * "gloss" or "matte" You have a number of customers, and each have some colors they like, either gloss or matte.
 * No customer will like more than one color in matte. You want to mix the colors, so that: There is just one batch
 * for each color, and it&#39;s either gloss or matte. For each customer, there is at least one color they like.
 * You make as few mattes as possible (because they are more expensive). Your program should accept an input file as a
 * command line argument, and print a result to standard out. An example input file is:
 *
 * 5
 * 1 M 3 G 5 G
 * 2 G 3 M 4 G
 * 5 M
 *
 * The first line specifies how many colors there are. Each subsequent line describes a customer. For example,
 * the first customer likes color 1 in matte, color 3 in gloss and color 5 in gloss. Your program should read an input
 * file like this, and print out either that it is impossible to satisfy all the customer, or describe, for each of
 * the colors, whether it should be made gloss or matte. The output for the above file should be:
 *
 * G G G G M
 *
 * because all customers can be made happy by every paint being prepared as gloss except number 5.
 * An example of a file with no solution is:
 *
 * 1
 * 1 G
 * 1 M
 *
 * Your program should print
 * No solution exists
 *
 * A slightly richer example is:
 *
 * 5
 * 2 M
 * 5 G
 * 1 G
 * 5 G 1 G 4 M
 * 3 G
 * 5 G
 * 3 G 5 G 1 G
 * 3 G
 * 2 M
 * 5 G 1 G
 * 2 M
 * 5 G
 * 4 M
 * 5 G 4 M
 *
 * ...which should print:
 *
 * G M G M G
 * One more example. The input:
 * 2
 * 1 G 2 M
 * 1 M
 *
 * ...should produce
 * M M
 *
 */
public class CodeChallenge {

    public static void main(String...args) {

        like(5, Arrays.asList("1M3G5G", "2G3M4G", "5M"));
        like(1, Arrays.asList("1G", "1M"));
        like(5, Arrays.asList("2M", "5G", "1G", "5G1G4M", "3G", "5G" , "3G5G1G", "3G", "2M", "5G1G", "2M", "5G", "4M", "5G4M"));
        like(2, Arrays.asList("1G2M", "1M"));
    }

    private static void like(int colors, List<String> customers) {
        Map<String, String> colorGrades = new HashMap<>();
        boolean noSolution = false;

        // may be we can avoid this for loop and do something in main for loop below
        for (int i = 1; i <= colors; i++) {
            colorGrades.put("" + i, "G");
        }

        for (int k = 0; k < customers.size(); k++) {
            String customer = customers.get(k);
            int mFound = -1;
            boolean found = false;
            for (int i = 1; i <= colors; i++) {
                for (int j = 0; j < customer.length() - 1; j += 2) {
                    String customerLike = customer.substring(j, j + 2);
                    if (customerLike.equals("" + i + colorGrades.get(""+i))) {
                        found = true;
                        break;
                    } else {
                        if (customerLike.equals("" + i +"M")) {
                            mFound = Integer.parseInt (""+customer.charAt(j));
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found && mFound != -1) {
                colorGrades.put("" + (mFound ), "M");
                k =-1;
            } else if (!found){
                noSolution=true;
                break;
            }
        }
        if (noSolution) {
            System.out.println("No Solution exists");
        } else {
            for (int i = 1; i <= colors; i++) {
                System.out.print(colorGrades.get("" + i) + "  ");
            }
            System.out.println("");
        }
    }
}