package ammu.com.privatechat.utils.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import ammu.com.privatechat.PrivateChat;
import ammu.com.privatechat.R;

public class ImageBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, File imageUrl) {
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_profile).error(R.drawable.ic_profile).
                into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(PrivateChat.getAppContext().getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        imageView.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError(Exception e) {
                        imageView.setImageResource(R.drawable.ic_profile);
                    }
                });
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_profile).error(R.drawable.ic_profile).
                into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(PrivateChat.getAppContext().getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        imageView.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError(Exception e) {
                        imageView.setImageResource(R.drawable.ic_profile);
                    }
                });
    }
}
