

public class BaseFunction extends Function {
	
	private String var;
	
	public BaseFunction (String var) {
		this.var = var;
	}
	
	public String toString () {
		return var;
	}
	
	public Function Derivative() {
		return new ConstantFunction ("1");
	}
}