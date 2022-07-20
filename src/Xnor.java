import java.util.Map;

/**
 * The Xnor class represents the logic gate XNOR.
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructor.
     * @param e1 an expression
     * @param e2 an expression
     */
    public Xnor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !((getE1().evaluate(assignment) && !getE2().evaluate(assignment))
                || (!getE1().evaluate(assignment) && getE2().evaluate(assignment)));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !((getE1().evaluate() && !getE2().evaluate())
                || (!getE1().evaluate() && getE2().evaluate()));
    }

    @Override
    public String toString() {
        return "(" + getE1().toString() + " # " + getE2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(getE1().assign(var, expression),  getE2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(getE1().nandify(), getE1().nandify()),
                                new Nand(getE2().nandify(), getE2().nandify())),
                        new Nand(getE1().nandify(), getE2().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getE1().norify(), new Nor(getE1().norify(), getE2().norify())),
                        new Nor(getE2().norify(), new Nor(getE1().norify(), getE2().norify())));
    }

    @Override
    public Expression simplify() {
        //the simplified expressions
        Expression simplifiedE1 = getE1().simplify();
        Expression simplifiedE2 = getE2().simplify();

        //if both of the simplified expressions have no variables
        if (simplifiedE1.getVariables().isEmpty() && simplifiedE2.getVariables().isEmpty()) {
            try {
                return new Val(new Xnor(simplifiedE1, simplifiedE2).evaluate());
            } catch (Exception e) {
                //Does nothing. This line will not be reached,
                //given both expressions do not have variables.
            }
        }

        if (getE1().toString().equals(getE2().toString())) {
            return new Val(true);
        }

        return new Xnor(simplifiedE1, simplifiedE2);
    }

}
