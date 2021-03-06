package com.example.roomres;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction buttonudenlogin = onView(
                allOf(withId(R.id.uden_login_button)));
        buttonudenlogin.check(matches(isDisplayed()));

        ViewInteraction buttonLogin = onView(
                allOf(withId(R.id.button_login)));
        buttonLogin.check(matches(isDisplayed()));

        ViewInteraction buttonRegister = onView(
                allOf(withId(R.id.button_Register)));
        buttonRegister.check(matches(isDisplayed()));

        ViewInteraction buttonSignOut = onView(
                allOf(withId(R.id.button_signOut)));
        buttonSignOut.check(matches(isDisplayed()));

        ViewInteraction editText = onView(withId(R.id.email_Edittext));
        editText.perform(replaceText("m@m.dk"));
        editText.check(matches(withText("m@m.dk")));

        ViewInteraction editText4 = onView(withId(R.id.password_Edittext));
        editText4.perform(replaceText("123456"));
        editText4.check(matches(withText("123456")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
