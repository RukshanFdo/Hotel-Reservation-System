package arrays;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Hotel {

    private static Room hotelRooms[]= new Room[8];
    private static Room queue[] = new Room[8]; // waiting list
    private static int start = 0; // start person's number
    private static int end = 0; // last person's number

    public static void main(String[] args) {
        // create menu
        // handle multiple inputs
        System.out.println("\n\t\t***** WELCOME TO HOTEL WARNAKULA *****");

        Scanner input = new Scanner(System.in);
        menu:// help to break while loop
        while (true) {
            System.out.println("\n\n*** Menu ***");
            System.out.println("\tA ---> Add Customer to a Room");
            System.out.println("\tV ---> View All Rooms");
            System.out.println("\tE ---> Display Empty Rooms");
            System.out.println("\tD ---> Delete Customer From Room");
            System.out.println("\tF ---> Find Room From Customer Name");
            System.out.println("\tS ---> Store Program Data");
            System.out.println("\tL ---> Load Program Data");
            System.out.println("\tO ---> View Guests Ordered Alphabetically by Name");
            System.out.println("\tQ ---> Quit");
            System.out.print("\nPlease Enter the Option: ");
            // help to manage capital inputs and simple inputs
            String customerInput = input.next().toUpperCase();
            switch (customerInput) {
                case "A":
                    // access customer adding method
                    addsCustomerToRoom();
                    break;
                case "V":
                    // access customer viewing method
                    viewsAllRoom();
                    break;
                case "E":
                    //access customer show empty method
                    emptyRooms();
                    break;
                case "D":
                    //access customer deleting method
                    deleteCustomer();
                    break;
                case "F":
                    //access customer finding method
                    roomFromCustomerName();
                    break;
                case "S":
                    //access store all data into text file methods
                    storeProgram();
                    break;
                case "L":
                    //access load data and crete hotel rooms methods
                    loadProgram();
                    break;
                case "O":
                    //access customer sorting by alphabetically order methods
                    orderedAlphabetically();
                    break;
                case "Q":
                    //exit from program
                    System.out.println("\n\n\t\t***** Thank You! Have a Nice Day *****");
                    break menu;
                default:
                    // show error when user enters wrong inputs
                    System.out.println("\n\tSorry, Invalid Input. Please Check the Letter and Try Again.");
                    break;
            }
        }
    }

    public static void addsCustomerToRoom() {
        // get customer's data
        // add customer to hotel
        // show message if hotel is full
        // if hotel is full user program call waiting list method

        System.out.println("\n\n\t---Add Customer to a Room---");
        boolean available = false;

        // initialize all variables
        int roomNum = 0;
        String firstName = "";
        String surname = "";
        int cardNum = 0;
        int guest = 0;
        int avaCount = 0;

        for (Room room : hotelRooms){
            // check available room count
            if (room == null){
                avaCount++;// help to count available count
            }
            roomNum++;
        }
        System.out.println("Available Rooms "+ avaCount);
        if (avaCount > 0){
            // if hotel have available room this part runs
            Scanner scanner = new Scanner(System.in);
            while (true){


                System.out.print("\nEnter the Room Number (0-7) or 8 to stop: ");
                try {
                    // get room number from user
                    roomNum = Integer.parseInt(scanner.nextLine());
                    if (roomNum >= 0 && roomNum <= 8){
                        if(roomNum == 8){
                            // check users input
                            // if user need to exit this part runs
                            System.out.println("\n\tAdd Customer Option is Stopped");
                            break;
                        }
                        else {
                            // if user need to add customer this part runs
                            if (hotelRooms[roomNum] == null){
                                // if user entered room is empty this art runs
                                available = true;// change available to true
                                System.out.println("\n\tRoom is Available");
                                break;
                            }
                            else {
                                // if room unavailable this message display
                                System.out.println("\n\tRoom " + roomNum + " is Unavailable");
                            }
                        }
                    }
                    else {
                        // if user entered invalid number this message runs
                        System.out.println("\n\tSorry, Enter Valid Number and Try Again.");
                    }
                }
                catch (NumberFormatException e) {
                    // if any error comes within this adding part this error message display
                    System.out.println("\n\tSorry, Invalid Input. Try Again.");
                }
            }

            if (available){
                // if room available this part runs
                while (true) {
                    // get user's details
                    System.out.print("\nEnter Customer First Name: ");
                    firstName = scanner.nextLine().toUpperCase();

                    System.out.print("\nEnter Customer SurName: ");
                    surname = scanner.nextLine().toUpperCase();

                    try {
                        // check error in card number
                        System.out.print("\nEnter Customer Card Number: ");
                        cardNum = Integer.parseInt(scanner.nextLine());
                    }
                    catch (NumberFormatException e) {
                        // card number error message
                        System.out.println("\n\tSorry, Invalid Input. Try Again.");
                    }

                    try {
                        // check error in guest count
                        System.out.print("\nEnter Customer's guest counts: ");
                        guest = Integer.parseInt(scanner.nextLine());
                    }
                    catch (NumberFormatException e) {
                        // check error in guest count
                        System.out.println("\n\tSorry, Invalid Input. Try Again.");
                    }


                    if (!firstName.isEmpty()){
                        // check first name whether is empty or not
                        // if it not empty this parts runs
                        Room rsvRoom = new Room(roomNum, firstName, surname, cardNum, guest);// add all data to room class. callthe constructer and create a object and asign in to rsvRoom variable.
                        hotelRooms[roomNum] = rsvRoom;
                        System.out.println("\n\tRoom " + rsvRoom.getRoomNum() + " Reserved by " + rsvRoom.getCusName() + " " + rsvRoom.getSecondName());
                        break;
                    }
                    else {
                        // if name is empty this one runs
                        System.out.println("\n\tSorry, Invalid Input. Try Again.");
                    }
                }
            }
        }
        else {
            // if all rooms are full this part runs
            System.out.println("\n\tHotel is Fulled.");


        }
    }

    public static void viewsAllRoom () {
        // show all room details
        System.out.println("\n\n\t---View All Rooms---\n");

        int roomNum = 0;

        for (Room room : hotelRooms){
            if (room == null){
                // if any room is empty this one runs
                // show room as empty
                System.out.println("\tRoom " + roomNum + " is Empty");
            }
            else {
                // if any room is not empty this one runs
                // show room with user' details
                System.out.println("\tRoom " + room.getRoomNum() + "\tOccupied by Customer: " + room.getCusName()+" "+room.getSecondName()+" [ Card No."+room.getCardNum()+" | "+"Count of Guest: "+ room.getNumOfGuest()+" ]");
            }
            roomNum++;
        }
    }

    public static void emptyRooms () {
        // show all empty rooms
        System.out.println("\n\n\t---Display Empty Rooms---\n");

        int avaCount = 0;
        int roomNum = 0;

        for (Room room : hotelRooms){
            if (room == null){
                // if room is empty show it as empty
                System.out.println("\tRoom " + roomNum);
                avaCount++;
            }
            roomNum++;
        }

        if (avaCount == 0){
            // if no empty rooms this part runs
            System.out.println("\tNo Empty Rooms");
        }
    }

    private static void deleteCustomer () {
        // delete customer from hotel
        // go to get details from waiting list
        System.out.println("\n\n\t---Delete Customer From Room---");

        // initialize
        int roomNum = 0;
        int unAvaCount = 0;

        for (Room room : hotelRooms){
            // check all rooms with details
            if (room != null){
                unAvaCount++;
            }
        }

        if (unAvaCount > 0){
            // if hotel have rooms with uses this part runs
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("\nPlease Enter the room number (0-7) or 8 to stop: ");
                try {
                    // get room number
                    roomNum = Integer.parseInt(scanner.nextLine());
                    if (roomNum >= 0 && roomNum <= 8) {
                        if (roomNum != 8) {
                            if (hotelRooms[roomNum] != null) {
                                // check room is available to delete or not
                                // if room has details delete all details
                                System.out.println("\n\tRoom " + roomNum + " is Available Again");
                                hotelRooms[roomNum] = null;// deleting part

                            } else {
                                // if room have not data already this message display
                                System.out.println("\n\tRoom " + roomNum + " is Not Reserved");
                            }
                        }
                        else {
                            // if user need to exit from deleting part this one runs
                            System.out.println("\n\tDelete Customer Option is Stopped");
                        }
                        break;
                    } else {
                        // if user entered invalid input this part runs
                        System.out.println("\n\tSorry, Enter Valid Number and Try Again.");
                    }
                } catch (NumberFormatException e) {
                    // if any error comes within deleting part this error message displayed
                    System.out.println("\n\tSorry, Invalid Input. Try Again.");
                }
            }
        }
        else {
            //if all rooms are empty this part runs
            System.out.println("\n\tHotel is Empty");
        }
    }

    private static void roomFromCustomerName () {
        // find room from customer name
        // show customer details
        System.out.println("\n\n\t---Find Room From Customer Name---");

        // initialize
        String cusName = "";

        boolean roomsRsv = false;

        boolean foundRoom = false;

        for (Room room : hotelRooms){
            // check hotel have occupied room or not
            if (room != null){
                roomsRsv = true;
            }
        }

        if (roomsRsv){
            // if hotel have occupied rooms this part run
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("\nEnter Customer First Name or 8 to Stop: ");
                cusName = scanner.nextLine().toUpperCase();// get customer name

                if (!cusName.isEmpty()){

                    if (cusName.equals("8")){
                        // check user need to exit or not
                        // if user need to exit this part runs
                        System.out.println("\n\tFind Room Option is Stop");
                    }
                    else {
                        //if user need to delete this part runs
                        int roomNum = 0;

                        System.out.println("");
                        for (Room room : hotelRooms){
                            if (room != null) {
                                // find name
                                if (cusName.equals(room.getCusName())) {
                                    // if user found this part runs
                                    System.out.println("Room No." + roomNum+" Customer Name :"+cusName+ " "+room.getSecondName());
                                    foundRoom = true;
                                }
                            }
                            roomNum++;
                        }

                        if (!foundRoom){
                            // if user not found this part runs
                            System.out.println("\n\tNo Customer Found Under This Name " + cusName);
                        }
                    }
                    break;
                }
                else {
                    // if user entered invalid input this part runs
                    System.out.println("\n\tSorry, Invalid Input. Try Again");
                }
            }
        }
        else {
            // if all rooms are empty this part runs
            System.out.println("\n\tHotel is Empty");
        }
    }

    public static void storeProgram () {
        // get all data from array
        // store all data into txt file

        System.out.println("\n\n\t---Store Program Data---\n");
        try{
            Formatter StoreFile = new Formatter("src/arrays/Hotel_Reservations.txt");// get file

            StoreFile.format("%s", "Hotel Warnakula");// write first line
            int roomNum = 0;
            for (Room room : hotelRooms) {
                // write other lines
                if (room == null) {
                    // if empty room found this one write
                    StoreFile.format("\nRoom %s\tCustomer Name: %s\tSurname: %s\tCard: %s\tGuest: %s", roomNum, "Empty","Empty","Empty","Empty");
                }
                else {
                    // if found details this one writes
                    StoreFile.format("\nRoom %s\tCustomer Name: %s\tSurname: %s\tCard: %s\tGuest: %s",roomNum,room.getCusName(),room.getSecondName(),room.getCardNum(),room.getNumOfGuest());
                }
                roomNum++;
            }

            StoreFile.close();// close object

            System.out.println("\n\tSuccessfully Stored to Hotel_Reservations File");
        }
        catch(Exception e){
            // error happened within storing part this part runs
            System.out.println("\n\tStoring Error");
        }
    }

    public static void loadProgram () {
        // load data from file
        // fill all details into rooms

        System.out.println("\n\n\t---Load Program Data---\n");
        try{
            Scanner loadFile = new Scanner(new File("src/arrays/Hotel_Reservations.txt"));// get file

            ArrayList<String> loadData = new ArrayList<>();// for get all data
            while(loadFile.hasNext()){
                // load all data from file
                String data = loadFile.next();
                loadData.add(data);
            }

            //initialize

            ArrayList<Integer> strRoomIndexList = new ArrayList<Integer>();
            ArrayList<Integer> strNameIndexList = new ArrayList<Integer>();
            ArrayList<Integer> strsurNameIndexList = new ArrayList<Integer>();
            ArrayList<Integer> strcardIndexList = new ArrayList<Integer>();
            ArrayList<Integer> strguestIndexList = new ArrayList<Integer>();


            int index = 0;
            for (String item : loadData){
                if (item.equals("Room")){
                    // get al room numbers indexes
                    strRoomIndexList.add(index);
                }
                else if (item.equals("Name:")){
                    // get all names indexes
                    strNameIndexList.add(index);
                }
                else if (item.equals("Surname:")){
                    // get all surname indexes
                    strsurNameIndexList.add(index);
                }
                else if (item.equals("Card:")){
                    // get all card details indexes
                    strcardIndexList.add(index);
                }
                else if (item.equals("Guest:")){
                    // get guest counts indexes
                    strguestIndexList.add(index);
                }
                index++;
            }

            strRoomIndexList.remove(0);// help to remove first line

            int count = 0;

            for (int strNameIndex : strNameIndexList){
                //initialize
                String firstName = "";
                String surname = "";
                String strCard = "";
                int card = 0;
                String strGuest = "";
                int guest = 0;

                for (int i = strNameIndex+1; i < strsurNameIndexList.get(count); i++) {

                    firstName = firstName + loadData.get(i).toUpperCase() ;// get first names
                }

                if (firstName.equals("EMPTY")) {
                    // if first name is equals to empty this part runs
                    hotelRooms[count] = null;// make room as empty
                }
                else {
                    for (int j = strsurNameIndexList.get(count) + 1; j < strcardIndexList.get(count); j++) {
                        surname = surname + loadData.get(j).toUpperCase();// get surname
                    }

                    for (int k = strcardIndexList.get(count) + 1; k < strguestIndexList.get(count); k++) {
                        strCard = strCard + loadData.get(k).toUpperCase();// get card
                    }

                    try{
                        // convert to integer
                        card = Integer.parseInt(strCard);
                    }
                    catch (NumberFormatException e){

                    }

                    if (count == strRoomIndexList.size()) {
                        for (int l = strguestIndexList.get(count) + 1; l < loadData.size(); l++) {
                            strGuest = strGuest + loadData.get(l).toUpperCase();// get guest count data as string
                        }
                    }
                    else {
                        for (int m = strguestIndexList.get(count) + 1; m < strRoomIndexList.get(count); m++) {
                            strGuest = strGuest + loadData.get(m).toUpperCase();// get guest count data as string
                        }
                    }

                    try{
                        guest = Integer.parseInt(strGuest);// convert  as integer
                    }
                    catch (NumberFormatException e){

                    }

                    Room room = new Room(count, firstName, surname, card, guest);// add all data to room
                    hotelRooms[count] = room;// adding part
                }
                count++;
            }

            loadFile.close();// close object

            System.out.println("\n\tSuccessfully Load from Hotel_Reservations File");
        }
        catch(Exception e){
            // if error comes this part runs
            System.out.println("\tLoading Error");
        }
    }

    private static void orderedAlphabetically (){

        // get details and sort all data by customer's name


        ArrayList<String> sortingList = new ArrayList<String>();

        for (Room itemx : hotelRooms){
            if (itemx != null){
                // get all room details
                // added data to list
                sortingList.add(itemx.getCusName().replace("_"," ")+" "+itemx.getSecondName().replace("_"," "));
            }
        }


        int arrayListSize = sortingList.size();// get array length
        for (int traversal = 1; traversal <= arrayListSize; traversal++) {
            for (int leftIndex = 0; leftIndex < arrayListSize - 1; leftIndex++) {
                int rightIndex = leftIndex + 1;
                if (sortingList.get(rightIndex).compareTo(sortingList.get(leftIndex)) < 0) {
                    // if right index is grater than left index this part runs

                    // shifting part
                    String rightStringInSortingList = sortingList.get(rightIndex);

                    sortingList.remove(rightIndex);

                    sortingList.add(leftIndex, rightStringInSortingList);
                }
            }
            for (int backwardsRightIndex = arrayListSize - 1; backwardsRightIndex > 0; backwardsRightIndex--) {
                int backwardsLeftIndex = backwardsRightIndex - 1;
                if (sortingList.get(backwardsRightIndex).compareTo(sortingList.get(backwardsRightIndex - 1)) < 0) {
                    // check two data and find biggest name

                    //shifting part
                    String backwardsRightStringInSortingList = sortingList.get(backwardsRightIndex);

                    sortingList.remove(backwardsRightIndex);

                    sortingList.add(backwardsLeftIndex, backwardsRightStringInSortingList);
                }
            }
        }
        System.out.println("\n\n\t---Alphabetically Name Order---\n");
        for (int index = 0; index < arrayListSize; index++) {
            // show all sorted names
            System.out.println(sortingList.get(index));
        }


    }


    public static class Room {
        //subclass for handle user's data
        //initilize
        private int roomNum;
        private String firstName;
        private String secondName;
        private int cardNum;
        private int numOfGuest;

        public Room(int roomNum, String firstName, String secondName, int cardNum, int numOfGuest) {
            //cunstractor
            this.roomNum = roomNum;
            this.firstName = firstName;
            this.secondName = secondName;
            this.cardNum = cardNum;
            this.numOfGuest = numOfGuest;
        }

        public int getRoomNum(){
            return roomNum;
        }//getter for roomNum

        public String getSecondName() {
            return secondName;
        }//getter for secondNum

        public int getCardNum() {
            return cardNum;
        }//getter for cardNum

        public int getNumOfGuest() {
            return numOfGuest;
        }//getter for numberOfGuest

        public String getCusName(){
            return firstName;
        }//getter for cusName
    }

}




