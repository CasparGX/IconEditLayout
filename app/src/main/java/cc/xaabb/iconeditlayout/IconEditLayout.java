package cc.xaabb.iconeditlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Caspar on 16/11/7.
 */

public class IconEditLayout extends LinearLayout {

    //---------------整体布局layout----------------
    private LayoutParams layoutParams;
    private int layoutPadding = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
    private int layoutPaddingLeft = layoutPadding;
    private int layoutPaddingRight = layoutPadding;
    private int layoutPaddingTop = layoutPadding;
    private int layoutPaddingBottom = layoutPadding;
    private int layoutRadius = 0;
    private int layoutColor = Color.parseColor("#FFFFFF");

    //---------------图标icon----------------
    private ImageView mImgIcon;
    private int mImgIconWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
    private int mImgIconHeight = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
    private Drawable mImgIconSrc;

    //---------------分割线splitLine----------------
    private View mViewSplitLine;
    private float splitLineWidth = 0;
    private int splitLineMarginLeft = layoutPadding;
    private int splitLineMarginTop = 0;
    private int splitLineMarginRight = layoutPadding;
    private int splitLineMarginBottom = 0;
    private int splitLineColor = Color.parseColor("#999999");

    //---------------输入框EditText----------------
    private EditText mEditText;
    private float mEditTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    private int mEditTextColor = Color.parseColor("#999999");
    private String mEditTextString;

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
                //---------------整体布局layout----------------
                case R.styleable.IconEditLayout_layoutBackground:
                    layoutColor = a.getColor(attr, Color.parseColor("#FFFFFF"));
                    break;
                case R.styleable.IconEditLayout_layoutPadding:
                    layoutPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.IconEditLayout_layoutPaddingLeft:
                    layoutPaddingLeft = a.getDimensionPixelSize(attr, layoutPadding);
                    break;
                case R.styleable.IconEditLayout_layoutPaddingRight:
                    layoutPaddingRight = a.getDimensionPixelSize(attr, layoutPadding);
                    break;
                case R.styleable.IconEditLayout_layoutPaddingTop:
                    layoutPaddingTop = a.getDimensionPixelSize(attr, layoutPadding);
                    break;
                case R.styleable.IconEditLayout_layoutPaddingBottom:
                    layoutPaddingBottom = a.getDimensionPixelSize(attr, layoutPadding);
                    break;
                case R.styleable.IconEditLayout_layoutRadius:
                    layoutRadius = a.getDimensionPixelSize(attr, 0);
                    break;

                //---------------分割线splitLine----------------
                case R.styleable.IconEditLayout_splitLineWidth:
                    splitLineWidth = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.IconEditLayout_splitLineMarginLeft:
                    splitLineMarginLeft = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.IconEditLayout_splitLineMarginTop:
                    splitLineMarginTop = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.IconEditLayout_splitLineMarginRight:
                    splitLineMarginRight = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.IconEditLayout_splitLineMarginBottom:
                    splitLineMarginBottom = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.IconEditLayout_splitLineColor:
                    splitLineColor = a.getColor(attr, Color.parseColor("#999999"));
                    break;

                //---------------图标icon----------------
                case R.styleable.IconEditLayout_iconHeight:
                    mImgIconHeight = a.getDimensionPixelSize(attr, 30);
                    break;
                case R.styleable.IconEditLayout_iconWidth:
                    mImgIconWidth = a.getDimensionPixelSize(attr, 30);
                    break;
                case R.styleable.IconEditLayout_iconSrc:
                    mImgIconSrc = a.getDrawable(attr);
                    break;

                //---------------输入框EditText----------------
                case R.styleable.IconEditLayout_iconEditText:
                    mEditTextString = a.getString(attr);
                    break;
                case R.styleable.IconEditLayout_iconEditTextColor:
                    mEditTextColor = a.getColor(attr, Color.parseColor("#000000"));
                    break;
                case R.styleable.IconEditLayout_iconEditTextSize:
                    mEditTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;


            }
        }
        a.recycle();
        init(context);
    }
    private void init(Context context) {
        initLayout(context);
        initIcon(context);
        initSplitLine(context);
        initEdit(context);
    }


    private void initLayout(Context context) {
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setOrientation(HORIZONTAL);
        this.setBackground(CornerUtil.cornerDrawable(layoutColor, layoutRadius));
        this.setPadding(layoutPaddingLeft,layoutPaddingTop,layoutPaddingRight,layoutPaddingBottom);
    }

    private void initIcon(Context context) {
        mImgIcon = new ImageView(context);
        LayoutParams param = new LayoutParams(mImgIconWidth, mImgIconHeight);
        param.gravity = Gravity.LEFT;
        param.gravity = Gravity.CENTER_VERTICAL;
        mImgIcon.setLayoutParams(param);
        mImgIcon.setId(R.id.img_id);
        mImgIcon.setImageDrawable(mImgIconSrc);
        addView(mImgIcon);
    }

    private void initSplitLine(Context context) {
        mViewSplitLine = new View(context);
        LayoutParams param = new LayoutParams((int) splitLineWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        param.bottomMargin = splitLineMarginBottom;
        param.leftMargin = splitLineMarginLeft;
        param.topMargin = splitLineMarginTop;
        param.rightMargin = splitLineMarginRight;
        mViewSplitLine.setLayoutParams(param);
        mViewSplitLine.setBackgroundColor(splitLineColor);
        addView(mViewSplitLine);
    }

    private void initEdit(Context context) {
        mEditText = new EditText(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        mEditText.setLayoutParams(params);
        mEditText.setPadding(0,0,0,0);
        mEditText.setText(mEditTextString);
        mEditText.setTextSize(mEditTextSize);
        mEditText.setTextColor(mEditTextColor);
        mEditText.setBackgroundColor(layoutColor);
        addView(mEditText);
    }


    public static class CornerUtil {
        public static Drawable cornerDrawable(int bgColor, float cornerRadius) {
            GradientDrawable bg = new GradientDrawable();
            bg.setCornerRadius(cornerRadius);
            bg.setColor(bgColor);
            return bg;
        }
    }


}
