package com.hiddenramblings.tagmo.amiibo;

import android.os.Parcel;
import android.os.Parcelable;

import com.hiddenramblings.tagmo.nfctech.KeyManager;
import com.hiddenramblings.tagmo.nfctech.TagReader;
import com.hiddenramblings.tagmo.nfctech.TagUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class AmiiboFile implements Parcelable {

    protected File filePath;
    protected long id;
    protected boolean decrypted;

    public AmiiboFile(File filePath, long id, boolean decrypted) {
        this.filePath = filePath;
        this.id = id;
        this.decrypted = decrypted;
    }

    public AmiiboFile(File filePath, long id) {
        this.filePath = filePath;
        this.id = id;
        this.decrypted = false;
    }

    public boolean isDecrypted() {
        return decrypted;
    }

    public File getFilePath() {
        return filePath;
    }

    @SuppressWarnings("unused")
    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.filePath);
        dest.writeLong(this.id);
    }

    protected AmiiboFile(Parcel in) {
        this.filePath = (File) in.readSerializable();
        this.id = in.readLong();
    }

    public static final Parcelable.Creator<AmiiboFile> CREATOR
            = new Parcelable.Creator<AmiiboFile>() {
        @Override
        public AmiiboFile createFromParcel(Parcel source) {
            return new AmiiboFile(source);
        }

        @Override
        public AmiiboFile[] newArray(int size) {
            return new AmiiboFile[size];
        }
    };
}
