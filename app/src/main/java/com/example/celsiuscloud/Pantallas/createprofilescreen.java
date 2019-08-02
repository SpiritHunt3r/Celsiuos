package com.example.celsiuscloud.Pantallas;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celsiuscloud.R;

import java.util.Calendar;

public class createprofilescreen extends AppCompatActivity {


    private String selectedGender = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createprofilescreen);


    }

    public void selectFecha(View v){
        final Calendar c = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        final EditText selectDate = findViewById(R.id.dateTxt);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        final CharSequence date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)+ "/"+String.valueOf(year);
                        selectDate.setText(date);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void selectMen(View v){
        ImageView iconoHombre = findViewById(R.id.hombreicono);
        ImageView iconoMujer = findViewById(R.id.mujericono);
        iconoHombre.setBackgroundColor(Color.parseColor("#65daca"));
        iconoMujer.setBackgroundColor(Color.TRANSPARENT);
        selectedGender = "Hombre";
    }

    public void selectWomen(View v){
        ImageView iconoHombre = findViewById(R.id.hombreicono);
        ImageView iconoMujer = findViewById(R.id.mujericono);
        iconoHombre.setBackgroundColor(Color.TRANSPARENT);
        iconoMujer.setBackgroundColor(Color.parseColor("#65daca"));
        selectedGender = "Mujer";
    }


    public void nextRegister(View v){
        EditText Nombre = findViewById(R.id.nameTxt);
        EditText Apellidos = findViewById(R.id.lastnameTxt);
        EditText Fecha = findViewById(R.id.dateTxt);
        EditText Estatura = findViewById(R.id.estaturaTxt);
        EditText Peso = findViewById(R.id.pesoTxt);
        if (Nombre.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Nombre",Toast.LENGTH_SHORT).show();
        }
        else if (Apellidos.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese sus Apellidos",Toast.LENGTH_SHORT).show();
        }
        else if (Fecha.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Fecha de nacimiento",Toast.LENGTH_SHORT).show();
        }
        else if (selectedGender.equals("")){
            Toast.makeText(getApplicationContext(),"Seleccione su Sexo",Toast.LENGTH_SHORT).show();
        }
        else if (Estatura.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Estatura",Toast.LENGTH_SHORT).show();
        }
        else if (Peso.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Peso",Toast.LENGTH_SHORT).show();
        }
        else{

        }
    }
}
