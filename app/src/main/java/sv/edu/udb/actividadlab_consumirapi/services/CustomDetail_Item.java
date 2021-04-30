package sv.edu.udb.actividadlab_consumirapi.services;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.udb.actividadlab_consumirapi.AddOrEditActivity;
import sv.edu.udb.actividadlab_consumirapi.MainActivity;
import sv.edu.udb.actividadlab_consumirapi.R;
import sv.edu.udb.actividadlab_consumirapi.models.Detalle;
import sv.edu.udb.actividadlab_consumirapi.models.Respuesta;

public class CustomDetail_Item extends BaseAdapter {
    Context context;
    List<Detalle.Item> list;
    public CustomDetail_Item(Context context, List<Detalle.Item> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        TextView TxtNId;
        TextView TxtNombre;
        TextView TxtEdad;
        TextView TxtTelefono;
        TextView TxtEmail;
        LinearLayout cardDetail;

        Detalle.Item item=list.get(i);
        if(view==null)
            view= LayoutInflater.from(context).inflate(R.layout.detail_item,null);
        //inicializando valores
        TxtNId=view.findViewById(R.id.txt_id);
        TxtNombre=view.findViewById(R.id.txt_nombre);
        TxtEdad=view.findViewById(R.id.txt_edad);
        TxtTelefono=view.findViewById(R.id.txt_telefono);
        TxtEmail=view.findViewById(R.id.txt_email);
        cardDetail=view.findViewById(R.id.cardDetalle);


        //mostrando datos
        TxtNId.setText("ID: "+String.valueOf(item.getId()));
        TxtNombre.setText("Nombre: "+item.getName());
        TxtEdad.setText("Edad: "+String.valueOf(item.getAge()));
        TxtTelefono.setText("Telefono: "+item.getMobile());
        TxtEmail.setText("Email: "+item.getEmail());

        cardDetail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String title = "Consulta";
                String message = "¿Está seguro que desea borrarlo?";
                String strbt1 = "Sí";
                String strbt2 = "No";
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle(title);
                ad.setIcon(android.R.drawable.ic_delete);
                ad.setMessage(message);
                ad.setPositiveButton(strbt1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                      eliminar(String.valueOf(item.getId()));
                    }
                });
                ad.setNegativeButton(strbt2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(context, "Operación cancelada",Toast.LENGTH_LONG).show();
                    }
                }).setIcon(android.R.drawable.ic_delete);

                ad.show();

                return true;
            }
        });
        cardDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddOrEditActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("name",item.getName());
                intent.putExtra("age",item.getAge());
                intent.putExtra("mobile",item.getMobile());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("accion","e");
                context.startActivity(intent);
            }
        });



        return view;
    }


    private void eliminar(String codigo) {
        Toast.makeText(context,codigo,Toast.LENGTH_SHORT).show();
        Call<Respuesta> call = servicio.service.deleteItem(codigo);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {

                Respuesta r = response.body();

              if (r.getSuccess()==1) {
                    Toast.makeText(context, r.getMessage(),
                            Toast.LENGTH_LONG).show();
                    EndUp();
                } else {Toast.makeText(context,  r.getMessage(),
                        Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(context, "Error:" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void EndUp()
    {
        Intent regresar=new Intent(context, MainActivity.class);
        context.startActivity(regresar);
    }

}
