package tcss450.uw.edu.phishapp;

import tcss450.uw.edu.phishapp.model.Credentials;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity
        implements RegisterFragment.OnRegisterFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener {

    private static final String TAG = "MAIN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (findViewById(R.id.frame_main_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_main_container, new LoginFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onRegisterSuccess(Credentials credentials) {

        Bundle args = new Bundle();
        args.putSerializable(getString(R.string.credential_key), credentials);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtras(args);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginSuccess(Credentials credentials, String jwt) {
        Bundle args = new Bundle();
        args.putSerializable(getString(R.string.credential_key), credentials);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtras(args);
        intent.putExtra(getString(R.string.keys_intent_jwt), jwt);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterClicked() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, registerFragment)
                .addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onWaitFragmentInteractionShow() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_main_container, new WaitFragment(), "WAIT")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onWaitFragmentInteractionHide() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentByTag("WAIT"))
                .commit();
    }
}
