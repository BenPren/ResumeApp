package ben.prendergast.resumeapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ben.prendergast.resumeapp.R;

/**
 * Created by Ben on 6/1/2015.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView homeTextView = (TextView)rootView.findViewById(R.id.homeTextView);
        // Have to set this in code since the HTML needs to be parsed.
        homeTextView.setText(Html.fromHtml(getString(R.string.home_text)));
        return rootView;
    }
}
