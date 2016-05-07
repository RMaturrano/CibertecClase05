package pe.cibertec.demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import pe.cibertec.demo01.adapter.recyclerview.RVFirstAdapter;
import pe.cibertec.demo01.dao.DataBaseHelper;
import pe.cibertec.demo01.dao.DataBaseSingleton;
import pe.cibertec.demo01.dao.PersonaDAO;
import pe.cibertec.demo01.entities.Persona;

public class FirstActivity extends AppCompatActivity {

    private RVFirstAdapter mRVFirstAdapter;
    private RecyclerView rvFirstPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        rvFirstPersonas= (RecyclerView) findViewById(R.id.rvFirstPersonas);

        rvFirstPersonas.setLayoutManager(new LinearLayoutManager(FirstActivity.this));

        mRVFirstAdapter = new RVFirstAdapter();

        rvFirstPersonas.setAdapter(mRVFirstAdapter);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(FirstActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(FirstActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mRVFirstAdapter.addAll(new PersonaDAO().listPersona());
    }
}
