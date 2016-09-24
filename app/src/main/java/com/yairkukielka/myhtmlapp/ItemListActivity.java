package com.yairkukielka.myhtmlapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yairkukielka.myhtmlapp.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(getDummyItems()));
    }

    private List<DummyContent.DummyItem> getDummyItems() {
        DummyContent dummyContent = new DummyContent();
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_MODE_LEGACY", Html.FROM_HTML_MODE_LEGACY));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_MODE_COMPACT", Html.FROM_HTML_MODE_COMPACT));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_OPTION_USE_CSS_COLORS", Html.FROM_HTML_OPTION_USE_CSS_COLORS));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE", Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_DIV", Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_HEADING", Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_LIST", Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM", Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM));
        dummyContent.addItem(DummyContent.createDummyItem("FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH", Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        return dummyContent.getITEMS();
    }

    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mNameView.setText(mValues.get(position).name);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_NAME, holder.mItem.name);
                        arguments.putInt(ItemDetailFragment.ARG_ITEM_HTML_FLAG, holder.mItem.htmlFlag);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_NAME, holder.mItem.name);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_HTML_FLAG, holder.mItem.htmlFlag);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = (TextView) view.findViewById(R.id.name);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mNameView.getText() + "'";
            }
        }
    }
}
