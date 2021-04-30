package sv.edu.udb.actividadlab_consumirapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Respuesta {


    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


    @SerializedName("success")
    @Expose
    int success;
    @SerializedName("message")
    @Expose
    String message;
}
