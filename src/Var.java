import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Var class represents a variable.
 */
public class Var implements Expression {
    private String var;

    /**
     * Constructor.
     * @param var a variable
     */
    public Var(String var) {
        this.var = new String(var);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (!assignment.containsKey(var)) {
            throw new Exception("Variable" + var + "not found.");
        }

        return assignment.get(var);
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Variable" + var + "not found.");
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new ArrayList<String>();
        l.add(var);
        return l;
    }

    @Override
    public String toString() {
        if (var == null) {
            return null;
        }
        return var;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        }

        return this;
    }

    @Override
    public Expression nandify() {
        return new Var(this.var);
    }

    @Override
    public Expression norify() {
        return new Var(this.var);
    }

    @Override
    public Expression simplify() {
        return new Var(this.var);
    }
}
