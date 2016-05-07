package pe.cibertec.demo01.dao;

import android.content.ContentValues;
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

        if (cursor.moveToFirst()) {
            do {
                lstPersona.add(transformCursorToPersona(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return lstPersona;
    }

    public Persona getPersona(Persona persona) {
        Cursor cursor = null;
        try {
            cursor = DataBaseSingleton.getInstance().query("Persona", null, "personaId = ?", new String[]{String.valueOf(persona.getPersonaId())}, null, null, null, "1");

            if (cursor.moveToFirst())
                persona = transformCursorToPersona(cursor);
            else
                persona = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            persona = null;
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            return persona;
        }
    }

    public boolean insertPersona(Persona persona) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", persona.getNombre());
        cv.put("apellido", persona.getApellido());
        cv.put("edad", persona.getEdad());
        cv.put("dni", persona.getDni());

        long inserto = DataBaseSingleton.getInstance().insert("Persona", null, cv);

        return inserto != -1;
    }

    public boolean updatePersona(Persona persona) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", persona.getNombre());
        cv.put("apellido", persona.getApellido());
        cv.put("edad", persona.getEdad());
        cv.put("dni", persona.getDni());

        int update = DataBaseSingleton.getInstance().update("Persona", cv, "personaId = ?", new String[]{String.valueOf(persona.getPersonaId())});

        return update > 0;
    }

    public boolean deletePersona(Persona persona) {

        int delete = DataBaseSingleton.getInstance().delete("Persona", "personaId = ?", new String[]{String.valueOf(persona.getPersonaId())});

        return delete > 0;
    }

    private Persona transformCursorToPersona(Cursor cursor) {
        Persona persona = new Persona();
        persona.setPersonaId(cursor.getInt(cursor.getColumnIndex("personaId")));
        persona.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
        persona.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
        persona.setEdad(cursor.getInt(cursor.getColumnIndex("edad")));
        persona.setDni(cursor.getString(cursor.getColumnIndex("dni")));

        return persona;
    }
}
