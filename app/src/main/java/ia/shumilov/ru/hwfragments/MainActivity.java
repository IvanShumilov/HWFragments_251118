package ia.shumilov.ru.hwfragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static ia.shumilov.ru.hwfragments.SecondFragment.CHILD_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements CallBack {
private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    public static final int SLEEP_THREAD_MILS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        mSecondFragment = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

    }

    @Override
    public void setText(String value) {
        if(!mSecondFragment.getStateButton()) {
            ChildFragment2 mChildFragment2 = (ChildFragment2) getSupportFragmentManager().findFragmentByTag(CHILD_FRAGMENT_TAG);
            if (mChildFragment2 != null && mChildFragment2.isVisible()) {
                mChildFragment2.setTextViewValueChildFragment(value);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirstFragment.unregistrReceiver();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirstFragment.registReceiver();
    }

}
