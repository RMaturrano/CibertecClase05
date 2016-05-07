package pe.cibertec.demo01.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import pe.cibertec.demo01.entities.Persona;

/**
 * Created by Android-SAB-PM on 07/05/2016.
 */
public class PersonaDAO {

    public ArrayList<Persona> listPersona() {
        Cursor cursor = DataBaseSingleton.getInstance().query("Persona", null, null, null, null, null, null);

        ArrayList<Persona> lstPersona = new ArrayList<>();
        Persona persona;

        if (cursor.moveToFirst()) {
            do {
                persona = new Persona();
                persona.setPersonaId(cursor.getInt(cursor.getColumnIndex("personaId")));
                persona.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                persona.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
                persona.setEdad(cursor.getInt(cursor.getColumnIndex("edad")));
                persona.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                lstPersona.add(persona);
            } while (cursor.moveToNext());
        }

        return lstPersona;
    }
}
