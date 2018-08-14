package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract() {
    }


    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);
    public static final String PATH_PRODUCTS = "products";

    public static final class InventoryEntry implements BaseColumns {

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        //name of the table
        public static final String TABLE_NAME = "products";
        //unique id of product. type:INTEGER
        public static final String _ID = BaseColumns._ID;
        //name of the product. type: TEXT
        public static final String COLUMN_PRODUCT_NAME = "Product_Name";
        //price of the product. type: INTEGER
        public static final String COLUMN_PRICE = "Price";
        //quantity of the product in inventory. type: INTEGER
        public static final String COLUMN_QUANTITY = "Quantity";
        //supplier of the product. type: TEXT
        public static final String COLUMN_SUPPLIER_NAME = "Supplier_Name";
        // phone of the supplier type: INTEGER
        public static final String COLUMN_SUPPLIER_PHOHE = "Supplier_Phone_Number";
    }
}
