package com.shimotsuji.android.taxcalculator;

import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCalculateTaxIncludedPrice_validInput() {
        Espresso.onView(ViewMatchers.withId(R.id.priceEditText)).perform(ViewActions.typeText("100"));
        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.resultTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("税込み価格: 110")));
    }

    @Test
    public void testCalculateTaxIncludedPrice_emptyInput() {
        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.resultTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("金額を入力してください。")));
    }

    @Test
    public void testCalculateTaxIncludedPrice_invalidInput() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            activity.runOnUiThread(() -> {
                EditText editText = activity.findViewById(R.id.priceEditText);
                editText.setText("abc");
            });
        });
        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.resultTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("有効な金額を入力してください。")));
    }
}