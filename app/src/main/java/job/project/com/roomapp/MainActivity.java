package job.project.com.roomapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
                JsonTask jsonTask=new JsonTask(getApplicationContext(),recyclerView);
                jsonTask.execute(new URL("https://dl.dropbox.com/s/33g6niamgjdzp0x/room.json?dl=0"));
            }else{
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    public class JsonTask extends AsyncTask<URL, Void,List<Room>> {
        HttpURLConnection cont;
        private RecycleRoomAdapter adapter;
        private Context context;
        private  RecyclerView recyclerView;

        public JsonTask(Context context, RecyclerView recyclerView) {
            this.context = context;
            this.recyclerView = recyclerView;

        }

        @Override
        protected List<Room> doInBackground(URL... urls) {
            ArrayList<Room>rooms=null;
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
