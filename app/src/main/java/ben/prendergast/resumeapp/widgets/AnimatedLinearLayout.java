package ben.prendergast.resumeapp.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Created by Ben on 6/2/2015.
 */
public class AnimatedLinearLayout extends LinearLayout {

    private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    private float xFraction;

    public AnimatedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AnimatedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimatedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setXFraction(float fraction) {
        xFraction = fraction;
        if (getHeight() == 0) {
            if (mPreDrawListener == null) {
                mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(mPreDrawListener);
                        setXFraction(xFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);
            }
            return;
        }

        float translationX = getWidth() * fraction;
        setTranslationX(translationX);
    }

    public float getXFraction() {
        if (getWidth() == 0) {
            return 0;
        }
        return getTranslationX() / getWidth();
    }

}
