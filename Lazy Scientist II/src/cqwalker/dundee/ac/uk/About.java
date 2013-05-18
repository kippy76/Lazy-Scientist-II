package cqwalker.dundee.ac.uk;

import java.io.IOException;

import org.json.JSONException;


import android.app.Activity;
import android.os.Bundle;

public class About extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		update();
	}

	public void update()
	{
	}

	public void onClick() throws IOException, JSONException
	{
	}

}