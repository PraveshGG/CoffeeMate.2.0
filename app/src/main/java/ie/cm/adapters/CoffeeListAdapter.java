package ie.cm.adapters;

import ie.cm.models.Coffee;
import ie.cm.R;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CoffeeListAdapter extends ArrayAdapter<Coffee> 
{
  private Context context;
  private OnClickListener deleteListener;
  public List<Coffee> coffeeList;

  public CoffeeListAdapter(Context context, OnClickListener deleteListener, List<Coffee> coffeeList)
  {
    super(context, R.layout.coffeerow, coffeeList);
    this.context = context;
    this.deleteListener = deleteListener;
    this.coffeeList = coffeeList;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    CoffeeItem coffeeItem = new CoffeeItem(context, parent,deleteListener,coffeeList.get(position));
    return coffeeItem.view;
  }

  @Override
  public int getCount()
  {
    return coffeeList.size();
  }
  
  @Override
  public Coffee getItem(int position)
  {
	  return coffeeList.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public int getPosition(Coffee c)
  {
    return coffeeList.indexOf(c);
  }
}
