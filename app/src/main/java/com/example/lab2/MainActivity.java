package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PhoneListAdapter.OnItemClickListener {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    private static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    private ElementViewModel mElementViewModel;
    private PhoneListAdapter mAdapter;
    private List<PhoneEntity> mPhoneList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FloatingActionButton addNewButton;

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PhoneListAdapter(this,this);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mElementViewModel = new ViewModelProvider(this).get(ElementViewModel.class);
        mAdapter.setPhoneViewModel(mElementViewModel);

        mElementViewModel.getAllPhones().observe(this,elements->{
            mAdapter.setElementList(elements);
            mPhoneList = elements;
        });

        addNewButton = findViewById(R.id.fabMain);
        addNewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditPhone.class);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent pack) {
        super.onActivityResult(requestCode, resultCode, pack);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

                 if(pack!=null)
                 {

                      mElementViewModel.insert(new PhoneEntity( pack.getStringExtra("Producent"),
                                                                pack.getStringExtra("Model"),
                                                                pack.getIntExtra("Version",0),
                                                                pack.getStringExtra("Site") ));
                     showToast("Dodanie wartosci");
                 }
            }else if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){

            if(pack!=null){

            PhoneEntity phone = new PhoneEntity(pack.getLongExtra("ID",0),
                                                pack.getStringExtra("Producent"),
                                                pack.getStringExtra("Model"),
                                                pack.getIntExtra("Version",0),
                                                pack.getStringExtra("Site"));
                showToast("Zaktualizowano");
            mElementViewModel.update(phone);
                }
            else showToast("UPDATE PACK IS NULL");
        }else
            showToast("Anulowano");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.pierwsza_opcja){
            mElementViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,EditPhone.class);
        intent.putExtra("id",mPhoneList.get(position).getId());
        intent.putExtra("prod",mPhoneList.get(position).getProducent());
        intent.putExtra("model",mPhoneList.get(position).getModel());
        intent.putExtra("wersja",mPhoneList.get(position).getVersion());
        intent.putExtra("strona",mPhoneList.get(position).getSite());
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE);
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}