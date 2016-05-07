package pe.cibertec.demo01;

import android.support.v7.app.AlertDialog;
import android.view.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pe.cibertec.demo01.R;
import pe.cibertec.demo01.dao.DataBaseSingleton;
import pe.cibertec.demo01.dao.PersonaDAO;
import pe.cibertec.demo01.entities.Persona;

/**
 * Created by Android-SAB-PM on 07/05/2016.
 */
public class SecondActivity extends AppCompatActivity {
    public final static String ARG_PERSONA = "arg_persona";

    private TextInputLayout tilSecondNombre, tilSecondApellido, tilSecondEdad, tilSecondDni;

    private Button btSecondGuardar;
    private Persona mPersona;
    private boolean isUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ARG_PERSONA)) {
            mPersona = getIntent().getParcelableExtra(ARG_PERSONA);
            isUpdate = true;
        } else {
            mPersona = null;
            isUpdate = false;
        }

        tilSecondNombre = (TextInputLayout) findViewById(R.id.tilSecondNombre);
        tilSecondApellido = (TextInputLayout) findViewById(R.id.tilSecondApellido);
        tilSecondEdad = (TextInputLayout) findViewById(R.id.tilSecondEdad);
        tilSecondDni = (TextInputLayout) findViewById(R.id.tilSecondDni);

        btSecondGuardar = (Button) findViewById(R.id.btSecondGuardar);

        btSecondGuardar.setOnClickListener(btSecondGuardarOnClickListener);

        if (isUpdate)
            setData();
    }

    private void setData() {
        tilSecondNombre.getEditText().setText(mPersona.getNombre());
        tilSecondApellido.getEditText().setText(mPersona.getApellido());
        tilSecondEdad.getEditText().setText(String.valueOf(mPersona.getEdad()));
        tilSecondDni.getEditText().setText(mPersona.getDni());
    }

    View.OnClickListener btSecondGuardarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isOK = true;

            tilSecondNombre.setError(null);
            tilSecondApellido.setError(null);
            tilSecondEdad.setError(null);
            tilSecondDni.setError(null);
            tilSecondNombre.setErrorEnabled(false);
            tilSecondApellido.setErrorEnabled(false);
            tilSecondEdad.setErrorEnabled(false);
            tilSecondDni.setErrorEnabled(false);

            if (tilSecondNombre.getEditText().getText().toString().trim().isEmpty()) {
                tilSecondNombre.setError("Ingrese su nombre");
                tilSecondNombre.setErrorEnabled(true);
                isOK = false;
            }

            if (tilSecondApellido.getEditText().getText().toString().trim().isEmpty()) {
                tilSecondApellido.setError("Ingrese su apellido");
                tilSecondApellido.setErrorEnabled(true);
                isOK = false;
            }
            if (tilSecondEdad.getEditText().getText().toString().trim().isEmpty()) {
                tilSecondEdad.setError("Ingrese su edad");
                tilSecondEdad.setErrorEnabled(true);
                isOK = false;
            }
            if (tilSecondDni.getEditText().getText().toString().trim().isEmpty()) {
                tilSecondDni.setError("Ingrese su DNI");
                tilSecondDni.setErrorEnabled(true);
                isOK = false;
            } else {
                if (tilSecondDni.getEditText().getText().toString().trim().length() < 8) {
                    tilSecondDni.setError("El DNI es de 8 caracteres");
                    tilSecondDni.setErrorEnabled(true);
                    isOK = false;
                }
            }

            if (isOK) {
                if (mPersona == null)
                    mPersona = new Persona();

                mPersona.setNombre(tilSecondNombre.getEditText().getText().toString().trim());
                mPersona.setApellido(tilSecondApellido.getEditText().getText().toString().trim());
                mPersona.setDni(tilSecondDni.getEditText().getText().toString().trim());
                mPersona.setEdad(Integer.parseInt(tilSecondEdad.getEditText().getText().toString().trim()));

                if (isUpdate) {
                    boolean isUpdated = new PersonaDAO().updatePersona(mPersona);
                    if (isUpdated) {
                        Toast.makeText(SecondActivity.this, mPersona.getNombre() + " " + mPersona.getApellido() + " ha sido actualizdo", Toast.LENGTH_LONG).show();
                        finish();
                    } else
                        new AlertDialog.Builder(SecondActivity.this).setTitle(R.string.app_name).setMessage("No se pudo actualizar en la base de datos").setNegativeButton("Aceptar", null).show();
                } else {
                    boolean isInserted = new PersonaDAO().insertPersona(mPersona);
                    if (isInserted) {
                        Toast.makeText(SecondActivity.this, mPersona.getNombre() + " " + mPersona.getApellido() + " ha sido registrado", Toast.LENGTH_LONG).show();
                        finish();
                    } else
                        new AlertDialog.Builder(SecondActivity.this).setTitle(R.string.app_name).setMessage("No se pudo regristrar en la base de datos").setNegativeButton("Aceptar", null).show();
                }
            }
        }
    };
}