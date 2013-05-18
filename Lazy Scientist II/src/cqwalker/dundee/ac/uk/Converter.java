package cqwalker.dundee.ac.uk;

import java.io.IOException;
import org.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Converter extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.converter);
	}
	
	public void calculate(View v) throws IOException, JSONException
	{
		int converter = 0;
		boolean error = false;
		String errorText = "";
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer1);
		TextView ratioA = (TextView) this.findViewById(R.id.txtRatioa);
		TextView ratioB = (TextView) this.findViewById(R.id.txtRatiob);
		TextView percent = (TextView) this.findViewById(R.id.txtPercent);
		TextView od = (TextView) this.findViewById(R.id.txtOD);
		if ((ratioA.getText().toString().length() == 0) && 
				(ratioB.getText().toString().length() == 0) && 
				(od.getText().toString().length() == 0) &&
				(percent.getText().toString().length() == 0))
		{
			error = true;
			errorText = "Provide figure(s) first!";
		}
		if ((ratioA.getText().toString().length() == 0) &&
				(percent.getText().toString().length() == 0) &&
				(od.getText().toString().length() == 0))
		{
			error = true;
			errorText = "Provide figure(s) first!";
		}
		if ((ratioA.getText().toString().length() != 0) && (percent.getText().toString().length() != 0))
		{
			percent.setText("");
		}
		try
		{
			if (ratioA.getText().toString().length() != 0)
			{
				@SuppressWarnings("unused")
				float test = Float.parseFloat(ratioA.getText().toString());
			}
			if (ratioB.getText().toString().length() != 0)
			{
				@SuppressWarnings("unused")
				float test = Float.parseFloat(ratioB.getText().toString());
			}
			if (percent.getText().toString().length() != 0)
			{
				float test = Float.parseFloat(percent.getText().toString());
				if ((test < 0) || (test > 100))
				{
					error = true;
					errorText = "Percentage 0-100 Please";				
				}
			}
			if (od.getText().toString().length() != 0)
			{
				@SuppressWarnings("unused")
				float test = Float.parseFloat(od.getText().toString());
			}
			
		}
		catch (Exception ex)
		{
			error = true;
			errorText = "Numerals Only Please";
		}
		if (error)
		{
			answer.setText(errorText);
		}
		else
		{
			// decide which calculation to perform
			if (ratioA.getText().toString().length() != 0)
			{
				converter = 1;
			}
			if (percent.getText().toString().length() != 0)
			{
				converter = 2;
			}
			if (od.getText().toString().length() != 0)
			{
				converter = 3;
			}
			switch (converter)
			{
			case 1:
				rtop();
				break;
			case 2:
				ptor();
				break;
			case 3:
				runOD();
				break;
			}
		}
	}
	
	private void rtop()
	{
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer1);
		TextView ratioA = (TextView) this.findViewById(R.id.txtRatioa);
		TextView ratioB = (TextView) this.findViewById(R.id.txtRatiob);
		int per = 0;
		float a = Float.parseFloat(ratioA.getText().toString());
		float b = Float.parseFloat(ratioB.getText().toString());
		per = (int) ((100/b) * a);
		answer.setText(ratioA.getText().toString() + ":" + ratioB.getText().toString() + " = " + per + "%");
		ratioA.setText("");
		ratioB.setText("");
	}
	
	private void ptor()
	{
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer1);
		TextView percentage = (TextView) this.findViewById(R.id.txtPercent);
		float per = Float.parseFloat(percentage.getText().toString());
		float perOriginal = Float.parseFloat(percentage.getText().toString());
		int a = (int) per;
		int b = 100;
		boolean stop = false;
		do
		{
			if ((per/2) != (int) (per/2))
			{
				if ((per/5) != (int) (per/5))
				{
					stop = true;
				}
				else
				{
					a = a/5;
					b = b/5;
					per = per/5;
				}
			}
			else
			{
				a = a/2;
				b = b/2;
				per = per/2;
			}
			
		}while (!stop);
		answer.setText(perOriginal + "% = " + a + ":" + b);		
	}
	
	private void runOD()
	{
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer1);
		TextView od = (TextView) this.findViewById(R.id.txtOD);
		double ODvalue = Double.parseDouble(od.getText().toString());
		double cellCount = (0.3031 * Math.pow(10, 7)) * (Math.pow(Math.E, 1.788 * ODvalue));
		String cellShort = String.format("%10.3f", cellCount / Math.pow(10, 7));
		
		String response = "OD660 of " + od.getText() + " equates to\n";
		response += (int)cellCount + " cells\n";
		response += cellShort + " x 10^7";
		answer.setText(response);
		
		
	}
}
