package cc.xaabb.iconeditlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
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

    //region---------------整体布局layout----------------
    private LayoutParams layoutParams;
    private GradientDrawable layoutBackground;
    private int layoutPadding = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
    private int layoutPaddingLeft = layoutPadding;
    private int layoutPaddingRight = layoutPadding;
    private int layoutPaddingTop = layoutPadding;
    private int layoutPaddingBottom = layoutPadding;
    private int layoutRadius = 0;
    private int layoutColor = Color.parseColor("#FFFFFF");
    private int layoutStrokeWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
    private int layoutStrokeColor = Color.parseColor("#999999");
    //endregion

    //region---------------图标icon----------------
    private ImageView mImgIcon;
    private int mImgIconWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
    private int mImgIconHeight = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
    private Drawable mImgIconSrc;
    //endregion

    //region---------------分割线splitLine----------------
    private View mViewSplitLine;
    private float splitLineWidth = 0;
    private int splitLineMarginLeft = 0;
    private int splitLineMarginTop = 0;
    private int splitLineMarginRight = 0;
    private int splitLineMarginBottom = 0;
    private int splitLineColor = Color.parseColor("#999999");
    //endregion

    //region---------------输入框EditText----------------
    private EditText mEditText;
    private float mEditTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    private int mEditTextColor = Color.parseColor("#999999");
    private String mEditTextString;
    //endregion


    //region---------------清空imgBtn----------------
    private ImageView mImgClearBtn;
    private boolean isShowClearBtn = true;
    //endregion

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
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconEditLayout);
            layoutColor = a.getColor(R.styleable.IconEditLayout_layoutBackground, Color.parseColor("#FFFFFF"));
            layoutPadding = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutPadding, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
            layoutPaddingLeft = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutPaddingLeft, layoutPadding);

            layoutPaddingRight = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutPaddingRight, layoutPadding);

            layoutPaddingTop = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutPaddingTop, layoutPadding);

            layoutPaddingBottom = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutPaddingBottom, layoutPadding);

            layoutRadius = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutRadius, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));

            layoutStrokeColor = a.getColor(R.styleable.IconEditLayout_layoutStrokeColor, Color.parseColor("#999999"));

            layoutStrokeWidth = a.getDimensionPixelSize(R.styleable.IconEditLayout_layoutStrokeWidth, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));


            //---------------分割线splitLine----------------
            splitLineWidth = a.getDimensionPixelSize(R.styleable.IconEditLayout_splitLineWidth, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));

            splitLineMarginLeft = a.getDimensionPixelSize(R.styleable.IconEditLayout_splitLineMarginLeft, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));

            splitLineMarginTop = a.getDimensionPixelSize(R.styleable.IconEditLayout_splitLineMarginTop, 0);

            splitLineMarginRight = a.getDimensionPixelSize(R.styleable.IconEditLayout_splitLineMarginRight, 0);

            splitLineMarginBottom = a.getDimensionPixelSize(R.styleable.IconEditLayout_splitLineMarginBottom, 0);

            splitLineColor = a.getColor(R.styleable.IconEditLayout_splitLineColor, Color.parseColor("#999999"));


            //---------------图标icon----------------
            mImgIconHeight = a.getDimensionPixelSize(R.styleable.IconEditLayout_iconHeight, 30);

            mImgIconWidth = a.getDimensionPixelSize(R.styleable.IconEditLayout_iconWidth, 30);

            mImgIconSrc = a.getDrawable(R.styleable.IconEditLayout_iconSrc);


            //---------------输入框EditText----------------
            mEditTextString = a.getString(R.styleable.IconEditLayout_iconEditText);

            mEditTextColor = a.getColor(R.styleable.IconEditLayout_iconEditTextColor, Color.parseColor("#000000"));

            mEditTextSize = a.getDimensionPixelSize(R.styleable.IconEditLayout_iconEditTextSize, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));


            //---------------清空按钮imgBtn----------------
            isShowClearBtn = a.getBoolean(R.styleable.IconEditLayout_showDeleteButton, true);

        a.recycle();

        initLayout(context);
        initIcon(context);
        initSplitLine(context);
        initEdit(context);
        initClearBtn(context);
    }

    private void initLayout(Context context) {
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setOrientation(HORIZONTAL);
        setLayoutBackground();
        setLayoutPadding(layoutPaddingLeft, layoutPaddingTop, layoutPaddingRight, layoutPaddingBottom, layoutStrokeWidth);
    }

    private void initIcon(Context context) {
        mImgIcon = new ImageView(context);
        LayoutParams param = new LayoutParams(mImgIconWidth, mImgIconHeight);
        param.gravity = Gravity.LEFT;
        param.gravity = Gravity.CENTER_VERTICAL;
        mImgIcon.setLayoutParams(param);
        mImgIcon.setImageDrawable(mImgIconSrc);
        addView(mImgIcon);
    }

    private void initSplitLine(Context context) {
        if (splitLineWidth <= 0) {
            return;
        }
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
        params.rightMargin = layoutRadius;
        params.gravity = Gravity.CENTER_VERTICAL;
        mEditText.setLayoutParams(params);
        mEditText.setSingleLine(true);
        mEditText.setPadding(0, 0, 0, 0);
        mEditText.setText(mEditTextString);
        mEditText.setTextSize(mEditTextSize);
        mEditText.setTextColor(mEditTextColor);
        mEditText.setBackgroundColor(layoutColor);
        //set listener show clear button when EditText has text
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isShowClearBtn && s.length() > 0) {
                    mImgClearBtn.setVisibility(VISIBLE);
                } else {
                    mImgClearBtn.setVisibility(GONE);
                }

            }
        });
        addView(mEditText);
    }

    private void initClearBtn(Context context) {
        if (!isShowClearBtn) {
            return;
        }
        mImgClearBtn = new ImageView(context);
        LayoutParams param = new LayoutParams(mImgIconWidth, mImgIconWidth);
        param.gravity = Gravity.RIGHT;
        param.gravity = Gravity.CENTER_VERTICAL;
        mImgClearBtn.setLayoutParams(param);
        mImgClearBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImgClearBtn.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_close));
        //set listener clear EditText's text when mImgClearBtn click
        mImgClearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
        addView(mImgClearBtn);
    }


    //region-----------------------set method for layout-------------------------
    /**
     * set layout padding
     * */
    public void setLayoutPadding(int layoutPaddingLeft, int layoutPaddingTop, int layoutPaddingRight, int layoutPaddingBottom) {
        this.layoutPaddingLeft = layoutPaddingLeft;
        this.layoutPaddingTop = layoutPaddingTop;
        this.layoutPaddingRight = layoutPaddingRight;
        this.layoutPaddingBottom = layoutPaddingBottom;
        setLayoutPadding(layoutPaddingLeft, layoutPaddingTop, layoutPaddingRight, layoutPaddingBottom, layoutStrokeWidth);
    }

    /**
     * set layout padding with layoutStrokeWidth for show the stroke
     * */
    private void setLayoutPadding(int layoutPaddingLeft, int layoutPaddingTop, int layoutPaddingRight, int layoutPaddingBottom, int layoutStrokeWidth) {
        this.setPadding(layoutPaddingLeft + layoutStrokeWidth,
                layoutPaddingTop + layoutStrokeWidth,
                layoutPaddingRight + layoutStrokeWidth,
                layoutPaddingBottom + layoutStrokeWidth);
    }

    private void setLayoutBackground() {
        layoutBackground = new GradientDrawable();
        layoutBackground = CornerUtil.cornerDrawable(layoutBackground, layoutColor, layoutRadius);
        layoutBackground = CornerUtil.strokeDrawable(layoutBackground, layoutStrokeWidth, layoutStrokeColor);
        this.setBackground(layoutBackground);
        mEditText.setBackgroundColor(layoutColor);
    }
    public void setLayoutBackground(int bgColor, int layoutRadius, int strokeWidth, int strokeColor) {
        this.layoutColor = bgColor;
        this.layoutRadius = layoutRadius;
        this.layoutStrokeWidth = strokeWidth;
        this.layoutStrokeColor = strokeColor;
        this.setLayoutBackground();
    }

    public void setLayoutStrokeColor(int layoutStrokeColor) {
        this.layoutStrokeColor = layoutStrokeColor;
    }

    public void setLayoutStrokeWidth(int layoutStrokeWidth) {
        this.layoutStrokeWidth = layoutStrokeWidth;
    }

    public void setLayoutRadius(int layoutRadius) {
        this.layoutRadius = layoutRadius;
    }

    public void setLayoutColor(int layoutColor) {
        this.layoutColor = layoutColor;
    }
    //endregion




    public static class CornerUtil {
        public static GradientDrawable cornerDrawable(GradientDrawable bg, int bgColor, float cornerRadius) {
            bg.setCornerRadius(cornerRadius);
            bg.setColor(bgColor);
            return bg;
        }

        public static GradientDrawable strokeDrawable(GradientDrawable bg, int width, int color) {
            bg.setStroke(width, color);
            return bg;
        }
    }


}
