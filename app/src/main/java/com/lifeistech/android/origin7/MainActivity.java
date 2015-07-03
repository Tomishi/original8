package com.lifeistech.android.origin7;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edit = (EditText)findViewById(R.id.editText);
        //���Ԃ�TimePicker��p���ē��͂���

        //�J�����_�[�^�ϐ��̐錾
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //text�̒�`
        final TextView text;
        text = (TextView) findViewById(R.id.textView);
        //TimePicker
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("test", String.format(" % 02d:%02d", hourOfDay, minute));
                        text.setText(String.format(" % 02d:%02d", hourOfDay, minute));
                        set(hourOfDay, minute) ;
                    }

                    //set�̓��e
                    //��œ��͂��������Ŕ��s����Intent���쐬

                    private void set( int hourOfDay, int minute) {

                        Intent intent = new Intent(getApplicationContext(), Receiver.class);

                        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                        //AlarmManager���擾
                        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                        //Calendar�̎擾
                        Calendar calendar = Calendar.getInstance();
                        //�����̐ݒ�
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        //AlarmManager���Z�b�g
                        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                    }
                },

                hour, minute, true);
        dialog.show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
