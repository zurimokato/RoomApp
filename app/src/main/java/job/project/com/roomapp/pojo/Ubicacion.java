package job.project.com.roomapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Ubicacion implements Parcelable {
    private double latd;
    private double longi;

    public Ubicacion() {

    }

    public Ubicacion(double latd, double longi) {
        this.latd = latd;
        this.longi = longi;
    }

    protected Ubicacion(Parcel in) {
        latd = in.readDouble();
        longi = in.readDouble();
    }

    public static final Creator<Ubicacion> CREATOR = new Creator<Ubicacion>() {
        @Override
        public Ubicacion createFromParcel(Parcel in) {
            return new Ubicacion(in);
        }

        @Override
        public Ubicacion[] newArray(int size) {
            return new Ubicacion[size];
        }
    };

    public double getLatd() {
        return latd;
    }

    public void setLatd(double latd) {
        this.latd = latd;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latd);
        dest.writeDouble(longi);
    }
}
