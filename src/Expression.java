import java.util.Map;
import java.util.List;

/**
 * The Expression interface is used to represent a logical expression.
 */
public interface Expression {
    /**
     * @param assignment a mapping the specifies the value of each variable
     * @return the evaluated expression using the variable values provided in the assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * @return the evaluated expression using
     * @throws Exception If the expression contains variables
     */
    Boolean evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression
     */
    String toString();

    /**
     * @param var a variable
     * @param expression a given expression
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    Expression assign(String var, Expression expression);

    /**
     * @return the expression tree resulting from converting
     * all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * @return the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     */
    Expression norify();

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}
