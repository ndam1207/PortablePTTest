package com.example.hihihahahehe.portablept.fragments;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.events.OnLoginEvent;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.example.hihihahahehe.portablept.utils.ScreenManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.toString();
    @BindView(R.id.login_button)
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private FaceBookModel faceBookModel;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setupUI(view);

        // Add code to print out the key hash
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.example.hihihahahehe.portablept",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        accessToken = AccessToken.getCurrentAccessToken();
        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("public_profile","email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration, check if not login before
        if(accessToken == null) {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    getInfoFacebook(loginResult);

                    ScreenManager.replaceFragment(getActivity().getSupportFragmentManager(), new RoleFragment(), R.id.layout_container_main, false);
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                    Toast.makeText(getContext(), "There is no connection !", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new FirstScreenFragment(),R.id.layout_container_main, false);
        }
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void getInfoFacebook(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                faceBookModel = new FaceBookModel();
                try {
                    faceBookModel.setId(object.getString("id"));
                    faceBookModel.setFirst_Name(object.getString("first_name"));
                    faceBookModel.setLast_Name(object.getString("last_name"));
                    faceBookModel.setGender(object.getString("gender"));
                    faceBookModel.setEmail(object.getString("email"));
                    faceBookModel.setImg(object.getJSONObject("picture").getJSONObject("data").getString("url"));

                    Log.d(TAG, faceBookModel.getImg());
                    //Add facebook info to Realm
                    RealmHandle.addData(faceBookModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,gender,email,picture.type(normal)");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
}
