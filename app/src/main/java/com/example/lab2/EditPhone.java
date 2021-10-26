package com.example.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class EditPhone extends AppCompatActivity {


    private EditText editproducent,editmodel,editversion,editsite;
    private Bundle pack;
    private Long id;
    private String producent,model,strona;
    private int version;
    private LiveData<PhoneEntity> phone;
    ElementViewModel phoneModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editproducent = findViewById(R.id.editProducent);
        editmodel = findViewById(R.id.editModel);
        editversion = findViewById(R.id.editVersion);
        editsite = findViewById(R.id.editWeb);
        Button website = findViewById(R.id.buttonWeb);
        Button cancel = findViewById(R.id.buttonCancel);
        Button update = findViewById(R.id.buttonEdit);

        pack = getIntent().getExtras();
        if(pack != null) {
            id = pack.getLong("id");
            producent = pack.getString("prod");
            model = pack.getString("model");
            version = pack.getInt("version");
            strona = pack.getString("strona");

            phoneModel = new ViewModelProvider(this).get(ElementViewModel.class);
            phone = phoneModel.getPhone(id);

            phone.observe(this, new Observer<PhoneEntity>() {
                @Override
                public void onChanged(PhoneEntity phone) {
                    editproducent.setText(producent);
                    editmodel.setText(model);
                   editversion.setText(String.valueOf(version));
                    editsite.setText(strona);
                }
            });
        }else
            update.setText("SAVE");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updatedProducent = editproducent.getText().toString();
                String updatedModel = editmodel.getText().toString();
                String updatedVersion = editversion.getText().toString();
                String updatedSite = editsite.getText().toString();

                if (updatedProducent.trim().length() == 0 | updatedModel.trim().length() == 0 | updatedVersion.trim().length() == 0 | updatedSite.isEmpty() ) {
                    showToast("Dane nie moga byc puste");

                } else {
                    int insVersion=Integer.parseInt( editversion.getText().toString());
                    Intent Updateintent = new Intent();
                    Updateintent.putExtra("ID",id);
                    Updateintent.putExtra("Producent",updatedProducent);
                    Updateintent.putExtra("Model",updatedModel);
                    Updateintent.putExtra("Version",insVersion);
                    Updateintent.putExtra("Site",updatedSite);
                    setResult(RESULT_OK,Updateintent);
                    finish();

                }
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insSite = editsite.getText().toString();
                if(insSite.startsWith("http://") || insSite.startsWith("https://")){
                    Intent BrowserIntent = new Intent("android.intent.action.VIEW", Uri.parse(insSite));
                    startActivity(BrowserIntent);}
                else
                    showToast("Wprowadz poprawny adres");
            }
        });
    }
    private void showToast(String text) {
        Toast.makeText(EditPhone.this, text, Toast.LENGTH_SHORT).show();
    }



}
