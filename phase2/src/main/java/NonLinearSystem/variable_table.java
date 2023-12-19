package NonLinearSystem;

import java.util.ArrayList;
import java.util.List;

public class variable_table
{
  public List<String> var_names ;
  public List<String> var_values ;
  public int count = 0;

  public variable_table()
  {
    var_names = new ArrayList<String>();
    var_values = new ArrayList<String>();
  }
  public void add_variable(String variable,String value)
  {
    for(int i=0;i<count;i++)
    {
      if(var_names.get(i).equals(variable))
      {
        var_values.set(i,value);
        return;
      }
    }
    var_names.add(variable);
    var_values.add(value);
    count++;
  }

  public boolean exist(String variable)
  {
    if(var_names.contains(variable))
      return true;
    return false;
  }

  public String valueOf(String variable)
  {
    for(int i=0;i<count;i++)
    {
      if(var_names.get(i).equals(variable))
        return var_values.get(i);
    }
    return null;
  }

}
