package kitapp.hska.de.kitapp.adapter;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.R;
import kitapp.hska.de.kitapp.domain.Comment;

/**
 * Created by Yannick on 20.06.2015.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CommentsAdapter(Context context, int resource, List<Comment> items) {
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
        Comment comment = getItem(position);

        if (comment != null) {

            TextView textViewName = (TextView) view.findViewById(R.id.CommentName);
            TextView textViewDate = (TextView) view.findViewById(R.id.CommentDate);
            TextView textViewText = (TextView) view.findViewById(R.id.CommentText);

            if (textViewName != null) {
                textViewName.setText(comment.getName());
            }
            if (textViewDate != null) {
                textViewDate.setText(comment.getDate().toString());
            }
            if (textViewText != null) {
                textViewText.setText(comment.getText());
            }

        }
        return view;

    }

}