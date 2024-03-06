package com.example.stepcounter;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MeasuresFragment extends Fragment {
    TextView res;
    android.widget.Button resBtn;
    TextView mCurrentHeight, mCurrentAge, mCurrentWeight;
    ImageView mIncrementWeight, mDecrementWeight, mIncrementAge, mDecrementAge;
    SeekBar mSeekBarHeight;
    RelativeLayout mMale, mFemale;
    int intWeight=65;
    int intAge=22;
    int currentProgress;
    String mIntProgress="170";
    String typeOfUser="0";
    String weight="65";
    String age="22";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_measures, container, false);

        res = rootView.findViewById(R.id.resultBMI);
        resBtn = rootView.findViewById(R.id.calculateBMI);
        mCurrentAge = rootView.findViewById(R.id.currentAge);
        mCurrentWeight = rootView.findViewById(R.id.currentWeight);
        mCurrentHeight = rootView.findViewById(R.id.currentHeight);
        mIncrementAge = rootView.findViewById(R.id.incrementAge);
        mDecrementAge = rootView.findViewById(R.id.decrementAge);
        mIncrementWeight = rootView.findViewById(R.id.incrementWeight);
        mDecrementWeight = rootView.findViewById(R.id.decrementWeight);
        mSeekBarHeight = rootView.findViewById(R.id.seekBarHeight);
        mMale = rootView.findViewById(R.id.male);
        mFemale = rootView.findViewById(R.id.female);

        mMale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mMale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mFemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                typeOfUser="Male";
            }
        });

        mFemale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mFemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mMale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                typeOfUser="Female";
            }
        });

        mSeekBarHeight.setMax(300);
        mSeekBarHeight.setProgress(170);
        mSeekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress=progress;
                mIntProgress=String.valueOf(currentProgress);
                mCurrentHeight.setText(mIntProgress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mIncrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intAge=intAge+1;
                age=String.valueOf(intAge);
                mCurrentAge.setText(age);
            }
        });

        mDecrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intAge=intAge-1;
                age=String.valueOf(intAge);
                mCurrentAge.setText(age);
            }
        });

        mIncrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight=intWeight+1;
                weight=String.valueOf(intWeight);
                mCurrentWeight.setText(weight);
            }
        });

        mDecrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight=intWeight-1;
                weight=String.valueOf(intWeight);
                mCurrentWeight.setText(weight);
            }
        });

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeOfUser.equals("0")){
                    Toast.makeText(getContext(),"Select Your Gender First !",Toast.LENGTH_SHORT).show();
                }
                else if (mIntProgress.equals("0")){
                    Toast.makeText(getContext(),"Select Your Height First !",Toast.LENGTH_SHORT).show();
                }
                else if (intAge==0 || intAge<0){
                    Toast.makeText(getContext(),"Age Is Incorrect !",Toast.LENGTH_SHORT).show();
                } else if (intWeight==0 || intWeight<0) {
                    Toast.makeText(getContext(),"Weight Is Incorrect !",Toast.LENGTH_SHORT).show();
                }
                else {
                    double heightInMeters = currentProgress / 100.0;
                    double bmi = intWeight / (heightInMeters * heightInMeters);
                    String result = String.format("Your BMI Is : %.2f", bmi);
                    res.setText(result);
                    String condition = getCondition(bmi);
                    res.append("\n"+condition);
                }
            }
            private String getCondition(double bmi) {
                if (bmi < 18.5) {
                    return "Underweight";
                } else if (bmi >= 18.5 && bmi < 24.9) {
                    return "Normal Weight";
                } else if (bmi >= 25 && bmi < 29.9) {
                    return "Overweight";
                } else if (bmi >= 30 && bmi < 34.9) {
                    return "Obese Class I";
                } else if (bmi >= 35 && bmi < 39.9) {
                    return "Obese Class II";
                } else {
                    return "Obese Class III";
                }
            }
        });
        return rootView;
    }
}