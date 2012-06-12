package com.androideity.rotaciondemo;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.Button;
import android.net.Uri;
import android.view.View;
import android.content.Intent;

public class RotacionDemoActivity extends Activity {
	
	static final int PICK_REQUEST=1337; 
	Button viewButton=null; 
	Uri contact=null; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn=(Button)findViewById(R.id.seleccionar); 
        
        btn.setOnClickListener(new View.OnClickListener() { 
          public void onClick(View view) { 
            Intent i=new Intent(Intent.ACTION_PICK, 
                               Contacts.CONTENT_URI); 
     
            startActivityForResult(i, PICK_REQUEST); 
          } 
        });
        
        viewButton=(Button)findViewById(R.id.ver); 
        
        viewButton.setOnClickListener(new View.OnClickListener() { 
          public void onClick(View view) { 
            startActivity(new Intent(Intent.ACTION_VIEW, contact)); 
          } 
        }); 
         
        restoreMe(savedInstanceState); 
         
        viewButton.setEnabled(contact!= null);  
    }//Fin de onCreate()
      
    
    @Override 
    protected void onActivityResult(int requestCode, 
    		int resultCode, Intent data) { 
    	if (requestCode==PICK_REQUEST) { 
    		if (resultCode==RESULT_OK) { 
    			contact=data.getData(); 
    			viewButton.setEnabled(true); 
            } 
        } 
    }//Fin de onActivityResult() 
       
    @Override
    protected void onSaveInstanceState(Bundle outState) { 
    	super.onSaveInstanceState(outState); 
        if (contact!=null) { 
        	outState.putString("contact", contact.toString()); 
        } 
    }//Fin de onSaveInstanceState()
        
    private void restoreMe(Bundle state){ 
    	contact=null; 
             
    	if (state!=null) { 
    		String contactUri=state.getString("contact"); 
               
    		if (contactUri!=null) { 
    			contact=Uri.parse(contactUri); 
            } 
    	} 
     }//Fin de restoreMe()
    
    
}