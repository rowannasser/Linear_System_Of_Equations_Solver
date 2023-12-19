package NonLinearSystem;
public class function
{
  private expression function_exp;
  private NonLinearSystem.variable_table variables;

  public function(String expression)
  {
    function_exp = new expression(expression);
    this.variables = this.function_exp.get_variables_table();
  }

  public NonLinearSystem.variable_table get_variables_table()
  {
    return variables;
  }

  public Float get_value(NonLinearSystem.variable_table table)
  {
    return function_exp.evaluate(table);
  }

}
