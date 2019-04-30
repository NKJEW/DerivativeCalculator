
public class UnaryFunction extends Function {
    private Operation operation;
    private Function func;
    
    public UnaryFunction(Operation operation, Function func) {
        this.operation = operation;
        this.func = func;
    }
    
    public Function Derivative() {
    	Function parentDerivative = null;
    	
        switch(operation) {
            case subtraction:
                parentDerivative = new ConstantFunction(-1);
                break;
            case sin:
                parentDerivative = new UnaryFunction(Operation.cos, func);
                break;
            case cos:
            	parentDerivative = new UnaryFunction(Operation.subtraction, new UnaryFunction(Operation.sin, func));
            	break;
            case tan:
            	parentDerivative = new BinaryFunction(Operation.exp, new BinaryFunction(Operation.quotient, new ConstantFunction(1), new UnaryFunction(Operation.cos, func)), new ConstantFunction(2));
            case arcsin:
            	return null;
        }
        
        return new BinaryFunction(Operation.product, parentDerivative, func.Derivative());
    }
}