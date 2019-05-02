
public class Parse {
	
    public static boolean isValid (String c) {
		return Character.isLetterOrDigit(c.charAt(0)) || "\\()".contains(c);
    }

    public static boolean isInteger (String c) {
    		for (int i = 0; i < c.length(); i++) {
    			if (!Character.isDigit(c.charAt(i))) {
    				return false;
    			}
    		}
    		return true;
    }
    
    public static BinaryFunction binaryValidate (BinaryFunction f) {
    		if (f.getFunc1() == null || f.getFunc2 () == null) {
    			return null;
    		}
    		return f;
    }
    
    public static UnaryFunction unaryValidate (UnaryFunction f) {
    		if (f.getFunc() == null) {
    			return null;
    		}
    		return f;
    }
    
	public static Function parse (String input, String var) {
			
		if (input.length() == 0) {
			System.out.println("Invalid operation sequence");
			return null;
		}
		
		int layer = 0;
		boolean tagged = false;
		String tag = "";
		int bracketLayer = 0;
		for (int pass = 0; pass < 4; pass ++) {
			layer = 0;
			tagged = false;
			tag = "";
			Function func = null;
			String c = "";
			bracketLayer = 0;
			for (int i = 0; i < input.length(); i++) {
				c = input.substring(i,i+1);
				if (layer == 0) {
					switch(pass) {
					case 0: // Checks for any addition or subtraction
						if (!tagged) {
							if (c.equals("+")) {
								func = binaryValidate(new BinaryFunction(Operation.addition, parse(input.substring(0, i), var), parse(input.substring(i+1), var)));
							} else if (c.equals("-") && i != 0 && !"b+-*/".contains(input.substring(i-1,i))) {
								func = binaryValidate(new BinaryFunction(Operation.subtraction, parse(input.substring(0, i), var), parse(input.substring(i+1), var)));
							}
						}
						break;
					case 1: // Checks for any multiplication or division
						if (!tagged) {
							if (i != 0 && isValid(c) && isValid(input.substring(i-1,i)) && !(Character.isDigit(input.charAt(i)) && Character.isDigit(input.charAt(i-1)))) {
								func = binaryValidate(new BinaryFunction(Operation.multiplication, parse(input.substring(0,i), var), parse(input.substring(i), var)));
							} else if (c.equals("*")) {
								func = binaryValidate(new BinaryFunction(Operation.multiplication, parse(input.substring(0, i), var), parse(input.substring(i+1), var)));
							} else if (c.equals("/")) {
								func = binaryValidate(new BinaryFunction(Operation.division, parse(input.substring(0, i), var), parse(input.substring(i+1), var)));
							} else if (c.equals("-")) {
								func = binaryValidate(new BinaryFunction(Operation.multiplication, new ConstantFunction("-1"), parse(input.substring(i+1), var)));
							}
						}
						break;
					case 2: // Checks for any exponentiation
						if (c.equals("^") && !tagged) {
							return binaryValidate(new BinaryFunction(Operation.exponentiation, parse(input.substring(0,i), var), parse(input.substring(i+1), var)));
						}
						break;
					case 3: // Checks for any other functions
						if (tagged) {
							if (c.equals("[")) {
								bracketLayer++;
								tag += c;
							} else if (c.equals("]")) {
								bracketLayer--;
								if (bracketLayer < 0) {
									System.out.println("Invalid bracket sequence");
									return null;
								}
								tag += c;
							} else if (c.equals("(") && bracketLayer == 0) {
								tagged = false;
								Operation op = Function.getOperation(tag.toLowerCase());
								if (op != null) {
									if (op == Operation.log) {
										return binaryValidate(new BinaryFunction(Operation.log, parse(tag.substring(4, tag.length()-1), var), parse(input.substring(i), var)));
									} else if (op == Operation.root) {
										return binaryValidate(new BinaryFunction(Operation.root, parse(tag.substring(5, tag.length()-1), var), parse(input.substring(i), var)));
									} else {
										return unaryValidate(new UnaryFunction (op, parse(input.substring(i), var)));
									}
								} else {
									System.out.println ("Invalid tag \'" + tag + "\'");
									return null;
								}
							} else {
								tag += c;
							}
						}
						break;
					}
				}
				if (c.equals(")") && bracketLayer == 0) {
					layer--;
					if (layer < 0) {
						System.out.println("Invalid parenthesis sequence");
						return null;
					}
				} else if (c.equals("(") && bracketLayer == 0) {
					layer++;
					tagged = false;
				} else if (c.equals("\\") && !tagged) {
					tagged = true;
					tag = "";
				}
			}
			if(func != null) {
				return func;
			}
		}
		if (layer > 0) {
			System.out.println("Invalid parenthesis sequence");
			return null;
		} else if (input.substring(0,1).equals("(") && input.substring(input.length()-1,input.length()).equals(")")) {
			return parse (input.substring(1,input.length()-1), var);
		} else if (input.equals(var)) {
			return new BaseFunction(var);
		} else if (input.length() > 1 && isInteger(input)) {
			return new ConstantFunction(input);
		} else if (input.length() == 1 && Character.isLetterOrDigit(input.charAt(0))) {
			return new ConstantFunction(input);
		}

		if (bracketLayer > 0) {
			System.out.println("Invalid bracket sequence");
			return null;
		}

		System.out.println("Invalid operation sequence");
		return null;
	}
}
