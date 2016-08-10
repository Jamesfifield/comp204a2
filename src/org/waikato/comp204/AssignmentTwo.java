package org.waikato.comp204;

import org.waikato.comp204.scrollback.GenericScrollback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * Note: When capacity is filled the oldest item is removed.
 * Note: The scrollback uses a FixedList class for storing data that can be reusable.
 */
public class AssignmentTwo {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the AssignmentTwo interactive console!\n");
        System.out.println("Type items followed by return to add them to the scrollback.\n" +
                "Special commands:\n" +
                "  .    - Retrieve and add the last command. Multiple periods will look back further in the scrollback\n" +
                "  show - Print details about the scrollback\n" +
                "  reset  - Reset the scrollback\n" +
                "  quit - Exit this console");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size;
        GenericScrollback scrollback;
        //scrollback scrollback;
        while(true){
            System.out.print("Please enter a scrollback size (default = 10) ");
            try{
                size = Integer.parseInt(br.readLine());
               // scrollback = new Scrollback(size);
                scrollback = new GenericScrollback<String>();
                break;
            }catch(NumberFormatException nfe){
                System.err.println("Invalid Format!");
            }

        }

        System.out.println("Created Scrollback of size " + size);

        while(true){
            String command = br.readLine();
            if(command.startsWith(".") && command.endsWith(".")){
                //Determines number of "." without using regex or other library.
                int count = command.length() - command.replace(".", "").length();
                String previousItem = (String)scrollback.getPrevious(scrollback.getCount() - count);
                scrollback.add(previousItem);
                System.out.println(previousItem);



            }else{

                switch (command.toUpperCase()){
                    case "SHOW" :
                        System.out.println("Scrollback capacity: " + scrollback.getCapacity());
                        System.out.println("Current elements: " + scrollback.getCount());
                        for (int i = 0; i < scrollback.getCount(); i++) {

                            System.out.println(scrollback.getPrevious(i));
                        }

                        break;
                    case "RESET" :
                        scrollback.clear();
                        break;
                    case "QUIT" :
                        br.close();
                        System.exit(0);
                        break;
                    default:
                        scrollback.add(command);
                        System.out.println("Ok.");

                        break;
                }
            }


        }





    }


}
