package com.ggf.qcpp.rencana_kerja;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.qcpp.App;
import com.ggf.qcpp.R;
import com.ggf.qcpp.rencana_kerja.model.RencanaKerjaModel;
import com.ggf.qcpp.rencana_kerja.model.SpkModel;
import com.ggf.qcpp.rencana_kerja.model.Users;
import com.ggf.qcpp.ui.SweetDialogs;
import com.ggf.qcpp.utils.CommonResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RencanaKerja extends AppCompatActivity implements IRencanaKerjaView{
    @BindView(R.id.mRecycleView)
    RecyclerView mRecycleView;

    @BindView(R.id.mFabAdd)
    FloatingActionButton mFabAdd;
    SweetAlertDialog sweetAlertDialog;


    RencanaKerjaPresenter presenter ;
    View dialogView;
    AlertDialog.Builder dialog;
    private HashMap<String, String> mandorMap = new HashMap<>();
    private HashMap<String, String> kasieMap = new HashMap<>();
    private HashMap<String, String> kabagMap = new HashMap<>();
    private final ArrayList<String> listMandor = new ArrayList<>();
    private final ArrayList<String> listKabag = new ArrayList<>();
    private final ArrayList<String> listKasie = new ArrayList<>();
    Spinner spinnerMandor ;
    Spinner spinnerKabag ;
    Spinner spinnerKasie ;

    SpkModel modelspk = new SpkModel();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rencana_kerja);
        ButterKnife.bind(this);
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        presenter = new RencanaKerjaPresenter(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        presenter.getListLeader(App.getApplication().getString(R.string.role_kabag));
        presenter.getListLeader(App.getApplication().getString(R.string.role_kasie));
        presenter.getListLeader(App.getApplication().getString(R.string.role_mandor));

//        presenter.getRencanaKerja();

        mFabAdd.setOnClickListener(view -> this.addSpk());
    }

    public void addSpk(){

        AlertDialog.Builder builder = new AlertDialog.Builder(RencanaKerja.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_spk, null);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        MaterialAutoCompleteTextView mListMandor  = dialogView.findViewById(R.id.mListMandor);
        MaterialAutoCompleteTextView mListKabag = dialogView.findViewById(R.id.mListKabag);
        MaterialAutoCompleteTextView mListKasie  = dialogView.findViewById(R.id.mListKasie);
        final TextInputEditText mTglSpk = dialogView.findViewById(R.id.mTglSpk);
        final TextInputEditText mTglReal= dialogView.findViewById(R.id.mTglReal);
        final TextInputEditText mNoSpk= dialogView.findViewById(R.id.mNoSpk);
        final TextInputEditText mShift= dialogView.findViewById(R.id.mShift);
        final TextInputEditText mCatatan= dialogView.findViewById(R.id.mCatatan);
        final Button mAddSPK= dialogView.findViewById(R.id.mAddSPK);
        final ImageButton btnClose= dialogView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(view->dialog.dismiss());



        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);




        // Saat kolom di-klik, tampilkan DatePickerDialog
        mTglSpk.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTglSpk.setText(selectedDate); // Set tanggal di EditText
                        modelspk.setTglSpk(mTglSpk.getText().toString());
                    },
                    year, month, day);

            datePickerDialog.show(); // Tampilkan dialog
        });
        mTglReal.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Tambahkan 1 ke bulan (karena bulan dimulai dari 0)
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mTglReal.setText(selectedDate); // Set tanggal di EditText
                        modelspk.setTglReal(mTglReal.getText().toString());

                    },
                    year, month, day);

            datePickerDialog.show(); // Tampilkan dialog
        });
        setDropdownAdapter(mListMandor, listMandor);
        setDropdownAdapter(mListKasie, listKasie);
        setDropdownAdapter(mListKabag, listKabag);
        setDropdownListener(mListMandor, listMandor, mandorMap, "mandor");
        setDropdownListener(mListKasie, listKasie, kasieMap, "kasie");
        setDropdownListener(mListKabag, listKabag, kabagMap, "kabag");


        mAddSPK.setOnClickListener(view -> {
            if (validateFields(dialogView)) {
                modelspk.setNo_spk(mNoSpk.getText().toString());
                modelspk.setShift(mShift.getText().toString().equals("") ? 0 : Integer.parseInt(mShift.getText().toString()));
                modelspk.setCatatan(mCatatan.getText().toString());
                Log.d("dataBody" ,new Gson().toJson(modelspk));
                presenter.createSPK(modelspk);

            }
        });


        // Tampilkan dialog dan atur ukurannya setelah ditampilkan
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void setDropdownAdapter(MaterialAutoCompleteTextView dropdown, List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }

    private void setDropdownListener(MaterialAutoCompleteTextView dropdown, List<String> list, Map<String, String> map, String type) {
        dropdown.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = list.get(position);
            String selectedEmail = map.get(selectedName);

            switch (type) {
                case "mandor":
                    modelspk.setMandorId(selectedEmail);
                    modelspk.setMandorNama(selectedName);
                    break;
                case "kasie":
                    modelspk.setKasieNama(selectedName);
                    modelspk.setKasiId(selectedEmail);
                    break;
                case "kabag":
                    modelspk.setKabagId(selectedEmail);
                    modelspk.setKabagNama(selectedName);
                    break;
            }

            // Log untuk debugging
            Log.d("dataBody", new Gson().toJson(modelspk));
        });
    }

    @Override
    public void onCreateSuccess(CommonResponse model) {
        SweetDialogs.commonSuccess(this, model.getRm(), true);
    }
    @Override
    public void onDataReady(List<RencanaKerjaModel> model) {
        Log.d("datanya" , new Gson().toJson(model));
    }

    @Override
    public void onListMandor(List<Users> model) {
        Log.d("onListMandor" , new Gson().toJson(model));
        mandorMap = new HashMap<>();
        for (int i = 0; i < model.size(); i++) {
            Users data = model.get(i);
            String email = data.getEmail();
            String name =data.getName();

            // Simpan ke HashMap dan List
            mandorMap.put(name, email);
            listMandor.add(name);
        }


    }

    @Override
    public void onListKasie(List<Users> model) {
        kasieMap = new HashMap<>();
        for (int i = 0; i < model.size(); i++) {
            Users data = model.get(i);
            String email = data.getEmail();
            String name =data.getName();

            // Simpan ke HashMap dan List
            kasieMap.put(name, email);
            listKasie.add(name);
        }



    }

    @Override
    public void onListKabag(List<Users> model) {
        kabagMap = new HashMap<>();
        for (int i = 0; i < model.size(); i++) {
            Users data = model.get(i);
            String email = data.getEmail();
            String name =data.getName();

            // Simpan ke HashMap dan List
            kabagMap.put(name, email);
            listKabag.add(name);
        }
        Log.d("namaKabag" , new Gson().toJson(listKabag));
    }

    @Override
    public void showLoadingIndicator() {

        sweetAlertDialog.setTitleText("Loading ...");
        sweetAlertDialog.show();

    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onNetworkError(String cause) {
        Toast.makeText(this, cause, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private boolean validateFields(View dialogView) {
        TextInputEditText[] fields = {
                dialogView.findViewById(R.id.mNoSpk),
                dialogView.findViewById(R.id.mTglSpk),
                dialogView.findViewById(R.id.mTglReal),
                dialogView.findViewById(R.id.mShift),
                dialogView.findViewById(R.id.mCatatan)
        };

        MaterialAutoCompleteTextView[] dropdownFields = {
                dialogView.findViewById(R.id.mListKabag),
                dialogView.findViewById(R.id.mListKasie),
                dialogView.findViewById(R.id.mListMandor)
        };

        for (TextInputEditText field : fields) {
            if (field.getText().toString().trim().isEmpty()) {
                field.setError("Field ini tidak boleh kosong!");
                field.requestFocus();
                return false;
            }
        }

        for (MaterialAutoCompleteTextView dropdown : dropdownFields) {
            if (dropdown.getText().toString().trim().isEmpty()) {
                dropdown.setError("Pilih salah satu opsi!");
                dropdown.requestFocus();
                return false;
            }
        }

        return true;
    }



}