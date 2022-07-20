import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * THe Val class represents a logical value.
 */
public class Val implements Expression {
    private boolean val;

    /**
     * Constructor.
     * @param val a boolean
     */
    public Val(boolean val) {
        this.val = val;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return val;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return val;
    }

    @Override
    public List<String> getVariables() {
        return  new ArrayList<String>();
    }

    @Override
    public String toString() {
        if (this.val) {
            return "T";
        }
        return "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.val);
    }

    @Override
    public Expression nandify() {
        return new Val(this.val);
    }

    @Override
    public Expression norify() {
        return new Val(this.val);
    }

    @Override
    public Expression simplify() {
        return new Val(this.val);
    }
}
