package com.example.lsddevinet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(foreignKeys =
        @ForeignKey(
        entity = Categorie.class,
        parentColumns = "id",
        childColumns = "idCategorie",
        onDelete = ForeignKey.NO_ACTION
        ))
public class Mot implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String img;
    private String mot;
    private String proposition;
    private int idCategorie;

    public Mot(String img, String mot, String proposition, int idCategorie) {
        this.img = img;
        this.mot = mot;
        this.proposition = proposition;
        this.idCategorie = idCategorie;
    }


    protected Mot(Parcel in) {
        id = in.readInt();
        img = in.readString();
        mot = in.readString();
        proposition = in.readString();
        idCategorie = in.readInt();
    }

    public static final Creator<Mot> CREATOR = new Creator<Mot>() {
        @Override
        public Mot createFromParcel(Parcel in) {
            return new Mot(in);
        }

        @Override
        public Mot[] newArray(int size) {
            return new Mot[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", mot='" + mot + '\'' +
                ", proposition='" + proposition + '\'' +
                ", idCategorie=" + idCategorie +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(img);
        parcel.writeString(mot);
        parcel.writeString(proposition);
        parcel.writeInt(idCategorie);
    }
}
