
import java.util.*;

public class DerivativeCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Expression: ");
        String _func = in.nextLine();
        _func = _func.replaceAll("\\s+", "");
        
        for (int i = 0; i < _func.length(); i++) {
	    		if(!Character.isLetterOrDigit(_func.charAt(i)) && !"+-*/\\()[]^".contains(_func.substring(i, i+1))) {
	    			System.out.println("Invalid character \'" + _func.substring(i, i+1) + "\' in expression");
	    			in.close();
	    			return;
	    		}
        }
        
        System.out.print("\nDifferentiate with respect to: ");
        String var = in.nextLine();
        
        if(var.length() != 1 || !Character.isLetter(var.charAt(0))) {
        		System.out.println("Invalid variable");
        		in.close();
        		return;
        }
        
        Function func = Parse.parse(_func, var);
        System.out.println("\nParsed result: " + func.toString());
        
        in.close();
    }
}