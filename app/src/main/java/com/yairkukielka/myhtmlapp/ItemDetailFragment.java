package com.yairkukielka.myhtmlapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yairkukielka.myhtmlapp.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_NAME = "item_name";
    public static final String ARG_ITEM_HTML_FLAG = "item_html_flag";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = getArguments().getString(ARG_ITEM_NAME);
        int htmlFlag = getArguments().getInt(ARG_ITEM_HTML_FLAG);
        mItem = new DummyContent.DummyItem(name, htmlFlag);

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle("Html tag flags");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

//        TextView textViewTitle = (TextView) rootView.findViewById(R.id.item_title);
//        textViewTitle.setText(mItem.name);

        TextView textViewDetail = (TextView) rootView.findViewById(R.id.item_detail);
        String source = inflater.getContext().getString(R.string.html, mItem.name);
        Spanned spannedText = Html.fromHtml(source, mItem.htmlFlag);
        textViewDetail.setText(spannedText);

        return rootView;
    }
}
