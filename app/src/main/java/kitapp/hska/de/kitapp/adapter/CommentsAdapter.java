package kitapp.hska.de.kitapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kitapp.hska.de.kitapp.R;
import kitapp.hska.de.kitapp.domain.Evaluation;

/**
 * Created by Yannick on 20.06.2015.
 */
public class CommentsAdapter extends ArrayAdapter<Evaluation> {

    public CommentsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CommentsAdapter(Context context, int resource, List<Evaluation> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.comment_item_layout, null);
        }
        Evaluation evaluation = getItem(position);

        if (evaluation != null) {

            TextView textViewName = (TextView) view.findViewById(R.id.CommentName);
            TextView textViewDate = (TextView) view.findViewById(R.id.CommentDate);
            TextView textViewText = (TextView) view.findViewById(R.id.CommentText);

            if (textViewName != null) {
                textViewName.setText(evaluation.getAuthor().getName());
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = "07/06/2013";

            Date date = null;

            try {

                date = formatter.parse(dateInString);
                System.out.println(date);
                System.out.println(formatter.format(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (textViewDate != null) {
                textViewDate.setText(formatter.format(date));
            }
            if (textViewText != null) {
                textViewText.setText(evaluation.getText());
            }

        }
        return view;

    }

}