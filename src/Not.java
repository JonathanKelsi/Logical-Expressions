import java.util.List;
import java.util.Map;

/**
 * The Not class represents the logic gate NOT.
 */
public class Not extends UnaryExpression {
    /**
     * Constructor.
     * @param e an expression
     */
    public Not(Expression e) {
        super(e);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !getE1().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !getE1().evaluate();
    }

    @Override
    public List<String> getVariables() {
        return getE1().getVariables();
    }

    @Override
    public String toString() {
        return "~(" + getE1().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getE1().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(getE1().nandify(), getE1().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getE1().norify(), getE1().norify());
    }

    @Override
    public Expression simplify() {
        //the simplified expressions
        Expression simplifiedE1 = getE1().simplify();

        //if both of the simplified expressions have no variables
        if (simplifiedE1.getVariables().isEmpty()) {
            try {
                return new Val(new Not(simplifiedE1).evaluate());
            } catch (Exception e) {
                //Does nothing. This line will not be reached,
                //given both expressions do not have variables.
            }
        }

        return new Not(simplifiedE1);
    }
}
