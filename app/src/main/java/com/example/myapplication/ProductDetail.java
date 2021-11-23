package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String createdAt = intent.getStringExtra("productCreate");
        String productImage = intent.getStringExtra("productImage");

        TextView productDetailName = (TextView) findViewById(R.id.productDetailName);
        TextView productDetailPrice = (TextView) findViewById(R.id.productDetailPrice);
        TextView productDetailCreated = (TextView) findViewById(R.id.productDetailCreated);
        ImageView productDetailImage = (ImageView) findViewById(R.id.productDetailImage);

        productDetailName.setText(productName);
        productDetailPrice.setText(productPrice);
        productDetailCreated.setText(createdAt);

        Picasso.get()
                .load(productImage)
                .into(productDetailImage);


    }
}