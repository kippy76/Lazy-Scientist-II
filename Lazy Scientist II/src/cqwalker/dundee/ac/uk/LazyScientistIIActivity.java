package cqwalker.dundee.ac.uk;

import android.os.Bundle;
import android.widget.TabHost;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class LazyScientistIIActivity extends TabActivity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Resources res = getResources();
	    TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;
	    Intent intent;
	    // About
	    intent = new Intent().setClass(this, About.class);
	    spec = tabHost.newTabSpec("About").setIndicator("About",
	                      res.getDrawable(R.drawable.about))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    // Converter
	    intent = new Intent().setClass(this, Converter.class);
	    spec = tabHost.newTabSpec("Conversions").setIndicator("Conversions",
	                      res.getDrawable(R.drawable.conversion))
	                  .setContent(intent);
	    tabHost.addTab(spec);	
	    // Dilution
	    intent = new Intent().setClass(this, Dilution.class);
	    spec = tabHost.newTabSpec("Dilution").setIndicator("Dilution",
	                      res.getDrawable(R.drawable.dilution))
	                  .setContent(intent);
	    tabHost.addTab(spec);	   	    
	    // Background image
	    Drawable back = res.getDrawable(R.drawable.bg1);
	    tabHost.getChildAt(0).setBackgroundDrawable(back);
	    // Select startup tab	 
	    
	    tabHost.setCurrentTab(0);
	    
	}
}