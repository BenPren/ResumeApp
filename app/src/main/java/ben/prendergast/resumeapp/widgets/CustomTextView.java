package ben.prendergast.resumeapp.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import ben.prendergast.resumeapp.R;
import ben.prendergast.resumeapp.utils.FontManager;

/**
 * Will handle custom font behavior and anything else Android doesn't give us.
 *
 * Created by Ben on 6/1/2015.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context, attrs);
    }
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView(context, attrs);
    }

    /**
     * Setup the custom attributes
     */
    private void setupView(Context context, AttributeSet attrs) {
        // Only do this if we are not in the layout editor
        if (!isInEditMode()) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

            if (ta != null) {
                String fontAsset = ta.getString(R.styleable.CustomTextView_textFont);

                if (fontAsset != null && fontAsset.length() > 0) {
                    Typeface tf = FontManager.getFont(context, fontAsset);
                    if (tf != null) {
                        setTypeface(tf);
                    }
                }
            }
        }
    }
}
