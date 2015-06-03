package ben.prendergast.resumeapp.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Handles any fonts that we use in the application.
 * Mostly likely from @see CustomTextView
 * Created by Ben on 6/1/2015.
 */
public class FontManager {
    private static Hashtable<String, Typeface> mFontCache = new Hashtable<>();

    private FontManager() { }

    /**
     * This trusts that you did not put .otf at the end of the fontName
     * @param context - used to retrieve the AssetManager
     * @param fontName - no .otf at the end!
     *
     * @return the font we just opened or the cached one
     */
    public static Typeface getFont(Context context, String fontName) {
        if(mFontCache.containsKey(fontName)) {
            return mFontCache.get(fontName);
        }
        try {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName + ".ttf");
            mFontCache.put(fontName, typeface);
            return typeface;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
