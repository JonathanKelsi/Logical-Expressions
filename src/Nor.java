import java.util.Map;

/**
 * The And class represents the logic gate NOR.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     * @param e1 an expression
     * @param e2 an expression
     */
    public Nor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getE1().evaluate(assignment) || getE2().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(getE1().evaluate() || getE2().evaluate());
    }

    @Override
    public String toString() {
        return "(" + getE1().toString() + " V " + getE2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(getE1().assign(var, expression),  getE2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(getE1().nandify(), getE1().nandify()),
                                new Nand(getE2().nandify(), getE2().nandify())),
                        new Nand(new Nand(getE1().nandify(), getE1().nandify()),
                                new Nand(getE2().nandify(), getE2().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(getE1().norify(), getE2().norify());
    }

    @Override
    public Expression simplify() {
        //the simplified expressions
        Expression simplifiedE1 = getE1().simplify();
        Expression simplifiedE2 = getE2().simplify();

        //if both of the simplified expressions have no variables
        if (simplifiedE1.getVariables().isEmpty() && simplifiedE2.getVariables().isEmpty()) {
            try {
                return new Val(new Nor(simplifiedE1, simplifiedE2).evaluate());
            } catch (Exception e) {
                //Does nothing. This line will not be reached,
                //given both expressions do not have variables.
            }
        }

        //if one is true
        if (simplifiedE1.toString().equals("T") || simplifiedE2.toString().equals("T")) {
            return new Val(false);
        }

        //if one is false
        if (simplifiedE1.toString().equals("F")) {
            return new Not(simplifiedE2);
        }
        if (simplifiedE2.toString().equals("F")) {
            return new Not(simplifiedE1);
        }

        //if both are equal
        if (simplifiedE1.toString().equals(simplifiedE2.toString())) {
            return new Not(simplifiedE1);
        }

        return new Nor(simplifiedE1, simplifiedE2);
    }
}
