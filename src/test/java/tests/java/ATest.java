/**
 * Statement:
 * We have a set of N (1 ≤ N ≤ 100) different versions of advertising messages that we would like to randomly select and
 * send out to the public. Each version has a weight w[i] (0 ≤ w[i] ≤ 1.000.000.000) of being selected.
 *
 * Implement a function to take an array w[] as input and return a message version i (0 ≤ i ≤ N-1) that satisfies the
 * weighted random condition.
 *      func f(w []int) int { * }
 *
 * For example, there are N = 3 versions where w[0] = 50, w[1] = 30 and w[2] = 60 which give a total weight of 140.
 *   - Now suppose that each time we randomly select a version for 1400 messages. The function satisfies the weighted
 *   random condition when around 500 times the selected message is version 0, around 300 times the message is version
 *   1, and around 600 times the message is version 2.
 *   - If we run the function for only 280 times, it is expected that we will get version 0 about 100 times, version 1
 *   about 60 times, and version 2 about 120 times.
 *
 * Requirement:
 *   - Describe your strategy to solve the task and how you test your code to make sure it works as expected.
 *   - Implement the function in any programming language that you feel comfortable to work
 *   - Write tests to cover your code, make sure that the weighted random condition is satisfied and all edge cases are
 *   checked properly.
 *   - Your code should be production ready, well organised and well tested.
 *
 *     private int randomSelection(ad[] ad_list) {
 *         int weight_sum = 0;
 *         // Add up all the weights for all the items in the list
 *         for (ad a : ad_list){
 *             weight_sum += a.ad_weight;
 *         }
 *         if (weight_sum == 0) {
 *             weight_sum = ad_list.length;
 *         }
 *
 *         // Pick a number at random between 1 and the sum of the weights
 *         Random rand = new Random();
 *         int randomVal = rand.nextInt(weight_sum)+1;
 *         System.out.println("Random number is " + randomVal);
 *
 *         // Iterate over the items
 *         for (ad a : ad_list){
 *             // For the current item, subtract the item’s weight from the random number that was originally picked
 *             randomVal -= a.ad_weight;
 *             if (randomVal <= 0) {
 *                 return a.ad_id;
 *             }
 *         }
 *         return 0;
 *     }
 *     @Test
 *     public void Test1() {
 *         int noOfMessage = 120;
 *         int noOfSelect_0 = 0, noOfSelect_1 = 0, noOfSelect_2 = 0, incorrect = 0;
 *
 *         for (int i=0; i<= noOfMessage; i++){
 *             int result = randomSelection(ad_list1);
 *             if (result == 0) {
 *                 noOfSelect_0 += 1;
 *             }
 *             else if (result == 1) {
 *                 noOfSelect_1 += 1;
 *             }
 *             else if (result == 2) {
 *                 noOfSelect_2 += 1;
 *             }
 *             else {
 *                 incorrect += 1;
 *             }
 *         }
 *
 *         System.out.println("Number select of 0 is " + noOfSelect_0);
 *         System.out.println("Number select of 1 is " + noOfSelect_1);
 *         System.out.println("Number select of 2 is " + noOfSelect_2);
 *         System.out.println("Incorrect response " + incorrect);
 *     }
 */

package tests.java;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Random;

public class ATest {
    ad[][] testList = {
            {new ad(0,0)},
            {new ad(0,0),  new ad(1,0)},
            {new ad(0,2)},
            {new ad(0,2),  new ad(1,2)},
            {new ad(0,0),  new ad(1,2),  new ad(2,2)},
            {new ad(0,50), new ad(1,30), new ad(2,60)}
    };

    @Test
    public void basicTest(){
        int count = 1;
        for (ad[] a : testList){
            System.out.println("Test array " + count);
            HashMap<Integer, Integer> result = verify(a,1400);
            for (HashMap.Entry<Integer, Integer> entry : result.entrySet()) {
                System.out.println("ID " + entry.getKey() + " has seclected " + entry.getValue() + " times");
            }
            count += 1;
            System.out.println("-----------------");
        }

    }

    public HashMap verify(ad[] ad_list, int NoOfMessage){
        HashMap<Integer, Integer> result = new HashMap<>();
        int weightSum = 0;
        for (ad a : ad_list){
            result.put(a.ad_id, 0);
            weightSum += a.ad_weight;
        }

        if(weightSum > 0){
            for(int i=0; i < NoOfMessage; i++){
                // Pick a number at random between 1 and the sum of the weights
                Random rand = new Random();
                int randomVal = rand.nextInt(weightSum)+1;
                //System.out.println("Random number is " + randomVal);

                for (ad a : ad_list){
                    randomVal -= a.ad_weight;
                    if (randomVal <= 0) {
                        int temp = result.get(a.ad_id);
                        //System.out.println("Temp of " + a.ad_id + " is " + temp);
                        result.put(a.ad_id,temp + 1);
                        break;
                    }
                }
            }
        }
        return result;
    }
}

class ad {
    int ad_id;
    int ad_weight;

    public ad(int ad_id, int ad_weight){
        this.ad_id = ad_id;
        this.ad_weight = ad_weight;
    }
}
