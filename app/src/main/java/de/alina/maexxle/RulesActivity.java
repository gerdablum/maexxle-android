package de.alina.maexxle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate
            (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        this.setTitle(R.string.toolbar_rules_caption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.hideOverflowMenu();


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        TextView heading = (TextView) findViewById(R.id.headingRules);
        TextView ruleText1 = (TextView) findViewById(R.id.textRules);
        TextView ruleText2 = (TextView) findViewById(R.id.textRules2);
        TextView ruleText3 = (TextView) findViewById(R.id.textRules3);
        TextView ruleText4 = (TextView) findViewById(R.id.textRules4);
        TextView ruleText5 = (TextView) findViewById(R.id.textRules5);
        TextView ruleValues = (TextView) findViewById(R.id.rulesValues);
        heading.setText(R.string.rules_caption);
        ruleText1.setText(R.string.rules1);
        ruleText2.setText(R.string.rules2);
        ruleText3.setText(R.string.rules3);
        ruleText4.setText(R.string.rules4);
        ruleText5.setText(R.string.rules5);
        ruleValues.setText(R.string.rules_values);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
