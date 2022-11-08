import java.util.*;
public class SlidingWindow{

    static int sent = 0;
    static int totalTransmit = 0;
    static int wSize;
    static int fCount;

    static void slide(ArrayDeque<Integer> window, ArrayList<Integer> frames){
        window.removeFirst();
        if(sent+wSize < frames.size()) window.add(frames.get(sent+wSize));
    }

    static void transmit(ArrayDeque<Integer> window){
        for(int f : window){
            System.out.print("Frame "+f+" sent. ");
            totalTransmit++;
        }
        System.out.println();
    }

    static void acknowledge(ArrayDeque<Integer> window, ArrayList<Integer> frames){
        Random random = new Random();   
        int size = window.size();
        boolean lost = false;
        for(int i = 0; i < size && !lost; i++){
            if(random.nextBoolean()){
                System.out.println("Frame "+window.peekFirst()+" acknowledgement received");
                slide(window,frames);
                sent++;
            }else{
                System.out.println("Frame "+window.peekFirst()+" acknowledgement lost");
                lost = true;
                System.out.println("Further frames discared...");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter no of frames: ");
        fCount = scan.nextInt();

        System.out.print("Enter window Size: ");
        wSize = scan.nextInt();

        // Create and Add Frames List
        ArrayList<Integer> frames = new ArrayList<>();
        for(int i = 0; i < fCount; i++) frames.add(i);

        ArrayDeque<Integer> window = new ArrayDeque<>();

        // Add Initial frame in window
        for(int i = 0; i < wSize; i++){
            window.add(frames.get(i));
        }

        while(sent < fCount){
            System.out.println();

            // Send all franes in window
            transmit(window);

            // Check Acknowledgement and slides window
            acknowledge(window,frames);

        }

        System.out.println("Total Transmission: "+totalTransmit);

    }
}