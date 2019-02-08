package tcss450.uw.edu.phishapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.blog.BlogPost;
import tcss450.uw.edu.phishapp.setlist.SetList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetPostFragment extends Fragment {


    public SetPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_post, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();;
        if (getArguments() != null) {
            SetList setList = (SetList) getArguments().get(getString(R.string.setPost_key));
            updateSetPostView(setList);
        }
    }


    public void updateSetPostView(SetList setList) {
        TextView textView_fragment_setpost_longdate = getActivity().findViewById(R.id.textView_fragment_setpost_longdate);
        TextView textView_fragment_setpost_location = getActivity().findViewById(R.id.textView_fragment_setpost_location);
        TextView textView_fragment_setpost_Data = getActivity().findViewById(R.id.textView_fragment_setpost_Data);
        Button button_fragment_setpost_fullpost = getActivity().findViewById(R.id.button_fragment_setpost_fullpost);

        textView_fragment_setpost_longdate.setText(setList.getLongDate());
        textView_fragment_setpost_location.setText(setList.getLocatoin());
        textView_fragment_setpost_Data.setText(Html.fromHtml(setList.getData()));

        button_fragment_setpost_fullpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(setList.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
