package ie.cm.fragments;

import ie.cm.activities.Base;
import ie.cm.activities.Edit;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CoffeeFragment  extends ListFragment implements  OnClickListener
{ 
  protected         Base                activity;
  protected static  CoffeeListAdapter 	listAdapter;
  protected         ListView 			listView;

  public CoffeeFragment() {
    // Required empty public constructor
  }

  public static CoffeeFragment newInstance() {
    CoffeeFragment fragment = new CoffeeFragment();
    return fragment;
  }

@Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    this.activity = (Base) context;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    listAdapter = new CoffeeListAdapter(activity, this, Base.coffeeList);
    setListAdapter (listAdapter);

  }
     
  @Override
  public void onStart()
  {
    super.onStart();
  }

  @Override
  public void onClick(View view) {
    if  (view.getTag() instanceof Coffee){
        onCoffDelete  ((Coffee) view.getTag());
    }
  }

  private void onCoffDelete(final Coffee coffee) {

    //getting the coffee name from the coffee object
    String stringName = coffee.name;

    //create AlertDialog.Builder object
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    //setting the message to show in alertDialog
    builder.setMessage("Are you sure you want to Delete the \'Coffee\' " +
            stringName + "?");
    //setCancelable will force the alertdialog to not get dismissed from outside touch events
    builder.setCancelable(false);
    //clicking on yes will remove the coffee object
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int id)
      {
        Base.coffeeList.remove(coffee); // remove from our list
        listAdapter.coffeeList.remove(coffee); // update adapters data
        listAdapter.notifyDataSetChanged(); // refresh adapter
      }
    }).setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int id)
      {
        dialog.cancel();
      }
    });
    AlertDialog alert = builder.create();
    alert.show();

  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);

    //creating a new bundle named coffee info to push to Edit.java class
    Bundle coffeeInfo = new Bundle();
    coffeeInfo.putLong("coffeeID",id+1);
    //intent pushes user from coffeeFragment to Edit.java
    Intent gotoEdit = new Intent(getActivity(), Edit.class);
    //adding the the position of the list view item to pass to edit.java
    //only the position is required and not the whole list
    // because we already have the list in Base.java which the Edit.java extends
    gotoEdit.putExtras(coffeeInfo);
    //start intent
   getActivity().startActivity(gotoEdit);
  }
}

  