package com.vibe.periodictable;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class CompoundGameActivity extends Activity {

    private static final int BG = 0xFF10141A;
    private static final int CARD = 0xFF1B2230;
    private static final int TEXT_MAIN = 0xFFF2F4F8;
    private static final int TEXT_DIM = 0xFF9AA3AF;
    private static final int GREEN = 0xFF66BB6A;
    private static final int RED = 0xFFEF5350;
    private static final int ACCENT = 0xFF1E88E5;

    private final int[] beaker = new int[119];
    private final ArrayList<Integer> beakerOrder = new ArrayList<Integer>();
    private final Random random = new Random();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private int target;
    private int score;
    private boolean hintShown;
    private boolean solving;

    private TextView targetView;
    private TextView scoreView;
    private TextView beakerChips;
    private TextView statusView;

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
        content.setPadding(dp(14), dp(18), dp(14), dp(16));
        root.addView(content, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);

        TextView title = new TextView(this);
        title.setText(L10n.pick(this, "⚗️ Simya Ustası", "⚗️ Alchemy Master"));
        title.setTextColor(TEXT_MAIN);
        title.setTextSize(22);
        title.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        header.addView(title, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        scoreView = new TextView(this);
        scoreView.setTextColor(GREEN);
        scoreView.setTextSize(15);
        scoreView.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        header.addView(scoreView);

        content.addView(header);
        addSpacer(content, 2);

        TextView sub = new TextView(this);
        sub.setText(L10n.pick(this,
                "İstenen maddeyi tablondan atomları seçerek kapta oluştur!",
                "Build the requested substance by picking atoms from the table!"));
        sub.setTextColor(TEXT_DIM);
        sub.setTextSize(13);
        content.addView(sub);
        addSpacer(content, 12);

        content.addView(label(L10n.pick(this, "Hedef madde", "Target substance"), TEXT_DIM, 13));
        addSpacer(content, 4);

        targetView = new TextView(this);
        LinearLayout targetCard = new LinearLayout(this);
        targetCard.setGravity(Gravity.CENTER);
        targetCard.setPadding(dp(14), dp(14), dp(14), dp(14));
        GradientDrawable cardBg = new GradientDrawable();
        cardBg.setColor(CARD);
        cardBg.setCornerRadius(dp(12));
        targetCard.setBackground(cardBg);
        targetView.setTextSize(26);
        targetView.setTextColor(TEXT_MAIN);
        targetView.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        targetCard.addView(targetView);
        content.addView(targetCard);
        addSpacer(content, 12);

        content.addView(label(L10n.pick(this, "Kap — son atılan atomu silmek için ⌫ Sil",
                "Beaker — press ⌫ Delete to remove the last atom"), TEXT_DIM, 12));
        addSpacer(content, 4);

        LinearLayout beakerCard = new LinearLayout(this);
        beakerCard.setPadding(dp(10), dp(10), dp(10), dp(10));
        GradientDrawable beakerBg = new GradientDrawable();
        beakerBg.setColor(0xFF16202E);
        beakerBg.setStroke(dp(2), ACCENT);
        beakerBg.setCornerRadius(dp(12));
        beakerCard.setBackground(beakerBg);

        HorizontalScrollView chipScroll = new HorizontalScrollView(this);
        chipScroll.setHorizontalScrollBarEnabled(false);
        beakerChips = new TextView(this);
        beakerChips.setTextSize(16);
        chipScroll.addView(beakerChips);
        beakerCard.addView(chipScroll);
        content.addView(beakerCard);
        addSpacer(content, 10);

        content.addView(buildButtons());
        addSpacer(content, 6);

        statusView = new TextView(this);
        statusView.setTextSize(14);
        statusView.setPadding(dp(4), dp(4), dp(4), dp(4));
        content.addView(statusView);
        addSpacer(content, 10);

        content.addView(label(L10n.pick(this, "Atom eklemek için tablodaki elemente dokun",
                "Tap an element in the table to add atoms"), TEXT_DIM, 12));
        addSpacer(content, 6);
        content.addView(buildGrid());

        nextTarget(false);
        setContentView(root);
    }

    private TextView label(String text, int color, float size) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(color);
        tv.setTextSize(size);
        return tv;
    }

    private void addSpacer(LinearLayout parent, int dp_) {
        View v = new View(this);
        parent.addView(v, new LinearLayout.LayoutParams(1, dp(dp_)));
    }

    private View buildButtons() {
        LinearLayout wrap = new LinearLayout(this);
        wrap.setOrientation(LinearLayout.VERTICAL);

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row1.addView(button(L10n.pick(this, "✅ Oluştur", "✅ Create"), GREEN, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solving) return;
                tryCreate();
            }
        }), rowParams());
        row1.addView(button(L10n.pick(this, "⌫ Sil", "⌫ Delete"), TEXT_MAIN, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLast();
            }
        }), rowParams());
        row1.addView(button(L10n.pick(this, "💡 İpucu", "💡 Hint"), 0xFFFFB74D, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solving) return;
                hintShown = true;
                setStatus(L10n.pick(CompoundGameActivity.this, "İpucu → ", "Hint → ")
                        + Compounds.pretty(Compounds.FORMULAS[target]), 0xFFFFB74D);
            }
        }), rowParams());
        wrap.addView(row1);

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row2.addView(button(L10n.pick(this, "🧹 Temizle", "🧹 Clear"), TEXT_DIM, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solving) return;
                clearBeaker();
                setStatus(L10n.pick(CompoundGameActivity.this, "Kap boşaltıldı.", "Beaker emptied."), TEXT_DIM);
                refreshBeaker();
            }
        }), rowParams());
        row2.addView(button(L10n.pick(this, "🪄 Kendin Oluştur", "🪄 Create for me"), 0xFF64B5F6, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoSolve();
            }
        }), rowParams());
        wrap.addView(row2);

        return wrap;
    }

    private LinearLayout.LayoutParams rowParams() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        lp.rightMargin = dp(6);
        return lp;
    }

    private TextView button(String text, int color, View.OnClickListener listener) {
        TextView b = new TextView(this);
        b.setText(text);
        b.setTextColor(0xFF10141A);
        b.setTextSize(14);
        b.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        b.setGravity(Gravity.CENTER);
        b.setPadding(dp(8), dp(12), dp(8), dp(12));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(dp(10));
        b.setBackground(bg);
        b.setClickable(true);
        b.setFocusable(true);
        b.setOnClickListener(listener);
        return b;
    }

    private View buildGrid() {
        HorizontalScrollView hScroll = new HorizontalScrollView(this);
        hScroll.setHorizontalScrollBarEnabled(false);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(18);

        for (int z = 1; z <= ElementTable.count(); z++) {
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(
                    GridLayout.spec(MainActivity.gridRow(ElementTable.row(z)), GridLayout.FILL, 1f),
                    GridLayout.spec(ElementTable.col(z) - 1, GridLayout.FILL, 1f));
            lp.width = dp(36);
            lp.height = dp(38);
            lp.setMargins(dp(1), dp(1), dp(1), dp(1));
            grid.addView(gridCell(z), lp);
        }

        hScroll.addView(grid);
        return hScroll;
    }

    private TextView gridCell(final int z) {
        TextView cell = new TextView(this);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(ElementTable.color(z));
        bg.setCornerRadius(dp(6));
        cell.setBackground(bg);
        cell.setText(Html.fromHtml("<small><small>" + z + "</small></small><br><b>"
                + ElementTable.SYMBOLS[z - 1] + "</b>"));
        cell.setTextColor(0xE6181A1E);
        cell.setTextSize(13);
        cell.setGravity(Gravity.CENTER);
        cell.setClickable(true);
        cell.setFocusable(true);
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solving) return;
                beaker[z]++;
                beakerOrder.add(z);
                setStatus(null, 0);
                refreshBeaker();
            }
        });
        return cell;
    }

    private void clearBeaker() {
        java.util.Arrays.fill(beaker, 0);
        beakerOrder.clear();
        setStatus(null, 0);
        refreshBeaker();
    }

    private void removeLast() {
        if (solving) return;
        if (beakerOrder.isEmpty()) {
            setStatus(L10n.pick(this, "Kap zaten boş.", "Beaker is already empty."), TEXT_DIM);
            return;
        }
        int z = beakerOrder.remove(beakerOrder.size() - 1);
        beaker[z]--;
        setStatus(L10n.pick(this,
                "⌫ " + L10n.elementName(this, z) + " silindi.",
                "⌫ Removed " + L10n.elementName(this, z) + "."), TEXT_DIM);
        refreshBeaker();
    }

    private void refreshBeaker() {
        if (beakerOrder.isEmpty()) {
            beakerChips.setText(L10n.pick(this, "🧪 Boş — atom ekleyin", "🧪 Empty — add atoms"));
            beakerChips.setTextColor(TEXT_DIM);
        } else {
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < beakerOrder.size(); i++) {
                int z = beakerOrder.get(i);
                String hex = String.format("#%06X", 0xFFFFFF & ElementTable.color(z));
                html.append("<b><font color='").append(hex).append("'>")
                        .append(ElementTable.SYMBOLS[z - 1]).append("</font></b>  ");
            }
            beakerChips.setText(Html.fromHtml("🧪  " + html.toString().trim()));
            beakerChips.setTextColor(TEXT_MAIN);
        }
        scoreView.setText(L10n.pick(this, "Skor: ", "Score: ") + score);
    }

    private boolean sameComposition(int[] a, int[] b) {
        for (int z = 1; z <= ElementTable.count(); z++) {
            if (a[z] != b[z]) return false;
        }
        return true;
    }

    private void autoSolve() {
        if (solving) return;
        solving = true;
        clearBeaker();
        setStatus(L10n.pick(this,
                "🪄 Ben yapıyorum: " + L10n.compoundName(this, target) + "...",
                "🪄 Making: " + L10n.compoundName(this, target) + "..."), 0xFF64B5F6);

        final ArrayList<Integer> units = Compounds.sequence(target);
        final long step = 250L;
        final int count = units.size();
        for (int i = 0; i < count; i++) {
            final int z = units.get(i);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    beaker[z]++;
                    beakerOrder.add(z);
                    setStatus(null, 0);
                    refreshBeaker();
                }
            }, step * (i + 1));
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setStatus(L10n.pick(CompoundGameActivity.this,
                        "🪄 " + L10n.compoundName(CompoundGameActivity.this, target) + " ("
                                + Compounds.pretty(Compounds.FORMULAS[target]) + ") oluştu! Sıradaki geliyor...",
                        "🪄 " + L10n.compoundName(CompoundGameActivity.this, target) + " ("
                                + Compounds.pretty(Compounds.FORMULAS[target]) + ") created! Next one coming..."),
                        GREEN);
            }
        }, step * count + 350L);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextTarget(true);
            }
        }, step * count + 1900L);
    }

    private void tryCreate() {
        if (beakerOrder.isEmpty()) {
            setStatus(L10n.pick(this, "⚠️ Kap boş! Önce atom ekleyin.", "⚠️ Beaker is empty! Add atoms first."), RED);
            return;
        }
        int[] inBeaker = new int[119];
        for (int i = 0; i < beakerOrder.size(); i++) {
            inBeaker[beakerOrder.get(i)]++;
        }
        int[] wanted = Compounds.composition(target);
        if (sameComposition(inBeaker, wanted)) {
            score++;
            refreshBeaker();
            new AlertDialog.Builder(this)
                    .setTitle(L10n.pick(this, "🎉 Doğru!", "🎉 Correct!"))
                    .setMessage(L10n.compoundName(this, target) + " " + L10n.pick(this, "oluştu! (", "created! (")
                            + Compounds.pretty(Compounds.FORMULAS[target]) + ")\n"
                            + L10n.pick(this, "Skor: ", "Score: ") + score)
                    .setPositiveButton(L10n.pick(this, "Sonraki madde ➜", "Next substance ➜"), null)
                    .show();
            nextTarget(true);
        } else {
            for (int i = 0; i < Compounds.count(); i++) {
                if (i != target && sameComposition(inBeaker, Compounds.composition(i))) {
                    setStatus(L10n.pick(this,
                            "⚠️ Bu atomlar \"" + L10n.compoundName(this, i) + "\" oluşturur, \""
                                    + L10n.compoundName(this, target) + "\" değil! (🪄 Kendin Oluştur'u deneyin)",
                            "⚠️ These atoms form \"" + L10n.compoundName(this, i) + "\", not \""
                                    + L10n.compoundName(this, target) + "\"! Try 🪄 Create for me"), RED);
                    return;
                }
            }
            setStatus(L10n.pick(this,
                    "⚠️ Bu atomlar \"" + L10n.compoundName(this, target) + "\" oluşturmaz! (🪄 Kendin Oluştur'u deneyin)",
                    "⚠️ These atoms do not form \"" + L10n.compoundName(this, target) + "\"! Try 🪄 Create for me"), RED);
        }
    }

    private void nextTarget(boolean clear) {
        handler.removeCallbacksAndMessages(null);
        int prev = target;
        int n = Compounds.count();
        int next = random.nextInt(n);
        while (next == prev) next = random.nextInt(n);
        target = next;
        if (clear) {
            java.util.Arrays.fill(beaker, 0);
            beakerOrder.clear();
        }
        hintShown = false;
        solving = false;
        targetView.setText(L10n.compoundName(this, target));
        setStatus(null, 0);
        refreshBeaker();
    }

    private void setStatus(String text, int color) {
        if (text == null) {
            statusView.setText("");
        } else {
            statusView.setText(text);
            statusView.setTextColor(color);
        }
    }

    private int dp(int v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }
}
