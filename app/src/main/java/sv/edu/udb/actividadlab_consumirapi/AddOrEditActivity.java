package sv.edu.udb.actividadlab_consumirapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.udb.actividadlab_consumirapi.models.Detalle;
import sv.edu.udb.actividadlab_consumirapi.models.Respuesta;
import sv.edu.udb.actividadlab_consumirapi.services.servicio;

public class AddOrEditActivity extends AppCompatActivity {
EditText nameTxt;
EditText ageTxt;
EditText mobileTxt;
EditText emailTxt;
String accion="";
int id=0;
Button btn_add;
Button btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit);
        inicializaInterface();
    }


    private void inicializaInterface(){

        Bundle datos = getIntent().getExtras();
        nameTxt=findViewById(R.id.txt_name);
        ageTxt=findViewById(R.id.txt_age);
        mobileTxt=findViewById(R.id.txt_mobile);
        emailTxt=findViewById(R.id.txt_email_input);
        btn_add=findViewById(R.id.Btn_add);
        btn_edit=findViewById(R.id.Btn_edit);


        //estableciendo datos
        id=datos.getInt("id");
        nameTxt.setText(datos.getString("name",""));
        ageTxt.setText(String.valueOf(datos.getInt("age",0)));
        mobileTxt.setText(""+datos.getString("mobile",""));
        emailTxt.setText(""+datos.getString("email",""));
        accion = datos.getString("accion","a");

        btn_add.setVisibility(View.INVISIBLE);
        btn_edit.setVisibility(View.INVISIBLE);
        if (accion.equals("e"))
        {

            btn_edit.setVisibility(View.VISIBLE);
        }
        else
        {

            btn_add.setVisibility(View.VISIBLE);
        }
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar(id);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });
    }

    public void actualizar(int _id) {
        String name =  nameTxt.getText().toString();
        String age = ageTxt.getText().toString().trim();
        String mobile=mobileTxt.getText().toString();
        String email=emailTxt.getText().toString();

        Detalle.Item _detalle = new Detalle.Item(name, Integer.parseInt(age),mobile,email);
        Call<Respuesta> call = servicio.service.updateDetail(_id,name,age,mobile,email);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta.getSuccess()==1) {
                    Toast.makeText(getBaseContext(),"Registro actualizado",
                            Toast.LENGTH_LONG).show();
                    //finish();
                    EndUp();
                } else {
                    Toast.makeText(getBaseContext(),"Error:" +response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error:" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void agregar() {
        String name =  nameTxt.getText().toString();
        String age = ageTxt.getText().toString().trim();
        String mobile=mobileTxt.getText().toString();
        String email=emailTxt.getText().toString();
        Call<Respuesta> call  = servicio.service.insertDetail(name,age,mobile,email);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.code()==200) {
                    Toast.makeText(getBaseContext(),"Registro Agregado satisfactoriamente",
                            Toast.LENGTH_LONG ).show();
                    // finish();
                    EndUp();

                } else
                {
                    Toast.makeText(getBaseContext(),"Error : " + response.code(),
                            Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error : " + t.getMessage(),
                        Toast.LENGTH_LONG ).show();
            }
        });
    }


    public void EndUp()
    {
        Intent regresar=new Intent(this,MainActivity.class);
        startActivity(regresar);
    }
}