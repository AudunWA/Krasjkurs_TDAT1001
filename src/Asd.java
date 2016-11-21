/**
 * Created by Audun on 21.11.2016.
 */
public class Asd {
    public static void main(String[] args) {
        int[] tabell = {1,3,5,7,4,2,6,9};
        for(int i=0; i < tabell.length; i++){
            if (i >= 4 && i <= 6) System.out.print(tabell[i]);
        }

        for(int i=0; i < tabell.length; i++){
            if (tabell[i] % 2 == 0) System.out.print(tabell[i]);
        }

        for(int i=0; i < tabell.length; i++){
            if (tabell[i] == 4 || tabell[i] == 2 || tabell[i] == 6) System.out.print(tabell[i]);
        }
    }
}
