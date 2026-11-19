package com.vibe.periodictable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int BG = 0xFF10141A;
    private static final int TEXT_MAIN = 0xFFF2F4F8;
    private static final int TEXT_DIM = 0xFF9AA3AF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);

        ScrollView root = new ScrollView(this);
        root.setBackgroundColor(BG);
        root.setFillViewport(true);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(12), dp(20), dp(12), dp(16));
        root.addView(content, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView title = new TextView(this);
        title.setText("Periyodik Tablo");
        title.setTextColor(TEXT_MAIN);
        title.setTextSize(24);
        title.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        content.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Bölüm 1: 118 element · ayrıntı için dokunun");
        subtitle.setTextColor(TEXT_DIM);
        subtitle.setTextSize(13);
        subtitle.setPadding(0, dp(2), 0, dp(12));
        content.addView(subtitle);

        TextView gameButton = new TextView(this);
        gameButton.setText("🧪 Madde Ustası — bölüme oyna!");
        gameButton.setTextColor(0xFF10141A);
        gameButton.setTextSize(15);
        gameButton.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        gameButton.setGravity(Gravity.CENTER);
        gameButton.setPadding(dp(10), dp(14), dp(10), dp(14));
        GradientDrawable gbg = new GradientDrawable();
        gbg.setColor(0xFFAED581);
        gbg.setCornerRadius(dp(12));
        gameButton.setBackground(gbg);
        gameButton.setClickable(true);
        gameButton.setFocusable(true);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CompoundGameActivity.class));
            }
        });
        content.addView(gameButton, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams glp = (LinearLayout.LayoutParams) gameButton.getLayoutParams();
        glp.bottomMargin = dp(12);
        gameButton.setLayoutParams(glp);

        content.addView(buildTable());
        content.addView(buildLegend());
        setContentView(root);
    }

    private View buildTable() {
        HorizontalScrollView hScroll = new HorizontalScrollView(this);
        hScroll.setHorizontalScrollBarEnabled(false);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(18);

        for (int z = 1; z <= ElementTable.count(); z++) {
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(
                    GridLayout.spec(rowSlot(ElementTable.row(z)), GridLayout.FILL, 1f),
                    GridLayout.spec(ElementTable.col(z) - 1, GridLayout.FILL, 1f));
            lp.width = dp(42);
            lp.height = dp(46);
            lp.setMargins(dp(1), dp(1), dp(1), dp(1));
            grid.addView(cellView(z), lp);
        }

        hScroll.addView(grid);
        return hScroll;
    }

    private static int rowSlot(int row) {
        return gridRow(row);
    }

    static int gridRow(int row) {
        return row <= 8 ? row - 1 : row;
    }

    private TextView cellView(final int z) {
        TextView cell = new TextView(this);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(ElementTable.color(z));
        bg.setCornerRadius(dp(7));
        cell.setBackground(bg);
        cell.setText(Html.fromHtml("<small><small>" + z + "</small></small><br><b>"
                + ElementTable.SYMBOLS[z - 1] + "</b>"));
        cell.setTextColor(0xE6181A1E);
        cell.setTextSize(15);
        cell.setGravity(Gravity.CENTER);
        cell.setClickable(true);
        cell.setFocusable(true);
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showElementDialog(z);
            }
        });
        return cell;
    }

    private View buildLegend() {
        HorizontalScrollView hScroll = new HorizontalScrollView(this);
        hScroll.setHorizontalScrollBarEnabled(false);
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0, dp(14), 0, 0);

        String[] names = ElementTable.CATEGORY_NAMES;
        int[] colors = ElementTable.CATEGORY_COLORS;
        for (int i = 0; i < names.length; i++) {
            LinearLayout chip = new LinearLayout(this);
            chip.setGravity(Gravity.CENTER_VERTICAL);
            chip.setPadding(dp(6), dp(4), dp(6), dp(4));
            GradientDrawable chipBg = new GradientDrawable();
            chipBg.setColor(0x22FFFFFF);
            chipBg.setCornerRadius(dp(14));
            chip.setBackground(chipBg);

            View dot = new View(this);
            GradientDrawable dotBg = new GradientDrawable();
            dotBg.setColor(colors[i]);
            dotBg.setCornerRadius(dp(5));
            dot.setBackground(dotBg);
            chip.addView(dot, new LinearLayout.LayoutParams(dp(10), dp(10)));

            TextView label = new TextView(this);
            label.setText(" " + names[i]);
            label.setTextColor(TEXT_DIM);
            label.setTextSize(11);
            chip.addView(label);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = dp(6);
            row.addView(chip, lp);
        }
        hScroll.addView(row);
        return hScroll;
    }

    private void showElementDialog(final int z) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(20), dp(16), dp(20), dp(8));

        TextView symbol = new TextView(this);
        symbol.setText(ElementTable.SYMBOLS[z - 1]);
        symbol.setTextColor(ElementTable.color(z));
        symbol.setTextSize(44);
        symbol.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        box.addView(symbol);

        TextView name = new TextView(this);
        name.setText(z + " · " + ElementTable.NAMES_TR[z - 1]);
        name.setTextColor(TEXT_MAIN);
        name.setTextSize(18);
        name.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        name.setPadding(0, dp(2), 0, dp(10));
        box.addView(name);

        int r = ElementTable.row(z);
        String groupText = (r == 9 || r == 10)
                ? "3 (f-blok dizisi)"
                : String.valueOf(ElementTable.group(z));

        box.addView(detail("Tür", ElementTable.category(z)));
        box.addView(detail("Atom kütlesi", ElementTable.MASSES[z - 1] + " u"));
        box.addView(detail("Periyot", String.valueOf(ElementTable.period(z))));
        box.addView(detail("Grup", groupText));
        box.addView(detail("Elektron dizilimi", ElementTable.electronConfig(z)));

        ScrollView scroll = new ScrollView(this);
        scroll.addView(box);

        new AlertDialog.Builder(this)
                .setView(scroll)
                .setPositiveButton("Kapat", null)
                .show();
    }

    private TextView detail(String label, String value) {
        TextView tv = new TextView(this);
        tv.setText(Html.fromHtml("<b><font color='#9AA3AF'>" + label + ":</font></b> "
                + "<font color='#F2F4F8'>" + value + "</font>"));
        tv.setTextSize(14);
        tv.setPadding(0, dp(4), 0, dp(4));
        tv.setTextIsSelectable(true);
        return tv;
    }

    private int dp(int v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }
}
