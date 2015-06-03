package ben.prendergast.resumeapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ben.prendergast.resumeapp.R;

/**
 * And example of some of the meterial design elements.
 *
 * Created by Ben on 6/2/2015.
 */
public class MaterialFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_material, container, false);
        RecyclerView listView = (RecyclerView)rootView.findViewById(R.id.materialList);
        // This will define how the Recycler is laid out.
        // Let's just go with a vertical list for now.
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // The content won't change the size of the list view so we can use this optimization.
        listView.setHasFixedSize(true);
        listView.setAdapter(new MaterialAdapter());
        return rootView;
    }

    /**
     * A really simple adapter for the recycler view.
     * Typically these will get broken out with much more functionality inside of them.
     */
    private class MaterialAdapter extends RecyclerView.Adapter<MaterialHolder> {
        String [] mContent;

        public MaterialAdapter() {
            mContent = getResources().getStringArray(R.array.material_content_array);
        }

        @Override
        public MaterialHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // Passing the view group, rather than null allows the layout parameters
            // included in the layout to be inflated properly
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_material_item, viewGroup, false);
            MaterialHolder holder = new MaterialHolder(view);
            // I would usually put any button press logic here
            return holder;
        }

        @Override
        public void onBindViewHolder(MaterialHolder viewHolder, int i) {
            viewHolder.contentText.setText(mContent[i]);
        }

        @Override
        public int getItemCount() {
            return mContent.length;
        }
    }

    /**
     * I made this static so that I do not keep local references to the parent activity, like the adapter does.
     */
    public static class MaterialHolder extends RecyclerView.ViewHolder {
        public TextView contentText;
        public MaterialHolder(View itemView) {
            super(itemView);
            contentText = (TextView)itemView.findViewById(R.id.contentText);
        }
    }

}
