package sv.edu.udb.actividadlab_consumirapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.udb.actividadlab_consumirapi.models.Detalle;
import sv.edu.udb.actividadlab_consumirapi.services.CustomDetail_Item;
import sv.edu.udb.actividadlab_consumirapi.services.DetalleAPI;

public class MainActivity extends AppCompatActivity {
ListView listViewDetalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewDetalles=findViewById(R.id.listview_detalles);
        ListAllDetails();
    }

    public void Agregar(View view)
    {
        Intent intent = new Intent(MainActivity.this,AddOrEditActivity.class);
        intent.putExtra("id",0);
        intent.putExtra("name","");
        intent.putExtra("age",0);
        intent.putExtra("mobile","");
        intent.putExtra("email","");
        intent.putExtra("accion","a");
        startActivity(intent);
    }

    public void ListAllDetails()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mytestingapisv.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetalleAPI detalleAPI=retrofit.create(DetalleAPI.class);

        Call<Detalle> call=detalleAPI.getAllDetails();


        call.enqueue(new Callback <Detalle>() {
            @Override
            public void onResponse(Call<Detalle> call, Response<Detalle> response) {
                try {
                    if(response.isSuccessful())
                    {
                        //    response.body().getResults().get(0).getSlug();

                        Detalle _detalle=response.body();

                        CustomDetail_Item adapter = new CustomDetail_Item(MainActivity.this, _detalle.getDetails());
                         listViewDetalles.setAdapter(adapter);


                        /*CustomAdapter adapter = new CustomAdapter(MenFragment.this.getContext(), _producto.getResults());
                        listviewProductos.setAdapter(adapter);
*/
                    }
                    else{
                        Toast.makeText(MainActivity.this,response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception exception)
                {
                    Toast.makeText(MainActivity.this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detalle>call, Throwable t) {
                Toast.makeText(MainActivity.this,"Ocurrio un error "+t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());

            }
        });


    }
}