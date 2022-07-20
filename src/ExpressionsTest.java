import java.util.Map;
import java.util.TreeMap;

/**
 * The ExpressionsTest class tests the other classes in this assigment.
 */
public class ExpressionsTest {
    /**
     * This method creates some nested expressions  and then prints them,
     * evaluates them, and asks for the variables in them.
     * @param args command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Create an expression
        Expression ex = new Xor(new And(new Var("y"), new Var("y")),
                                new Nand(new Xnor(new Var("x"), new Var("y")),
                                        new Not(new Var("z"))));

        //Print the expression
        System.out.println(ex.toString());

        //Print the value of the expression with an assignment to every variable
        Map<String, Boolean> assign = new TreeMap<>();
        assign.put("x", true);
        assign.put("y", false);
        assign.put("z", true);
        System.out.println(ex.evaluate(assign));

        //Print the Nandified version of the expression
        System.out.println(ex.nandify().toString());

        //Print the Norified version of the expression
        System.out.println(ex.norify().toString());

        //Print the simplified version of the expression
        System.out.println(ex.simplify().toString());
    }
}
