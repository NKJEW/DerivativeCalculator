
public abstract class Function {
    public abstract Function Derivative();
    
    public static Operation getOperation (String op) {
		switch(op) {
		case "sin": case "sine": return Operation.sin;
		case "cos": case "cosine": return Operation.cos;
		case "tan": case "tangent": return Operation.tan;
		case "csc": case "cosecant": return Operation.csc;
		case "sec": case "secant": return Operation.sec;
		case "cot": case "cotangent": return Operation.cot;
		case "asin": case "asine": case "arcsin": case "arcsine": return Operation.asin;
		case "acos": case "acosine": case "arccos": case "arccosine": return Operation.acos;
		case "atan": case "atangent": case "arctan": case "arctangent": return Operation.atan;
		case "acsc": case "acosecant": case "arccsc": case "arccosecant": return Operation.acsc;
		case "asec": case "asecant": case "arcsec": case "arcsecant": return Operation.asec;
		case "acot": case "acotangent": case "arccot": case "arccotangent": return Operation.acot;
		case "log": return Operation.logbase10;
		case "root": case "sqrt": return Operation.sqrt;
		case "ln": return Operation.ln;
		case "abs": return Operation.abs;
		}
		if (op.length() > 3 && op.substring(0,4).equals("log[") && op.substring(op.length()-1, op.length()).equals("]")) {
			return Operation.log;
		}
		if (op.length() > 4 && op.substring(0,5).equals("root[") && op.substring(op.length()-1, op.length()).equals("]")) {
			return Operation.root;
		}
		return null;
    }
    
    public static String getSymbol (Operation op) {
    		switch(op) {
    		case addition: return "+";
    		case subtraction: return "-";
    		case multiplication: return "*";
    		case division: return "/";
    		case logbase10: return "log";
    		case exponentiation: return "^";
    	    default: return op.toString();
    		}
    }
}