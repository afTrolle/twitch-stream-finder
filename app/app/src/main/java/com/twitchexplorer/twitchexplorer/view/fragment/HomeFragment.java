package com.twitchexplorer.twitchexplorer.view.fragment;

import android.widget.ArrayAdapter;

import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;


public class HomeFragment extends BaseFragment {
    @Override
    int getViewLayout() {
        return R.layout.search_view;
    }

//    @BindView(R.id.spinner)
//    MaterialBetterSpinner materialSpinner;

//    @BindView(R.id.search_stream_stream_max_time)
//    MaterialEditText materialEditText;

    @Override
    public void onStart() {
        super.onStart();
        String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  //      materialSpinner.setAdapter(adapter);
/*
        materialEditText.setInputType(InputType.TYPE_NULL);
        materialEditText.setRawInputType(InputType.TYPE_NULL);
        materialEditText.setCursorVisible(false);
        materialEditText.setShowClearButton(true);
        materialEditText.setShowSoftInputOnFocus(false);
        final TextWatcher textWatcher = new TextWatcher() {

            private String previous;
            private boolean appendOld;
            private boolean clear;
            private boolean ignore;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!appendOld)
                    previous = new String(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appendOld = !previous.equals(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (clear) {
                    clear = false;
                    s.append(previous);
                    return;
                }
                if (appendOld) {
                    clear = true;
                    s.clear();
                }
            }
        };
        materialEditText.addTextChangedListener(textWatcher);
        materialEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        materialEditText.removeTextChangedListener(textWatcher);
                        materialEditText.setText(selectedHour + ":" + selectedMinute);
                        materialEditText.addTextChangedListener(textWatcher);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        */
    }

    /*
    @OnClick(R.id.search_stream_stream_max_time)
    public void buttonclick(final MaterialEditText materialEditText){
        //TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                materialEditText.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
*/
    @Override
    void initDagger(FragmentComponent component) {
        component.inject(this);
    }
}
