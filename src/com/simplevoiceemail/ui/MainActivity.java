package com.simplevoiceemail.ui;

import java.util.ArrayList;

import com.simplevoiceemail.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SpeechRecognizer mSpeechRecognizer;
	private Intent mSpeechRecognizerIntent;
	private ImageButton recordButton;
	private TextView result;
	private boolean isListening;
	private final int REQ_CODE_SPEECH_INPUT = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
		mSpeechRecognizerIntent = new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		mSpeechRecognizerIntent.putExtra(
				RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());

		SpeechRecognitionListener listener = new SpeechRecognitionListener();
		mSpeechRecognizer.setRecognitionListener(listener);

		recordButton = (ImageButton) findViewById(R.id.btnSpeak);
		recordButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isListening = !isListening;

				if (!isListening)
					mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
				else
					mSpeechRecognizer.stopListening();
			}
		});

		result = (TextView) findViewById(R.id.txtSpeechInput);
	}

	protected class SpeechRecognitionListener implements RecognitionListener {

		@Override
		public void onBeginningOfSpeech() {
			// Log.d(TAG, "onBeginingOfSpeech");
		}

		@Override
		public void onBufferReceived(byte[] buffer) {

		}

		@Override
		public void onEndOfSpeech() {
			// Log.d(TAG, "onEndOfSpeech");
		}

		@Override
		public void onError(int error) {
			mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

			// Log.d(TAG, "error = " + error);
		}

		@Override
		public void onEvent(int eventType, Bundle params) {

		}

		@Override
		public void onPartialResults(Bundle partialResults) {

		}

		@Override
		public void onReadyForSpeech(Bundle params) {
		}

		@Override
		public void onResults(Bundle results) {
			//Log.d(TAG, "onResults"); //$NON-NLS-1$
			ArrayList<String> matches = results
					.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
			// matches are the return values of speech recognition engine
			// Use these values for whatever you wish to do
			result.setText(matches.get(0));
		}

		@Override
		public void onRmsChanged(float rmsdB) {
		}
	}

}
