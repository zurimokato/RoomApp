package job.project.com.roomapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import job.project.com.roomapp.adapters.RecycleRoomAdapter;
import job.project.com.roomapp.parser.RoomGsonParser;
import job.project.com.roomapp.pojo.Room;

public class MainActivity extends AppCompatActivity {

    private RecycleRoomAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Room>rooms;
    JsonTask jsonTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recicleRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try{
            ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null &&info.isConnected()){
                jsonTask=new JsonTask(getApplicationContext(),recyclerView);
                jsonTask.execute(new URL("https://dl.dropbox.com/s/33g6niamgjdzp0x/room.json?dl=0"));
            }else{
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.map:
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                rooms=jsonTask.getList();
                if (rooms!=null){
                    intent.putParcelableArrayListExtra("Lista",rooms);
                }

                startActivity(intent);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }

    public class JsonTask extends AsyncTask<URL, Void,List<Room>> {
        HttpURLConnection cont;
        private RecycleRoomAdapter adapter;
        private Context context;
        private  RecyclerView recyclerView;
        private ArrayList<Room>rooms=null;

        public JsonTask(Context context, RecyclerView recyclerView) {
            this.context = context;
            this.recyclerView = recyclerView;

        }

        @Override
        protected List<Room> doInBackground(URL... urls) {

            try {
                cont=(HttpURLConnection)urls[0].openConnection();
                cont.setConnectTimeout(15000);
                cont.setReadTimeout(10000);
                int statusCode=cont.getResponseCode();
                if (statusCode!=200){
                    rooms=new ArrayList<>();
                    rooms.add(new Room(null,"Error",0,null,"error",0,"Error",null));

                }else {
                    InputStream stream=new BufferedInputStream(cont.getInputStream());
                    RoomGsonParser gsonParser=new RoomGsonParser();
                    rooms=(ArrayList<Room>)gsonParser.leerFlujo(stream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                cont.disconnect();
            }

            return rooms;
        }

        public ArrayList<Room> getList(){
            return rooms;
        }

        @Override
        protected void onPostExecute(List<Room> rooms) {
            super.onPostExecute(rooms);
            if (rooms!=null){
                adapter=new RecycleRoomAdapter(getApplicationContext(),rooms);
                recyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(context, "Ocurrio un error de parsing de json", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
