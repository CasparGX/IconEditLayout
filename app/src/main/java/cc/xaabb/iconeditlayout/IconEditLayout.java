package cc.xaabb.iconeditlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Caspar on 16/11/7.
 */

public class IconEditLayout extends LinearLayout {

    private ImageView mImgIcon;
    private int mImgIconWidth = 0;
    private int mImgIconHeight = 0;
    private Drawable mImgIconSrc;

    private Paint mImgIconPaint;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IconEditLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public IconEditLayout(Context context) {
        this(context, null);
    }

    public IconEditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IconEditLayout, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.IconEditLayout_iconHeight:
                    mImgIconHeight = a.getDimensionPixelSize(attr, 30);
                    break;
                case R.styleable.IconEditLayout_iconWidth:
                    mImgIconWidth = a.getDimensionPixelSize(attr, 30);
                    break;
                case R.styleable.IconEditLayout_iconSrc:
                    mImgIconSrc = a.getDrawable(attr);
                    break;
            }
        }
        a.recycle();
        init(context);
    }
    private void init(Context context) {
        mImgIcon = new ImageView(context);
        mImgIcon.setId(R.id.img_id);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(mImgIconWidth, mImgIconHeight);
        mImgIcon.setLayoutParams(param);
        mImgIcon.setImageDrawable(mImgIconSrc);
        addView(mImgIcon);
    }


}
