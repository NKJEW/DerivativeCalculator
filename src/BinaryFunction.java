

public class BinaryFunction extends Function {
    private Operation operation;
    private Function func1;
    private Function func2;
    
    public BinaryFunction (Operation operation, Function func1, Function func2) {
        this.operation = operation;
        this.func1 = func1;
        this.func2 = func2;
    }
    
    public Operation getOperation () {
        return operation;
    }
    
    public Function getFunc1 () {
        return func1;
    }
    
    public Function getfunc2 () {
    	return func2;
    }
    
    public Function Derivative() {
        return null;
    }
}