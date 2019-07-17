package ie.cm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ie.cm.R;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.models.Coffee;

public class Home extends Base {

    TextView recentList;
    CoffeeListAdapter coffeeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                openInfoDialog(Home.this);
                            }
                        }).show();
            }
        });
    }

    public void add(View v)
    {
        goToActivity(this,Add.class,null);
    }

    @Override
    protected void onResume() {
        super.onResume();


        coffeeFragment = CoffeeFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction().add(R.id.fragment_layout,
                coffeeFragment).commit();

//        if(!coffeeList.isEmpty())
//            recentList.setText(coffeeList.toString());
//        else
//            recentList.setText(getString(R.string.recentlyViewedListEmptyMessage));
    }

    public void setupCoffees(){
        coffeeList.add(new Coffee("Standard Black", "Some Shop",2.5,1.99,false));
        coffeeList.add(new Coffee("Regular Joe", "Joe's Place",3.5,2.99,true));
        coffeeList.add(new Coffee("Espresso", "Ardkeen Stores",4.5,1.49,true));
    }

}
