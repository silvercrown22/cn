import java.util.*;
public class CRC{

    static void add(ArrayList<Integer> dividend, ArrayList<Integer> remainder){
        int k = dividend.size() - remainder.size();

        for(int i = 0; i < remainder.size(); i++){
            dividend.set(k++,remainder.get(i));
        }
    }


    static void xorDivison(ArrayList<Integer> curr, ArrayList<Integer> divisor){

        for(int i = 0; i < curr.size(); i++){
            if(curr.get(i)==divisor.get(i)){
                curr.set(i,0);
            }else{
                curr.set(i,1);
            }
        }

        while(curr.size()>0&&curr.get(0)!=1) curr.remove(0);

    }

    static ArrayList<Integer> divison(ArrayList<Integer> dividend, ArrayList<Integer> divisor){

        ArrayList<Integer> curr = new ArrayList<>();

        int i = 0;

        while(i<dividend.size()){

            // Fill 
            while(i<dividend.size() && curr.size()!=divisor.size()) {
                if(curr.isEmpty() && dividend.get(i)==0) i++;
                else curr.add(dividend.get(i++));
            }

            // XOR division of curr
            if(curr.size()==divisor.size()) xorDivison(curr,divisor);

        }

        return new ArrayList<Integer>(curr);
    }

    static ArrayList<Integer> strToList(String str){
        ArrayList<Integer> list = new ArrayList<>();
        
        for(char c : str.toCharArray()){
            if(c!=' ') list.add(c-'0');
        }

        return list;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Input Bits from user
        System.out.print("Enter Data Bits: ");
        String sBits = scan.nextLine();

        // Input Divisor from user
        System.out.print("Enter Divisor: ");
        String sDivisor = scan.nextLine();

        // Convert String to int arrayList
        ArrayList<Integer> bits = strToList(sBits);
        ArrayList<Integer> divisor = strToList(sDivisor);

        // Add divisor.length 0 bits
        for(int i = 0; i < divisor.size()-1; i++) bits.add(0);

        // Dividing
        ArrayList<Integer> remainder = divison(bits, divisor);

        // Adding Remainder to bits
        add(bits,remainder);

        // Display Sending Data
        System.out.println("Sending Data " + bits +"...");

        // Input Bits from user
        System.out.print("Enter Received Data Bits: ");
        sBits = scan.nextLine();

        // Convert String to int arrayList
        bits = strToList(sBits);

        // Dividing
        remainder = divison(bits, divisor);

        if(remainder.size()==0) System.out.println("No Error.");
        else System.out.println("Error!");
            
    }
}

