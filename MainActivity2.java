package com.example.jtspr.morsecodemessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Vibrator;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity
{
    public void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    public void dash()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        sleep(500);

    }
    public void dot()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);
        sleep(500);

    }

    String inputMessage, outputMessage, lowerMessage;
    char current;

    EditText messageInput;
    //TextView outputTextView;
    Button translateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = (EditText) findViewById(R.id.messageEditText);
        //outputTextView = (TextView) findViewById(R.id.outputTextView);
        translateButton = (Button) findViewById(R.id.translateButton);

        translateButton.setOnClickListener(new View.OnClickListener()
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            @Override
            public void onClick(View view)
            {
                inputMessage = messageInput.getText().toString();

                for(int count = 0; count < inputMessage.length(); ++count)
                {
                    lowerMessage = inputMessage.toLowerCase();
                    current = lowerMessage.charAt(count);


                    switch(current)
                    {
                        case 'a':
                            dot();
                            dash();
                            sleep(500);
                            break;
                        case 'b':
                           dash();
                           dot();
                           dot();
                           dot();
                           sleep(500);
                           break;
                        case 'c':
                            dash();
                            dot();
                            dash();
                            dot();
                            sleep(500);
                            break;
                        case 'd':
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'e':
                            dot();
                            sleep(1000);
                            break;
                        case 'f':
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'g':
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'h':
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'i':
                            dot();
                            sleep(500);
                            dot();;
                            sleep(1000);
                            break;
                        case 'j':
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'k':
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'l':
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'm':
                            dash();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'n':
                            dash();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'o':
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'p':
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 'q':
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'r':
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 's':
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case 't':
                            dash();
                            sleep(1000);
                            break;
                        case 'u':
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'v':
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'w':
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'x':
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'y':
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dash();
                            sleep(500);
                            dash();
                            sleep(1000);
                            break;
                        case 'z':
                            dash();
                            sleep(500);
                            dash();
                            sleep(500);
                            dot();
                            sleep(500);
                            dot();
                            sleep(1000);
                            break;
                        case ' ':
                            sleep(2500);
                            break;
                    }

                }



            }
        });

    }
}
