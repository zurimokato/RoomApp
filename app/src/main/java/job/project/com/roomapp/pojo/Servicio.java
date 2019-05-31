package job.project.com.roomapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Servicio implements Parcelable {
    private  Long id;
    private String tipo;

    public Servicio() {
    }

    public Servicio(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    protected Servicio(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        tipo = in.readString();
    }

    public static final Creator<Servicio> CREATOR = new Creator<Servicio>() {
        @Override
        public Servicio createFromParcel(Parcel in) {
            return new Servicio(in);
        }

        @Override
        public Servicio[] newArray(int size) {
            return new Servicio[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        dest.writeString(tipo);
    }
}
