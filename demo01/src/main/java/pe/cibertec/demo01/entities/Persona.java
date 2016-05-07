package pe.cibertec.demo01.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android-SAB-PM on 07/05/2016.
 */
public class Persona implements Parcelable {
    private int personaId;
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;

    public Persona() {
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.personaId);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeInt(this.edad);
        dest.writeString(this.dni);
    }

    protected Persona(Parcel in) {
        this.personaId = in.readInt();
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.edad = in.readInt();
        this.dni = in.readString();
    }

    public static final Parcelable.Creator<Persona> CREATOR = new Parcelable.Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}
