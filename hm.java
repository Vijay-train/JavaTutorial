import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class hm {

    public static void printDupes(int[] nums){
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for (int i = 0; i < nums.length;i++){
            if (!uniqueNumbers.add(nums[i])){
                System.out.println(nums[i]);
            }
        }


    }

    public static void TwoSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (set.contains(complement)) {
                System.out.println(nums[i] + " and " + complement);
                return;
            }
            set.add(nums[i]);
        }
        
        System.out.println("No pair found");
    }
    
    public static void main(String[] args){
        /*
        HashMap<String, String> stateCapitals = new HashMap<>();

        stateCapitals.put("Karnataka","Bengluru");
        stateCapitals.put("UttarPradesh","Lucknow");
        stateCapitals.put("Mizoram","aizawl");
        stateCapitals.put("A&N","Sri Vijaya Puram");
        stateCapitals.put("AndhraPradesh","Amravati");


        // iterate a hashmap
        // keys
        for( String state:stateCapitals.keySet() ){
            System.out.println("State: " + state +" Capital is " + stateCapitals.get(state));
        }

        // values
        System.out.println("Calling .values()");
        for (String capital:stateCapitals.values()){
            System.out.println(capital);
        }

        // key+value
        System.out.println("Calling both");
        for (Map.Entry<String, String> entry : stateCapitals.entrySet()) {
            System.out.println("State = " + entry.getKey() + " Capital = " + entry.getValue());
        }    
        
        // 1 create a hashset
        HashSet<String>  rollNos = new HashSet<>();

        boolean ret = false;

        // add elements in hashset
        ret= rollNos.add("123");
        System.out.println(ret);

        rollNos.add("456");
        rollNos.add("abc");

        ret = rollNos.add("abc");
        System.out.println(ret);

        System.out.println(rollNos);

        // check if an element exists or not
        boolean isPresent = rollNos.contains("12323");

        System.out.println(isPresent);

        rollNos.remove("abc");
        System.out.println(rollNos);
        */

        int[] numbers = {2,7,11,15};
        TwoSum(numbers,9);

    }
}
