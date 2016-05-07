package pe.cibertec.demo01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import pe.cibertec.demo01.adapter.recyclerview.RVFirstAdapter;
import pe.cibertec.demo01.adapter.recyclerview.interfaces.IRVFirstAdapter;
import pe.cibertec.demo01.dao.DataBaseHelper;
import pe.cibertec.demo01.dao.DataBaseSingleton;
import pe.cibertec.demo01.dao.PersonaDAO;
import pe.cibertec.demo01.entities.Persona;

public class FirstActivity extends AppCompatActivity implements IRVFirstAdapter {

    private RVFirstAdapter mRVFirstAdapter;
    private RecyclerView rvFirstPersonas;
    private Button btFirstAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        rvFirstPersonas = (RecyclerView) findViewById(R.id.rvFirstPersonas);
        btFirstAgregar = (Button) findViewById(R.id.btFirstAgregar);

        rvFirstPersonas.setLayoutManager(new LinearLayoutManager(FirstActivity.this));

        mRVFirstAdapter = new RVFirstAdapter(FirstActivity.this);

        rvFirstPersonas.setAdapter(mRVFirstAdapter);

        btFirstAgregar.setOnClickListener(btFirstAgregarOnClickListener);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(FirstActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(FirstActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRVFirstAdapter.clearAndAddAll(new PersonaDAO().listPersona());
    }

    View.OnClickListener btFirstAgregarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onSelectItem(Persona persona) {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra(SecondActivity.ARG_PERSONA, persona);
        startActivity(intent);
    }
}