package com.rk.dataentry.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rk.dataentry.R;
import com.rk.dataentry.api.RetrofitClient;
import com.rk.dataentry.model.PostModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText et_Name,et_sales_id,et_mobile,et_Age,et_Address,et_applying_for;
    Button btn_submit;
    Spinner sp_gender;

    String[] Select_gender = {"Select Gender", "Male ", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        et_Name=findViewById(R.id.et_Name);
        et_sales_id=findViewById(R.id.et_sales_id);
        et_mobile=findViewById(R.id.et_mobile);
        et_Age=findViewById(R.id.et_Age);
        sp_gender = findViewById(R.id.sp_gender);
        et_Address=findViewById(R.id.et_Address);
        et_applying_for=findViewById(R.id.et_applying_for);
        btn_submit=findViewById(R.id.btn_submit);

        sp_gender.setOnItemSelectedListener(this);


        ArrayAdapter arrayGender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Select_gender);
        arrayGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(arrayGender);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_Name.getText().toString().equals("")){
                    et_Name.findFocus();
                    et_Name.setError("Enter Full Name");
                    Toast.makeText(MainActivity.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                }


                else if (et_sales_id.getText().toString().equals("")){
                    et_sales_id.findFocus();
                    et_sales_id.setError("Enter Sales ID");
                    Toast.makeText(MainActivity.this, "Enter Sales ID", Toast.LENGTH_SHORT).show();
                }


                else if (et_mobile.getText().toString().equals("")){
                    et_mobile.findFocus();
                    et_mobile.setError("Enter Mobile Number");
                    Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }else if (et_mobile.getText().toString().length()<11){
                    et_mobile.findFocus();
                    et_mobile.setError("Enter Correct Mobile Number");
                    Toast.makeText(MainActivity.this, "Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
                }else if (sp_gender.getSelectedItem().toString().equals("Select Gender")){
                    Toast.makeText(MainActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
                }else if (et_Age.getText().toString().equals("")){
                    et_Age.findFocus();
                    et_Age.setError("Enter Age");
                    Toast.makeText(MainActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();

                }else {
                    postData();
                }


            }

        });

    }

    public void postData(){
        RetrofitClient.getClient().postInformation(et_Name.getText().toString(),et_sales_id.getText().toString(),et_mobile.getText().toString(),et_Age.getText().toString(),sp_gender.getSelectedItem().toString(),et_Address.getText().toString(),et_applying_for.getText().toString()).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful()){

                    Intent intent=new Intent(getApplicationContext(),PostConfirmActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Add Unsuccess", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
