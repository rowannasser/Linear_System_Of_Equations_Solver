package NonLinearSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class expression
{
  private String expression;
  private String post_fix_with_vars;
  private String post_fix_expression;
  private Float result;
  private NonLinearSystem.variable_table variables_table = new NonLinearSystem.variable_table();
  private Map<Integer,String> local_map = new HashMap<Integer,String>();
  private Stack<String> post_fix_stack = new Stack<String>();

  public expression()
  {}

  public expression(String exp)
  {
    String t="";
    for(int i=0;i<exp.length();i++)
    {
      if(exp.charAt(i)==' ')
        continue;
      t += exp.charAt(i);
    }

    exp = t;
    t="";
    for(int i=0;i<exp.length();i++)
    {
      if(exp.charAt(i)=='-')
      {
        if( i-1 <0 || operator(exp.charAt(i-1))!= 0)
        {
          t += Float.toString(0f);
        }
      }
        t += exp.charAt(i);
    }
    System.out.println("Simplified expression is "+t);
    this.expression = t;
    this.post_fix();
  }

  private boolean isFloat(String str)
  {
    try
    {
      float f = Float.parseFloat(str);
      return true;
    }
    catch(Exception e)
    {
      return false;
    }
  }

  private Integer operator(Character c)
  {
    Character[] operators = {'+','-','*','/','^','(',')','$',' '};
    Integer[] precedence ={1,1,2,2,3,-1,-1,-2,-4};
    for(int i=0;i<operators.length;i++)
      if(c == operators[i])
        return precedence[i];
    return 0;
  }

  private String parse_expression(String exp)
  {
    String intermediate_string="";
    for(int i=0;i<exp.length();i++)
    {
      String temp = "";
      while(i<exp.length() && operator(exp.charAt(i))==0 )
      {
        temp += exp.charAt(i++);
      }
      if(temp != "")
      {
          try
          {
            float f = Float.parseFloat(temp);
          }
          catch(Exception e)
          {
              variables_table.add_variable(temp,"t");
          }
          local_map.put(intermediate_string.length(),temp);
          intermediate_string += "n";
      }
      if( i<exp.length() && exp.charAt(i)!='$')
        intermediate_string += exp.charAt(i);
    }
    return intermediate_string;
  }

  public Float evaluate(NonLinearSystem.variable_table values)
  {

    this.post_fix_expression = this.post_fix_with_vars;
    for(int i=0;i<values.count;i++)
    {
      this.replace(values.var_names.get(i),Float.parseFloat(values.var_values.get(i)));
    }
    Stack<String> good_stack = replace_on_stack(values);
    String parsed_post_fix = parse_expression(this.post_fix_expression);
    Stack<Float> local_stack = new Stack<Float>();

    while(!good_stack.empty())
    {
      if(isFloat(good_stack.peek()))
      {
       local_stack.push(Float.parseFloat(good_stack.pop()));
      }
      else
      {
        Float n2 = local_stack.pop();
        Float n1 = local_stack.pop();
        Float ans=0f;
        switch(good_stack.pop())
        {
          case "+":
                    ans = n1+n2;
                    break;
          case "-":
                    ans = n1-n2;
                    break;
          case "*":
                    ans = n1*n2;
                    break;
          case "/":
                    ans = n1/n2;
                    break;
          case "^":
                    ans = (float) Math.pow(n1,n2);
                    break;
        }
        local_stack.push(ans);
      }
    }
    this.result = local_stack.pop();
    return this.result;
  }

  public void post_fix()
  {
    Stack<Character> local_stack = new Stack<Character>();
    String intermediate_string = parse_expression(expression);
    String post_fix_expression="";
    for(int i=0;i<intermediate_string.length();i++)
    {
      if(intermediate_string.charAt(i)=='n')
      {
       post_fix_stack.push(local_map.get(i));
        post_fix_expression += local_map.get(i)+"$";
      }
      else
      {
        if(intermediate_string.charAt(i)=='(')
        {
          local_stack.push('(');
          continue;
        }
        if(!local_stack.empty())
        {
          if(intermediate_string.charAt(i)==')')
          {
            while(local_stack.peek()!='(')
            {
              post_fix_stack.push(Character.toString(local_stack.peek()));
              post_fix_expression += local_stack.pop();
            }
            local_stack.pop();
            continue;
          }
          while(!local_stack.empty() && (operator(local_stack.peek()) >= operator(intermediate_string.charAt(i))))
          {
            post_fix_stack.push(Character.toString(local_stack.peek()));
            post_fix_expression += local_stack.pop();
          }
          local_stack.push(intermediate_string.charAt(i));
        }
        else
          local_stack.push(intermediate_string.charAt(i));
      }
    }
    while(!local_stack.empty())
    {
        post_fix_stack.push(Character.toString(local_stack.peek()));
        post_fix_expression += local_stack.pop();
    }
    this.post_fix_with_vars = post_fix_expression;
  }

  public NonLinearSystem.variable_table get_variables_table()
  {
    return this.variables_table;
  }

  public void replace(String var,float val)
  {
    int index = post_fix_expression.indexOf(var);
    while(index >= 0)
    {
      if(((index+var.length()) >= post_fix_expression.length() || operator(post_fix_expression.charAt(index+var.length()))!=0) && (
      (index-1)<0 || operator(post_fix_expression.charAt(index-1))!=0))
      {
        String str_val = Float.toString(val);
        String new_exp = post_fix_expression.substring(0,index) + str_val + post_fix_expression.substring(index+var.length(),post_fix_expression.length());
        post_fix_expression = new_exp;
      }
      index = post_fix_expression.indexOf(var,index+1);
    }
  }

  public Stack<String> replace_on_stack(NonLinearSystem.variable_table values)
  {
    Stack<String> s1 = new Stack<String>();
    Stack<String> s2 = new Stack<String>();
    s1.addAll(post_fix_stack);
    while(!s1.empty())
    {
      String value = s1.pop();
      if(values.exist(value))
      {
        s2.push(values.valueOf(value));
      }
      else
        s2.push(value);
    }
    return s2;
  }

  public String get_expression()
  {
    return this.expression;
  }
}
