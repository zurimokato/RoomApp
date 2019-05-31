package job.project.com.roomapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Room implements Parcelable{
    private Long id;
    private String titulo;
    private float valoracion;
    private ArrayList<Servicio>servicio;
    private String descripcion;
    private int precio;
    private String imagen;
    private Ubicacion ubicacion;

    public Room() {
    }

    public Room(Long id, String titulo, float valoracion, ArrayList<Servicio> servicio, String descripcion, int precio, String imagen, Ubicacion ubicacion) {
        this.id = id;
        this.titulo = titulo;
        this.valoracion = valoracion;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
    }

    protected Room(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        titulo = in.readString();
        valoracion = in.readFloat();
        servicio = in.createTypedArrayList(Servicio.CREATOR);
        descripcion = in.readString();
        precio = in.readInt();
        imagen = in.readString();
        ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public ArrayList<Servicio> getServicio() {
        return servicio;
    }

    public void setServicio(ArrayList<Servicio> servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(titulo);
        dest.writeFloat(valoracion);
        dest.writeTypedList(servicio);
        dest.writeString(descripcion);
        dest.writeInt(precio);
        dest.writeString(imagen);
        dest.writeParcelable(ubicacion, flags);
    }
}
