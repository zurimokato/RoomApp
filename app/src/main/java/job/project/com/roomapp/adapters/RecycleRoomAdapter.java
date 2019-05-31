package job.project.com.roomapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import job.project.com.roomapp.Main2Activity;
import job.project.com.roomapp.R;
import job.project.com.roomapp.pojo.Room;

public class RecycleRoomAdapter extends RecyclerView.Adapter<RecycleRoomAdapter.RoomViewHolder> {
    private Context context;
    private List<Room>rooms;
    private LayoutInflater inflater;

    public RecycleRoomAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root=inflater.inflate(R.layout.card_room,viewGroup,false);
        RoomViewHolder holder=new RoomViewHolder(root,context,rooms);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int i) {
        Room room=rooms.get(i);
        roomViewHolder.titulo.setText(room.getTitulo());
        roomViewHolder.priceTex.setText(Integer.toString(room.getPrecio()));
        roomViewHolder.rating.setRating( room.getValoracion());
        Picasso.get().load(room.getImagen()).into(roomViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static  class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private  Context context;
        private List<Room>rooms;
        private TextView titulo;
        private TextView priceTex;
        private RatingBar rating;
        private ImageView imageView;

        public RoomViewHolder(View itemView,Context context,List<Room>rooms){
            super(itemView);
            itemView.setOnClickListener(this);
            this.context=context;
            this.rooms=rooms;

            titulo=itemView.findViewById(R.id.titleRoomCard);
            priceTex=itemView.findViewById(R.id.CardPriceRoom);
            imageView=itemView.findViewById(R.id.cardimagen);
            rating=itemView.findViewById(R.id.cardRating);

        }

        @Override
        public void onClick(View v) {
            int i=getAdapterPosition();
            Room r=rooms.get(i);
            Intent next=new Intent(context, Main2Activity.class);
            next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            next.putExtra("room",r);
            this.context.startActivity(next);

        }
    }
}
