

public class BinaryFunction extends Function {
    private Operation operation;
    private Function func1;
    private Function func2;
    
    public BinaryFunction (Operation operation, Function func1, Function func2) {
        this.operation = operation;
        this.func1 = func1;
        this.func2 = func2;
    }
    
    public Function getFunc1 () {
    		return func1;
    }
    
    public Function getFunc2 () {
    		return func2;
    }
    
    public Operation getOperation () {
    		return operation;
    }
    
    public String toString() {
    		if (operation == Operation.log || operation == Operation.root) {
    			return getSymbol(operation) + "[" + func1 + "]" + "(" + func2 + ")";
    		} else {
    			return "(" + func1 + getSymbol(operation) + func2 + ")";
    		}
    }
    
    public Function Derivative() {
        return null;
    }
}