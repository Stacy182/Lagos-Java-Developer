package com.example.android.stacy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class DisplayUser extends AppCompatActivity {

    ImageLoader imageLoader = Controller.getInstance().getImageLoader();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_user);

        Intent i = getIntent();
        String login= i.getStringExtra("login");
        String ID = i.getStringExtra("id");
      String image = i.getStringExtra("avatar_url");
        String get_url =i.getStringExtra("url");


        final TextView txtname = (TextView) findViewById(R.id.name);
        TextView txtId = (TextView) findViewById(R.id._id);
        final TextView Murl = (TextView) findViewById(R.id._github_url);
        Button shareUser = (Button) findViewById(R.id.button);

        NetworkImageView imgflag = ( NetworkImageView) findViewById(R.id.thumbnail);

        txtname.setText(login);
        txtId.setText(ID);
         Murl.setText(get_url);
        imgflag.setImageUrl(image, imageLoader);

        Murl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String urlAsString = Murl.getText().toString();
                openWebPage(urlAsString);
            }

        });

        shareUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textThatYouWantToShare =
                        "Check out this awesome developer " +
                                txtname+ ","+Murl;
                shareText(textThatYouWantToShare);
            }
        });




    }



    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void shareText(String textToShare) {

        String mimeType = "text/plain";

        String title = "share dev";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }



}
