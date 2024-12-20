package com.example.uygulama;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ShopActivity extends AppCompatActivity {
    ViewFlipper imgBanner;
    ArrayList<productItem>productlist;
    ProductAdapters adapters;
    RecyclerView playout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        imgBanner = findViewById(R.id.imgBanner);
        int sliders[] = {
                R.drawable.banner1, R.drawable.banner2, R.drawable.banner3
        };
        for (int slide : sliders) {
            bannerFlipper(slide);
        }
        playout= findViewById(R.id.productcard);
        productlist=new ArrayList<>();
        productlist.add(new productItem(R.drawable.gorsel1,"10.000","37 Değerlendirme","Akıllı Saat",1,0));
        productlist.add(new productItem(R.drawable.gorsel2,"60.000","64 Değerlendirme","Telefon",2,0));
        productlist.add(new productItem(R.drawable.gorsel3,"1.000","43 Değerlendirme","Elbise",3,0));
        productlist.add(new productItem(R.drawable.gorsel4,"4.500","16 Değerlendirme","Mutfak Robotu",4,0));
        productlist.add(new productItem(R.drawable.gorsel5,"2.500","20 Değerlendirme","Kahve Makinesi",5,0));
        productlist.add(new productItem(R.drawable.gorsel6,"250","3 Değerlendirme","Eldiven",6,0));
        adapters=new ProductAdapters(productlist,this);
        playout.setAdapter(adapters);
        playout.setLayoutManager(new LinearLayoutManager(this));
    }
    public void bannerFlipper(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imgBanner.addView(imageView);
        imgBanner.setFlipInterval(3000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this, android.R.anim.fade_in);
        imgBanner.setOutAnimation(this, android.R.anim.fade_out);
    }

}

