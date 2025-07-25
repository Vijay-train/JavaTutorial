import java.util.ArrayList;

public class MyArrayList {
    // homework
    // 1. create an arraylist of integers, add some elements, print the list, insert at some index
    //.   remove an element, clear the list

    // 2. Investigate if an ArrayList can be created with a 2d arraylist

    /*
    3. Practice: Your Music Playlist!
        It should present a main menu with 4 options to the user 
            Enter 1 to Add songs to the playlist.
            Enter 2 to See the current number of songs.
            Enter 3  to Play a specific song .
            Enter 4 to Remove a song from the playlist if you don't like it anymore
            Enter 5 to exit the program
        If they entered 1, then it should present the below
            Enter name of song 
            Then it should add the song to the arraylist and then print “Song Added”
            Print the main menu again
        If they entered 2,
            Then it should print all songs
            Print the main menu again
        If they entered 3
            Then it should ask the name of the song as input
            Then it should print that song, if that exists in the arraylist
            Otherwise, it should print “Song not found”
            Print the main menu again
        If they entered 4
            Then it should ask the name of the song to be removed
            If the song is there in the arraylist, it will remove and then print “Song successfully removed”
            If the song is not found, it will print “Song not found”
            Print the main menu again

    */


    public static void PrintArrayList(){
        // Declaration and Initialization
        ArrayList<String> studentNames = new ArrayList<>();
       


        studentNames.add("Aanya");
        studentNames.add("Akshaya");
        studentNames.add("Ameena");
        studentNames.add("Tofu");

        System.out.println("Printing our students = " + studentNames);

        // replace
        studentNames.set(1,"NewAkshaya");
        System.out.println("*New Printing our students = " + studentNames);

        // remove
        studentNames.remove("Tofu");
        System.out.println("*After remove Printing our students = " + studentNames);

        //insert
        studentNames.add(1,"vijay");
        System.out.println("*After remove Printing our students = " + studentNames);

        // size
        System.out.println("THe size of the arraylist = " + studentNames.size());

        // search for an element
        System.out.println("Does this list contain vijay = " 
        + studentNames.contains("vijay"));

        System.out.println(("Does this list contain foo = "
         + studentNames.contains(("foo"))));

        // check if the list is empty?
        System.out.println("Is my list empty? = "
        + studentNames.isEmpty());

       

        // print all elements
        for (int i =0 ; i < studentNames.size(); i++){
            System.out.println(studentNames.get(i));
        }
        System.out.println("=====");
        for (String item:studentNames){
            System.out.println(item);
        }


    }
    public static void main(String[] args){
        PrintArrayList();
    }
}
