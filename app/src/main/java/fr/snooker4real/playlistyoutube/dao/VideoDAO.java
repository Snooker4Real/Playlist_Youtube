package fr.snooker4real.playlistyoutube.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.snooker4real.playlistyoutube.db.VideoDBHelper;
import fr.snooker4real.playlistyoutube.model.Video;

public class VideoDAO extends DAO {

    public VideoDAO(Context context) {
        super(new VideoDBHelper(context));
    }

    public Video find(Long id) {
        Video video = null;

        open();

        Cursor cursor = db.rawQuery("select * from " + VideoDBHelper.VIDEO_TABLE_NAME +
                " where " + VideoDBHelper.VIDEO_KEY + " = ?", new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            video = new Video();
            video.setId(cursor.getLong(VideoDBHelper.VIDEO_KEY_COLUMN_INDEX));
            video.setDescription(cursor.getString(VideoDBHelper.VIDEO_DESCRIPTION_COLUMN_INDEX));
            video.setUrl(cursor.getString(VideoDBHelper.VIDEO_URL_COLUMN_INDEX));
            video.setCategory(cursor.getString(VideoDBHelper.VIDEO_CATEGORY_COLUMN_INDEX));
            cursor.close();
        }

        close();

        return video;

    }

    public List<Video> list() {
        List<Video> videos = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("select * from " + VideoDBHelper.VIDEO_TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Video video = new Video();
                video.setId(cursor.getLong(VideoDBHelper.VIDEO_URL_COLUMN_INDEX));
                video.setTitle(cursor.getString(VideoDBHelper.VIDEO_TITLE_COLUMN_INDEX));
                video.setDescription(cursor.getString(VideoDBHelper.VIDEO_URL_COLUMN_INDEX));
                video.setCategory(cursor.getString(VideoDBHelper.VIDEO_CATEGORY_COLUMN_INDEX));

                videos.add(video);

                cursor.moveToNext();
            }

            cursor.close();
        }

        close();

        return videos;
    }

    public void add(Video video) {
        open();

        ContentValues values = new ContentValues();

        values.put(VideoDBHelper.VIDEO_TITLE, video.getTitle());
        values.put(VideoDBHelper.VIDEO_DESCRIPTION, video.getDescription());
        values.put(VideoDBHelper.VIDEO_URL, video.getUrl());
        values.put(VideoDBHelper.VIDEO_CATEGORY, video.getCategory());

        long id = db.insert(VideoDBHelper.VIDEO_TABLE_NAME, null, values);
        video.setId(id);

        close();
    }

    public void update(Video video) {
        open();

        ContentValues values = new ContentValues();

        values.put(VideoDBHelper.VIDEO_TITLE, video.getTitle());
        values.put(VideoDBHelper.VIDEO_DESCRIPTION, video.getDescription());
        values.put(VideoDBHelper.VIDEO_URL, video.getUrl());
        values.put(VideoDBHelper.VIDEO_CATEGORY, video.getCategory());

        db.update(VideoDBHelper.VIDEO_TABLE_NAME, values, VideoDBHelper.VIDEO_KEY + " = ?",
                new String[]{String.valueOf(video.getId())});

        close();
    }

    public void delete(Video video) {
        open();

        db.delete(VideoDBHelper.VIDEO_TABLE_NAME, VideoDBHelper.VIDEO_KEY + " = ?", new String[]{String.valueOf(video.getId())});

        close();
    }


}
