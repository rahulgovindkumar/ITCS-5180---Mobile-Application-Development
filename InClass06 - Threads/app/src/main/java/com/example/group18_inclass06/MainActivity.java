/*InClass06
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.group18_inclass06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.example.group18_inclass06.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService threadPool;
    Handler handler;
    int complexity = 5, currentProgress = 1, selectedComplexity = 0;
    String TAG = "demo";
    ActivityMainBinding binding;
    ArrayList<Double> outputNumbers = new ArrayList<Double>();
    ArrayAdapter<Double> adapter;
    Double outputSum = 0.0000000000;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.constraintLayoutOutput.setVisibility(View.GONE);
        binding.seekBar.setProgress(0);
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.textViewSelectedComplexity.setText(i + " Times");
                selectedComplexity = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, outputNumbers);
        binding.listViewReturnedNumbers.setAdapter(adapter);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                currentProgress++;
                outputNumbers.add((double) message.obj);
                adapter.notifyDataSetChanged();
                outputSum += (double) message.obj;
                binding.textViewAverage.setText("Average: " + (outputSum / currentProgress));
//                Log.d(TAG, "handleMessage: " + (double) message.obj + " Cprogress: " + currentProgress + " Sum: " + outputSum);
                binding.textViewProgressStatus.setText(currentProgress+"/"+complexity);
                binding.progressBarIterations.setProgress(currentProgress);
                if(complexity > currentProgress + 1) {
                    threadPool.execute(new generateRandom());
                } else  {
                    isRunning = false;
                }
                return false;
            }
        });

        binding.buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    startThreads();
                }
            }
        });

    }

    private void startThreads() {
        if (selectedComplexity > 0) {
            isRunning = true;
            complexity = selectedComplexity;
            binding.textViewProgressStatus.setText("0/" + complexity);
            binding.textViewAverage.setText("Average: 0");
            binding.progressBarIterations.setMax(complexity);
            binding.progressBarIterations.setProgress(0);
            threadPool = Executors.newFixedThreadPool(2);
            outputNumbers.clear();
            adapter.notifyDataSetChanged();
            outputSum = 0.0000000000;
            currentProgress = 0;
            threadPool.execute(new generateRandom());
            threadPool.execute(new generateRandom());
            binding.constraintLayoutOutput.setVisibility(View.VISIBLE);
        }
    }

    class generateRandom implements Runnable {
        @Override
        public void run() {
            double returnNumber = HeavyWork.getNumber();
            Message numberMessage = new Message();
            numberMessage.obj = (double) returnNumber;
            handler.sendMessage(numberMessage);
        }
    }
}