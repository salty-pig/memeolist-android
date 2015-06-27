package org.jboss.aerogear.memeolist.utils;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * Created by summers on 6/27/15.
 */
public final class UIUtils {
    private UIUtils(){}

    /**
     * Will set the content of the view to text and underline it.
     *
     * @param view a textView to add underlined text to
     * @param text text to underline
     */
    public static void setTextWithUnderline(@NonNull TextView view, @NonNull String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        view.setText(spanString);

    }

}
