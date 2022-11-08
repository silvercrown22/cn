import java.util.*;
public class HammingCode{

    static HashSet<Integer> checkBits = new HashSet<>();

    static int[] strtoIntArr(String s){
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for(char c : s.toCharArray()){
            if(c!=' '){
                arr.add(c-'0');
            }
        }

        int[] iArr = new int[arr.size()];
        int k = 0;
        for(int a : arr){
            iArr[k++] = a;
        }

        return iArr;
    }


    static int[] getTransmitArray(int[] bits){
        
        // Calculate Redundant bit

        int r = 0;
        while(Math.pow(2,r) < bits.length + r + 1) r++;


        // Calculate Check bit position in arr

        for(int i = 0 ; i < r ; i++){
            checkBits.add((int)Math.pow(2,i)-1);
        }


        // Create array array with redundant bits
        int[] transmitArr = new int[bits.length+r+1];


        // Fill array with redundent bit
        int k = bits.length - 1;
        for(int i = 0; i < transmitArr.length-1; i++){
            if(!checkBits.contains(i)) transmitArr[i] = bits[k--];
        }

        // Calculate parity value for checkBits

        for(int checkBit : checkBits){
            int temp = 0;
            int i = checkBit;
            while(i < transmitArr.length-1){
                for(int j = 0; j <= checkBit && i < transmitArr.length-1; j++, i++) if(!checkBits.contains(i)) temp += transmitArr[i];
                for(int j = 0; j <= checkBit && i < transmitArr.length-1; j++, i++) {}
            }

            transmitArr[checkBit] = temp%2==0 ? 0 : 1;
        }


        // Fill overall parity for double bit check

        int temp = 0;
        for(int i = 0; i < transmitArr.length-1; i++){
            temp += transmitArr[i];
        }

        transmitArr[transmitArr.length-1] = temp%2==0 ? 0 : 1;
    

        // Return transmit array

        return transmitArr;
    }


    static boolean isParityError(int[] bits){
        int temp = 0;
        for(int i = 0; i < bits.length; i++){
            temp += bits[i];
        }

        return temp%2==0 ? false : true;
    }

    static int getErrorBit(int[] transmitArr){

        StringBuilder s  = new StringBuilder();

        for(int checkBit : checkBits){
            int temp = 0;
            int i = checkBit;
            while(i < transmitArr.length-1){
                for(int j = 0; j <= checkBit && i < transmitArr.length-1; j++, i++) if(!checkBits.contains(i) || i == checkBit) temp += transmitArr[i];
                for(int j = 0; j <= checkBit && i < transmitArr.length-1; j++, i++) {}
            }

            s.insert(0,temp%2==0 ? "0" : "1");
        }

        return Integer.parseInt(s.toString(),2);
    }

    static void fixErrorBit(int[] bits,int errorBit){
        bits[errorBit-1] = bits[errorBit-1] == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Get Bits from user

        System.out.print("Enter bits: ");   
        String sBits = scan.nextLine();


        // Convert String to int array bit

        int[] bits = strtoIntArr(sBits);


        // Create Transimission Array
        int[] transmitArr = getTransmitArray(bits);


        // Display Transmit array
        System.out.println("Sending data "+Arrays.toString(transmitArr)+"...");


        // Get Received Bits from user

        System.out.print("Enter Received bits: ");   
        sBits = scan.nextLine();


        // Convert String to int array bit

        bits = strtoIntArr(sBits);


        // Check overall parity
        
        boolean p = isParityError(bits);


        // Get Error bit or No Error bit '0'

        int errorBit = getErrorBit(bits);


        if(!p && errorBit == 0) System.out.println("No Error");
        else if(!p && errorBit != 0) System.out.println("Double Bit error");
        else{
            System.out.println("Error in bit position: "+errorBit);
            fixErrorBit(bits,errorBit);
            System.out.println("Corrected bits: "+ Arrays.toString(bits));
        }
    }
}