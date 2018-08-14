package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

public class InventoryCursorAdapter extends CursorAdapter{

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_product, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView productNameView = view.findViewById(R.id.product_name);
        TextView priceView = view.findViewById(R.id.price);
        TextView quantityView = view.findViewById(R.id.quantity);
        Button sellButton =view.findViewById(R.id.buttonSell);

        int productId = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry._ID));
        final Uri contentUri = Uri.withAppendedPath(InventoryContract.InventoryEntry.CONTENT_URI, Integer.toString(productId));

        int productNameColumnIndex =
                cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex =
                cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE);
        final int quantityColumnIndex =
                cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY);

        String productName = cursor.getString(productNameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        final String quantity = cursor.getString(quantityColumnIndex);

        productNameView.setText(productName);
        priceView.setText(price);
        quantityView.setText(quantity);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantityInt = Integer.parseInt(quantity);
                if(quantityInt>0){
                    quantityInt =quantityInt-1;
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY,quantityInt);
                    context.getContentResolver().update(contentUri,values,null,null);
                    Toast.makeText(context, R.string.sold, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,R.string.not_enough_inventory,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
