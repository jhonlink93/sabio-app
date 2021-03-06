package org.sabio.sabioapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.sabio.sabioapp.R;
import org.sabio.sabioapp.helpers.Utilities;
import org.sabio.sabioapp.presentation.presenter.AuthContract;
import org.sabio.sabioapp.presentation.presenter.LoginContract;
import org.sabio.sabioapp.presentation.presenter.LoginPresenter;
import org.sabio.sabioapp.presentation.presenter.SignUpContract;
import org.sabio.sabioapp.presentation.view.activity.AuthActivity;
import org.sabio.sabioapp.presentation.view.activity.MainActivity;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.UserActionListener mActionListener;
    private TextInputLayout tilEmailLogin;
    private TextInputLayout tilPasswordLogin;
    private Button btnSignIn;
    private TextView tvRecoverPassword;
    private TextView tvCreateAccount;
    private ProgressBar pbProgressBar;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mActionListener = new LoginPresenter(this);

        tilEmailLogin = view.findViewById(R.id.tilEmailLogin);
        tilPasswordLogin = view.findViewById(R.id.tilPasswordLogin);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        tvRecoverPassword = view.findViewById(R.id.tvRecoverPassword);
        tvCreateAccount = view.findViewById(R.id.tvCreateAccount);
        pbProgressBar = view.findViewById(R.id.pbProgressBar);

        btnSignIn.setOnClickListener(this);
        tvRecoverPassword.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);

        mActionListener.configure();

        return view;
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG);
    }

    @Override
    public void goToSignUpFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(SignUpFragment.getInstance(), true); //TODO: Registrar
    }

    @Override
    public void goToRecoverPassword() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(RecoverPasswordFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {
        Intent intentMainActivity = new Intent(getContext(), MainActivity.class);
        startActivity(intentMainActivity);
        getActivity().finish();
    }

    @Override
    public void showProgress() {
        pbProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePRogress() {
        pbProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRememberedUser(String email) {
        tilEmailLogin.getEditText().setText(email);
    }

    public void onLogin() {
        Boolean result = true;
        String email = tilEmailLogin.getEditText().getText().toString();
        String password = tilPasswordLogin.getEditText().getText().toString();

        if (Utilities.isEmpty(email)) {
            tilEmailLogin.setError("El email es requerido");
            tilEmailLogin.setErrorEnabled(true);
            result = false;
        } else {
            tilEmailLogin.setError(null);
            tilEmailLogin.setErrorEnabled(false);
        }

        if (Utilities.isEmpty(password)) {
            tilPasswordLogin.setError("La contraseña es requerida");
            tilPasswordLogin.setErrorEnabled(true);
            result = false;
        } else {
            tilPasswordLogin.setError(null);
            tilPasswordLogin.setErrorEnabled(false);
        }

        if (result) {
            mActionListener.onLogin(email, password, true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                onLogin();
                break;
            case R.id.tvRecoverPassword:
                goToRecoverPassword();
                break;
            case R.id.tvCreateAccount:
                goToSignUpFragment();
                break;
        }
    }
}
