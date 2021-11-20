package fr.snooker4real.playlistyoutube;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import fr.snooker4real.playlistyoutube.model.Video;

public class DetailsVideoActivity extends AppCompatActivity {

    private static final String YT_URL = "https://www.youtube.com/watch?v=";
    private Video video;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvUrl;
    private TextView tvCategory;
    private Button btnView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_video);

        tvTitle = findViewById(R.id.tvTitleDetails);
        tvDescription = findViewById(R.id.tvDescription);
        tvUrl = findViewById(R.id.tvUrlDetails);
        tvCategory = findViewById(R.id.tvCategoryDetails);
        btnView = findViewById(R.id.btnView);

        Intent i = getIntent();
        video = i.getParcelableExtra(MainActivity.KEY_VIDEO);

        System.out.println(video);

        tvTitle.setText(video.getTitle());
        tvDescription.setText(video.getDescription());
        tvUrl.setText(String.format(Locale.FRANCE, "%s%s", YT_URL, video.getUrl()));
        tvCategory.setText(video.getCategory());

        btnView.setOnClickListener(v -> {

            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getUrl()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YT_URL + video.getUrl()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        });
    }
}
