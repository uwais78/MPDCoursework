package labstuff.gcu.me.org.mdassesment;

/**
 * Created by Uwais on 25/03/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoadworksClass extends AppCompatActivity {

    private List<Traffic> roadworksList;
    private List<Traffic> selectedList;

    private ArrayAdapter<Traffic> roadworksAdapter;
    private ArrayAdapter<Traffic> selectedAdapter;

    private Button getRoadworkBtn;

    private EditText getNameTxt;
    private ListView roadworksListview;
    private ListView selectedListView;
    Date itemSelect;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roadworks_layout);
        //Keyboard is hidden
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //Assigning the variables that will be used as the access variables for each
        //tool value
        getRoadworkBtn = (Button) findViewById(R.id.getRoadworkBtn);
        getRoadworkBtn.setInputType(0);
        getNameTxt = (EditText) findViewById(R.id.enterNameTxt);
        roadworksListview = (ListView) findViewById(R.id.roadworksListView);
        selectedListView = (ListView) findViewById(R.id.selectedListView);
        //The results list is set as invisible to be hidden until the user has searched
        selectedListView.setVisibility(View.INVISIBLE);
        //This function gives you the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        roadworksList = (ArrayList<Traffic>)getIntent().getSerializableExtra("roadworksList");
        selectedList = new ArrayList<Traffic>();

        roadworksAdapter = new trafficArrayAdapter(RoadworksClass.this, 0, roadworksList);
        roadworksListview.setAdapter(roadworksAdapter);

        roadworksListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {

                Traffic traffic = roadworksList.get(position);

                Intent intent = new Intent(RoadworksClass.this, DetailsClass.class);
                intent.putExtra("title", traffic.getTitle());
                intent.putExtra("description", traffic.getDescription());
                intent.putExtra("link", traffic.getLink());
                intent.putExtra("georss", traffic.getGeorss());
                intent.putExtra("author", traffic.getAuthor());
                intent.putExtra("comments", traffic.getComments());
                intent.putExtra("pubDate", traffic.getPubDate());
                startActivity(intent);
            }
        });
        //This is the view which displays the results of the seatched items
        selectedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {
                //The values are loaded as a traffic instance
                Traffic traffic = selectedList.get(position);
                //The intent is used to place values on UI
                Intent intent = new Intent(RoadworksClass.this, DetailsClass.class);
                //Each value is placed inside of the UI
                //The name is displayed and then the value is retrieved
                intent.putExtra("title", traffic.getTitle());
                intent.putExtra("description", traffic.getDescription());
                intent.putExtra("link", traffic.getLink());
                intent.putExtra("georss", traffic.getGeorss());
                intent.putExtra("author", traffic.getAuthor());
                intent.putExtra("comments", traffic.getComments());
                intent.putExtra("pubDate", traffic.getPubDate());
                startActivity(intent);
            }
        });
        //When the user selects search on the roadworks page this is the function that is run
        getRoadworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try  {
                    //The virtual keyboard is closed
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                //An array is made to make the list of the returned items
                selectedList = new ArrayList<Traffic>();
                //The text input is retrieved
                String getTxtInput = getNameTxt.getText().toString();
                getRoadworksWithName(getTxtInput);
                //The search returns are made
                if (selectedList.size() > 0) {
                    selectedAdapter = new trafficArrayAdapter(RoadworksClass.this, 0, selectedList);
                    //The returns are made visible
                    selectedListView.setAdapter(selectedAdapter);
                    selectedListView.setVisibility(View.VISIBLE);
                    roadworksListview.setVisibility(View.INVISIBLE);
                }
                else
                {
                    //Error message is displayed and roadworks list is kept visible
                    displayErrorMessage();
                    selectedListView.setVisibility(View.INVISIBLE);
                    roadworksListview.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayErrorMessage() {
        Toast.makeText(RoadworksClass.this,
                "No entries found!",
                Toast.LENGTH_LONG).show();
    }

    public void getRoadworksWithName(String searchTerm) {
        for (Traffic t : roadworksList) {
            String search = t.getTitle();
            if (search.toLowerCase().indexOf(searchTerm.toLowerCase()) != -1) {
                selectedList.add(t);
            }
        }
    }
}