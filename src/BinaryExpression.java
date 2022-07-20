import java.util.List;

/**
 * The BinaryExpression class represents a binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression e2;

    /**
     * Constructor.
     * @param e1 an expression
     * @param e2 an expression
     */
    protected BinaryExpression(Expression e1, Expression e2) {
        super(e1);
        this.e2 = e2;
    }

    /**
     * E2 getter.
     * @return E2 field.
     */
    protected Expression getE2() {
        return e2;
    }

    @Override
    public List<String> getVariables() {
        List<String> l1 = getE1().getVariables();
        List<String> l2 = e2.getVariables();

        for (String s : l2) {
            if (!l1.contains(s)) {
                l1.add(s);
            }
        }
        return l1;
    }
}
