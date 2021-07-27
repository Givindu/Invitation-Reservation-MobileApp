package com.example.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdatePackage extends AppCompatActivity {

    TextView txtpack1,txtprice1,txtpack2,txtprice2;
    Button btnpack1,btnpack2;
    DatabaseReference dbRef;
    Package pk;
    String cusname,kala;

    String vari= "second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_package);

        btnpack1 = findViewById(R.id.btnpack1);
        txtpack1 = findViewById(R.id.txtpack1);
        txtprice1 = findViewById(R.id.txtprice1);

        btnpack2 = findViewById(R.id.btnpack2);
        txtpack2 = findViewById(R.id.txtpack2);
        txtprice2 = findViewById(R.id.txtprice2);

        pk = new Package();

        cusname = "Lakshan";


        btnpack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // dbRef = FirebaseDatabase.getInstance().getReference().child("Package");

                Intent i = getIntent();
                String s1 = i.getStringExtra("value");
                String s2 = i.getStringExtra("value1");
                String s3 = i.getStringExtra("value2");
                      kala = i.getStringExtra("laka");

                pk.setName(txtpack1.getText().toString().trim());
                pk.setPrice(Double.parseDouble(txtprice1.getText().toString().trim()));

                pk.setDate(s1);
                pk.setTime(s2);
                pk.setType(s3);
                pk.setCusName(cusname);

                final DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Package");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(kala))
                        upref.child(pk.getDate()).setValue(pk);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdatePackage.this,ShoppingCart.class);
                intent.putExtra("date",pk.getDate());
                startActivity(intent);



            }

        });






        btnpack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // dbRef = FirebaseDatabase.getInstance().getReference().child("Package");

                Intent i = getIntent();
                String s1 = i.getStringExtra("value");
                String s2 = i.getStringExtra("value1");
                String s3 = i.getStringExtra("value2");
                kala = i.getStringExtra("laka");

                pk.setName(txtpack2.getText().toString().trim());
                pk.setPrice(Double.parseDouble(txtprice2.getText().toString().trim()));

                pk.setDate(s1);
                pk.setTime(s2);
                pk.setType(s3);
                pk.setCusName(cusname);

                final DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Package");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(kala))
                            upref.child(pk.getDate()).setValue(pk);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdatePackage.this,ShoppingCart.class);

                intent.putExtra("check",vari);
                intent.putExtra("date",pk.getDate());


                startActivity(intent);

            }

        });
    }


}