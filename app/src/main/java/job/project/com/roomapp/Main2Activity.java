package job.project.com.roomapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import job.project.com.roomapp.pojo.Room;

public class Main2Activity extends AppCompatActivity {
    private TextView titulo;
    private TextView detalle;
    private  TextView price;
    private ImageView imageView;
    private RatingBar ratingBar;
    private Button buton;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        titulo=findViewById(R.id.roomTittle);
        detalle=findViewById(R.id.roomTextDetail);
        ratingBar=findViewById(R.id.rating);
        imageView=findViewById(R.id.imagen);
        price=findViewById(R.id.price_room);
        buton=findViewById(R.id.reservar);

        Intent intent=getIntent();
        room=intent.getParcelableExtra("room");
        if (room==null){
            Toast.makeText(this, "Error al enviar la habitacion", Toast.LENGTH_SHORT).show();
        }else{
            titulo.setText(room.getTitulo());
            detalle.setText(room.getDescripcion());
            Picasso.get().load(room.getImagen()).into(imageView);
            ratingBar.setRating(room.getValoracion());
            price.setText(Integer.toString(room.getPrecio()));
        }

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(intent);
            }
        });

    }
}
