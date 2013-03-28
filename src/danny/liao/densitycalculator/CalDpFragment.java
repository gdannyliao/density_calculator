package danny.liao.densitycalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

/**
 * this fragment can will accept an input number and calculate the result by different model<br/>
 * it support dp to px and px to dp, you can change model by a spinner 
 * @author danny 
 *
 */
public class CalDpFragment extends Fragment{
	private static final String MODEL_DP = "model_dp";
	private static final String MODEL_PX = "model_px";
	//declear the current model 
	private String mCurrentModel = MODEL_DP; 
	
	/**
	 * use this factory method to get a new fragment instance
	 * @return new {@link CalDpFragment};
	 */
	public static CalDpFragment newInstance(){
		CalDpFragment fgm = new CalDpFragment();
		
		return fgm;
	}
	//void constructor
	public CalDpFragment(){};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_caldp, container, false);
		
		//initial all widget of this layout
		final EditText inputEdit = (EditText) layout.findViewById(R.id.editText_fgm_caldp);
		Spinner unitSpinner = (Spinner) layout.findViewById(R.id.spinner_fgm_caldp);
		
		final TextView result1 = (TextView) layout.findViewById(R.id.textView_num1);
		final TextView result2 = (TextView) layout.findViewById(R.id.textView_num2);
		final TextView result3 = (TextView) layout.findViewById(R.id.textView_num3);
		final TextView result4 = (TextView) layout.findViewById(R.id.textView_num4);
		final TextView result5 = (TextView) layout.findViewById(R.id.textView_num5);
		
		final TextView unit1 = (TextView) layout.findViewById(R.id.textView_unit1);
		final TextView unit2 = (TextView) layout.findViewById(R.id.textView_unit2);
		final TextView unit3 = (TextView) layout.findViewById(R.id.textView_unit3);
		final TextView unit4 = (TextView) layout.findViewById(R.id.textView_unit4);
		final TextView unit5 = (TextView) layout.findViewById(R.id.textView_unit5);
		
		final View clearButton = layout.findViewById(R.id.button_fragment_caldp_clear);
		
		final DpCalculator dpCalculator = new DpCalculator();
		final PxCalculator pxCalculator = new PxCalculator();
		
		//set clear button
		clearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				inputEdit.setText("");
			}
		});
		
		//set spinner adapter
		unitSpinner.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView = new TextView(getActivity());
				//FIXME it should adapt different resolutions
				textView.setLayoutParams(new LayoutParams(100, 80));
				textView.setGravity(Gravity.CENTER);
				
				switch(position){
				case 0:
					textView.setText("dp");
					break;
				case 1:
					textView.setText("px");

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
		//when spinner item changed, it will change the units and recalculate 
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Editable oldText = inputEdit.getText();
				switch(position){
				case 0:
					mCurrentModel = MODEL_DP;
					changeUnit(MODEL_DP);
					break;
				case 1:
					mCurrentModel = MODEL_PX;
					changeUnit(MODEL_PX);
					break;
				}
				//recalculate
				inputEdit.setText(oldText);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
			private void changeUnit(String model){
				String unit = null;
				if(model == MODEL_PX){
					unit = "dp";
				} else unit = "px";
				
				unit1.setText(unit);
				unit2.setText(unit);
				unit3.setText(unit);
				unit4.setText(unit);
				unit5.setText(unit);
			}
		});
		//when the text change , it will calculate and show the results depends by different model
		inputEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				double num = 0;
				
				if(s.length() > 0){
					 num = Double.valueOf(s.toString());
				}
				
				Double[] results = null;
				if(mCurrentModel == MODEL_DP){
					results = dpCalculator.calculate(num);
				} else {
					results = pxCalculator.calculator(num);
				}
				
				if(results.length > 1){
					result1.setText(String.valueOf(results[0]));
					result2.setText(String.valueOf(results[1]));
					result3.setText(String.valueOf(results[2]));
					result4.setText(String.valueOf(results[3]));
					result5.setText(String.valueOf(results[4]));
				} else {
					String nullRes = "0";
					result1.setText(nullRes);
					result2.setText(nullRes);
					result3.setText(nullRes);
					result4.setText(nullRes);
					result5.setText(nullRes);

				}
			}
		});
		return layout;
	}
	
}
