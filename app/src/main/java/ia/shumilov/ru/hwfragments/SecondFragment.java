package ia.shumilov.ru.hwfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    private Button buttonCreateChildFragment;
    private FragmentTransaction mFragmentTransaction;
    public static final String CHILD_FRAGMENT_TAG = "child_fragment";
    public static final String KEY_SAFE_STATE = "key_for_button";
    private boolean flag = false;
    private FragmentManager mManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mManager = getFragmentManager();
        mFragmentTransaction = mManager.beginTransaction();
        if(flag){
            creatChildFragment(mFragmentTransaction);
        }

        buttonCreateChildFragment = view.findViewById(R.id.buttonFragment2);
        buttonCreateChildFragment.setEnabled(!flag);
        buttonCreateChildFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(new Intent(getActivity().getApplicationContext(), CustomService.class));
                creatChildFragment(mFragmentTransaction);
                buttonCreateChildFragment.setEnabled(false);
            }
        });


    }

    private void creatChildFragment(FragmentTransaction fragmentTransaction) {
        // добавляем фрагмент
        ChildFragment2 childFragment2 = new ChildFragment2();
        fragmentTransaction.add(R.id.fragment2, childFragment2, CHILD_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    public boolean getStateButton() {
        return buttonCreateChildFragment.isEnabled();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        flag = !getStateButton();
        outState.putBoolean(KEY_SAFE_STATE, flag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            flag = false;
        } else {
            flag = savedInstanceState.getBoolean(KEY_SAFE_STATE, false);
        }
    }
}
