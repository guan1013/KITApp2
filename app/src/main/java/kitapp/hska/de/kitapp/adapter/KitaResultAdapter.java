package kitapp.hska.de.kitapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import kitapp.hska.de.kitapp.R;
import kitapp.hska.de.kitapp.domain.Kita;

/**
 * Created by bwpc on 20.06.2015.
 */
public class KitaResultAdapter extends ArrayAdapter<Kita> {


    public KitaResultAdapter(Context context, int resource, List<Kita> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.kita_result_item_layout, null);
        }
        Kita kita = getItem(position);

        if (kita != null) {
            TextView textViewName = (TextView) view.findViewById(R.id.result_textview_name);
            RatingBar ratingBarEvaluation = (RatingBar) view.findViewById(R.id.result_ratingbar_rating);
            ImageView imageViewTN = (ImageView) view.findViewById(R.id.result_imageview_thumbnail);
            TextView textViewCircuit = (TextView) view.findViewById(R.id.result_textview_circuit);
            ImageButton buttonTel = (ImageButton) view.findViewById(R.id.result_imagebutton_tel);
            ImageButton buttonEmail = (ImageButton) view.findViewById(R.id.result_imagebutton_email);
            ImageButton buttonFav = (ImageButton) view.findViewById(R.id.result_imagebutton_fav);
            ImageButton buttonCont = (ImageButton) view.findViewById(R.id.result_imagebutton_cont);


            if (textViewName != null) {
                textViewName.setText(kita.getName());
            }

            if (ratingBarEvaluation != null) {
                ratingBarEvaluation.setRating(kita.getAvgRating().floatValue());
            }

            if (imageViewTN != null) {

            }

            if (textViewCircuit != null) {
                // TODO: CALCULATE DISTANCE
                String distance = "PLACEHOLDER";
                textViewCircuit.setText(distance + " km");
            }

            if (buttonTel != null) {
                final String phone = "tel:" + kita.getAddress().getPhone();
                buttonTel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                        dialIntent.setData(Uri.parse(phone));
                        getContext().startActivity(dialIntent);
                    }
                });


            }

            if (buttonEmail != null) {
                final String email = kita.getAddress().getEmail();
                buttonEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mailIntent = new Intent(Intent.ACTION_SEND);
                        mailIntent.setType("plain/text");
                        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                        getContext().startActivity(Intent.createChooser(mailIntent, ""));
                    }
                });

            }

            if (buttonFav != null) {

            }

            if (buttonCont != null) {

                final String email = kita.getAddress().getEmail();
                final String phone = kita.getAddress().getEmail();

                buttonCont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent contIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                        contIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                        contIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                                .putExtra(ContactsContract.Intents.Insert.PHONE, phone)
                                .putExtra("finishActivityOnSaveCompleted", true);

                        getContext().startActivity(contIntent);
                    }
                });

            }
        }
        return view;
    }

}