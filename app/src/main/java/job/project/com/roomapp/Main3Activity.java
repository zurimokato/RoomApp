package job.project.com.roomapp;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView arrive, departure;
    private Button btnpayment;
    public final Calendar c = Calendar.getInstance();
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    private EditText fecha1 = null, fecha2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnpayment=findViewById(R.id.pyament);
        arrive=findViewById(R.id.ib_obtener_fecha1);
        departure=findViewById(R.id.ib_obtener_fecha2);

        fecha1=findViewById(R.id.arrival);
        fecha2=findViewById(R.id.departure);

        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intet);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha1:
                obtenerFecha(fecha1);
                break;
            case R.id.ib_obtener_fecha2:
                obtenerFecha(fecha2);
                break;
        }
    }

    private void obtenerFecha(final EditText editText) {

    }
}
