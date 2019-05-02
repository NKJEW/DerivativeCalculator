
public class ConstantFunction extends Function {
    private String constant;
    
    public ConstantFunction(String constant) {
        this.constant = constant;
    }
    
    public Function Derivative () {
    		return new ConstantFunction ("0");
    }
    
    public String toString() {
    		return constant;
    }
}