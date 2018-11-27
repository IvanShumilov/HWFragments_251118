package ia.shumilov.ru.hwfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChildFragment2 extends Fragment {

    public ChildFragment2() {
        // Required empty public constructor
    }

    private TextView textViewChildFragment;
    private static final String KEY_SAFE_STATE = "key_for_text_in_textView";
    private String text;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewChildFragment = view.findViewById(R.id.childTextViewFragment2);
        textViewChildFragment.setText(text);
    }

    public void setTextViewValueChildFragment(String val){
        textViewChildFragment.setText(val);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        text = String.valueOf(textViewChildFragment.getText());
        outState.putString(KEY_SAFE_STATE, text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            text = "";
        } else {
            text = savedInstanceState.getString(KEY_SAFE_STATE, text);
        }
    }
}
