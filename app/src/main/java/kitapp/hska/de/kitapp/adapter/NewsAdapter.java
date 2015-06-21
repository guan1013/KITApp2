package kitapp.hska.de.kitapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kitapp.hska.de.kitapp.R;
import kitapp.hska.de.kitapp.domain.News;

/**
 * Created by Yannick on 21.06.2015.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public NewsAdapter(Context context, int resource, List<News> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.news_item_layout, null);
        }
        News news = getItem(position);

        if (news != null) {

            TextView textViewName = (TextView) view.findViewById(R.id.newsAuthor);
            TextView textViewText = (TextView) view.findViewById(R.id.newsText);

            if (textViewName != null) {
                textViewName.setText(news.getAuthor().getName());
            }

            if (textViewText != null) {
                textViewText.setText(news.getText());
            }

        }
        return view;

    }

}
