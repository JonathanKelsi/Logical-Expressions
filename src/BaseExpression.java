/**
 * The BaseExpression represents an expression in its most abstract form.
 */
public abstract class BaseExpression implements Expression {
    private Expression e1;

    protected BaseExpression(Expression e1) {
        this.e1 = e1;
    }

    protected Expression getE1() {
        return e1;
    }
}
