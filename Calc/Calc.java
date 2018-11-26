package calc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brendan Morgenstern
 */
public class Calc {
    private Stack ops;
    private final String expression;
    private static int priority(char op){
        switch(op){
            case '(':
                return 0;
            case '^':
                return 5;
            case '*':
                return 4;
            case '/':
                return 3;
            case '+':
                return 2;
            case '-':
                return 1;
            default:
                return -1;
        }
    }
  
    public Calc(String ex){
        expression = ex;
    }
    
    public String getExpression(){
        return expression;
    }
    public String replaceBrackets(){
        String ex = getExpression();
        ex = ex.replace("{", "(");
        ex = ex.replace("[", "(");
        ex = ex.replace("}", ")");
        ex = ex.replace("]", ")");
        return ex;
    }
    public boolean IsBalanced(){
        ops = new Stack(256);
        for(int iter = 0; iter < expression.length(); iter++){
            char curr = expression.charAt(iter);
            if(curr == '{' || curr == '[' || curr == '(')
                ops.push(curr);
            else if(curr == '}' && ops.peek().equals('{'))
                ops.pop();
            else if(curr == ']' && ops.peek().equals('['))
                ops.pop();
            else if(curr == ')' && ops.peek().equals('('))
                ops.pop();
        }
        return ops.empty();
    }
    
    public static double Compute(double a, double b, char op){
        switch(op){
            case '^':
                return Math.pow(a,b);
            case '*':
                return a*b;
            case '/':
                return a/b;
            case '+':
                return a+b;
            case '-':
                return a-b;
            default:
                return a;
        }
    }
    
    public String PostFixS(){
        String retstr="";
        List<String> pfe = PostFix();
        for(String s : pfe){
            if(s.matches("[0-9.A-Za-z^*/+-]+"));
                retstr += s;
        }
        return String.format("The postfix expression for the given expression is %s", retstr);
    }
    
    public List<String> PostFix(){
        StringBuilder postfix = new StringBuilder();
        List<String> ls = new ArrayList<String>();
        String exp = replaceBrackets();
        for(int iter = 0; iter < exp.length(); iter++){
            char c = exp.charAt(iter);
            if(String.valueOf(c).matches("[0-9.A-Za-z]+"))
                postfix.append(c);
            else if(c == ')'){
                if(!postfix.toString().equals(""))
                    ls.add(postfix.toString());
                postfix = new StringBuilder();
                while(!ops.peek().equals('('))
                    ls.add(ops.pop().toString());
                    //postfix.append(ops.pop().toString());
                ops.pop();
            }
            else{
                if(!postfix.toString().equals(""))
                    ls.add(postfix.toString());
                postfix = new StringBuilder();
                int pri = priority(c);
                if(pri == -1)
                    continue;
                while(!ops.empty() && pri !=0 && pri < priority(ops.peek().toString().charAt(0)))
                    ls.add(ops.pop().toString());
                    //postfix.append(ops.pop().toString());
                ops.push(c);
            }
        }
        if(!postfix.toString().equals(""))
            ls.add(postfix.toString());
        while(!ops.empty())
            ls.add(ops.pop().toString());
            //postfix.append(ops.pop().toString());
        return ls;
    }
    
    public String Evaluate(){
        List<String> postfix = PostFix();
        String retstr = "";
        for(int i = 0; i < postfix.size(); i++){
            String repstr;
            if(priority(postfix.get(i).charAt(0)) > 0){
                System.out.println(String.format("%s%s%s", postfix.get(i-2), postfix.get(i), postfix.get(i-1)));
                try{
                    double b = Double.parseDouble(postfix.get(i-1));
                    double a = Double.parseDouble(postfix.get(i-2));
                    double result = Compute(a,b,postfix.get(i).charAt(0));
                    repstr = String.valueOf(result);
                }
                catch(IndexOutOfBoundsException ie){
                    break; 
                }
                catch(Exception e){
                    repstr = String.format("%s%s%s", postfix.get(i-2), postfix.get(i), postfix.get(i-1));
                }
                postfix.remove(i-1);postfix.remove(i-2);
                postfix.set(i-2, repstr);
                i=-1;
            }
        }
        for(String s : postfix)
            retstr += s;
        return retstr;
    }
}
