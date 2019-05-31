package job.project.com.roomapp.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import job.project.com.roomapp.pojo.Room;

public class RoomGsonParser {

    public List<Room>leerFlujo(InputStream in) throws IOException {
        Gson gson=new Gson();
        JsonReader reader=new com.google.gson.stream.JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Room>rooms=new ArrayList<>();
        Room room=null;
        reader.beginArray();
        while(reader.hasNext()){
            room=gson.fromJson(reader,Room.class);
            rooms.add(room);
        }

        reader.endArray();
        reader.close();
        return rooms;


    }
}
