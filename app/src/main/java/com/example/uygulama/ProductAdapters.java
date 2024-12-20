package com.example.uygulama;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ProductAdapters extends RecyclerView.Adapter<ProductAdapters.viewHolder>{
    private ArrayList<productItem> productList;
    ArrayList<productItem>plist;
    Context context;
    public ProductAdapters(ArrayList<productItem> plist, Context context) {
        this.plist = plist;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.product_layout,parent,false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,int position){
        productItem currentProduct = plist.get(position);
     final productItem items= plist.get(position);
     holder.pimage.setImageResource(items.getImage());
     holder.desc.setText(items.getTitle());
     holder.price.setText(items.getPrice());
     holder.rank.setText(items.getRank());
        holder.productQuantity.setText("Adet: " + currentProduct.getQuantity());
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adet değeri arttırılır
                currentProduct.setQuantity(currentProduct.getQuantity() + 1);
                holder.productQuantity.setText("Adet: " + currentProduct.getQuantity());
            }
        });
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eğer adet 0'dan büyükse, adet azaltılabilir
                if (currentProduct.getQuantity() > 0) {
                    currentProduct.setQuantity(currentProduct.getQuantity() - 1);  // Adeti azalt
                    holder.productQuantity.setText("Adet: " + currentProduct.getQuantity());
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return plist.size();

    }
    public class viewHolder extends RecyclerView.ViewHolder{
ImageView pimage;
TextView desc,price,rank;
        public TextView productQuantity;
        public Button btnIncrease, btnDecrease, btnAddToCart;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pimage=itemView.findViewById(R.id.productimage);
            desc=itemView.findViewById(R.id.productdesc);
            price=itemView.findViewById(R.id.productprice);
            rank=itemView.findViewById(R.id.prank);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            productQuantity = itemView.findViewById(R.id.productQuantity);
        }
    }
}
