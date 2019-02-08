package tcss450.uw.edu.phishapp;

import android.content.Context;
import tcss450.uw.edu.phishapp.model.Credentials;
import tcss450.uw.edu.phishapp.model.Credentials.Builder;
import tcss450.uw.edu.phishapp.utils.SendPostAsyncTask;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnRegisterFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    private OnRegisterFragmentInteractionListener mListener;
    private Credentials mCredentials;

    private static final String TAG = "Register";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        Button button_register_register = (Button) v.findViewById(R.id.button_register_register);

        button_register_register.setOnClickListener(this::onClick);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        attemptRegister(v);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRegisterFragmentInteractionListener extends WaitFragment.OnFragmentInteractionListener{
        // TODO: Update argument type and name
        void onRegisterSuccess(Credentials credentials);
    }

    private void handleErrorsInTask(String result) {
        Log.e("ASYNC_TASK_ERROR",  result);
    }

    private void handleRegisterOnPre() {
        mListener.onWaitFragmentInteractionShow();
    }

    private void handleRegisterOnPost(String result) {
        try {
            JSONObject resultsJSON = new JSONObject(result);
            Log.wtf("Register", result);
            boolean success =
                    resultsJSON.getBoolean(
                            getString(R.string.keys_json_register_success));

            if (success) {
                //Register was successful. Switch to the loadLoginFragment.
                mListener.onRegisterSuccess(mCredentials);
                return;
            } else {
                //Register was unsuccessful. Don’t switch fragments and
                // inform the user
                ((TextView) getView().findViewById(R.id.editText_register_email))
                        .setError("Register Unsuccessful");
            }
            mListener.onWaitFragmentInteractionHide();
        } catch (JSONException e) {
            //It appears that the web service did not return a JSON formatted
            //String or it did not have what we expected in it.
            Log.e("JSON_PARSE_ERROR",  result
                    + System.lineSeparator()
                    + e.getMessage());

            mListener.onWaitFragmentInteractionHide();
            ((TextView) getView().findViewById(R.id.editText_register_email))
                    .setError("Register Unsuccessful");
        }
    }



    private void attemptRegister(final View theButton) {

        EditText emailEdit = getActivity().findViewById(R.id.editText_register_email);
        EditText passwordEdit = getActivity().findViewById(R.id.editText_register_password);
        EditText retypePWEdit = getActivity().findViewById(R.id.editText_register_retypePW);
        EditText firstNEdit = getActivity().findViewById(R.id.editText_register_firstN);
        EditText lastNEdit = getActivity().findViewById(R.id.editText_register_lastN);
        EditText userNEdit = getActivity().findViewById(R.id.editText_register_username);


        boolean hasError = false;
        if (emailEdit.getText().length() == 0) {
            hasError = true;
            emailEdit.setError("Field must not be empty.");
        }  else if (emailEdit.getText().toString().chars().filter(ch -> ch == '@').count() != 1) {
            hasError = true;
            emailEdit.setError("Field must contain a valid email address.");
        }
        if (passwordEdit.getText().length() == 0) {
            hasError = true;
            passwordEdit.setError("Field must not be empty.");
        }
        if (retypePWEdit.getText().length() == 0) {
            hasError = true;
            retypePWEdit.setError("Field must not be empty.");
        }
        if (!retypePWEdit.getText().toString().equals(passwordEdit.getText().toString())) {
            hasError = true;
            retypePWEdit.setError("Password doesn't match");
        }

        if (firstNEdit.getText().length() == 0) {
            hasError = true;
            retypePWEdit.setError("Field must not be empty.");
        }
        if (lastNEdit.getText().length() == 0) {
            hasError = true;
            retypePWEdit.setError("Field must not be empty.");
        }
        if (userNEdit.getText().length() == 0) {
            hasError = true;
            retypePWEdit.setError("Field must not be empty.");
        }
        if (!hasError) {
            Credentials credentials = new Credentials.Builder(
                    userNEdit.getText().toString(),
                    passwordEdit.getText().toString(),
                    firstNEdit.getText().toString(),
                    lastNEdit.getText().toString(),
                    emailEdit.getText().toString())
                    .build();

            //build the web service URL
            Uri uri = new Uri.Builder()
                    .scheme("https")
                    .appendPath(getString(R.string.ep_base_url))
                    .appendPath(getString(R.string.ep_register))
                    .build();

            //build the JSONObject
            JSONObject msg = credentials.asJSONObject();

            mCredentials = credentials;

            //instantiate and execute the AsyncTask.
            new SendPostAsyncTask.Builder(uri.toString(), msg)
                    .onPreExecute(this::handleRegisterOnPre)
                    .onPostExecute(this::handleRegisterOnPost)
                    .onCancelled(this::handleErrorsInTask)
                    .build().execute();
        }
    }
}
