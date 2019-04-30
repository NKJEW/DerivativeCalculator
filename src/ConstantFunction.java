
public class ConstantFunction extends Function {
    private int constant;
    
    public ConstantFunction(int constant) {
        this.constant = constant;
    }
    
    public int getConstant () {
        return this.constant;
    }
    
    public Function Derivative () {
            return new ConstantFunction (0);
    }
}