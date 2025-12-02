package com.ggf.qcpp.b_account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.R;
import com.ggf.qcpp.b_account.model.LoginResponse;
import com.ggf.qcpp.b_account.model.UserModel;
import com.ggf.qcpp.c_home.c_1_home;
import com.ggf.qcpp.c_home.home_leader;
import com.ggf.qcpp.c_home.home_leader2;
import com.ggf.qcpp.c_home.home_mandor2;
import com.ggf.qcpp.c_home.home_pengamat2;
import com.ggf.qcpp.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class b_2_login extends AppCompatActivity implements IAuthView{

    Button btn_login;
    @BindView(R.id.mUsername)
    EditText mUsername;

    @BindView(R.id.mPassword)
    EditText mPassword;

    SweetAlertDialog sweetAlertDialog;
    AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_2_login);
        ButterKnife.bind(this);
        presenter = new AuthPresenter(this);
        if (presenter.isLoggedIn()) {
            if (App.getPref().getString(Prefs.PREF_ROLE, "").equals("6")) {
                this.goToDashboardPengamat();
            }
            else {
                this.goToDashboardMandor();
            }
        }
        btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(view->this.onSubmit(mUsername.getText().toString() , mPassword.getText().toString()));


        //StatusBar Transparant
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

    }

    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

    @Override
    public void onSubmit(String username , String Password) {

        presenter.login(username , Password);
    }

    @Override
    public void onLoginSuccess(LoginResponse model) {
        Log.d("responLogin" , new Gson().toJson(model));
        presenter.storeProfile(new Gson().toJson(model));
        App.getPref().put(Prefs.PREF_FIRST_TIME, true);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
        App.getPref().put(Prefs.PREF_ROLE, model.getData().getUser().getId_role());

        if(Integer.parseInt(model.getData().getUser().getId_role()) == 6){
            this.goToDashboardPengamat();
        }else{
            this.goToDashboardMandor();
        }
    }

    @Override
    public void showLoadingIndicator() {
//        Toast.makeText(this, "woi", Toast.LENGTH_SHORT).show();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }
    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    void goToDashboardPengamat(){
        startActivity(new Intent(b_2_login.this , c_1_home.class));
        finish();
    }

    void goToDashboardMandor(){
        startActivity(new Intent(this , home_leader.class));
        finish();
    }
}