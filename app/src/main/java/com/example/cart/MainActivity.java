package com.example.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView txtpack1,txtprice1,txtpack2,txtprice2;
    Button btnpack1,btnpack2;
    DatabaseReference dbRef;
    Package pk;
    String cusname;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



                Intent i = getIntent();
                String s1 = i.getStringExtra("value");
                String s2 = i.getStringExtra("value1");
                String s3 = i.getStringExtra("value2");


                    pk.setName(txtpack1.getText().toString().trim());
                    pk.setPrice(Double.parseDouble(txtprice1.getText().toString().trim()));

                    pk.setDate(s1);
                    pk.setTime(s2);
                    pk.setType(s3);
                    pk.setCusName(cusname);


                DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Package");
                rref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.hasChild(pk.getDate()))
                            Toast.makeText(getApplicationContext(),"Date has already taken",Toast.LENGTH_SHORT).show();
                        else {

                            dbRef = FirebaseDatabase.getInstance().getReference().child("Package");
                            dbRef.child(pk.getDate()).setValue(pk);


                            Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();

                           Intent intent = new Intent(MainActivity.this,ShoppingCart.class);
                           intent.putExtra("date",pk.getDate());
                           startActivity(intent);

                      }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        btnpack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent o = getIntent();
                String s1 = o.getStringExtra("value");
                String s2 = o.getStringExtra("value1");
                String s3 = o.getStringExtra("value2");

                pk.setName(txtpack2.getText().toString().trim());
                pk.setPrice(Double.parseDouble(txtprice2.getText().toString().trim()));

                pk.setDate(s1);
                pk.setTime(s2);
                pk.setType(s3);
                pk.setCusName(cusname);
                DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Package");
                rref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(pk.getDate()))
                            Toast.makeText(getApplicationContext(),"Date has already taken",Toast.LENGTH_SHORT).show();
                        else {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Package");
                            dbRef.child(pk.getDate()).setValue(pk);
                            //dbRef.push().setValue(pk);




                            Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this,ShoppingCart.class);

                            i.putExtra("date",pk.getDate());

                            startActivity(i);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });


    }
    }


