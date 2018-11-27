package ia.shumilov.ru.hwfragments;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;

import static ia.shumilov.ru.hwfragments.CustomService.MY_ACTION;
import static ia.shumilov.ru.hwfragments.CustomService.MY_FILTER;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    private CallBack mCallBack;
    private String message;
    private static final String KEY_SAFE_STATE = "mess";


    // Store the listener that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CallBack) {
            mCallBack = (CallBack) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement CallBack");
        }
    }

    private EditText editTextFragment1;
    private BroadcastReceiver mReceiver;
    public static final IntentFilter mIntentFilter = new IntentFilter(MY_ACTION);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextFragment1 = view.findViewById(R.id.edit_text_fragmet1);
        editTextFragment1.setText(message);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                message = intent.getStringExtra(MY_FILTER);
                editTextFragment1.setText(message);
                mCallBack.setText(String.valueOf(editTextFragment1.getText()));
            }
        };
        registReceiver();
    }

    @SuppressLint("SetTextI18n")
    public void registReceiver() {
        getActivity().registerReceiver(mReceiver, mIntentFilter);
        editTextFragment1.setText("receiver turn on");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_SAFE_STATE, message);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            message = "new state";
        } else {
            message = savedInstanceState.getString(KEY_SAFE_STATE, "");
        }
    }
    public void unregistrReceiver(){
        Objects.requireNonNull(getActivity()).unregisterReceiver(mReceiver);
        editTextFragment1.setText("receiver turn off");
        System.out.println("receiver turn off");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
