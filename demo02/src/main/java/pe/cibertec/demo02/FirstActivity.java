package pe.cibertec.demo02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    private EditText etFirstCorreo, etFirstClave;
    private Button btFirstIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        etFirstCorreo = (EditText) findViewById(R.id.etFirstCorreo);
        etFirstClave = (EditText) findViewById(R.id.etFirstClave);
        btFirstIngresar = (Button) findViewById(R.id.btFirstIngresar);

        btFirstIngresar.setOnClickListener(btIngresarOnClickListener);

        if (!PreferenceManager.getDefaultSharedPreferences(FirstActivity.this).getBoolean("ingreso", false)) {
            PreferenceManager.getDefaultSharedPreferences(FirstActivity.this).edit().putString("correo", "pxlrios@cibertec.edu.pe").putString("clave", "123456").putString("nombre", "Luis Alonso").putString("apellido", "Rios").putInt("edad", 27).putString("dni", "12345678").putBoolean("ingreso", false).commit();
        } else
            Ingresar();
    }

    View.OnClickListener btIngresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (etFirstCorreo.getText().toString().trim().isEmpty()) {
                setMesage("Ingrese su correo");
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(etFirstCorreo.getText().toString().trim()).matches()) {
                setMesage("Ingrese un correo en un formato correcto");
                return;
            }
            if (etFirstClave.getText().toString().trim().isEmpty()) {
                setMesage("Ingrese su clave");
                return;
            }

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(FirstActivity.this);
            if (!etFirstCorreo.getText().toString().trim().equals(sp.getString("correo", "")) || !etFirstClave.getText().toString().trim().equals(sp.getString("clave", ""))) {
                setMesage("Correo y/o clave incorrectos");
                return;
            }

            sp.edit().putBoolean("ingreso", true).commit();
            Ingresar();
        }
    };

    private void Ingresar() {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

    private void setMesage(String mesage) {
        new AlertDialog.Builder(FirstActivity.this).setTitle(R.string.app_name).setMessage(mesage).setNegativeButton("Aceptar", null).show();
    }
}
