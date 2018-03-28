package labstuff.gcu.me.org.mdassesment;

/**
 * Created by Uwais on 25/03/2018.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class IncidentsClass extends AppCompatActivity {

    //Current incidents URL
    private String curIncidentsUrl="https://trafficscotland.org/rss/feeds/currentincidents.aspx";

    private List<Traffic> resultsOfIncidents;
    private ArrayAdapter<Traffic> incidentAdapter;
    //Variable used to display the results of the incidents page
    private ListView incidentsResults;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incidents_layout);
        //Gets the view for the incidents list view
        incidentsResults = (ListView) findViewById(R.id.incidentsListView);
        resultsOfIncidents = (ArrayList<Traffic>)getIntent().getSerializableExtra("incidentsList");
        incidentAdapter = new trafficArrayAdapter(IncidentsClass.this, 0, resultsOfIncidents);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        incidentsResults.setAdapter(incidentAdapter);
        incidentsResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {
                //Creates an instance of
                Traffic traffic = resultsOfIncidents.get(position);

                Intent intent = new Intent(IncidentsClass.this, DetailsClass.class);
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
}

