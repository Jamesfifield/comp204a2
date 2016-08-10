package org.waikato.comp204;

import org.waikato.comp204.scrollback.GenericScrollback;
import org.waikato.comp204.scrollback.ScrollbackController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/***
 * Note: When capacity is filled the oldest item is removed.
 * Note: The scrollback uses a FixedList class for storing data that can be reusable.
 * Note: getLast() is idempotent. I added a method called getPrevious that provides desired behaviour.
 */
public class AssignmentTwo {
    public static void main(String[] args) throws IOException {

        //UI stuff. can move to other class / method.
        System.out.println("Welcome to the AssignmentTwo interactive console!\n" +
                "Type items followed by return to add them to the scrollback.\n" +
                "Special commands:\n" +
                "  .    - Retrieve and add the last command. Multiple periods will look back further in the scrollback\n" +
                "  show - Print details about the scrollback\n" +
                "  reset  - Reset the scrollback\n" +
                "  quit - Exit this console \n" +
                "Please enter a scrollback size (default = 10) ");

        //----

        //Used to except input from the user. Stream is closed on exit.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //---

        //Build scrollback using user input after validation
            //Make sure these values are valid before initialising them.
            int size;
            ScrollbackController<String> sbController;

            while(true){
                try{
                    size = Integer.parseInt(br.readLine());
                     sbController = new ScrollbackController(new GenericScrollback<String>(size));
                    break;
                }catch(Exception nfe){
                    System.err.println("Invalid Format!");
                }
            }
            System.out.println("Created Scrollback of size " + size);
        //-------


        //Remaining code excepts user input as a command and then matches in to a command on the scrollback controller.
        //The controller will then build and return the view witch will be displayed to the console using the render() method.
            while(true){
                String command = br.readLine();     //get next command
                //mapping code
                if(command.startsWith(".") && command.endsWith(".")){   // COMMAND = . (Previous item)
                    render(sbController.getPrevious(command).toString());
                }else{
                    switch (command.toUpperCase()){
                        case "SHOW" :
                            render(sbController.getScrollBackData());  //Returns a view (string) containg all items in the scrollback.
                            break;
                        case "RESET" :
                            render(sbController.clear());
                            break;
                        case "QUIT" :
                            render("Good bye");
                            br.close();
                            System.exit(0);
                            break;
                        default:
                            render(sbController.add(command));
                            break;
                    }
                }
            }
        //-------


    }

    public static void render(String view){
        System.out.println(view);
    }



}
