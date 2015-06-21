package kitapp.hska.de.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.NewsAdapter;
import kitapp.hska.de.kitapp.domain.News;

public class NewsActivity extends AppCompatActivity {

    private final static String NEWS_BUNDLE_KEY = "news";


    private ListView newsListView;

    private List<News> getNewsList() {

        Bundle bundle = this.getIntent().getExtras();

        List<News> news = new ArrayList<>();
        if (bundle != null) {
            try {
                news = (ArrayList<News>) bundle.get(NEWS_BUNDLE_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return news;
    }

    private void initViews() {

        newsListView = (ListView) findViewById(R.id.news_ListView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();

        newsListView = (ListView) findViewById(R.id.news_ListView);

        final List<News> news = getNewsList();

        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.news_item_layout, news);

        newsListView.setAdapter(newsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
