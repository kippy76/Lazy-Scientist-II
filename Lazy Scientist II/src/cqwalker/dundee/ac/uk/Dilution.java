package cqwalker.dundee.ac.uk;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dilution extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dilution);
	}

	public void calculate(View v) throws IOException, JSONException
	{
		switch (getOption())
		{
		case 1:
			calculateSimple();
			break;
		case 2:
			calculateSerial();
			break;
		}
	}

	private int getOption()
	{
		int option = 0;
		// Simple values
		TextView ov = (TextView) this.findViewById(R.id.txtVolume);
		TextView wp = (TextView) this.findViewById(R.id.txtTargetStrength);
		// Serial values
		TextView df = (TextView) this.findViewById(R.id.txtDilutionFactor);
		TextView sv = (TextView) this.findViewById(R.id.txtSourceVolume);
		// Find out if simple/serial selected
		if ((ov.getText().toString().length() != 0) && (wp.getText().toString().length() != 0))
		{
			option = 1;
		}
		if ((df.getText().toString().length() != 0) && (sv.getText().toString().length() != 0))
		{
			option = 2;
		}
		return option;
	}

	private void calculateSimple()
	{
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer);
		TextView origVol = (TextView) this.findViewById(R.id.txtVolume);
		TextView wantedPercent = (TextView) this.findViewById(R.id.txtTargetStrength);
		float solvent = 0;
		float solute = 0;
		try
		{
			float OV = Float.parseFloat(origVol.getText().toString());
			float WP = Float.parseFloat(wantedPercent.getText().toString());
			solvent = (OV / 100) * WP;
			solute = OV - solvent;
		}
		catch (Exception e)
		{
			answer.setText("Error");
			return;
		}
		answer.setText((int) solvent + "ml solvent\nadded to\n" + (int) solute + "ml solute");
	}

	private void calculateSerial()
	{
		TextView answer = (TextView) this.findViewById(R.id.txtAnswer);
		TextView df = (TextView) this.findViewById(R.id.txtDilutionFactor);
		TextView sv = (TextView) this.findViewById(R.id.txtSourceVolume);
		answer.setText("");
		int stage = 0;
		int diluteFactor = 0;
		float origVolume = 0;
		String volText = "";
		try
		{
			origVolume = Float.parseFloat(sv.getText().toString());
			diluteFactor = Integer.parseInt(df.getText().toString());
			do
			{
				stage++;
				volText += "(" + String.valueOf(stage) + ") " + (int) origVolume + "uL ";
				if (stage == 1)
				{
					if (diluteFactor > 1)
					{
						volText += ">> " + Integer.valueOf((int) origVolume * 99) + "uL = 10-2";
					}
					else
					{
						volText += ">> " + Integer.valueOf((int) origVolume * 9) + "uL = 10-1";
					}
				}
				else
				{
					if (diluteFactor > 1)
					{
						volText += "of (" + (stage - 1) + ") >> " + Integer.valueOf((int)origVolume * 99) + "uL";
					}
					else
					{
						volText += "of (" + (stage - 1) + ") >> " + Integer.valueOf((int)origVolume * 9) + "uL";
					}
					volText += " = 10-";
					if (diluteFactor > 1)
					{
						volText += (2 * stage);
					}
					else
					{
						volText += ((2 * stage) - 1);
					}
				}
				volText += "\n";
				diluteFactor -= diluteFactor >= 2 ? 2 : 1;
			} while (diluteFactor > 0);
			answer.setText(volText);
		}
		catch (Exception e)
		{
			answer.setText("Error");
			return;
		}

	}

}