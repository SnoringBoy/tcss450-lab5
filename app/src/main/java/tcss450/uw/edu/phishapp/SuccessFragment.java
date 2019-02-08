package tcss450.uw.edu.phishapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment {


    public SuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Credentials credential = (Credentials) getActivity().getIntent().getSerializableExtra(getString(R.string.credential_key));
        updateTextView(credential);

    }

    public void updateTextView(Credentials credentials) {
        TextView textView_success_display = getActivity().findViewById(R.id.textView_success_display);
        textView_success_display.setText(credentials.getEmail());
    }
}
