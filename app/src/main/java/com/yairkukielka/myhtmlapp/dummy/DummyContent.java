package com.yairkukielka.myhtmlapp.dummy;

import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    private final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public List<DummyItem> getITEMS() {
        return ITEMS;
    }

    public void addItem(DummyItem item) {
        ITEMS.add(item);
    }

    public static DummyItem createDummyItem(String name, int htmlFlag) {
        return new DummyItem(name, htmlFlag);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String name;
        public final int htmlFlag;

        public DummyItem(String name, int htmlFlag) {
            this.name = name;
            this.htmlFlag = htmlFlag;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
