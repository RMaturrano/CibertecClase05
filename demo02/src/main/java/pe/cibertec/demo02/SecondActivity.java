package pe.cibertec.demo02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Android-SAB-PM on 07/05/2016.
 */
public class SecondActivity extends AppCompatActivity {

    private TextView tvSecondNombre, tvSecondApellido, tvSecondEdad, tvSecondDni;
    private Button btSecondCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tvSecondNombre = (TextView) findViewById(R.id.tvSecondNombre);
        tvSecondApellido = (TextView) findViewById(R.id.tvSecondApellido);
        tvSecondEdad = (TextView) findViewById(R.id.tvSecondEdad);
        tvSecondDni = (TextView) findViewById(R.id.tvSecondDni);
        btSecondCerrarSesion = (Button) findViewById(R.id.btSecondCerrarSesion);
        btSecondCerrarSesion.setOnClickListener(btSecondCerrarSesionOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SecondActivity.this);
        tvSecondNombre.setText(sp.getString("nombre", ""));
        tvSecondApellido.setText(sp.getString("apellido", ""));
        tvSecondEdad.setText(String.valueOf(sp.getInt("edad", 0)));
        tvSecondDni.setText(sp.getString("dni", ""));
    }

    View.OnClickListener btSecondCerrarSesionOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PreferenceManager.getDefaultSharedPreferences(SecondActivity.this).edit().clear().commit();
            Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
