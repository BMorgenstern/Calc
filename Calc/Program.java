package calc;

import javax.swing.JOptionPane;

/**
 *
 * @author Brendan Morgenstern
 */
public class Program {
    public static void main(String[] args) {
        // TODO code application logic here
        Calc calc = new Calc(JOptionPane.showInputDialog("Enter a mathematical expression"));
        try{
            if(!calc.IsBalanced())
                JOptionPane.showMessageDialog(null, "Expression is not balanced");
            else{
                System.out.println(calc.PostFixS());
                JOptionPane.showMessageDialog(null,calc.Evaluate());
            }
        }
        catch(NullPointerException e){ }
        
    }
}
