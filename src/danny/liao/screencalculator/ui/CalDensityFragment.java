package danny.liao.screencalculator.ui;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import danny.liao.densitycalculator.R;
import danny.liao.screencalculator.util.DensityCalculator;
import danny.liao.screencalculator.util.DimensionCalculator;

public class CalDensityFragment extends Fragment {
	private static final int MODEL_CAL_DENSITY = 0;
	private static final int MODEL_CAL_DIMENSION = 1;
	private int mCurrentModel = MODEL_CAL_DENSITY;
	
	public static CalDensityFragment newInstance(){
		return new CalDensityFragment();
	}
	
	public CalDensityFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_cal_density, null);
		//initial widgets
		final EditText lengthInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_length);
		final EditText widthInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_width);
		final EditText dimensionInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_inch);
		
		final TextView resultText = (TextView) layout.findViewById(R.id.textView_fragment_caldensity_result);
		final TextView resultUnit = (TextView) layout.findViewById(R.id.textView_fragment_caldensity_result_unit);
		
		final Spinner unitSpinner = (Spinner) layout.findViewById(R.id.spinner_fragment_caldensity_unit);
		
		final DensityCalculator densityCalculator = new DensityCalculator();
		final DimensionCalculator dimensionCalculator = new DimensionCalculator();
		
		final View calculateButton = layout.findViewById(R.id.button_fragment_caldensity_calculate);
		
		//set unit spinner adapter
		unitSpinner.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView = new TextView(getActivity());
				//FIXME it should adapt different resolutions
				textView.setLayoutParams(new LayoutParams(100, 80));
				textView.setGravity(Gravity.CENTER);
				
				switch(position){
				case 0:
					textView.setText("inch");
					break;
				case 1:
					textView.setText("dpi");
					break;
				}
				
				return textView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public int getCount() {
				return 2;
			}
		});
		//when spinner item changed, switch the model and recalculate
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch(position){
				case MODEL_CAL_DENSITY:
					mCurrentModel = MODEL_CAL_DENSITY;
					resultUnit.setText("dpi");
					calculateButton.performClick();
					break;
				case MODEL_CAL_DIMENSION:
					mCurrentModel = MODEL_CAL_DIMENSION;
					resultUnit.setText("inch");
					calculateButton.performClick();
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		//clear button
		layout.findViewById(R.id.button_fragment_caldensity_clear).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				lengthInput.setText(null);
				widthInput.setText(null);
				dimensionInput.setText(null);
				resultText.setText("0");
			}
		});
		//calculator 
		calculateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lengthInput.getText().length() == 0 || widthInput.getText().length() == 0 
						|| dimensionInput.getText().length() == 0 || lengthInput.getText().toString().equals(".")
						|| widthInput.getText().toString().equals(".") || dimensionInput.getText().toString().equals("."))
				{
					//TODO let the error hint more comfortable
					Toast.makeText(getActivity(), getResources().getString(R.string.arg_hint), Toast.LENGTH_SHORT).show();
					return;
				}
				
				double length = Double.parseDouble(lengthInput.getText().toString());
				double width = Double.parseDouble(widthInput.getText().toString());
				double dimension = Double.parseDouble(dimensionInput.getText().toString());
				// use this to show the number with format
				DecimalFormat df = new DecimalFormat("##0.000");
				
				//use different calculator to calculate the result and show it
				switch(mCurrentModel){
				case MODEL_CAL_DENSITY:
					double result = densityCalculator.calculator(length, width, dimension);
					if(result > 0){
						resultText.setText(String.valueOf(df.format(result)));
					} else resultText.setText("0");
					break;
				case MODEL_CAL_DIMENSION:
					double result1 = dimensionCalculator.calculator(length, width, dimension);
					if(result1 > 0){
						resultText.setText(String.valueOf(df.format(result1)));
					} else resultText.setText("0");
					break;
				}
			}
		});
		return layout;
	}
	
}
