package labstuff.gcu.me.org.mdassesment;

/**
 * Created by Uwais on 25/03/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsClass extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        //Displays the back button on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Each element is loaded into its own variable
        TextView detailTitle = (TextView) findViewById(R.id.title);
        TextView detailDesc = (TextView) findViewById(R.id.description);
        TextView linkToTS = (TextView) findViewById(R.id.link);
        TextView georssDesc = (TextView) findViewById(R.id.georss);
        TextView authorDesc = (TextView) findViewById(R.id.author);
        TextView commentsDesc = (TextView) findViewById(R.id.comments);
        TextView pubDateDesc = (TextView) findViewById(R.id.pubDate);


        //Intent is used to populate layout
        Intent intent = getIntent();
        //Each intent is given its own variable
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String link = intent.getStringExtra("link");
        String georss = intent.getStringExtra("georss");
        String author = intent.getStringExtra("author");
        String comments = intent.getStringExtra("comments");
        String pubDate = intent.getStringExtra("pubDate");

        detailTitle.setText(title);
        detailDesc.setText(description);
        linkToTS.setText("Link: " + link);
        georssDesc.setText("Georss: " + georss);
        authorDesc.setText("Author: " + author);
        commentsDesc.setText("Comments: " + comments);
        pubDateDesc.setText("Date Published: " + pubDate);
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