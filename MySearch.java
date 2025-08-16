public class MySearch {

    public static void linearSearch(int[] arr, int key) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("item found at index = " + i);
                found = true;
                break;
            }
        }     

        if (found == false){
            System.out.println("Item not found");
        }
    }

    public static void binarySearch(int[]arr, int key){
        int low = 0;
        int high = arr.length - 1;
        boolean found = false;

        while (low <=high){
            int mid = (low + high)/2;

            if (arr[mid] == key){
                System.out.println("item found at index = " + mid);
                found = true;
                break;
            }

            else if (arr[mid] < key){
                low = mid + 1;
            }

            else if (arr[mid] > key){
                high = mid - 1;
            }
        }

        if (found == false){
            System.out.println("Item not found");
        }
    }


    public static void main(String[] args) {
        System.out.println(("Searching..."));
        int[] num = {10};
        int keyToFind = 100;

        binarySearch(num, keyToFind);

 
    }
    
}
