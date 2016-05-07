package pe.cibertec.demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

import pe.cibertec.demo01.dao.DataBaseHelper;
import pe.cibertec.demo01.dao.DataBaseSingleton;
import pe.cibertec.demo01.dao.PersonaDAO;
import pe.cibertec.demo01.entities.Persona;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(FirstActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(FirstActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Persona> lstPersona = new PersonaDAO().listPersona();
    }
}
