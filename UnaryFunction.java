
public class UnaryFunction extends Function {
    private Operation operation;
    private Function func;
    
    public UnaryFunction(Operation operation, Function func) {
        this.operation = operation;
        this.func = func;
    }
    
    public String toString () {
    		return getSymbol (operation) + "(" + func + ")";
    }
    
    public Function getFunc () {
    		return func;
    }
    
    public Operation getOperation () {
		return operation;
    }
    
    public Function Derivative() {
        switch(operation) {
            case subtraction:
                return new UnaryFunction(Operation.subtraction, func.Derivative());
            case sin:
                return new BinaryFunction(Operation.multiplication, new UnaryFunction(Operation.cos, func), func.Derivative());
            case cos:
                return new BinaryFunction(Operation.multiplication, new UnaryFunction(Operation.subtraction, new UnaryFunction(Operation.sin, func)), func.Derivative());
            case tan:
                return new BinaryFunction(Operation.multiplication, new BinaryFunction(Operation.exponentiation, new UnaryFunction(Operation.cos, func), new ConstantFunction("2")), func.Derivative());
            default:
            		return new ConstantFunction("0");
        }
    }
}