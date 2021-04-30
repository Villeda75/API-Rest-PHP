package sv.edu.udb.actividadlab_consumirapi.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import sv.edu.udb.actividadlab_consumirapi.models.Detalle;
import sv.edu.udb.actividadlab_consumirapi.models.Respuesta;

public interface DetalleAPI {

    @GET("APIRESTPHP/displayAll.php")
    public Call<Detalle> getAllDetails();


    @DELETE("APIRESTPHP/deleteData.php/")
    Call<Respuesta> deleteItem(@Query("id") String id);

    @FormUrlEncoded
    @POST("APIRESTPHP/insertData.php")
    Call<Respuesta> insertDetail(
            @Field("name") String name,
            @Field("age") String age,
            @Field("mobile") String mobile,
            @Field("email") String email
    );


    @FormUrlEncoded
    @POST("APIRESTPHP/updateData.php/")
    Call<Respuesta> updateDetail(@Field("id") int id,
                                 @Field("name") String name,
                                 @Field("age") String age,
                                 @Field("mobile") String mobile,
                                 @Field("email") String email);
}
