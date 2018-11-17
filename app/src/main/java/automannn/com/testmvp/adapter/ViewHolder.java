package automannn.com.testmvp.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
        //代表视图 dto的根部局
        private View currView;

        public ViewHolder(View view){
            this.currView = view;
            currView.setTag(this);
        }

        public <T extends  View> T getView(int id){
            T t = (T) currView.findViewById(id);

            return t;
        }

        public View getRootView() {
            return currView;
        }


        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }

        public ViewHolder setImageResourceFromBitmap(int id, Bitmap bitmap) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageBitmap(bitmap);
            } else {
                Drawable drawable = new BitmapDrawable(bitmap);
                view.setBackground(drawable);
            }
            return this;
        }
        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }
        public void invalidate(){
            this.currView.invalidate();
        }

}
